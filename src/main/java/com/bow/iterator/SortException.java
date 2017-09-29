package com.bow.iterator;

import java.lang.*;
import com.bow.chainexception.*;

public class SortException extends ChainException 
{
  public SortException(String s) {super(null,s);}
  public SortException(Exception e, String s) {super(e,s);}
}
