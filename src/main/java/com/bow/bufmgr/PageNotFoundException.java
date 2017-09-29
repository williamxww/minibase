package com.bow.bufmgr;
import com.bow.chainexception.*;


public class PageNotFoundException extends ChainException{

  public PageNotFoundException(Exception e, String name)
  { super(e, name); }
 


}




