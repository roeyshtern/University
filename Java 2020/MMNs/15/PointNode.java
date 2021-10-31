/**
 * PointNode - Represent PointNode in linked list of Points
 * @Author : Roey Shtern
 * @Version : 21/6/2020
 */
public class PointNode
{
    //instance variables
    private Point _point;
    private PointNode _next;
    /**
     * Constructs a PointNode object. 
     * Construct a new point node instance with the specified point and set next to null
     * @param p point to set for the new instance
     */
    public PointNode(Point p)
    {
        this._point = new Point(p);
        this._next = null;
    }
    /**
     * Constructs a PointNode object. 
     * Construct a new point node instance with the specified point and next PointNode
     * @param p point to set for the new instance
     * @param n PointNode to set for the new instance
     */
    public PointNode(Point p, PointNode n)
    {
        this._point = new Point(p);
        this._next = n;
    }
    /**
     * Copy constructor for PointNode. Construct a point node with new point with the same values and with the same next
     * @param p The point node object from which to construct the new point node
     */
    public PointNode(PointNode p)
    {
        this._point = new Point(p._point);
        this._next = p._next;
    }
    /**
     * Returns new point instance with the same values as this pointnode's point.
     * @return new point instance with the same values as this pointnode's point.
     */
    public Point getPoint()
    {
        return new Point(this._point);
    }
    /**
     * Returns the next value of the point node.
     * @return the next value of the point node.
     */
    public PointNode getNext()
    {
        return this._next;
    }
    /**
     * Changes the point of this PointNode
     * @param p The new point
     */
    public void setPoint(Point p)
    {
        this._point = new Point(p);
    }
    /**
     * Changes the next of this PointNode
     * @param next The new next
     */
    public void setNext(PointNode next)
    {
        this._next = next;
    }
}