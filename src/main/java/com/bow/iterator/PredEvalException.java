package com.bow.iterator;
import com.bow.chainexception.*;

import java.lang.*;

public class  PredEvalException extends ChainException {
  public PredEvalException(String s){super(null,s);}
  public PredEvalException(Exception prev, String s){ super(prev,s);}
}
