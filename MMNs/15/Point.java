/**
 * Point - Represent Point on axis
 * @Author : Roey Shtern
 * @Version : 21/6/2020
 */
public class Point
{
    //instance variables
    private double _x;
    private double _y;
    /**
     * Constructs a Point object. 
     * Construct a new point instance with the specified x and y
     * @param x the x value on axis
     * @param y the y value on axis
     */
    public Point(double x, double y)
    {
        _x = x;
        _y = y;
    }
    /**
     * Copy constructor for Point. Construct a point with the same instance variables as another time.
     * @param other The point object from which to construct the new point
     */
    public Point(Point other)
    {
        _x = other._x;
        _y = other._y;
    }
    /**
     * Returns the x value of the point.
     * @return the x value of the point
     */
    public double getX()
    {
        return this._x;
    }
    /**
     * Returns the y value of the point.
     * @return the y value of the point
     */
    public double getY()
    {
        return this._y;
    }
    /**
     * Changes the x of the point
     * @param num The new x
     */
    public void setX(double num)
    {
        this._x = num;
    }
    /**
     * Changes the y of the point
     * @param num The new y
     */
    public void setY(double num)
    {
        this._y = num;
    }
    /**
     * Returns a string representation of this point(x,y).
     * @return String representation of this point(x,y).
     */
    public String toString()
    {
        return "(" + this._x + "," + this._y + ")";
    }
    /**
     * Checks if the received point is equal to this point.
     * @param other The point to be compared with this point
     * @return true if the received point is equal to this point
     */
    public boolean equals(Point other)
    {
        return this._x == other._x && this._y == other._y;
    }
    /**
     * Checks if this point is above a received point.
     * @param other The point to check if this point is above
     * @return true if this point is above other point
     */
    public boolean isAbove(Point other)
    {
        return this._y > other._y;
    }
    /**
     * Checks if this point is under a received point.
     * @param other The point to check if this point is under
     * @return true if this point is under other point
     */
    public boolean isUnder(Point other)
    {
        return !isAbove(other);
    }
    /**
     * Checks if this point is left to a received point.
     * @param other The point to check if this point is left to a received point
     * @return true if this point is left to the other point
     */
    public boolean isLeft(Point other)
    {
        return this._x < other._x;
    }
    /**
     * Checks if this point is right to a received point.
     * @param other The point to check if this point is right to a received point
     * @return true if this point is right to the other point
     */
    public boolean isRight(Point other)
    {
        return !isLeft(other);
    }
    /**
     * Calculates the distances between two points. 
     * @param p The point to check the distance to.
     * @return double the distance between the two points
     */
    public double distance(Point p)
    {
        //sqrt((y2-y1)^2 + (x2 -x1)^2) - the formula to calc the distance between two points
        return Math.sqrt(Math.pow((p._y - this._y), 2) + Math.pow((p._x - this._x), 2));
    }
    /**
     * This function will move this point according to the recieved dx and dy
     * @param dx the delta of x to move this point
     * @param dy the delta of y to move this point
     */
    public void move(double dx, double dy)
    {
        this._x += dx;
        this._y += dy;
    }
}