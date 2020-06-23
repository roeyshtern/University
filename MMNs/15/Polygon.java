/**
 * Polygon - Polygon represented by a linked list of vertices(PointNode)
 * @Author : Roey Shtern
 * @Version : 21/6/2020
 */
public class Polygon
{
    //instance variables
    private PointNode _head;
    /**
     * Constructs a Polygon object. 
     * Construct a new polygon instance with head set to null 
     * Time complexity: O(1)
     * Space complexity: O(1)
     */
    public Polygon()
    {
        this._head = null;
    }
    /**
     * This function will add vertice to the linked list in the postition specified
     * The first postition is 1, if the pos specified is occupied than the point will be insert as the next point in the list
     * if the position is bigger by one from the length of list than the point will be added to the end of the list
     * if the pos is bigger by two or more or less than 1 than the function return false and not insert the point
     * Time complexity: 9 + 4*n = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @param p the point to add to polygon
     * @param pos the position that the point will be inserted in the list
     * @return true if added and false if cannot add the point to the list
     */
    public boolean addVertex(Point p, int pos)
    {
        if(pos < 1 || p == null)
        {
            return false;
        }
        if(pos == 1)
        {
            this._head = new PointNode(p, this._head);
            return true;
        }
        if(this._head == null)
        {
            return false;
        }
        int i = 2;
        PointNode curr = this._head;
        while(curr.getNext() != null && i < pos)
        {
            i++;
            curr = curr.getNext();
        }
        if(curr.getNext() == null && i + 1 < pos)
        {
            return false;
        }
        if(curr.getNext() == null && i == pos)
        {
            curr.setNext(new PointNode(p));
            return true;
        }
        PointNode temp = curr.getNext().getNext();
        curr.setNext(new PointNode(p, temp));
        return true;
        
    }
    /**
     * This function returns copy of the highest vertex, if there is no vertices return null
     * Time complexity: 4 + 4*n = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return copy of the highest vertex, if there is no vertices return null
     */
    public Point highestVertex()
    {
        if(this._head == null)
        {
            return null;
        }
        PointNode curr = this._head.getNext();
        Point max = this._head.getPoint();
        while(curr != null)
        {
            if(curr.getPoint().isAbove(max))
            {
                max = curr.getPoint();
            }
            curr = curr.getNext();
        }
        return new Point(max);
    }
    //count the number of vertices in this polygon
    private int count()
    {
        int i = 1;
        PointNode curr = this._head;
        while(curr != null)
        {
            i++;
            curr = curr.getNext();
        }
        return i;
    }
    /**
     * Returns a string representation of this Polygon(x,y),(x,y)..(x,y). if there is no vertices will be return a message
     * Time complexity: 5 + 5n + 4 + 3n = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return string representation of this Polygon(x,y),(x,y)..(x,y). if there is no vertices will be return a message
     */
    public String toString()
    {
        if(this._head == null)
        {
            return "The polygon has 0 vertices.";
        }
        int count = this.count();
        String finalResult = "The polygon has " + count + " vertices:\n(";
        PointNode curr = this._head;
        while(curr != null)
        {
            finalResult += curr.getPoint().toString();
            if(curr.getNext() != null)
            {
                finalResult += ",";
            }
            curr = curr.getNext();
        }
        return finalResult;
    }
    /**
     * Returns the perimeter of the polygon, if there is only two vertices then return the distance
     * if there is one or zero vertices than return 0
     * Time complexity: 4 + 6n = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return the perimeter of the polygon or distance between two vertices or 0 if 1 or 0 vertices in polygon
     */
    public double calcPerimeter()
    {
        if(this._head == null || this._head.getNext() == null)
        {
            return 0;
        }
        PointNode curr = this._head;
        double distance = 0;
        while(curr.getNext() != null)
        {
            distance += curr.getPoint().distance(curr.getNext().getPoint());
            curr = curr.getNext();
        }
        return distance;
    }
    /**
     * Returns the area of the polygon, if there is less than 3 vertices than returns 0
     * Time complexity: 7*n - 4 = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return the area of the polygon, if there is less than 3 vertices than returns 0
     */
    public double calcArea()
    {
        if(this._head == null || this._head.getNext() == null || this._head.getNext().getNext() == null)
        {
            return 0;
        }
        double area = 0;
        PointNode curr = this._head.getNext();
        while(curr.getNext() != null)
        {
            area += getAreaOfTriangle(this._head, curr, curr.getNext());
            curr = curr.getNext();
        }
        return area;
    }
    // calculate the area of of trinalge accordsing to heron formula
    private double getAreaOfTriangle(PointNode head, PointNode curr, PointNode currNext)
    {
        double distanceHeadToCurr = head.getPoint().distance(curr.getPoint());//a - distance from head to curr
        double distanceCurrToNext = curr.getPoint().distance(currNext.getPoint());//b - distance from curr to curr.next
        double distanceHeadToNext = head.getPoint().distance(currNext.getPoint());//c - distance from head to curr.next
        double s = (distanceHeadToCurr + distanceCurrToNext + distanceHeadToNext)/2.0; //s is half the permiter of the triangle between head curr and curr.next
        return s*(s-distanceHeadToCurr)*(s-distanceCurrToNext)*(s-distanceHeadToNext);// Heron Formula: s(s-a)(s-b)(s-c)
    }
    /**
     * Returns true if this polygin area is bigger than the other area, otherwise false
     * Time complexity: O(n) + O(n) = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return Returns true if this polygin area is bigger than the other area, otherwise false
     */
    public boolean isBigger(Polygon other)
    {
        if(other == null)
        {
            return false;
        }
        return this.calcArea() > other.calcArea();
    }
    /**
     * Returns the position of the specified point in the list, or if not in the list return -1
     * Time complexity: 4 + 4*n = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return the position of the specified point in the list, or if not in the list return -1
     */ 
    public int findVertex(Point p)
    {
        if(_head == null || p == null)
        {
            return -1;
        }
        PointNode curr = this._head;
        int i = 1;
        while(curr != null)
        {
            if(curr.getPoint().equals(p))
            {
                return i;
            }
            i++;
            curr = curr.getNext();
        }
        return -1;
    }
    /**
     * Return copy of the next point according to the specified point, if the point is not in the list then returns null
     * if the specified point is the last in the list then returns the first point
     * Time complexity: 4 + 4n -4 = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return the next point in the list, the first if the specified is the last or null if not in the list
     */
    public Point getNextVertex(Point p)
    {
        if(this._head == null || p == null)
        {
            return null;
        }
        PointNode curr = this._head;
        while(curr.getNext() != null)
        {
            if(curr.getPoint().equals(p))
            {
                return new Point(curr.getNext().getPoint());
            }
            curr = curr.getNext();
        }
        if(curr.getPoint().equals(p))
        {
            return new Point(this._head.getPoint());
        }
        return null;
    }
    /**
     * Returns rectangle that contains this polygon and parallel to the axis
     * if the is less then 3 vertices then returns null
     * Time complexity: 4 + 4n -4 = O(n)
     * Space complexity: O(1) - final no dynamic allocations
     * @return new polygon of rectangle that contains this polygon and if less then 3 vertices returns null
     */
    public Polygon getBoundingBox()
    {
        if(this._head == null || this._head.getNext() == null || this._head.getNext().getNext() == null)
        {
            return null;
        }
        double leftmostX = this.getRightMost().getX();
        double rightmostX = this.getRightMost().getX();
        double highestY = this.highestVertex().getY();
        double lowestY = this.getLowest().getY();
        
        Polygon rectangle = new Polygon();
        
        rectangle.addVertex(new Point(leftmostX, lowestY), 1);
        rectangle.addVertex(new Point(leftmostX, highestY), 2);
        rectangle.addVertex(new Point(rightmostX, lowestY), 3);
        rectangle.addVertex(new Point(rightmostX, highestY), 4);
        return rectangle;
        
    }
    private Point getLeftMost()
    {
        if(this._head == null)
        {
            return null;
        }
        PointNode curr = this._head.getNext();
        Point max = this._head.getPoint();
        while(curr != null)
        {
            if(curr.getPoint().isLeft(max))
            {
                max = curr.getPoint();
            }
            curr = curr.getNext();
        }
        return max;
    }
    private Point getRightMost()
    {
        if(this._head == null)
        {
            return null;
        }
        PointNode curr = this._head.getNext();
        Point max = this._head.getPoint();
        while(curr != null)
        {
            if(curr.getPoint().isRight(max))
            {
                max = curr.getPoint();
            }
            curr = curr.getNext();
        }
        return max;
    }
    private Point getLowest()
    {
        if(this._head == null)
        {
            return null;
        }
        PointNode curr = this._head.getNext();
        Point max = this._head.getPoint();
        while(curr != null)
        {
            if(curr.getPoint().isUnder(max))
            {
                max = curr.getPoint();
            }
            curr = curr.getNext();
        }
        return max;
    }
}
