package com.bow.bufmgr;
import com.bow.chainexception.*;

public class InvalidBufferException extends ChainException{
  
  
  public InvalidBufferException(Exception e, String name)
    { 
      super(e, name); 
    }

}




