/*
 * filename: Trapezoid.java
 * Author: Roey Shtern
 * Date: 02/04/2020
*/
import java.util.Scanner;
/*
 * This class will print the area and the perimeter of trapezoid
 * from the user input, the left coordinates of the bases and their lengths
*/
public class Trapezoid
{
    public static void main ( String [] args)
    {
        double area = 0;
        double perimeter = 0;
        int height = 0;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Please enter the left point coordinates of the base followed by its length: ");
        
        int leftXFirstBase = scan.nextInt();
        int leftYFirstBase = scan.nextInt();
        int lengthFirstBase = scan.nextInt();
        
        System.out.println("Please enter the left point coordinates of the other base followed by its length: ");
        
        int leftXOtherBase = scan.nextInt();
        int leftYOtherBase = scan.nextInt();
        int lengthOtherBase = scan.nextInt();
        
        // calculate the height between two y coordinate of two parallel lines to x axis
        height = Math.abs(leftYFirstBase - leftYOtherBase);
        
        // use the formula to calculate area of trapezoid
        area = (height * (lengthFirstBase + lengthOtherBase)) / 2;
        
        // use the formula to calculate the distance between the two left coordinates of the bases
        double distanceLeftCoordinates = Math.sqrt(Math.pow((leftXFirstBase - leftXOtherBase), 2) + Math.pow((leftYFirstBase - leftYOtherBase), 2));
        
        /* 
         * use the formula to calculate the distance between the two right coordinates of the bases
         * right coordinates is the same y and x is the left x plus the length of the base
         */
        double distanceRightCoordinates = Math.sqrt(Math.pow(((leftXFirstBase + lengthFirstBase) - (leftXOtherBase + lengthOtherBase)), 2) + 
                                                    Math.pow((leftYFirstBase - leftYOtherBase), 2));
        
        // sum all the sides of the trapezoid
        perimeter = distanceLeftCoordinates + distanceRightCoordinates + lengthFirstBase + lengthOtherBase;

        System.out.println("The area of the trapezoid is " + area);
        System.out.println("The perimeter of the trapezoid is  " + perimeter);
    } // end of main
} // end of class Trapezoid