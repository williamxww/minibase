package com.bow.diskmgr;
import com.bow.chainexception.*;

public class InvalidPageNumberException extends ChainException {
  
  
  public InvalidPageNumberException(Exception ex, String name) 
    { 
      super(ex, name); 
    }
}




