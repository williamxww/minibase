package com.bow.bitmap;
import com.bow.chainexception.*;

public class KeyTooLongException extends ChainException
{
  public KeyTooLongException() {super();}
  public KeyTooLongException(String s) {super(null,s);}
  public KeyTooLongException(Exception e, String s) {super(e,s);}

}
