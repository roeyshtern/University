/**
 * Time2 - Represents time by the minutes from midnight. 
 * Values must represent a proper time.
 * @Author : Roey Shtern
 * @Version : 22/4/2020
 */
public class Time2
{
    //instance variables
    private int _minFromMid;
    private final int DEFAULT_VALUE = 0;
    private final int MIN_HOUR=0;
    private final int MIN_MINUTE=0;
    private final int MAX_HOUR=23;
    private final int MAX_MINUTE=59;
    private final int MINUTE_IN_HOUR=60;
    private final int MINUTE_IN_DAY = 1440;
    /**
     * Constructs a Time2 object. 
     * Construct a new time instance with the specified hour and minute . 
     * hour should be between 0-23, otherwise it should be set to 0. 
     * minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time2 (int h, int m) 
    {
        // if one of the values is not in the range than it will be DEFAULT_VALUE
        if(h < MIN_HOUR || h > MAX_HOUR)
        {
            h = DEFAULT_VALUE;
        }
        if(m < MIN_MINUTE || m > MAX_MINUTE)
        {
            m = DEFAULT_VALUE;
        }
        _minFromMid = h * MINUTE_IN_HOUR + m;
    }

    /**
     * Copy constructor for Time2. Construct a time with the same instance variables as another time.
     * @param other The time object from  which to construct the new time
     */
    public Time2 (Time2 t)
    {
        this(t._minFromMid / t.MINUTE_IN_HOUR, t._minFromMid % t.MINUTE_IN_HOUR);
    }

    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour()
    {
        return _minFromMid / MINUTE_IN_HOUR;
    }

    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute()
    {
        return _minFromMid % MINUTE_IN_HOUR;
    }

    /**
     * Changes the hour of the time.
     *   If illegal number is received hour will remain  unchanged.
     * @param num The new hour 
     */
    public void setHour (int num)
    {
        if(num >= MIN_HOUR && num <= MAX_HOUR)
        {
            _minFromMid = num * MINUTE_IN_HOUR + this.getMinute();
        }
    }

    /**
     * Changes the minute of the time.
     *   If illegal number is received minute will remain unchanged.
     * @param num The new minute 
     */
    public void setMinute (int num)
    {
        if(num >= MIN_MINUTE && num <= MAX_MINUTE)
        {
            _minFromMid = _minFromMid - this.getMinute() + num;
        }
    }

    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return _minFromMid;
    }

    /**
     * Checks if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return true if the received time is  equal to this time
     */
    public boolean equals(Time2 other)
    {
        return _minFromMid == other._minFromMid;
    }

    /**
     * Checks if this time is  before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before (Time2 other)
    {
        return _minFromMid < other._minFromMid;
    }

    /**
     * Checks if this time is  after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after (Time2 other)
    {
        return other.before(this);
    }

    /**
     * Calculates the difference (in minutes) between two times. 
     * Assumption: this time is after other time.
     * @param other The time to check the difference to.
     * @return int difference in minutes
     */
    public int difference (Time2 other)  
    {
        return _minFromMid - other._minFromMid;
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
        return "" + timeUnit;
    }
    /**
     * Returns a string representation of this time (""hh:mm"").
     * @return String representation of this time (""hh:mm"").
     */
    public String toString()
    {
        return getValidStringTime(this.getHour()) + ":" + getValidStringTime(this.getMinute());
    }
    
    /**
     * Adds num Minutes to time.
     * @param num The number of minutes to add
     * @return the update time
     */
    public Time2 addMinutes(int num)
    {
        int totalMinUnderDay = (_minFromMid + num) % MINUTE_IN_DAY;
        if(totalMinUnderDay < 0)
        {
            /*
             * if totalMinUnderDay is less then zero than the total min should be totalMinUnderDay + MINUTE_IN_DAY
             * for example if totalMinUnderDay is -5, than it will be -5 + 1440 = 1440 + (-5) = 1335 = "23:55"
             */
            totalMinUnderDay += MINUTE_IN_DAY;
        }
        return new Time2(totalMinUnderDay / MINUTE_IN_HOUR, totalMinUnderDay % MINUTE_IN_HOUR);
    }
}