package com.bow.catalog;
import com.bow.chainexception.*;

public class IndexCatalogException extends ChainException{

   public IndexCatalogException(Exception err, String name)
    {
      super(err, name);
    }
}

