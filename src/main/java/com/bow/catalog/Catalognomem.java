package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalognomem extends ChainException {

   public Catalognomem(Exception err, String name)
	{
	       super(err, name);
	}
}

