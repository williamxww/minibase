package com.bow.bitmap;
import com.bow.chainexception.*;

public class IndexFullDeleteException extends ChainException
{
  public IndexFullDeleteException() {super();}
  public IndexFullDeleteException(String s) {super(null,s);}
  public IndexFullDeleteException(Exception e, String s) {super(e,s);}

}
