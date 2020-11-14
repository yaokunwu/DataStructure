// Ozbirn, 10/19/2015
// Word Search Puzzle program as described in Weiss textbook, ch5.
// Not completed, left for students to complete if they wish.
//

import java.util.HashSet;

public class WordPuzzle
{
   public static void main(String args[])
   {
      HashSet<String> table = new HashSet<>();

      // add some words for testing, later can read a dictionary from a file
      table.add("and");
      table.add("the");
      table.add("for");
      table.add("not");


      // hard-code a puzzle for testing, later can generate randomly 
      char matrix[][] = {
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxandxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray(),
          "xxxxxxxxxx".toCharArray()
	  };

      // display puzzle
      for (int i=0; i<matrix.length; i++)
      {
         for (int j=0; j<matrix[i].length; j++)
            System.out.print(matrix[i][j] + " ");
         System.out.println();
      }

      // check for words 
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<matrix.length; i++)
      {
         for (int j=0; j<matrix[i].length; j++)
         {
	    sb.setLength(0);
            for (int k=0; k + j < matrix[i].length; k++)
            {	  
               sb.append(matrix[i][j+k]);
               if (table.contains(new String(sb))) 
                   System.out.println("Found \"" + sb + "\" horizontally at "
		         + "(" + i + "," + j + ")");
            }
         }
      }
   }
}