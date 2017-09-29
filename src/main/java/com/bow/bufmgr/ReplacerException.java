package com.bow.bufmgr;
import com.bow.chainexception.*;

public class ReplacerException extends ChainException{

  public ReplacerException(Exception e, String name)
  { super(e, name); }
 
}

