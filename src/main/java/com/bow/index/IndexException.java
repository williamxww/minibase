package com.bow.index;

import java.lang.*;
import com.bow.chainexception.*;

public class IndexException extends ChainException 
{
  public IndexException() {super();}
  public IndexException(String s) {super(null,s);}
  public IndexException(Exception e, String s) {super(e,s);}
}
