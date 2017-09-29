package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogioerror extends ChainException {

   public Catalogioerror(Exception err, String name)
	{
	       super(err, name);
	}
}

