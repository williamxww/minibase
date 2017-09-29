/* File hferr.java  */

package com.bow.heap;
import com.bow.chainexception.*;

public class HFException extends ChainException{


  public HFException()
  {
     super();
  
  }

  public HFException(Exception ex, String name)
  {
    super(ex, name);
  }



}
