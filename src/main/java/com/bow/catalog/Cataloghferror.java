package com.bow.catalog;
import com.bow.chainexception.*;

public class Cataloghferror extends ChainException {

 public Cataloghferror()
   {
      super();
   }

   public Cataloghferror(Exception err, String name)
	{
	       super(err, name);
	}
}

