package com.bow.catalog;
import com.bow.chainexception.*;

public class RelCatalogException extends ChainException{

   public RelCatalogException(Exception err, String name)
    {
      super(err, name);
    }
}

