/* File hferr.java  */

package com.bow.heap;
import com.bow.chainexception.*;

public class HFDiskMgrException extends ChainException{


  public HFDiskMgrException()
  {
     super();
  
  }

  public HFDiskMgrException(Exception ex, String name)
  {
    super(ex, name);
  }



}
