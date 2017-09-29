package com.bow.iterator;

import java.lang.*;
import com.bow.chainexception.*;

public class UnknowAttrType extends ChainException {
  public UnknowAttrType(String s){super(null,s);}
  public UnknowAttrType(Exception prev, String s){super(prev,s);}
}
