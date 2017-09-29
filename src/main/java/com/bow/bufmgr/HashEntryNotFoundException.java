package com.bow.bufmgr;
import com.bow.chainexception.*;


public class HashEntryNotFoundException extends ChainException{

  public HashEntryNotFoundException(Exception ex, String name)
  { 
    super(ex, name); 
  }
 


}




