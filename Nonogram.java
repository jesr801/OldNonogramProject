package nonogrampuzzletwo;

/**
 * Class holds a 2D array of 1's and 0's and methods which return the solution and
 * hints to the sequence of the array
 * @author Jesus Rodriguez
 * @version 4/7/2021
 */
public class Nonogram 
{
    private int[][] sol;
    private int[][] ent;
    
    /**
     * Creates a 2D array of random 1's and 0's and of a given size and a 2D
     * array of all 0's
     * @param size preferred size of the array
     */
    public Nonogram(int size)
    {
        sol = new int[size][size];
        for (int i = 0; i < sol.length; i++)
            for (int j = 0; j < sol[i].length; j++)
                sol[i][j] = (int) (Math.random() * 2);
        
        ent = new int[size][size];
         for (int i = 0; i < ent.length; i++)
            for (int j = 0; j < ent[i].length; j++)
                ent[i][j] = 0;
    }
    
    /**
     * Creates a nonogram of 1's and 0's and a nonogram of all 0's using 
     * a pre-made 2D array
     * @param solution a 
     */
    public Nonogram(int[][] solution)
    {
        sol = solution;
        
        ent = new int[sol.length][sol[0].length];
         for (int i = 0; i < ent.length; i++)
            for (int j = 0; j < ent[i].length; j++)
                ent[i][j] = 0;
    }
    
    /**
     * Creates a 2D array of random 1's and 0's and an array of all 0's 
     * of size 5x5
     */
    public Nonogram()
    {
        this(5);
    }
    
    
    /**
     * Returns the Nonogram solution as a String 
     * @return String of a Nonogram solution
     */
    @Override
    public String toString()
    {
        String result = "Nonogram solution:";
        
        for (int j = 0; j < sol[0].length; j++)
        {
            result += "\n";
            for (int i = 0; i < sol.length; i++)
                result += sol[j][i] + "\t";
        }
        
        return result;
    }
    
    /**
     * Returns a string containing clues to a specific Nonogram row
     * @param r row index specified 
     * @return a string of the amount of times '1' appears in sequence
     */
    public String getRowClue(int r) 
    {
        String result = "";
        int j = 0;
        int count = 0;
        while (j < sol[r].length)
        {
            while (j < sol[r].length && sol[r][j] == 0)
            {
                j++;
            }
            while (j < sol[r].length && sol[r][j] == 1)
            {
                j++;
                count++;
            }
            if (count > 0)
            {
                result += count + " ";
                count = 0;
            }
            else if ("".equals(result))
            {
                result += "0 ";
            }   
        }
        return result;
    }
    
     /**
     * returns the sequence of 1's from all rows in a string array
     * @return the sequence of 1's from all rows in a string array
     */
    public String[] getRowClues()
    {
        String[] result = new String[sol.length];
        for (int i=0; i < sol.length; i++ )
        {
            result[i] = getRowClue(i);
        }
        return result;
    }
    
    /**
     * Returns a string containing clues to a specific Nonogram column
     * @param c column index specified 
     * @return a string of the amount of times '1' appears in sequence
     */
    public String getColumnClue(int c) 
    {
         String result = "";
        int i = 0;
        int count = 0;
        while (i < sol[c].length)
        {
            while (i < sol.length && sol[i][c] == 0)
            {
                i++;
            }
            while (i < sol[c].length && sol[i][c] == 1)
            {
                i++;
                count++;
            }
            if (count > 0)
            {
                result += count + " ";
                count = 0;
            }
            else if ("".equals(result))
                {
                    result += "0 ";
                }
        }
        return result;
    }
    
    /**
     * returns the sequence of 1's from all columns in a string array
     * @return the sequence of 1's from all columns in a string array
     */
    public String[] getColumnClues()
    {
         String[] result = new String[sol.length];
        for (int i=0; i < sol[0].length; i++ )
        {
            result[i] = getColumnClue(i);
        }
        return result;
    }
    
    /**
     * returns the Nonogram solution as a 2D array
     * @return a 2d array of the solution
     */
    public int[][] getSolution()
    {
        return sol;
    }
    
    /**
     * returns the entered Nonogram as a 2D array
     * @return a 2D array of the entered Nonogram
     */
    public int[][] getEntered()
    {
        return ent;
    }
    
    /**
     * resets the entered Nonogram to all 0's
     */
    public void resetEntered()
    {
       for (int i = 0; i < ent.length; i++)
            for (int j = 0; j < ent[i].length; j++)
                ent[i][j] = 0;
    }
    
    /**
     * checks if entered Nonogram and Nonogram solution are the same
     * @return returns true if both are the same, false otherwise
     */
    public boolean puzzleSolved()
    {
        boolean result = true;
        
        for (int i = 0; i < ent.length; i++)
            for (int j = 0; j < ent[i].length; j++)
                if (ent[i][j] != sol[i][j])
                    result = false;
        
        return result;
    }
    
    /**
     * changes an index in the entered Nonogram to either 1 or 0
     * @param i the row index
     * @param j the column index
     */
    public void toggleCell(int i, int j)
    {
        if (ent[i][j] == 0)
            ent[i][j] = 1;
        else
            ent[i][j] = 0;
    }
}
