package com.bow.diskmgr;
import com.bow.chainexception.*;

public class OutOfSpaceException extends ChainException {

  public OutOfSpaceException(Exception e, String name)
    { 
      super(e, name); 
    }
}

