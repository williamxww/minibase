package com.bow.diskmgr;
import com.bow.chainexception.*;

public class InvalidRunSizeException extends ChainException {
  
  public InvalidRunSizeException(Exception e, String name)
    { 
      super(e, name); 
    }
}




