package com.bow.iterator;

import java.lang.*;
import com.bow.chainexception.*;

public class UnknownKeyTypeException extends ChainException 
{
  public UnknownKeyTypeException() {super();}
  public UnknownKeyTypeException(String s) {super(null,s);}
  public UnknownKeyTypeException(Exception e, String s) {super(e,s);}
}
