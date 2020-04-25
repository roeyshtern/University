/**
 * Represents a Train.
 * Author : Roey Shtern
 * Version : 22/4/2020
 */ 
public class Train
{
    private String _destination;
    private Time1 _departure;
    private int _duration;
    private int _passengers;
    private int _seats;
    private int _price;
    
    private final int DEFAULT_VALUE = 0;

    /**
     * Constructor of class Train. 
     * Constructs a new train. 
     * duration should be positive, otherwise it should be set to 0. 
     * pass should be positive, otherwise it should be set to 0. 
     * pass should be less than seats otherwise it should be set to seats. 
     * seats should be positive, otherwise it should be set to 0. 
     * price should be positive, otherwise it should be set to 0.
     * @param dest the destination of the train
     * @param h the hour of departure.
     * @param m the minute of departue.
     * @param duration the duration of the travel.
     * @param pass the number of passeners.
     * @param seats the number of seats in the train.
     * @param price the price of the travel.
     */    
    public Train(String dest, int h, int m, int duration, int pass, int seats, int price)
    {
        _destination = dest;
        _duration = duration < DEFAULT_VALUE ? DEFAULT_VALUE : duration;
        _seats = seats < DEFAULT_VALUE ? DEFAULT_VALUE : seats;
        if(pass > _seats)
        {
            _passengers = _seats;
        }
        else if(pass < DEFAULT_VALUE)
        {
            _passengers = DEFAULT_VALUE;
        }
        else
        {
            _passengers = pass;
        }
        _price = price < DEFAULT_VALUE ? DEFAULT_VALUE : price;
        _departure = new Time1(h, m);
    }

    /**
     * Copy constructor for Train.
     * Construct a train with the same instance variables as another train.
     * @ param other The train object from which to construct the new train.
     */    
    public Train(Train other)
    {
        _destination = other._destination;
        _duration = other._duration;        
        _seats = other._seats;
        _passengers = other._passengers;
        _price = other._price;
        _departure = new Time1(other._departure);
    }

    /**
     * returns the departure time.
     * @return the departure time.
     */
    public Time1 getDeparture()
    {
        return new Time1(_departure);
    }

    /**
     * returns the destination.
     * @return the destination of the train.
     */
    public String getDestination()
    {
        return _destination;    
    }

    /**
     * returns the duration of the train.
     * @return the duration of the train.
     */
    public int getDuration()
    {
        return _duration;
    }

    /**
     * returns the number of passengers.
     * @return the number of passengers.
     */
    public int getPassengers()
    {
        return _passengers;
    }

    /**
     * returns the price of the train.
     * @return the price of the train.
     */
    public int getPrice()
    {
        return _price;
    }

    /**
     * returns the number of seats.
     * @return the number of seats.
     */
    public int getSeats()
    {
        return _seats;
    }

    /**
     * updates the departure time of the train.
     * t is not null.
     * @param t the new departure time of the train.
     */
    public void setDeparture(Time1 t)
    {
        _departure = new Time1(t);
    }

    /**
     * updates the destination of the train.
     * d in not null.
     * @param d the new detination of the train.
     */
    public void setDestination(String d)
    {
        _destination = d;
    }    

    /**
     * updates the duration of the train.
     * d should be positive or zero, otherwise duration is unchanged.
     * @param d the new duration of the train.
     */
    public void setDuration(int d)
    {
        if(d >= 0)
        {
            _duration = d;
        }
    }

    /**
     * updates the number of passengers.
     * p should be positive or zero, otherwise passengers is unchanged. 
     * p should be less than seats otherwise it should be set to seats.
     * @param p the new number of passengers.
     */
    public void setPassengers(int p)
    {
        if(p >= 0)
        {
            _passengers = p > _seats ? _seats : p;
        }
    }

    /**
     * updates the price.
     * p should be positive or zero, otherwise price is unchanged.
     * @param p the new price.
     */
    public void setPrice(int p)
    {
        if(p >= 0)
        {
            _price = p;
        }
    }

    /**
     * updates the number of seats.
     * s should be positive or zero, otherwise seats is unchanged. 
     * s should be larger than passengers, otherwise seats is unchanged.
     * @param s the new number of seats.
     */
    public void setSeats(int s)
    {
        if(s >= 0 && s > _passengers)
        {
            _seats = s;
        }
    }

    /**
     * Check if the received train is equal to this train.
     * @param other The train to be compared with this train
     * @return true if the received train is equal to this train.
     */
    public boolean equals(Train other)
    {
        return _destination == other._destination && _seats == other._seats && _departure.equals(other._departure);
    }

    /**
     * Returns the arrival time.
     * @return the arrival time.
     */
    public Time1 getArrivalTime()
    {
        return _departure.addMinutes(_duration);
    }

    /**
     * Add num passengers to the train.
     * @param num The number of passengers to add.
     * @return true if the total number of current passengers and num is less or equal to seats.
     */
    public boolean addPassengers(int num)
    {
        if(num + _passengers > _seats)
        {
            return false;
        }
        _passengers += num;
        return true;
    }

    /**
     * Returns true if train is full.
     * @return true if train is full.
     */
    public boolean isFull()
    {
        return _passengers == _seats;
    }

    /**
     * Returns true if the price for this train is cheaper 
     * than the other train.
     * @param other the other train to compare price with.
     * @return true if the price for this train is cheaper than the other train.
     */
    public boolean isCheaper(Train other)
    {
        return _price < other._price;
    }

    /**
     * Returns the total price for all passengers.
     * @return the total price for all passengers.
     */
    public int totalPrice()
    {
        return _price * _passengers;
    }

    /**
     * Returns true if the arrival time of this train is earlier than 
     * the arrival time of the other train.
     * other is not null.
     * @param other the other train to compare arrival time with.
     * @return true if the arrival time of this train is earlier than arrival time of the other train.
     */
    public boolean arrivesEarlier(Train other)
    {
        return this.getArrivalTime().before(other.getArrivalTime());
    }

    /**
     * Return a string representation of the train.
     * @return String representation of the train.
     */
    public String toString()
    {
        if(this.isFull())
        {
            return "Train to " + _destination + " departs at " + _departure + ". Train is full";
        }
        else
        {
            return "Train to " + _destination + " departs at " + _departure + ". Train is not full";
        }
    }
} // end of Train
