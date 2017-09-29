package com.bow.index;

import java.lang.*;
import com.bow.chainexception.*;

public class InvalidSelectionException extends ChainException 
{
  public InvalidSelectionException() {super();}
  public InvalidSelectionException(String s) {super(null,s);}
  public InvalidSelectionException(Exception e, String s) {super(e,s);}
}
