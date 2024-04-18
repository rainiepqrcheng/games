package eatingsnake;

public class scoretransfer {
   private static scoretransfer  unit;
   private int score;
   public static scoretransfer getunit() 
   {
    if (unit == null) {
        unit = new scoretransfer();
    }
    return unit;
}

 public int getscore()
 {
    
    return score;
 }


public void setscore(int score)
{
 this.score = score;
}

}
