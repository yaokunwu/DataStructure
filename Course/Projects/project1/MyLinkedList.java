/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }

    private void clear( )
    {
        doClear( );
    }

    /*** Project #1 part1***/
    /**
     *
     * @param position1
     * @param position2
     * receives two index positions as parameters, and swaps the nodes at
     * these positions by changing the links, provided both positions are
     * within the current size
     */
    //a.
    public void swap(int position1, int position2) {
        if (Math.max(position1, position2) >= size() || Math.min(position1, position2) < 0 || position1 == position2) {
            return;
        }
        if (position1 > position2) {
            int tmp = position2;
            position2 = position1;
            position1 = tmp;
        }
        Node<AnyType> nodeP1= beginMarker;
        Node<AnyType> nodeP2= beginMarker;
        for (int i = 0; i <= position1; i++) {
            nodeP1 = nodeP1.next;
        }
        for (int i = 0; i <= position2; i++) {
            nodeP2 = nodeP2.next;
        }
        if (nodeP1.next != nodeP2) {
            Node<AnyType> nodeP1Prev = nodeP1.prev;
            Node<AnyType> nodeP1Next = nodeP1.next;
            nodeP1.prev.next = nodeP2;
            nodeP1.next.prev = nodeP2;
            nodeP2.prev.next = nodeP1;
            nodeP1.prev = nodeP2.prev;
            nodeP2.prev = nodeP1Prev;
            nodeP1.next = nodeP2.next;
            nodeP2.next.prev = nodeP1;
            nodeP2.next = nodeP1Next;
        } else {
            nodeP1.prev.next = nodeP2;
            nodeP2.next.prev = nodeP1;
            nodeP2.prev = nodeP1.prev;
            nodeP1.prev = nodeP2;
            nodeP1.next = nodeP2.next;
            nodeP2.next = nodeP1;
        }
    }

    /**
     *
     * @param position
     * receives an integer (positive or negative) and shifts the list this
     * many positions forward (if positive) or backward (if negative).
     */
    //b.
    public void shift(int position) {
        position = position % size();
        if (position < 0) {
            position = size() + position;
        }
        int left = 0, right = position - 1;
        while (left < right) {
            swap(left, right);
            left++;
            right--;
        }

        left = position;
        right = size() - 1;
        while (left < right) {
            swap(left, right);
            left++;
            right--;
        }
        reverse();
    }

    private void reverse() {
        Node<AnyType> lastNode = reverse(beginMarker);
        Node<AnyType> tmp = beginMarker;
        this.beginMarker = lastNode;
        this.endMarker = tmp;
    }

    private Node<AnyType> reverse(Node<AnyType> head) {
        if (head == null || head.next == null) {
            head.prev = null;
            return head;
        }
        Node<AnyType> prevNode = reverse(head.next);
        head.next.next = head;
        Node<AnyType> prevPrev = head.prev;
        head.prev = head.next;
        if (prevPrev == null) {
            head.next = null;
        }
        return prevNode;
    }

    /**
     *
     * @param position
     * @param n
     * receives an index position and number of elements as parameters, and
     * removes elements beginning at the index position for the number of
     * elements specified, provided the index position is within the size
     * and together with the number of elements does not exceed the size
     */
    //c.
    public void erase(int position, int n) {
        if (position >= size() || position + n > size()) {
            return;
        }
        Node<AnyType> firstNode = beginMarker;
        Node<AnyType> lastNode = beginMarker;
        for (int i = 0; i <= position + n; i++) {
            lastNode = lastNode.next;
            if (i == position - 1) {
                firstNode = lastNode;
            }
        }
        firstNode.next = lastNode;
        lastNode.prev = firstNode;
    }

    /**
     *
     * @param position
     * @param otherList
     * receives another MyLinkedList and an index position as parameters, and
     * copies the list from the passed list into the list at the specified
     * position, provided the index position does not exceed the size.
     */
    //d.
    public void insertList(int position, MyLinkedList<AnyType> otherList) {
        if (position > size()) {
            return;
        }
        Node<AnyType> firstNode = beginMarker;
        for (int i = 0; i < position; i++) {
            firstNode = firstNode.next;
        }
        Node<AnyType> secondNode = firstNode.next;
        Node<AnyType> otherCurr = otherList.getNode(0);//
        Node<AnyType> copyedHead = new Node<AnyType> (null, null, null);
        Node<AnyType> prevCopyed = copyedHead;
        while (otherCurr.next != null) {
            Node<AnyType> currCopy = new Node<>(otherCurr.data, null, null);
            prevCopyed.next = currCopy;
            currCopy.prev = prevCopyed;
            prevCopyed = currCopy;
            otherCurr = otherCurr.next;
        }
        firstNode.next = copyedHead.next;
        secondNode.prev = prevCopyed;
        copyedHead.next.prev = firstNode;
        prevCopyed.next = secondNode;
    }
    /*** Project #1 part1 ends, main method is at the end***/


    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }

    public boolean isEmpty( )
    {
        return size( ) == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }

    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;

        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );

        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }

    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext( )
        {
            return current != endMarker;
        }

        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( );

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );

            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }

        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{

    private static void restore(MyLinkedList<Integer> lst1) {
        lst1.doClear();
        for( int i = 1; i < 5; i++ ) {
            lst1.add(i);
        }
    }

    /**
     * Demonstrate each of my methods
     */
    //e.
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );
        restore(lst);
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        for( int i = 1; i < 5; i++ ) {
            lst2.add(i + 4);
        }

        System.out.print("Original list: ");
        System.out.println( lst);

        System.out.print("To be inserted list: ");
        System.out.println( lst2);
        System.out.println();
        // swap
        System.out.print("After swap postion 1, 3 in Original list: ");
        lst.swap(1,3);
        System.out.println( lst);
        restore(lst);

        System.out.print("After swap postion 0, 1 in Original list: ");
        lst.swap(0,1);
        System.out.println( lst);
        restore(lst);
        System.out.println();

        //shift
        System.out.print("After shift Original list by +2: ");
        lst.shift(2);
        System.out.println( lst );
        restore(lst);

        System.out.print("After shift Original list by -1: ");
        lst.shift(-1);
        System.out.println( lst);
        restore(lst);
        System.out.println();

        //erase
        System.out.print("After erase 2 elements from Original list at position 1: ");
        lst.erase(1,2);
        System.out.println( lst );
        restore(lst);

        System.out.print("After erase 3 elements from Original list at position 0: ");
        lst.erase(0,3);
        System.out.println( lst );
        restore(lst);
        System.out.println();

        //insertList
        System.out.print("After insert list2 to original list at postion 2:  ");
        lst.insertList(2, lst2);
        System.out.println( lst );
        restore(lst);

        System.out.print("Check if the toBeInserted list is modified:  ");
        System.out.println( lst2 );
        System.out.println("toBeInserted List remain unchanged, deep copy is successfully performed.");
        System.out.println();
    }

}