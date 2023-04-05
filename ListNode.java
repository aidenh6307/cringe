/**
 * ListNode.java
 *
 * This class contains methods that emulate the function of a value in a SinglyLinkedList
 * @author Aiden Huang
 * @version 1.0
 * @since 3/20/2023
 */
public class ListNode<E>
{
    private E value;
    private ListNode<E>next;
    public ListNode (E v)
    {
        value = v;
        next = null;
    }
    public ListNode (E v, ListNode<E> nx)
    {
        value = v;
        next = nx;
    }

    // Returns the value of the listnode
    public E getValue ( )
    {
        return value;
    }

    // returns the next listnode in the list
    public ListNode<E> getNext ( )
    {
        if(next != null)
            return next;
        else
            return null;
    }

    // sets the value of the listnode
    public void setValue (E v)
    {
        value = v;
    }

    // sets the value of the next listnode
    public void setNext (ListNode<E> nx)
    {
        next = nx;
    }
}
