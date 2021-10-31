/**
 * This class will represent train station
 * @author: Roey Shtern
 * @version: 03/05/2020
 */
public class RailwayStation
{
    private Train [] _station;
    private int _noOfTrs;
    
    final private int MAX_TRAINS = 100;
    /**
     * Constructs a RailwayStation object. 
     * Construct a new rail station instance with details about trains.
     * station should be up to to MAX_TAINS trains.
     */
    public RailwayStation()
    {
        _station = new Train[MAX_TRAINS];
        _noOfTrs = 0;
    }
    /**
     * Add train to the station.
     *   The new train wont be added if the train is not 
     *   in the station and the station is not full or train to add is not null
     * @param trainToAdd train to add to the station 
     * @return true if the train has been added
     */
    public boolean addTrain(Train trainToAdd)
    {
        if(_noOfTrs == MAX_TRAINS || trainToAdd == null)
        {
            return false;
        }
        for(int i = 0; i < _noOfTrs; i++)
        {
            if(_station[i].equals(trainToAdd))
            {
                return false;
            }
        }
        _station[_noOfTrs++] = new Train(trainToAdd);
        return true;
    }
    /**
     * Remove train from the station.
     *   The train wont be removed if the train is not in the station or its null
     * @param trainToRemove train to remove from the station
     * @return true if the train has been removed 
     */
    public boolean removeTrain(Train trainToRemove)
    {       
        for(int i = 0; i < _noOfTrs && trainToRemove != null; i++)
        {
            if(_station[i].equals(trainToRemove))
            {
                if(i + 1 != _noOfTrs) // check if the train is the last train
                {
                    _station[i] = _station[_noOfTrs - 1];
                }
                _noOfTrs--;
                return true;
            }
        }
        return false;
    }
    /**
     * Return the first departure for the given place
     * @param place the place to search for the first departure
     * @return the first departure for the given place
     */
    public Time1 firstDepartureToDestination(String place)
    {
        Time1 firstDeparture = new Time1(23, 59);
        boolean isFound = false;
        if(place == null)
        {
            return null;
        }
        for(int i = 0 ; i < _noOfTrs ; i++)
        {
            if(_station[i].getDestination().equals(place) &&  _station[i].getDeparture().before(firstDeparture) || _station[i].getDeparture().equals(firstDeparture))
            {
                firstDeparture = _station[i].getDeparture();
                isFound = true;
            }
        }
        if(isFound)
        {
            return firstDeparture;
        }
        return null;
    }
    /**
     * Return how much full trains in the station
     * @return how much full trains in the station
     */
    public int howManyFullTrains()
    {
        int count = 0;
        for(int i = 0 ; i < _noOfTrs ; i++)
        {
            if(_station[i].isFull())
            {
                count++;
            }
        }
        return count;
    }
    /**
     * Return the most popular destination from all the trains in the station
     * @return most popular destination from all the trains in the station
     */
    public String mostPopularDestination()
    {
        String [] destinations = new String[MAX_TRAINS];
        int [] destinationCount = new int[MAX_TRAINS];
        
        if(_noOfTrs == 0)
        {
            return null;
        }
        
        int max = 0;
        int maxIndex = 0;
        for(int i = 0; i < _noOfTrs ; i++)
        {
            int currentCount = 0;
            for(int j = i + 1 ; j < _noOfTrs ; j++)
            {
                if(_station[i].getDestination() == _station[j].getDestination())
                {
                    currentCount++;
                }
            }
            if(currentCount > max)
            {
                max = currentCount;
                maxIndex = i;
            }
        }
        return _station[maxIndex].getDestination();
    }
    /**
     * Return the train that have the most expensive ticket from all the trains tickets in the station
     * @return train that have the most expensive ticket from all the trains tickets in the station
     */
    public Train mostExpensiveTicket()
    {
        int mostExpensiveIndex = -1;
        int mostExpensivePrice = -1;
        for(int i = 0 ; i < _noOfTrs ; i++)
        {
            if(_station[i].getPrice() > mostExpensivePrice)
            {
                mostExpensivePrice = _station[i].getPrice();
                mostExpensiveIndex = i;
            }
        }
        if(mostExpensiveIndex != -1)
        {
            return new Train(_station[mostExpensiveIndex]);
        }
        return null;
    }
    /**
     * Return the longest ride from all rides of the trains in the station
     * @return longest ride from all rides of the trains in the station
     */
    public Train longestTrain()
    {
        int longestRideIndex = -1;
        int longestRide = -1;
        for(int i = 0 ; i < _noOfTrs ; i++)
        {
            if(_station[i].getDuration() > longestRide)
            {
                longestRide = _station[i].getDuration();
                longestRideIndex = i;
            }
        }
        if(longestRideIndex != -1)
        {
            return new Train(_station[longestRideIndex]);
        }
        return null;
    }
    /**
     * Returns a string representation of the train station
     * @return string representation of the train station
     */
    public String toString()
    {
        String trainsString = "";
        if(_noOfTrs > 0)
        {
            trainsString = "The trains today are:\n";
        }
        else
        {
            trainsString = "There are no trains today.";
        }
        
        for(int i = 0 ; i < _noOfTrs ; i++)
        {
            trainsString += _station[i].toString() + "\n";
        }
        return trainsString;
    }
}