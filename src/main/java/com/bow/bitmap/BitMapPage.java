package com.bow.bitmap;

import java.io.*;

import com.bow.btree.ConstructPageException;
import com.bow.diskmgr.*;
import com.bow.heap.*;

public class BitMapPage extends HFPage{
	
	int keyType;
	
	public BitMapPage() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public BitMapPage(Page page, int keyType) 
	throws IOException,ConstructPageException
	{
		super(page);
	    this.keyType=keyType;   
	}
	
	public byte[] getBMpageArray()
	{
		return this.getHFpageArray();
	}
	
	public void getBMpageArray(byte [] array)
	{
		this.data=array;
	}
	
	  


}
