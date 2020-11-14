// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{

    /** Project Part I Start **/

    /**
     * a
     */
    private int nodeCount = 0;
    public int nodeCount() {
        nodeCountHelper(root);
        return nodeCount;
    }

    private void nodeCountHelper(BinaryNode<AnyType> root) {
        if (root == null) {
            return;
        }
        nodeCount++;
        nodeCountHelper(root.left);
        nodeCountHelper(root.right);
    }

    /**
     * b
     */
    public boolean isFull() {
        return isFullHelper(root);
    }

    private boolean isFullHelper(BinaryNode<AnyType> root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return isFullHelper(root.left) && isFullHelper(root.right);
    }

    /**
     * c
     */
    public boolean compareStruture(BinarySearchTree<AnyType> otherTree) {
        return compareStrutureHelper(root, otherTree.getRoot());
    }

    private boolean compareStrutureHelper(BinaryNode<AnyType> root1, BinaryNode<AnyType> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return compareStrutureHelper(root1.left, root2.left) && compareStrutureHelper(root1.right, root2.right);
    }

    /**
     * d
     */
    public boolean equals(BinarySearchTree<AnyType> otherTree) {
        return equalsHelper(root, otherTree.getRoot());
    }

    private boolean equalsHelper(BinaryNode<AnyType> root1, BinaryNode<AnyType> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || !root1.element.equals(root2.element)) {
            return false;
        }
        return equalsHelper(root1.left, root2.left) && equalsHelper(root1.right, root2.right);
    }

    /**
     * e
     */
    public BinarySearchTree<AnyType> copy() {
        BinarySearchTree<AnyType> copyed = new BinarySearchTree<>();
        copyed.insert(root.element);
        copyHelper(root, copyed.getRoot());
        return copyed;
    }

    private void copyHelper(BinaryNode<AnyType> root1, BinaryNode<AnyType> root2) {
        if (root1.left != null) {
            BinaryNode<AnyType> leftCopyed = new BinaryNode<>(root1.left.element);
            root2.left = leftCopyed;
            copyHelper(root1.left, root2.left);
        }
        if (root1.right != null) {
            BinaryNode<AnyType> rightCopyed = new BinaryNode<>(root1.right.element);
            root2.right = rightCopyed;
            copyHelper(root1.right, root2.right);
        }
    }

    /**
     * f
     */
    public BinarySearchTree<AnyType> mirror() {
        BinarySearchTree<AnyType> copyed = this.copy();
        mirrorHelper(copyed.getRoot());
        return copyed;
    }

    private void mirrorHelper(BinaryNode<AnyType> root) {
        if (root == null) {
            return;
        }
        BinaryNode<AnyType> left = root.left;
        BinaryNode<AnyType> right = root.right;
        root.left = right;
        root.right = left;
        mirrorHelper(root.left);
        mirrorHelper(root.right);
    }

    /**
     * g
     */
    public boolean isMirror(BinarySearchTree<AnyType> otherTree) {
        return isMirrorHelper(root, otherTree.getRoot());
    }

    private boolean isMirrorHelper(BinaryNode<AnyType> root1, BinaryNode<AnyType> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || !root1.element.equals(root2.element)) {
            return false;
        }
        return isMirrorHelper(root1.right, root2.left) && isMirrorHelper(root1.left, root2.right);
    }

    /**
     * h1
     */
    public void rotateRight(AnyType x) {
        root = rotateRightHelper(x, root);
    }

    private BinaryNode<AnyType> rotateRightHelper(AnyType x, BinaryNode<AnyType> curr) {
        if (curr == null) {
            return null;
        }
        BinaryNode<AnyType> newRoot = curr;
        if (curr.element.compareTo(x) > 0) {
            curr.left =  rotateRightHelper(x, curr.left);
        } else if (curr.element.compareTo(x) < 0) {
            curr.right = rotateRightHelper(x, curr.right);
        } else {
            if (curr.left != null) {
                newRoot = curr.left;
                BinaryNode<AnyType> leftRight = curr.left.right;
                newRoot.right = curr;
                curr.left = leftRight;
            }
        }
        return newRoot;
    }

    /**
     * h2
     */
    public void rotateLeft(AnyType x) {
        root = rotateLeftHelper(x, root);
    }

    private BinaryNode<AnyType> rotateLeftHelper(AnyType x, BinaryNode<AnyType> curr) {
        if (curr == null) {
            return null;
        }
        BinaryNode<AnyType> newRoot = curr;
        if (curr.element.compareTo(x) > 0) {
            curr.left =  rotateLeftHelper(x, curr.left);
        } else if (curr.element.compareTo(x) < 0) {
            curr.right = rotateLeftHelper(x, curr.right);
        } else {
            if (curr.right != null) {
                newRoot = curr.right;
                BinaryNode<AnyType> rightLeft = curr.right.left;
                newRoot.left = curr;
                curr.right = rightLeft;
            }
        }
        return newRoot;
    }

    /**
     * i
     */
    public void printLevels() {
        printLevelsHelper(root);
    }

    private void printLevelsHelper(BinaryNode<AnyType> curr) {
        Queue<BinaryNode<AnyType>> queue = new LinkedList<>();
        queue.offer(curr);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                BinaryNode<AnyType> currNode = queue.poll();
                System.out.print(currNode.element);
                System.out.print(" ");
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
            System.out.println();
        }
    }

    /** Project Part I End **/

    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        //if( isEmpty( ) )
          //  throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        //if( isEmpty( ) )
          //  throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;

    /**
     * Newly added
     * @return return the root of the current Tree
     */
    public BinaryNode<AnyType> getRoot() {
        return root;
    }


        // Test program
    /** Project Part II Start **/

    /**
     * j
     */
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> testTree1 = new BinarySearchTree<>( );
        testTree1.insert(5);
        testTree1.insert(3);
        testTree1.insert(8);
        testTree1.insert(1);
        testTree1.insert(4);
        System.out.println("Test tree 1 (Level Printing): ");
        testTree1.printLevels();
        System.out.println();

        BinarySearchTree<Integer> testTree2 = new BinarySearchTree<>( );
        testTree2.insert(10);
        testTree2.insert(5);
        testTree2.insert(15);
        testTree2.insert(2);
        testTree2.insert(7);
        System.out.println("Test tree 2 (Level Printing): ");
        testTree2.printLevels();
        System.out.println();

        // nodeCount
        System.out.println("a. nodeCount test");
        System.out.print("Node Count for Test tree 1: ");
        System.out.println(testTree1.nodeCount());
        System.out.println();

        // isFull
        System.out.println("b. isFull test");
        System.out.print("Is Test tree 1 full? ");
        System.out.println(testTree1.isFull());
        System.out.println();

        //CompareStructure
        System.out.println("c. compareStructure test");
        System.out.print("Are Test tree 1 and Test tree 2 the same structure? ");
        System.out.println(testTree1.compareStruture(testTree2));
        System.out.println();

        //equals
        System.out.println("d. equals test");
        System.out.print("Are Test tree 1 and Test tree 2 identical? ");
        System.out.println(testTree1.equals(testTree2));
        System.out.print("Are Test tree 1 and Test tree 1 identical? ");
        System.out.println(testTree1.equals(testTree1));
        System.out.println();

        //copy
        System.out.println("e. copy test");
        System.out.println("Print the copy of Test Tree 1: ");
        BinarySearchTree<Integer> copyedTree = testTree1.copy();
        copyedTree.printLevels();
        System.out.print("Is this a deep copy? ");
        System.out.println(copyedTree != testTree1);
        System.out.println();

        //mirror
        System.out.println("f. mirror test");
        System.out.println("Print the mirror of Test Tree 1: ");
        BinarySearchTree<Integer> mirrorTree = testTree1.mirror();
        mirrorTree.printLevels();
        System.out.println();

        //ismirror
        System.out.println("g. isMirror test");
        System.out.print("Is the mirror obtained above correct? ");
        System.out.println(testTree1.isMirror(mirrorTree));
        System.out.println();

        // rotateright
        System.out.println("h. rotateRight and rotateLeft test");
        System.out.println("After rotating right at 5 from orginal Test Tree 1: ");
        testTree1.rotateRight(5);
        testTree1.printLevels();
        System.out.println();
        // restore test tree 1
        testTree1.rotateLeft(3);

        // rotateLeft
        System.out.println("After rotating left at 3 from orginal Test Tree 1: ");
        testTree1.rotateLeft(3);
        testTree1.printLevels();
        System.out.println();

        // printLevels
        System.out.println("i. printLevels test");
        System.out.println("Print the Test tree 2 level by level");
        testTree2.printLevels();
    }
    /** Project Part II End **/
}
