package com.bow.iterator;

import java.lang.*;
import com.bow.chainexception.*;

public class JoinLowMemory extends ChainException {
  public JoinLowMemory(String s){super(null,s);}
  public JoinLowMemory(Exception prev, String s){super(prev,s);}
}
