package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogdupattrs extends ChainException{

   public Catalogdupattrs(Exception err, String name)
	{
	       super(err, name);
	}
}

