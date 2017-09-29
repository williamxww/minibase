package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogindexnotfound extends ChainException {

 public Catalogindexnotfound()
   {
      super();
   }

   public Catalogindexnotfound(Exception err, String name)
	{
	       super(err, name);
	}
}

