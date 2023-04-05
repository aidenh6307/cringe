/**
 * BestPath.java
 * This class solves the Traveling Salesman problem
 *
 * @author Aiden Huang
 * @version 1.0
 * @since 3/29/2023
 */

import java.awt.Color;
public class BestPath
{
	private ListNode<Point> first;

	public BestPath()
	{
		first = null;
	}

	// This method returns the number of elements in the list
	public int size ( )
	{
		int size = 0;
		ListNode<Point> node = first;
		while (node != null)
		{
			size++;
			node = node.getNext();
		}
		return size;
	}

	// This method returns the total distance between all points
	public double length ( )
	{
		double size = 0.0;
		ListNode<Point> node = first;
		while (node.getNext() != null)
		{
			size += node.getValue().getDist(node.getNext().getValue());
			node = node.getNext();
		}
		size += first.getValue().getDist(node.getValue());
		return size;
	}

	// This method inserts a point at the closest index
	public void insertPointAtNearestNeighbor(Point p)
	{
		if(first==null)
		{
			first = new ListNode<Point>(p,null);
		}
		else
		{
			insert(p,getClosest(p));
		}
	}

	// This method finds the point at which the smallest increase in length is realized
	public void insertPointAtSmallestIncrease(Point p)
	{
		if(first==null)
		{
			first = new ListNode<Point>(p,null);
		}
		else
		{
			insert(p,getLength(p));
		}
	}

	// This method returns the index where the total length added to the list is the least
	public int getLength(Point p)
	{
		ListNode<Point> check = first;
		int index = 1;
		double len = Integer.MAX_VALUE;
		int i = 1;
		double startLen = length();
		while(check != null)
		{
			insert(p, i);
			double checker = length();
			if(checker-startLen < len)
			{
				index = i;
				len = checker-startLen;
			}
			deleteIndex(i);
			i++;
			check = check.getNext();
		}
		return index;
	}
	// This method draws the line from the last to the first point
	public void end()
	{
		ListNode<Point> last = first;

		while (last.getNext() != null)
			last = last.getNext();
		StdDraw.setPenColor(new Color(0,0,255));
		StdDraw.line(first.getValue().getX(), first.getValue().getY()
				,last.getValue().getX(), last.getValue().getY());
		StdDraw.setPenColor(new Color(255, 0, 0));
		StdDraw.filledCircle(first.getValue().getX(), first.getValue().getY(),2);

	}

	// This method inserts a point at a certain index
	public void insert(Point value, int index)
	{
		if (index == 0)
			first = new ListNode<Point>(value,null);
		else
		{
			ListNode<Point> x = first;

			for (int i = 0; i < index - 1; i++)
				x = x.getNext();

			x.setNext(new ListNode<Point>(value, x.getNext()));
		}
	}

	// This method finds the closest point
	public int getClosest(Point p)
	{
		ListNode<Point> node = first;
		int minIndex = 0;
		double minDist = Integer.MAX_VALUE*0.99;
		int i = 0;
		while (node != null)
		{
			i++;
			Point thisP = node.getValue();
			double dist = thisP.getDist(p);
			if(dist < minDist)
			{
				minDist  = dist;
				minIndex = i;
			}
			node = node.getNext();
		}
		return minIndex;
	}
	//  You'll need other methods

	// This method draws the points and the lines
	public void draw ( )
	{
		ListNode<Point> node = first;
		while(node.getNext() != null)
		{
			
			//  Draw the points and connect them
			StdDraw.setPenColor(new Color(0,0,255));
			StdDraw.line(node.getValue().getX(), node.getValue().getY()
					,node.getNext().getValue().getX(), node.getNext().getValue().getY());
			StdDraw.setPenColor(new Color(255,0,0));
			StdDraw.filledCircle(node.getValue().getX(), node.getValue().getY(),2);
			node = node.getNext();
		}
	}

	// Deletes an index from the List
	public void deleteIndex(int index)
	{
		if (index == 0)
		{
			first = first.getNext();
			return;
		}

		ListNode<Point> node = first;
		for (int i = 0; i < index - 1; i++)
		{
			node = node.getNext();
		}

		ListNode<Point> next = node.getNext();
		node.setNext(next.getNext());
	}
	// This method creates a string version of the list
    public String toString()
    {
		int count = 0;
		ListNode<Point> node = first;
		String result = new String("");
		while(node != null)
		{
			result += String.format("%4d: %s%n",count,(Point)node.getValue());
			node = node.getNext();
			count++;
		}
		return result;
    }
}