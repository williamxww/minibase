package com.bow.bitmap;
import com.bow.chainexception.*;

public class  DeleteFileEntryException  extends ChainException 
{
  public DeleteFileEntryException() {super();}
  public DeleteFileEntryException(String s) {super(null,s);}
  public DeleteFileEntryException(Exception e, String s) {super(e,s);}

}
