package com.bow.heap;
import com.bow.chainexception.*;

public class InvalidTupleSizeException extends ChainException{

   public InvalidTupleSizeException()
   {
      super();
   }
   
   public InvalidTupleSizeException(Exception ex, String name)
   {
      super(ex, name); 
   }

}

