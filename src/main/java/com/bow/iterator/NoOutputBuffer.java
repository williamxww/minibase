package com.bow.iterator;

import java.lang.*;
import com.bow.chainexception.*;

public class NoOutputBuffer extends ChainException {
  public NoOutputBuffer(String s){super(null,s);}
  public NoOutputBuffer(Exception prev, String s){super(prev,s);}
}
