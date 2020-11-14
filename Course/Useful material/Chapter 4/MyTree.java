
// Ozbirn, 10/13/2014
// Demonstration of binary tree with recursive methods.

public class MyTree
{
   private Node root;

   public void setup()
   {
      //     50
      //    /  \
      //  80    60
      //  /\    /
      // 20 35 55

      // create above tree (not a search tree)
      Node leaf1 = new Node(20, null, null);
      Node leaf2 = new Node(35, null, null);
      Node leaf3 = new Node(55, null, null);

      Node p1 = new Node(80, leaf1, leaf2);
      Node p2 = new Node(60, leaf3, null);

      root = new Node(50, p1, p2);
   }

   public int sum()
   {
      return sum(root);
   }

   public int max()
   {
      return max(root);
   }

   private int sum(Node t)
   {
      if (t == null)
         return 0;
      else
      {
         return t.data + sum(t.left) + sum(t.right); 
      }
   }

   private int max(Node t)
   {
      if (t == null)
         return 0;
      else
      {
         return maxofthree(t.data, max(t.left), max(t.right));
      }
   }

   private int maxofthree(int a, int b, int c)
   {
      int max = a;
      if (b > max)
         max = b;
      if (c > max)
         max = c;
      return max;
   }


   private static class Node
   {
      int data;
      Node left;
      Node right;

      Node(int d, Node lt, Node rt)
      {
         data = d;
	 left = lt;
	 right = rt;
      }
   }

   public static void main(String args[])
   {
      MyTree tree = new MyTree();

      tree.setup();

      int treesum = tree.sum();
      System.out.println("Sum is: " + treesum);

      int treemax = tree.max();
      System.out.println("Max is: " + treemax);
   }
}
