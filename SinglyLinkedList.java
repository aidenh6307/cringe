/**
 * SinglyLinkedList.java
 * This class contains methods that emulate the function of a SinglyLinkedList
 * @author Aiden Huang
 * @version 1.0
 * @since 3/20/2023
 */
import java.util.NoSuchElementException;
public class SinglyLinkedList<E>
{
    private ListNode<E> first;
    private ListNode<E> last;
    public SinglyLinkedList()
    {
        first = null;
        last = null;
    }

    // This method returns the first element of the list
    public E getFirst()
    {
        if(first == null)
            throw new NoSuchElementException();
        return first.getValue();
    }

    // This method adds a new value to the front of the list from the parameter value
    public void addFirst(E value)
    {
        first = new ListNode<E>(value, first);
    }

    // This method prints out the contents of the list
    public void printList ()
    {
        ListNode node = first;
        while (node != null)
        {
            System.out.print(node.getValue() + "  ");
            node = node.getNext();
        }
    }

    // This method returns the last element of the list
    public E getLast()
    {
        if (last == null)
        {
            throw new NoSuchElementException();
        }
        return last.getNext().getValue();
    }


    // This method adds a new element to the array from the rear using the parameter value
    public void addLast(E value)
    {
        if (first == null)
        {
            addFirst(value);
        }
        else
        {
            last = first;

            while (last.getNext() != null)
                last = last.getNext();

            last.setNext(new ListNode<E>(value));
        }
    }

    // This method returns the length of the List
    public int size()
    {
        int size = 0;
        ListNode node = first;
        while (node != null)
        {
            size++;
            node = node.getNext();
        }
        return size;
    }

    // This method adds a value at the parameter index
    public void insert(E value, int index)
    {
        if(index>=size())
        {
            index=size();
        }
        if (index == 0)
            addFirst(value);
        else
        {
            ListNode<E> x = first;

            for (int i = 0; i < index - 1; i++)
                x = x.getNext();

            x.setNext(new ListNode<E>(value, x.getNext()));
        }
    }

    // This method deletes a certain value
    public void deleteValue(E value)
    {
        ListNode<E> previous = null;
        ListNode<E> current = first;
        while (current != null)
        {
            if (current.getValue().equals(value))
            {
                if (previous == null)
                {
                    first = current.getNext();
                    current = first;
                }
                else
                {
                    previous.setNext(current.getNext());
                    current = current.getNext();
                }
            }
            else
            {
                previous = current;
                current = current.getNext();
            }
        }
    }

    // This method removes a value at the parameter index
    public void deleteIndex(int index)
    {
        if (index >= size() || index < 0)
        {
            return;
        }

        if (index == 0)
        {
            first = first.getNext();
            if (first == null)
            {
                last = null;
            }
            return;
        }

        ListNode<E> node = first;
        for (int i = 0; i < index - 1; i++)
        {
            node = node.getNext();
        }

        ListNode<E> next = node.getNext();
        node.setNext(next.getNext());
        if (next.getNext() == null)
        {
            last = node;
        }
    }

    // This method swaps the ListNodes at the two indices
    public void swap(int index1, int index2)
    {
        if (index1 == index2 || size() < 2)
        {
            return;
        }

        if (index1 > index2)
        {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }

        ListNode<E> prev1 = null;
        ListNode<E> curr1 = first;
        for (int i = 0; i < index1; i++)
        {
            prev1 = curr1;
            curr1 = curr1.getNext();
        }

        ListNode<E> prev2 = null;
        ListNode<E> curr2 = first;
        for (int i = 0; i < index2; i++)
        {
            prev2 = curr2;
            curr2 = curr2.getNext();
        }

        if (prev1 != null)
        {
            prev1.setNext(curr2);
        }
        else
        {
            first = curr2;
        }

        if (prev2 != null)
        {
            prev2.setNext(curr1);
        }
        else
        {
            first = curr1;
        }
        ListNode<E> temp = curr1.getNext();
        curr1.setNext(curr2.getNext());
        curr2.setNext(temp);

        if (curr1.getNext() == null)
        {
            last = curr1;
        }
        else if (curr2.getNext() == null)
        {
            last = curr2;
        }
    }

    // This method reverses the List
    public void reverse()
    {
        ListNode<E> prev = null;
        ListNode<E> current = first;

        while (current != null)
        {
            ListNode<E> next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        last = first;
        first = prev;
    }
}