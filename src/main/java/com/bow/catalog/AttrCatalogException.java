package com.bow.catalog;
import com.bow.chainexception.*;

public class AttrCatalogException extends ChainException{

   public AttrCatalogException(Exception err, String name)
    {
      super(err, name);
    }
}

