package lightingfight;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class messagePass {
  private static messagePass pass;
  private BooleanProperty stopped = new SimpleBooleanProperty(true);
  

  static messagePass passMessage()
  {
    if(pass==null)
    {
      pass =new messagePass();
    }
    return pass;
  }

  public void changestate(boolean set)
  {
   stopped.set(set);
  }

  public BooleanProperty getstate()
  {
    return stopped;
  }
}
