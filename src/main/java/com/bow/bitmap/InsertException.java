package com.bow.bitmap;
import com.bow.chainexception.*;

public class  InsertException extends ChainException 
{
  public InsertException() {super();}
  public InsertException(String s) {super(null,s);}
  public InsertException(Exception e, String s) {super(e,s);}

}
