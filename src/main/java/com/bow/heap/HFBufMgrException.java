package com.bow.heap;
import com.bow.chainexception.*;

public class HFBufMgrException extends ChainException{


  public HFBufMgrException()
  {
     super();
  
  }

  public HFBufMgrException(Exception ex, String name)
  {
    super(ex, name);
  }



}
