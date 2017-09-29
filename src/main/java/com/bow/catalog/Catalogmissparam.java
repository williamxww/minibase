package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogmissparam extends ChainException {

   public Catalogmissparam(Exception err, String name)
	{
	       super(err, name);
	}
}

