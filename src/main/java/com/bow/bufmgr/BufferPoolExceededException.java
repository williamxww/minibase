package com.bow.bufmgr;
import com.bow.chainexception.*;

public class BufferPoolExceededException extends ChainException{

  public BufferPoolExceededException(Exception e, String name)
  { super(e, name); }
 
}
