package com.bow.heap;
import com.bow.chainexception.*;

public class FieldNumberOutOfBoundException extends ChainException{

   public FieldNumberOutOfBoundException()
   {
      super();
   }
   
   public FieldNumberOutOfBoundException (Exception ex, String name)
   {
      super(ex, name); 
   }

}

