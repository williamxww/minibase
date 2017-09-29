package com.bow.heap;
import com.bow.chainexception.*;


public class InvalidSlotNumberException extends ChainException {


  public InvalidSlotNumberException ()
  {
     super();
  }

  public InvalidSlotNumberException (Exception ex, String name)
  {
    super(ex, name);
  }



}
