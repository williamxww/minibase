package com.bow.btree;
import com.bow.chainexception.*;

public class  FreePageException  extends ChainException 
{
  public FreePageException() {super();}
  public FreePageException(String s) {super(null,s);}
  public FreePageException(Exception e, String s) {super(e,s);}

}
