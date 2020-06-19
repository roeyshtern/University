public class Ex14
{
    public static void main(String [] args)
    {
        int[] arr = new int[]{-8, -10, 4, 2, 1, -2, 3, 10, 18};
        int[][] drm = new int[][]{
            new int[]{2, 0, 1, 2, 3},
            new int[]{2, 3, 5, 5, 4},
            new int[]{8, -1, 6, 8, 7},
            new int[]{3, 4, 7, 2, 4},
            new int[]{2, 4, 3, 1, 2}
        };
        int[] arr1 = new int[]{1, 1, 1, 9}; // only one at the side
        System.out.println("isOddBIgger: " + isOddBigger(arr1, 0, 0, 0));
        //win(arr); //must be even n items
        //findTriplet(arr);
        //System.out.println("count: " + count("subsequence", "sue"));
        //System.out.println(String.format("drm.length=%d, drm[0].length=%d", drm.length, drm[0].length));
        //int min = findMin(-1,-1,0,-1);
        //System.out.println("min=" + min);
        //System.out.println("min route: " + prince(drm, 0, 0));
    }
    public static void win(int[] arr)
    {
        int isOddBigger = isOddBigger(arr, 0, 0, 0);
        printPath(arr, isOddBigger, 0, arr.length - 1, 0, 0);
    }
    public static int isOddBigger(int arr[], int evenSum, int oddSum, int i)
    {
        if(i == arr.length)
        {
            return evenSum < oddSum ? 1 : 0;
        }
        return isOddBigger(arr, evenSum + arr[i], oddSum + arr[i + 1], i + 2);
    }
    public static void printPath(int arr[], int isOddBigger, int low, int high, int amirSum, int tamarSum)
    {
        if(low < high)
        {
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
            System.out.println("Final score:");
            System.out.println("Amir total " + amirSum);
            System.out.println("Tamar total " + tamarSum);
        }
        
    }
    public static void printMove(int[] arr, String player, int i)
    {
        System.out.println(player + " took " + arr[i]);
    }
    public static int findTriplet(int[] arr)
    {
        int firstMax = findMaxWithout(arr, 0, -1, 0);
        //System.out.println("firstMax: " + firstMax);
        int firstMin = findMinWithout(arr, 0, -1, 0);
        //System.out.println("firstMin: " + firstMin);
        int secondMax = findMaxWithout(arr, firstMin, firstMax, 0);
        //System.out.println("secondMax: " + secondMax);
        int thirdMax = findMaxWithout(arr, firstMin, secondMax, 0);
        //System.out.println("thirdMax: " + thirdMax);
        int secondMin = findMinWithout(arr, firstMax, firstMin, 0);
        //System.out.println("secondMin: " + secondMin);
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
            System.out.println(firstMax + "*" + secondMax + "*" + thirdMax + "=" + firstSum);
            return firstSum;
        }
        else
        {
            System.out.println(firstMax + " " + firstMin + " " + secondMin);
            System.out.println(firstMax + "*" + firstMin + "*" + secondMin + "=" + secondSum);
            return secondSum;
        }
    }
    public static int findMaxWithout(int[] arr, int maxIndex, int maxWithoutIndex, int i)
    {
        if(i < arr.length)
        {
            //System.out.println("arr[i] > arr[maxIndex]: " + arr[i] + " " + arr[maxIndex]);
            //System.out.println("maxWithoutIndex= " + maxWithoutIndex);
            //System.out.println("i: " + i);
            if(arr[i] > arr[maxIndex] && (maxWithoutIndex == -1 || arr[i] <= arr[maxWithoutIndex]) && i != maxWithoutIndex)
            {
                maxIndex = i;
            }
            return findMaxWithout(arr, maxIndex, maxWithoutIndex, ++i);
        }
        else
        {
            return maxIndex;
        }
    }
    public static int findMinWithout(int[] arr, int minIndex, int minWithoutIndex, int i)
    {
        if(i < arr.length)
        {
            //System.out.println("arr[i] < arr[minIndex]: " + arr[i] + " " + arr[minIndex]);
            //System.out.println("minWithoutIndex= " + minWithoutIndex);
            //System.out.println("i: " + i);
            if(arr[i] < arr[minIndex] && (minWithoutIndex == -1 || arr[i] >= arr[minWithoutIndex]) && i != minWithoutIndex)
            {
                minIndex = i;
            }
            return findMinWithout(arr, minIndex, minWithoutIndex, ++i);
        }
        return minIndex;
    }
    public static int count(String str, String pattern)
    {
        return recCount(str, pattern, 0, 0);
    }
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
            sum += recCount(str, pattern, strIndex + 1, patternIndex + 1);
        }
        strIndex++;
        sum += recCount(str, pattern, strIndex, patternIndex);
        return sum;
    }
    public static int prince(int[][] drm, int i, int j)
    {
        int eastSum = -1;
        int southSum = -1;
        int westSum = -1;
        int northSum = -1;
        int temp = 0;
        System.out.println("Current cell i,j = " + i + "," + j + " drm[i][j]=" + drm[i][j]);
        printArr(drm);
        if(drm[i][j] == -1)
        {
            System.out.println("\tReached to evil! i,j = " + i + "," + j);
            return 1;
        }
        temp = drm[i][j];
        
        //east move
        if(isMovable(drm, i, j, i, j + 1))
        {
            System.out.println(String.format("\tCan move east! i,j = %d, %d next i=%d, next j=%d", i, j, i, j+1));
            drm[i][j] = -2;
            eastSum = prince(drm, i, j + 1);
            drm[i][j] = temp;
        }
        //south move
        if(isMovable(drm, i, j, i + 1, j))
        {
            System.out.println(String.format("\tCan move south! i,j = %d, %d next i=%d, next j=%d", i, j, i+1, j));
            drm[i][j] = -2;
            southSum = prince(drm, i + 1, j);
            drm[i][j] = temp;
        }
        //west move
        if(isMovable(drm, i, j, i, j - 1))
        {
            System.out.println(String.format("\tCan move west! i,j = %d, %d next i=%d, next j=%d", i, j, i, j-1));
            drm[i][j] = -2;
            westSum = prince(drm, i, j - 1);
            drm[i][j] = temp;
        }
        //north move
        if(isMovable(drm, i, j, i - 1, j))
        {
            System.out.println(String.format("\tCan move north! i,j = %d, %d next i=%d, next j=%d", i, j, i-1, j));
            drm[i][j] = -2;
            northSum = prince(drm, i - 1, j);
            drm[i][j] = temp;
        }
        System.out.println(String.format("\teastSum=%d, southSum=%d, westSum=%d, northSum=%d", eastSum, southSum, westSum, northSum));
        int min = findMin(eastSum, southSum, westSum, northSum) + 1;
        System.out.println("\tmin is= " + min);
        return min;
    }
    public static boolean isMovable(int drm[][], int i, int j, int iToMove, int jToMove)
    {
        if(drm.length > iToMove && iToMove >= 0 && drm[0].length > jToMove && jToMove >= 0)
        {
            System.out.println(String.format("\tIn the borders current i=%d, j=%d, nexti=%d, nextj=%d", i, j, iToMove, jToMove));
            if(drm[iToMove][jToMove] == -1 ||
            (drm[iToMove][jToMove]!= -2 &&
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
    public static int findMin(int eastSum, int southSum, int westSum, int northSum)
    {
        int max = 0;
        max = Math.max(Math.max(eastSum, southSum), Math.max(westSum, northSum));
        
        if(eastSum > -1 && eastSum < max)
        {
            max = eastSum;
        }
        if(southSum > -1 && southSum < max)
        {
            max = southSum;
        }
        if(westSum > -1 && westSum < max)
        {
            max = westSum;
        }
        if(northSum > -1 && northSum < max)
        {
            max = northSum;
        }
        
        return max == -1 ? -2 : max;
    }
    public static void printArr(int[][] drm)
    {
        for(int i = 0; i < drm.length; i++)
        {
            for(int j = 0 ; j < drm[i].length; j++)
            {
                System.out.print(drm[i][j] + ", ");
            }
            System.out.println();
        }
    }
    
}