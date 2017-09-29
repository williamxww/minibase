package com.bow.catalog;
import com.bow.chainexception.*;

public class CatalogException extends ChainException{

   public CatalogException(Exception err, String name)
    {
      super(err, name);
    }
}

