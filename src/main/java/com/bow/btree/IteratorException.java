package com.bow.btree;
import com.bow.chainexception.*;

public class IteratorException extends ChainException
{
  public IteratorException() {super();}
  public IteratorException(String s) {super(null,s);}
  public IteratorException(Exception e, String s) {super(e,s);}

}