// Ozbirn, 10/10/2016
// Demonstration of binary tree with recursive methods.

public class MyTree
{
   private Node root;
   
   public void buildSampleTree()
   {

      Node ll = new Node(0, null, null);
      Node rl = new Node(3, null, null);
      Node rr = new Node(5, null, null);
   
      Node l = new Node(1, ll, null);
      Node r = new Node(4, rl, rr);
      
      root = new Node(2, l, r);
   }

   public void printTree()
   {
      printTree(root);
   }

   private void printTree(Node t)
   {
      if (t != null)
      {
         printTree(t.left);
         System.out.println(t.data);
         printTree(t.right);
      }   
   }

   public int sum()
   {
      return sum(root);
   }
   
   private int sum(Node t)
   {
      if (t == null)
         return 0;
      else
         return t.data + sum(t.left) + sum(t.right);
   }


   public int sumodd()
   {
      return sumodd(root);
   }
   
   private int sumodd(Node t)
   {
      if (t == null)
         return 0;
      else
         if (t.data%2 == 0)
            return sumodd(t.left) + sumodd(t.right);
         else
            return t.data + sumodd(t.left) + sumodd(t.right);
   }
   
   
   public int sumleaves()
   {
      return sumleaves(root);
   }
   
   private int sumleaves(Node t)
   {
      if (t == null)
         return 0;
      else
         if (t.left == null && t.right == null)
            return t.data;
         else
            return sumleaves(t.left) + sumleaves(t.right);         
   
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
      
      tree.buildSampleTree();
      
      tree.printTree();
      
      int mysum = tree.sum();
      
      System.out.println("Sum is: " + mysum);
      
      mysum = tree.sumodd();
      
      System.out.println("Sum of odd values is: " + mysum);

      mysum = tree.sumleaves();
      
      System.out.println("Sum of leaf values is: " + mysum);

   }

}