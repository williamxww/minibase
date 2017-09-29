/**
 * 
 */
package com.bow.tests;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.bow.bitmap.BitMapFile;
import com.bow.bitmap.InstantiateColumnarFile;
import com.bow.bitmap.KeyClass;
import com.bow.btree.BT;
import com.bow.btree.BTFileScan;
import com.bow.btree.BTreeFile;
import com.bow.btree.IntegerKey;
import com.bow.btree.KeyDataEntry;
import com.bow.btree.StringKey;

import com.bow.global.AttrType;
import com.bow.global.Convert;
import com.bow.global.RID;
import com.bow.global.Size;
import com.bow.global.SystemDefs;
import com.bow.global.TID;
import com.bow.heap.HFBufMgrException;
import com.bow.heap.HFDiskMgrException;
import com.bow.heap.HFException;
import com.bow.heap.Heapfile;
import com.bow.heap.InvalidSlotNumberException;
import com.bow.heap.Scan;
import com.bow.heap.Tuple;
import com.bow.columnar.ColumnarFileInfo;
import com.bow.columnar.Columnarfile;
import com.bow.columnar.TupleScan;
import com.bow.diskmgr.pcounter;

/**
 * @author gaurav
 *
 */
public class Query {

	private static BitMapFile bmf = null;	
	private static BTreeFile btf=null; 
	private static String columnDbName;
	private static String columnarFileName;
	private static ArrayList <String> columnNames=new ArrayList<String>();
	private static String accessType;
	private static String valueConstraint;
	private static String numBuf;
	private static String columnName;
	private static String operator;
	private static String columnValue;
	private static KeyClass value=null;
	private static int columnIndex=0;
	private static int columnAbsoluteIndex=0;
	private static Columnarfile f = null;
	private static AttrType[] types;
	private static int startRead = 0, startWrite = 0;
	private static String []colNames;
	/**
	 * @param args
	 */
	
	public boolean isInteger( String input )  
	 {  
	    try  
	    {  
	       Integer.parseInt( input );  
	       return true;  
	    }  
	    catch( Exception e)  
	    {  
	       return false;  
	    }  
	} 
	
	public static void parseValue(String tempValue)
	{
		StringTokenizer st = new StringTokenizer(tempValue, ":");
		System.out.println("0..."+tempValue);
		columnName=st.nextToken();
		System.out.println("1..."+columnName);
		for(String str: colNames)
		{
			if(str.equalsIgnoreCase(columnName))
				break;
			columnIndex++;
		}
		operator=st.nextToken();
		System.out.println("1..."+operator);
		columnValue=st.nextToken();
		System.out.println("1..."+columnValue);
		try  
	    {  
	       Integer.parseInt( columnValue );  
	       value=InstantiateColumnarFile.setKeyClass(value, 0, columnValue);
	    }  
	    catch( Exception e)  
	    {  
	    	value=InstantiateColumnarFile.setKeyClass(value, 1, columnValue);
	    } 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		types = new AttrType[4];
		types[0] = new AttrType(AttrType.attrString);
		types[1] = new AttrType(AttrType.attrString);
		types[2] = new AttrType(AttrType.attrInteger);
		types[3] = new AttrType(AttrType.attrInteger);
		startRead = pcounter.rcounter;
		startWrite = pcounter.wcounter;
		columnDbName=args[1];
		columnarFileName=args[2];
		
		accessType=args[args.length-1];
		numBuf=args[args.length-2];
		valueConstraint=args[args.length-3];
		for(int i=3;i<args.length-3;i++)
			columnNames.add(args[i]);


		SystemDefs sysdef = new SystemDefs(columnDbName,100000,Integer.parseInt(numBuf),"Clock");
		ColumnarFileInfo cInfo = new  ColumnarFileInfo(columnarFileName+".hdr");
		f=new Columnarfile(columnarFileName, 4, types);
		cInfo = f.getColumnarFileInfo(columnarFileName+".hdr");
		colNames = cInfo.getColumnNames();
		f.setColumnNames(colNames);
		Query.parseValue(valueConstraint);
		System.out.println ("\n  Begin Index Test: \n");
		final boolean OK = true;
		final boolean FAIL = false;
		int choice=10000;
		final int reclen = 128;

		boolean status = OK;
		TID tid = new TID();
		for(String str: colNames)
		{
			if(str.equalsIgnoreCase(columnName))
				break;
			columnAbsoluteIndex++;
		}
		Heapfile reqHFile=f.getHeapfileForColumname(columnName);
		
		int keyType=0, lowkeyInt=0, hikeyInt=0;
		IntegerKey lowkey=new IntegerKey(0);
		IntegerKey hikey=new IntegerKey(0);
		BTFileScan scan=null;
		KeyDataEntry entry;
		RID rid=new RID();
		
		Scan hScan;
		try {
			hScan = reqHFile.openScan();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			hScan=null;
		}
		
		if(accessType.equalsIgnoreCase("Btree"))
		{
			try 
			{
				Tuple hTuple=null;
				if(value instanceof com.bow.bitmap.IntegerKey)
				btf = new BTreeFile("com/bow/btree" +columnName, AttrType.attrInteger, 4, 1/*delete*/);

				BT.printBTree(btf.getHeaderPage());
				System.out.println("printing leaf pages...");

				  
				keyType=AttrType.attrInteger;
				System.out.println("Please input the LOWER integer key (null if -3): ");
				  lowkeyInt=GetStuff.getChoice(); 
				  lowkey=new IntegerKey(lowkeyInt);
				  System.out.println("Please input the HIGHER integer key (null if -2): ");
				  hikeyInt=GetStuff.getChoice(); 
				  hikey=new IntegerKey(hikeyInt);
				  if( lowkeyInt==-3)     
				    lowkey=null;
				  if( hikeyInt==-2)     
				    hikey=null;
				  if( hikeyInt!=-1 || lowkeyInt!=-1 ) 
				    scan= btf.new_scan(lowkey, hikey);
				  while((entry=scan.get_next())!=null)
				  {
					  if(entry!=null)
					  {
						  char sw=operator.charAt(0);
						  boolean eval=false;
						  switch(sw)
						  {
						  case '>':
							  if(entry.key instanceof IntegerKey)
							  {
								  if(((IntegerKey)entry.key).getKey()>((com.bow.bitmap.IntegerKey)value).getKey())
									  eval=true;
								  else
									  eval=false;
							  }
							  else if(entry.key instanceof StringKey)
							  {
								  if(((StringKey)entry.key).getKey().compareTo(((com.bow.bitmap.StringKey)value).getKey())>0)
									  eval=true;
								  else
									  eval=false;
							  }
							  break;
						  case '<':
							  if(entry.key instanceof IntegerKey)
							  {
								  if(((IntegerKey)entry.key).getKey()<((com.bow.bitmap.IntegerKey)value).getKey())
									  eval=true;
								  else
									  eval=false;
							  }
							  else if(entry.key instanceof StringKey)
							  {
								  if(((StringKey)entry.key).getKey().compareTo(((com.bow.bitmap.StringKey)value).getKey())>0)
									  eval=true;
								  else
									  eval=false;
							  }
							  break;
						  case '!':
							  if(entry.key instanceof IntegerKey)
							  {
								  if(((IntegerKey)entry.key).getKey()>((com.bow.bitmap.IntegerKey)value).getKey())
									  eval=true;
								  else
									  eval=false;
							  }
							  else if(entry.key instanceof StringKey)
							  {
								  if(((StringKey)entry.key).getKey().compareTo(((com.bow.bitmap.StringKey)value).getKey())!=0)
									  eval=true;
								  else
									  eval=false;
							  }
							  break;
						  case '=':
							  if(entry.key instanceof IntegerKey)
							  {
								  if(((com.bow.btree.IntegerKey)entry.key).getKey().equals(((com.bow.bitmap.IntegerKey)value).getKey()))
									  eval=true;
								  else
									  eval=false;
							  }
							  else if(entry.key instanceof StringKey)
							  {
								  if(((StringKey)entry.key).getKey().compareTo(((com.bow.bitmap.StringKey)value).getKey())==0)
									  eval=true;
								  else
									  eval=false;
							  }
							  break;

						  }
						  if(eval)
						  {
							  RID tempRid=new RID();
							  System.out.println("SCAN RESULT: "+ entry.key + " " + entry.data);
							  tempRid=((com.bow.btree.LeafData)entry.data).getData();
							  if(value instanceof com.bow.bitmap.IntegerKey)
							  {
								  int position=reqHFile.RidToPos(tempRid);
								  Query.getProjection(position);
							  }
							  eval=false;
						  }
					  }
				  }
				  System.out.println("AT THE END OF SCAN!");
				  System.out.println("Disk Reads"+ (pcounter.rcounter - startRead));
				  System.out.println("Disk Writes"+ (pcounter.wcounter - startWrite));
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if(accessType.equalsIgnoreCase("Bitmap"))
		{
			try 
			{
				String bmfName="bitmap."+columnName+columnValue;
				bmf=InstantiateColumnarFile.getColumnarFile(bmf, f, value, columnIndex,bmfName, reqHFile);				
				
				bmf.printBitMapFile();
				System.out.println("created Bitmap index..."+bmf.getRecCnt());
				bmf.getTuplesBitMap(reqHFile, types);
				
				System.out.println("Disk Reads"+ (pcounter.rcounter - startRead));
				System.out.println("Disk Writes"+ (pcounter.wcounter - startWrite));
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

		if(accessType.equalsIgnoreCase("Columnscan"))
		{
			int position = 0;
			try 
			{
				Tuple t; 
				if (value instanceof com.bow.bitmap.IntegerKey)
				{
					while ((t = hScan.getNext(rid))!=null)
					{
						position++;
						 char sw=operator.charAt(0);
						  switch(sw)
						  {
						  case '>':
							  if (((com.bow.bitmap.IntegerKey)value).getKey().compareTo((Convert.getIntValue(0,t.getData())))<0)
								{
									getProjection(position);
																		
								}
							  
							  break;
						  case '<':
							  if (((com.bow.bitmap.IntegerKey)value).getKey().compareTo((Convert.getIntValue(0,t.getData())))>0)
								{
									getProjection(position);
																		
								}
							  
							  break;
						  case '!':
							  
							  if (!((com.bow.bitmap.IntegerKey)value).getKey().equals(Convert.getIntValue(0,t.getData())))
								{
									getProjection(position);
									
									
								}
							 
							  break;
						  case '=':

								if (((com.bow.bitmap.IntegerKey)value).getKey().equals(Convert.getIntValue(0,t.getData())))
								{
									getProjection(position);
																	
								}
							  break;

						  }
					}
				}
				else if (value instanceof com.bow.bitmap.StringKey)
				{
					while ((t = hScan.getNext(rid))!=null)
					{
						position++;
						 char sw=operator.charAt(0);
						  switch(sw)
						  {
						  case '>':
							  if (((com.bow.bitmap.StringKey)value).getKey().compareTo((Convert.getStrValue(0,t.getData(),Size.STRINGSIZE)))<0)
								{
									getProjection(position);
								}
							  
							  break;
						  case '<':
							  if (((com.bow.bitmap.StringKey)value).getKey().compareTo((Convert.getStrValue(0,t.getData(),Size.STRINGSIZE)))>0)
								{
									getProjection(position);
																		
								}
							  
							  break;
						  case '!':
							  
							  if (!(((com.bow.bitmap.StringKey)value).getKey().compareTo((Convert.getStrValue(0,t.getData(),Size.STRINGSIZE)))==0))
								{
									getProjection(position);
																		
								}
							 
							  break;
						  case '=':

							  if (((com.bow.bitmap.StringKey)value).getKey().compareTo((Convert.getStrValue(0,t.getData(),Size.STRINGSIZE)))==0)
								{
									getProjection(position);
									
								}
							  break;

						  }
					}
				}
				hScan.closescan();
				System.out.println("Disk Reads"+ (pcounter.rcounter - startRead));
				System.out.println("Disk Writes"+ (pcounter.wcounter - startWrite));
				
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}	
		if(accessType.equalsIgnoreCase("Filescan"))
		{
			try
			{
				TupleScan ts = new TupleScan(f);
				Tuple t = null;
				tid.recordIDs = new RID[f.getnumColumns()];
				for(int j =0 ; j < f.getnumColumns() ; j++)
					tid.recordIDs[j] = new RID();
				int position=0;
				while ((t=ts.getNext(tid))!=null)
				{
					position++;
					if(value instanceof com.bow.bitmap.IntegerKey)
					{
						char sw=operator.charAt(0);
						  switch(sw)
						  {
						  case '>':
							  if (((com.bow.bitmap.IntegerKey)value).getKey().compareTo(t.getIntFld(columnAbsoluteIndex+1))<0)
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							  
							  break;
						  case '<':
							  if (((com.bow.bitmap.IntegerKey)value).getKey().compareTo(t.getIntFld(columnAbsoluteIndex+1))>0)
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							  
							  break;
						  case '!':
							  
							  if (!(((com.bow.bitmap.IntegerKey)value).getKey().compareTo(t.getIntFld(columnAbsoluteIndex+1))==0))
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							 
							  break;
						  case '=':

							  if (((com.bow.bitmap.IntegerKey)value).getKey().compareTo(t.getIntFld(columnAbsoluteIndex+1))==0)
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							  break;

						  }
						
					}
					else if(value instanceof com.bow.bitmap.StringKey)
					{
						char sw=operator.charAt(0);
						  switch(sw)
						  {
						  case '>':
							  if (((com.bow.bitmap.StringKey)value).getKey().compareTo(t.getStrFld(columnAbsoluteIndex+1))<0)
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							  
							  break;
						  case '<':
							  if (((com.bow.bitmap.StringKey)value).getKey().compareTo(t.getStrFld(columnAbsoluteIndex+1))>0)
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							  
							  break;
						  case '!':
							  
							  if (!(((com.bow.bitmap.StringKey)value).getKey().compareTo(t.getStrFld(columnAbsoluteIndex+1))==0))
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							 
							  break;
						  case '=':

							  if (((com.bow.bitmap.StringKey)value).getKey().compareTo(t.getStrFld(columnAbsoluteIndex+1))==0)
								{
								  System.out.print("Record:"+position);
								  getFileProjection(t);
								}
							  break;

						  }
											
					}
				}
				System.out.println("Disk Reads"+ (pcounter.rcounter-startRead));
				System.out.println("Disk Writes"+ (pcounter.wcounter-startWrite));
				
			}
			catch (Exception e)
			{
				
			}
		}
		try {
		SystemDefs.JavabaseBM.resetAllPinCount();
		SystemDefs.JavabaseBM.flushAllPages();
		SystemDefs.JavabaseDB.closeDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static TID getProjection(int position) throws InvalidSlotNumberException, HFException, HFDiskMgrException, HFBufMgrException, Exception
	{
		int colSize = columnNames.size();
		ArrayList<Tuple> arrTuples=new ArrayList<Tuple>();
		RID rid = new RID();
		Heapfile hf;
		Tuple t;
		TID tid=new TID(colSize);
		tid.recordIDs=new RID[colSize];
		tid.pos=position;
		tid.numRIDs=colSize;
		System.out.print("[");
		for(int j=0;j<colSize;j++)
			tid.recordIDs[j]=new RID();

		System.out.print("[");
		for (int i = 0 ; i < colSize ; i++)
		{
			hf = f.getHeapfileForColumname(columnNames.get(i).toString());
			rid = hf.PosToRid(position);
			t = hf.getRecord(rid);
			tid.recordIDs[i]=rid;
			if(t.getLength()>4)
				System.out.print(Convert.getStrValue(0, t.getData(), t.getLength())+", ");
			else
				System.out.print(Convert.getIntValue(0, t.getData())+", ");
			arrTuples.add(hf.getRecord(rid));
		}
		System.out.println("]");
		return tid;
	}
	
	public static void getFileProjection(Tuple t)
	{
		int i=0;
		System.out.print("[");
		for(String str: columnNames)
		{
			while(!str.equalsIgnoreCase(colNames[i]))i++;
			try{
			if(f.getAttributeTypeForColumname(str).attrType == AttrType.attrInteger)
			{
				System.out.print(t.getIntFld(i+1)+", ");
			}
			else if(f.getAttributeTypeForColumname(str).attrType == AttrType.attrString)
			{
				System.out.print(t.getStrFld(i+1)+", ");
			}
			}catch(Exception e)
			{e.printStackTrace();}
			i = 0;
		}
		System.out.print("]\n");
	}
	
	
}
