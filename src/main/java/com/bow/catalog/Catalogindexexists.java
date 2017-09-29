package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogindexexists extends ChainException {

 public Catalogindexexists()
   {
      super();
   }

   public Catalogindexexists(Exception err, String name)
	{
	       super(err, name);
	}
}

