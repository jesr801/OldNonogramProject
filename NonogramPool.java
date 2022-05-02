package nonogrampuzzletwo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

/**
 * Uses a Data folder containing .txt files to create a list of pre-made Nonogram solutions
 * @author Jesus Rodriguez
 * @version 5/7/2021
 */
public class NonogramPool 
{
    private Nonogram[] puzzlePool;
    private String[] puzzleName;
    private int fTotal;
    private String currentName; 
    
    
   
    /**
     * Creates an array of Nonograms using files found in Data folder
     * @param path the file path to pull pre-made solutions from
     */
    public NonogramPool(Path path)
    {
        fTotal = 0;
        puzzlePool = new Nonogram[100]; //Max size of the pool list
        puzzleName = new String[100];
        fillPuzzle(path);
    }
    
    /**
     * private helper method that creates a list of files to be read, repeating the 
     * method when interacting with subfolders. Also adds file names to separate 
     * array for future use.
     * @param path file to be read from
     */
    private void fillPuzzle(Path path)
    {
        File[] files;
        File aFile;
        
        //puts a groups of files in a list to be read
        aFile = new File(path.toString());
        files = aFile.listFiles();
        
        for (File file : files)
        {
            if (file.isDirectory())
            {
                fillPuzzle(file.toPath());
            }
            else
            {
                puzzlePool[fTotal] = new Nonogram(readFile(file));
                puzzleName[fTotal] = file.getName().replace(".txt", "");
                fTotal++;
            }
        }
        
    }
    
   /**
    * Helper method that returns a 2D array read from a file
    * @param file file to be read from
    * @return a 2d array of a Nonogram solution
    */
    private int[][] readFile(File file)
    {
        int[][] nono = new int[5][5];
        
        try
        {
            Scanner in = new Scanner(file);
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++ )
                {
                    nono[i][j] = in.nextInt();
                }
            }
            in.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            return nono;
        }
    }
    
    /**
     * returns a random Nonogram solution from a list of pre-made solutions. also
     * sets Nonogram's file name to a provate String
     * @return a random Nonogram solution
     */
    public Nonogram getRandomPuzzle()
    {
        Random rand = new Random();
        int r = rand.nextInt(fTotal);
        currentName = puzzleName[r];
        return puzzlePool[r];
    }
    
    /**
     * returns a string of a Nonogram file name derived from getRandomPuzzle
     * @return a string of Nonogram puzzle name
     */
    public String getPuzzleName()
    {
        return currentName;
    }
}
