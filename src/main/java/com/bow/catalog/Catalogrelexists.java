package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogrelexists extends ChainException{

   public Catalogrelexists(Exception err, String name)
	{
	       super(err, name);
	}
}

