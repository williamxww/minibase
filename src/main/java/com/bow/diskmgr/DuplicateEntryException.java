package com.bow.diskmgr;
import com.bow.chainexception.*;

public class DuplicateEntryException extends ChainException {
  
  public DuplicateEntryException(Exception e, String name)
    {
      super(e, name); 
    }
}

