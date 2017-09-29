package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogrelnotfound extends ChainException {

   public Catalogrelnotfound(Exception err, String name)
	{
	       super(err, name);
	}
}

