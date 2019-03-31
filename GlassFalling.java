/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
     if(floors <= 1)
            return floors;
        if(sheets == 1)
            return floors;

        int min = Integer.MAX_VALUE;//set the minimum max value first so I can compare and find minimum
        int numofTrial;
        /**
         * This is glass falling algorithm using recursion. I have to find max value between 2 cases in i floors.
         * 1.If glass is shattered - try again with (i-1, sheets -1)
         * 2. If glass is not shattered - try again from more than i floors and sheets remain same
         */
        for(int i = 1; i <=floors; i++)
        {
            numofTrial = Math.max(glassFallingRecur(i-1,sheets-1),glassFallingRecur(floors-i,sheets));
            min = Math.min(min, numofTrial);
        }
        return min+1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized() {
     int memo[][] = new int[floors+1][sheets+1];
        for(int i = 0; i<=floors; i++)
            for(int j = 0; j<=sheets; j++)
                memo[i][j] = 0;
        return glassFallingMemoized(floors, sheets, memo);
  }

  public int glassFallingMemoized(int floors, int sheets, int memo[][])
  {
    if(memo[floors][sheets] != 0)//if it is already calculated, return it so doesn't need to calculate same thing twice
      return memo[floors][sheets];
    if(floors <= 1)
    {
        memo[floors][sheets] = floors;
        return memo[floors][sheets];
    }
    if(sheets == 1)
    {
      memo[floors][sheets] = floors;
      return memo[floors][sheets];
    }
    
    int min = Integer.MAX_VALUE;//set the minimum max value first so I can compare and find minimum
    int temp;
    
    for(int i = 1; i <=floors; i++)
    {
      temp = Math.max(glassFallingMemoized(i-1,sheets-1,memo),glassFallingMemoized(floors-i,sheets,memo));
      min = Math.min(min, temp);
    }
    
    memo[floors][sheets] = min + 1;
    return memo[floors][sheets];
  }
  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    int memo[][] = new int[sheets+1][floors+1];
        int temp;
        for(int i =1; i<=sheets; i++)//case of when floor is only 1
        {
            memo[i][0] = 0;
            memo[i][1] = 1;
        }
        for(int i = 1; i<=floors; i++)//case of when I have only 1 sheet
        {
            memo[0][i] = 0;
            memo[1][i] = i;
        }


        for(int i = 2; i<=sheets;i++)
            for(int j = 2; j<=floors; j++)
            {
                memo[i][j] = Integer.MAX_VALUE;
                for(int k = 1; k <= j; k++)
                {
                    temp = 1 + Math.max(memo[i - 1][j - 1], memo[i][j - k]);
                    memo[i][j] = Math.min(temp, memo[i][j]);
                }
            }
        return memo[sheets][sheets];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
