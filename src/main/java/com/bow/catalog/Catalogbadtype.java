package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogbadtype extends ChainException {

   public Catalogbadtype(Exception err, String name)
	{
	       super(err, name);
	}
}

