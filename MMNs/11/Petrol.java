/*
This class will prints calculation of the conevertion between
miles per gallon to liters per 100 km
*/
import java.util.Scanner;
public class Petrol
{
    /*
     * this function will get miles for one gallon
     * convert it to liters per 100 km
     * print the result
    */
    public static void main ( String [] args)
    {
        final int MAX = 100;
        final int NUMBER_OF_KILOMETERS = 100;
        final double ONE_MILE_TO_KILOMETERS = 1.609;
        final double ONE_GALLON_TO_LITERS = 3.785;

        Scanner scan = new Scanner(System.in);        
        
        System.out.println("Please enter the car's petrol consumption " +
                           "measured in miles/gallon:");
        
        // Gets the miles per one gallon from user
        double milesPerGallon = scan.nextDouble();
        
        double kilomteresPerGallon = milesPerGallon * ONE_MILE_TO_KILOMETERS;
        
        // Calculate how much gallons this car needs to drive 100 km
        double howMuchGallonPerOneHundredKilometer = NUMBER_OF_KILOMETERS / kilomteresPerGallon;
        
        // Calculate how much liters this car needs to drive 100 km
        double gallonsToLiteres = ONE_GALLON_TO_LITERS * howMuchGallonPerOneHundredKilometer;
        
        // Round the answer to two digit after the dot
        double litersPerOneHundredKilometer = Math.round(gallonsToLiteres * MAX) / (MAX * 1.0);
        
        // Output
        System.out.println("The car's petrol consumption converted to liters/100km is:\n" +
                           litersPerOneHundredKilometer);
    } // end of method main
} // end of class main