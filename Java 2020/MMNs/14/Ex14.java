/**
 * Ex14 - implements all the answer for mmn 14 
 * @Author : Roey Shtern
 * @Version : 20/6/2020
 */
public class Ex14
{
    // final variables
    private static final int SUM_DEFAULT_VALUE = 0;
    private static final int START_INDEX = 0;
    private static final int NOT_FOUND_VALUE = -1;
    private static final int DEFAULT_NUM_OF_MOVES = -1;
    private static final int EVIL_CELL_VALUE = -1;
    private static final int DEFAULT_VISITED_CELL_VALUE = -2;
    /**
     * Prints the path of the game according to tamar and amir choices
     * Time complexity = O(isOddBigger()) + O(PrintPath) = O(n) + O(n) = O(2n) = O(n)
     * Space complexity = O(isOddBigger()) + O(PrintPath) = O(1) + O(1) = O(1)
     * @param arr - array of coins for the game. must be even length.
     */
    public static void win(int[] arr)
    {
        int isOddBigger = isOddBigger(arr, SUM_DEFAULT_VALUE, SUM_DEFAULT_VALUE, START_INDEX);
        printPath(arr, isOddBigger, START_INDEX, arr.length - 1, SUM_DEFAULT_VALUE, SUM_DEFAULT_VALUE);
    }
    /**
     * Calculate the sum of coins in the even indexes and odd indexes to know which indexes should amir pick to win
     * work recursivley to find the sums, go through the array one time
     * Time complexity = 2*n + 1 = O(n)
     * Space complexity = O(1) - final, no allocation using variables;
     * @param arr - array of coins for the game. must be even length.
     * @param evenSum - the current sum of even indexes, carry through all recursive calls when running on the array 
     * @param oddSum - the current sum of odd indexes, carry through all recursive calls when running on the array
     * @param i - the current index of the array for checking the value in that array index
     * @return 1 if odd is bigger and 0 if if even is bigger or equal
     */
    public static int isOddBigger(int arr[], int evenSum, int oddSum, int i)
    {
        if(i == arr.length)
        {
            return evenSum < oddSum ? 1 : 0;
        }
        // add to the current sums the value according to odd and even index(start from 0 then 0 for even and 1 for odd)
        // and move i = i+2 to calc the next pair
        return isOddBigger(arr, evenSum + arr[i], oddSum + arr[i + 1], i + 2);
    }
    /**
     * This function will fo through the arr of coins once and calculate the move of amir and tamar according to tamar logic and amir logic, 
     * pick the values in the index where the sum of that indexes is bigger(even or odd indexes)
     * according to the flag isOddBigger that determinate which indexes to choose and finally print their paths int the game and the final score
     * Time comlexity = 8*n + O(printMove) + 3 = 10n + 3 = O(n)
     * Space complexity = O(1) - final, no allocation using variables;
     * @param arr - array of coins for the game. must be even length.
     * @param isOddBigger - the flag that determinate which indexes amir should take to win
     * @param low - the current lowest index in the game
     * @param high - the current highest index in the game
     * @param amirSum - the current sum for amir in the game
     * @param tamarSum - the current sum for tamar in the game
     */
    public static void printPath(int arr[], int isOddBigger, int low, int high, int amirSum, int tamarSum)
    {
        if(low < high)
        {
            // if odd is bigger(1) and the low index is odd then took him, and leave tamar only even indexes to choose
            // for example arr.length = 4 low == 0 and high == 3 (took high, tamar turn)-> low == 0 and high == 2
            // and the other way around for even values - isOddBigger(0) = choose only even and leave tamar odd indexes
            // for example arr.length = 4 low == 0 and high == 3 (took low, tamar turn)-> low == 1 and high == 3
            if(low%2 == isOddBigger)
            {
                printMove(arr, "Amir", low);
                amirSum += arr[low];
                low++;
            }
            else
            {
                printMove(arr, "Amir", high);
                amirSum += arr[high];
                high--;
            }
            if(arr[low] > arr[high])
            {
                printMove(arr, "Tamar", low);
                tamarSum += arr[low];
                low++;
            }
            else
            {
                printMove(arr, "Tamar", high);
                tamarSum += arr[high];
                high--;
            }
            printPath(arr, isOddBigger, low, high, amirSum, tamarSum);
        }
        else
        {
            System.out.println("Final Score:");
            System.out.println("Amir total " + amirSum);
            System.out.println("Tamar total " + tamarSum);
        }
        
    }
    /**
     * This function will print which player took what value according to what she get
     * Time complexity - O(1)
     * Space complexity - O(1) - final, no allocation using variables;
     * @param arr - array of coins for the game. must be even length.
     * @param player - the name of the player
     * @param i - the index of the value that the player took
     */
    public static void printMove(int[] arr, String player, int i)
    {
        System.out.println(player + " took " + arr[i]);
    }
    /**
     * This function will find and print the triple values that represent the highest product
     * this function will go over and find the three max values and the two lowest values in the array
     * because the highest product = product of the three max values or the product of the two lowest and the highest valueas
     * Time complexity = O(findMaxWithout)*3 + O(findMinWithout)*2 + 10n = O(n)*5 + 10n = O(n) 
     * Space complexity = O(findMaxWithout)*5 + O(findTriplet without findmaxWithout) =  = O(1) - final, no allocation using variables
     * @param arr - the array of numbers to search in
     * @return the product of the triple values that represent the highest product
     */
    public static int findTriplet(int[] arr)
    {
        int firstMax = findMaxWithout(arr, START_INDEX, NOT_FOUND_VALUE, NOT_FOUND_VALUE, START_INDEX);
        int firstMin = findMinWithout(arr, START_INDEX, NOT_FOUND_VALUE, START_INDEX);
        int secondMax = findMaxWithout(arr, firstMin, firstMax, NOT_FOUND_VALUE, START_INDEX);
        int thirdMax = findMaxWithout(arr, firstMin, firstMax, secondMax, START_INDEX);
        int secondMin = findMinWithout(arr, firstMax, firstMin, START_INDEX);
        firstMax = arr[firstMax];
        secondMax = arr[secondMax];
        thirdMax = arr[thirdMax];
        firstMin = arr[firstMin];
        secondMin = arr[secondMin];
        int firstSum =  firstMax * secondMax * thirdMax;
        int secondSum = firstMin * secondMin * firstMax;
        if(firstSum > secondSum)
        {
            System.out.println(firstMax + " " + secondMax + " " + thirdMax);
            return firstSum;
        }
        else
        {
            System.out.println(firstMax + " " + firstMin + " " + secondMin);
            return secondSum;
        }
    }
    /**
     * This function will find the max value in the array and if specified(maxWithoutIndex or/and secondMaxWithoutIndex) also the second and the third max
     * this function go through the array and search for the index of the highest value according to the indexes to ignore from (maxWithoutIndex or/and secondMaxWithoutIndex)
     * Time complexity = 4*n + 1 = O(n)
     * Space Complexity = O(1) - final, no allocation using variables
     * @param arr - the array of the values to search in
     * @param maxIndex - the current index of the max value in the array
     * @param maxWithoutIndex - the index of the max value to ignore from when searching the second or third max values
     * @param secondMaxWithoutIndex - the index of the second max value to ignore from when searching the  third max values
     * @param i - hte current index to search on int the array 
     * @return the index of the highest value according to the indexes to ignore from (maxWithoutIndex or/and secondMaxWithoutIndex)
     */
    public static int findMaxWithout(int[] arr, int maxIndex, int maxWithoutIndex, int secondMaxWithoutIndex, int i)
    {
        if(i < arr.length)
        {
            if(arr[i] > arr[maxIndex] && (maxWithoutIndex == NOT_FOUND_VALUE || arr[i] <= arr[maxWithoutIndex]) && i != maxWithoutIndex && i != secondMaxWithoutIndex)
            {
                maxIndex = i;
            }
            return findMaxWithout(arr, maxIndex, maxWithoutIndex, secondMaxWithoutIndex, ++i);
        }
        else
        {
            return maxIndex;
        }
    }
    /**
     * This function will find the min value in the array and if specified minWithoutIndex also the second min
     * this function go through the array and search for the index of the lowest value according to the indexes to ignore from minWithoutIndex
     * Time complexity = 4*n + 1 = O(n)
     * Space Complexity = O(1) - final, no allocation using variables
     * @param arr - the array of the values to search in
     * @param maxIndex - the current index of the max value in the array
     * @param minWithoutIndex - the index of the min value to ignore from when searching the second min values
     * @param i - hte current index to search on int the array 
     * @return the index of the lowest value according to the indexes to ignore from minWithoutIndex
     */
    public static int findMinWithout(int[] arr, int minIndex, int minWithoutIndex, int i)
    {
        if(i < arr.length)
        {
            if(arr[i] < arr[minIndex] && (minWithoutIndex == NOT_FOUND_VALUE || arr[i] >= arr[minWithoutIndex]) && i != minWithoutIndex)
            {
                minIndex = i;
            }
            return findMinWithout(arr, minIndex, minWithoutIndex, ++i);
        }
        return minIndex;
    }
    /**
     * This function will find the number of occurrences of the pattern string, not have to be continuous in the str string
     * @param str - the string to search in the pattern string
     * @param pattern - the pattern to search for in the str
     * @return the number of occurrences of the pattern string, not have to be continuous in the str string
     */
    public static int count(String str, String pattern)
    {
        return recCount(str, pattern, 0, 0);
    }
    /**
     * This function will find go through all index of the str and search in the rest of the str if the other chars of pattern string shows
     * and return the number of occurrences of the pattern string, not have to be continuous in the str string
     * @param str - the string to search in the pattern string
     * @param pattern - the pattern to search for in the str
     * @param strIndex - the current index in str to search from
     * @paran patternIndex - the current index in pattern to check according to
     * @return the number of occurrences of the pattern string, not have to be continuous in the str string
     */
    public static int recCount(String str, String pattern, int strIndex, int patternIndex)
    {
        int sum = 0;
        if(patternIndex == pattern.length())
        {
            return 1;
        }
        if(strIndex == str.length())
        {
            return 0;
        }
        if(str.charAt(strIndex) == pattern.charAt(patternIndex))
        {
            // if cound match to one of pattern chars then advance in one and continue to search
            sum += recCount(str, pattern, strIndex + 1, patternIndex + 1);
        }
        strIndex++;
        sum += recCount(str, pattern, strIndex, patternIndex);
        return sum;
    }
    /**
     * This function will find the lowest steps path to reach the evil cell from the prince position in the arr(i, j)
     * it will go through drm and check every path possibile from the cell of the prince to reach to the evil cell
     * it will find all the paths by check all possible move and see if you can reach to the evil cell, it will use recursive backtracing by 
     * test every way path possible and if cannot reach from that direction will try another direction
     * @param drm - the map of the game drm(Digital Roof Map)
     * @param i - the i index of the prince current cell
     * @param j - the j index of the prince current cell
     * @return the lowest steps path count to reach the evil cell from the prince position in the arr(i, j)
     */
    public static int prince(int[][] drm, int i, int j)
    {
        int eastSum = DEFAULT_NUM_OF_MOVES;
        int southSum = DEFAULT_NUM_OF_MOVES;
        int westSum = DEFAULT_NUM_OF_MOVES;
        int northSum = DEFAULT_NUM_OF_MOVES;
        int temp = 0;
        if(drm[i][j] == EVIL_CELL_VALUE)
        {
            return 1;
        }
        // save the current value of the cell so that can be changed and restore to this value
        temp = drm[i][j];
        
        //east move
        if(isMovable(drm, i, j, i, j + 1))
        {
            // change the cell the prince step to default value to recognise it in the rest of the run of the function
            drm[i][j] = DEFAULT_VISITED_CELL_VALUE; 
            eastSum = prince(drm, i, j + 1);
            drm[i][j] = temp;
        }
        //south move
        if(isMovable(drm, i, j, i + 1, j))
        {
            drm[i][j] = DEFAULT_VISITED_CELL_VALUE;
            southSum = prince(drm, i + 1, j);
            drm[i][j] = temp;
        }
        //west move
        if(isMovable(drm, i, j, i, j - 1))
        {
            drm[i][j] = DEFAULT_VISITED_CELL_VALUE;
            westSum = prince(drm, i, j - 1);
            drm[i][j] = temp;
        }
        //north move
        if(isMovable(drm, i, j, i - 1, j))
        {
            drm[i][j] = DEFAULT_VISITED_CELL_VALUE;
            northSum = prince(drm, i - 1, j);
            drm[i][j] = temp;
        }
        // return the min step count path  according to the paths from all the possible directions
        return findMin(eastSum, southSum, westSum, northSum) + 1;
    }
    /**
     * This function will check if the move the prince tring to do is valid
     * @param drm - the map of the game drm(Digital Roof Map)
     * @param i - the i index of the prince current cell
     * @param j - the j index of the prince current cell
     * @param iToMove - the i index of the prince next cell
     * @param jToMove - the j index of the prince next cell
     * @return if the move the prince tring to do is valid
     */
    public static boolean isMovable(int drm[][], int i, int j, int iToMove, int jToMove)
    {
        // if in the bords limits
        if(drm.length > iToMove && iToMove >= START_INDEX && drm[0].length > jToMove && jToMove >= START_INDEX)
        {
            // if the move is valid
            if(drm[iToMove][jToMove] == EVIL_CELL_VALUE ||
            (drm[iToMove][jToMove]!= DEFAULT_VISITED_CELL_VALUE &&
            (drm[i][j] + 1 == drm[iToMove][jToMove] ||
            drm[i][j] - 1 == drm[iToMove][jToMove] ||
            drm[i][j] - 2 == drm[iToMove][jToMove] ||
            drm[i][j] == drm[iToMove][jToMove])))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * This function will find the min step count path from all the given sums of all the direction and ignore the paths the are not valid(-1)
     * @param eastSum - the step count of the path from the east direction
     * @param southSum - the step count of the path from the south direction 
     * @param westSum - the step count of the path from the west direction
     * @param northSum - the step count of the path from the north direction
     * @return the min step count path from all the given sums of all the direction and ignore the paths the are not valid(-1)
     */
    public static int findMin(int eastSum, int southSum, int westSum, int northSum)
    {
        int max = 0;
        max = Math.max(Math.max(eastSum, southSum), Math.max(westSum, northSum));
        
        if(eastSum > DEFAULT_NUM_OF_MOVES && eastSum < max)
        {
            max = eastSum;
        }
        if(southSum > DEFAULT_NUM_OF_MOVES && southSum < max)
        {
            max = southSum;
        }
        if(westSum > DEFAULT_NUM_OF_MOVES && westSum < max)
        {
            max = westSum;
        }
        if(northSum > DEFAULT_NUM_OF_MOVES && northSum < max)
        {
            max = northSum;
        }
        // return the max or if all the paths are not valid return DEFAULT_NUM_OF_MOVES -1 so the next move will add +1 current move and the min path
        // will stay unvalid in the value of DEFAULT_NUM_OF_MOVES
        return max == DEFAULT_NUM_OF_MOVES ? DEFAULT_NUM_OF_MOVES - 1 : max;
    }    
}