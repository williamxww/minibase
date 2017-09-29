package com.bow.catalog;
import com.bow.chainexception.*;

public class Catalogattrnotfound extends ChainException {

   public Catalogattrnotfound(Exception err, String name)
	{
	       super(err, name);
	}
}

