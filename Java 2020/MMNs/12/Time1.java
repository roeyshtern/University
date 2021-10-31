/**
 * Represent time of day
 * @author Roey Shtern
 * @version 22/4/2020
 */
public class Time1
{
    private int _hour;
    private int _minute;
    
    private final int DEFAULT_VALUE = 0;
    private final int MIN_HOUR = 0;
    private final int MIN_MINUTE = 0;
    private final int MAX_HOUR = 23;
    private final int MAX_MINUTE = 59;
    private final int MINUTE_IN_HOUR = 60;
    private final int MINUTE_IN_DAY = 1440;
    /**
     * Constructs a Time1 object. 
     * Construct a new time instance with the specified hour and minute . 
     * hour should be between 0-23, otherwise it should be set to 0. 
     * minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time1(int h, int m)
    {
        _hour = h;
        _minute = m;
        
        if(h < MIN_HOUR || h > MAX_HOUR)
        {
            _hour = DEFAULT_VALUE;
        }
        if(m < MIN_MINUTE || m > MAX_MINUTE)
        {
            _minute = DEFAULT_VALUE;
        }
    }
    /**
     * Copy constructor for Time1. Construct a time with the same instance variables as another time.
     * @param other The time object from  which to construct the new time
     */
    public Time1(Time1 t)
    {
        this(t._hour, t._minute);
    }
    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour()
    {
        return _hour;
    }
    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute()
    {
        return _minute;
    }
    /**
     * Changes the hour of the time.
     *   If illegal number is received hour will remain  unchanged.
     * @param num The new hour 
     */
    public void setHour(int num)
    {
        if(num >= MIN_HOUR && num <= MAX_HOUR)
        {
            _hour = num;
        }
    }
    /**
     * Changes the minute of the time.
     *   If illegal number is received minute will remain unchanged.
     * @param num The new minute 
     */
    public void setMinute(int num)
    {
        if(num >= MIN_MINUTE && num <= MAX_MINUTE)
        {
            _minute = num;
        }
    }
    /*
     * make time unit to the their string representation "hh" or "mm"
     */ 
    private String getValidStringTime(int timeUnit)
    {
        if(timeUnit < 10)
        {
            return "0" + timeUnit;
        }
        // change this
        return "" + timeUnit;
    }
    /**
     * Returns a string representation of this time (""hh:mm"").
     * @return String representation of this time (""hh:mm"").
     */
    public String toString()
    {
        return getValidStringTime(_hour) + ":" + getValidStringTime(_minute);
    }
    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return MINUTE_IN_HOUR * _hour + _minute;
    }
    /**
     * Checks if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return true if the received time is  equal to this time
     */
    public boolean equals(Time1 other)
    {
        return (_hour == other._hour && _minute == other._minute);
    }
    /**
     * Checks if this time is  before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before(Time1 other)
    {
        return _hour < other._hour || (_hour == other._hour && _minute < other._minute);
    }
    /**
     * Checks if this time is  after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after(Time1 other)
    {
        return other.before(this);
    }
    /**
     * Calculates the difference (in minutes) between two times. 
     * Assumption: this time is after other time.
     * @param other The time to check the difference to.
     * @return int difference in minutes
     */
    public int difference(Time1 other)
    {
        return (_hour - other._hour) * MINUTE_IN_HOUR + _minute - other._minute;
    }
    /**
     * Adds num Minutes to time.
     * @param num The number of minutes to add
     * @return the update time
     */
    public Time1 addMinutes(int num)
    {
        int totalMinUnderDay = (this.minFromMidnight() + num) % MINUTE_IN_DAY;
        if(totalMinUnderDay < 0)
        {
            /*
             * if totalMinUnderDay is less then zero than the total min should be totalMinUnderDay + MINUTE_IN_DAY
             * for example if totalMinUnderDay is -5, than it will be -5 + 1440 = 1440 + (-5) = 1335 = "23:55"
             */
            totalMinUnderDay += MINUTE_IN_DAY;
        }
        return new Time1(totalMinUnderDay / MINUTE_IN_HOUR, totalMinUnderDay % MINUTE_IN_HOUR);
    }
}