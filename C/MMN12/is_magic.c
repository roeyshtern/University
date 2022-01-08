#include "is_magic.h"

#define SUCCESS 1
#define FAILURE 0


/*
 this function will check if the given mat is an a magic square - all the row/column/diagonal sum is equal
 will check first for diagonals
 then check for row and column and will valudate all the input is valid(bigger then 0 and less then N*N)
 and check if there is no value that repeat itself
 return if the magic square is a basic magic square or not
*/
int is_magic(int mat[][N])
{
    int values_to_check[N*N] = {0};
    int i=0;
    int j=0;
    /* sumd1 and sumd2 are the sum of the two diagonals*/
    int sumd1 = 0, sumd2=0;
    for (i = 0; i < N; i++)
    {
        /* (i, i) is the diagonal from top-left -> bottom-right*/
        /* (i, n - i - 1) is the diagonal from top-right -> bottom-left*/
        sumd1 += mat[i][i];
        sumd2 += mat[i][N-1-i];
    }
    /* if the two diagonal sums are unequal then it is not a magic square*/
    if(sumd1!=sumd2)
    {
        return FAILURE;
    }
    /* For sums of Rows*/
    for (i = 0; i < N; i++) {
         
        int rowSum = 0, colSum = 0;   
        for (j = 0; j < N; j++)
        {
            if(mat[i][j] > N*N || mat[i][j] < 1)
            {
                return FAILURE;
            }
            if(values_to_check[mat[i][j]] == SUCCESS)
            {
                return FAILURE;
            }
            else
            {
                values_to_check[mat[i][j]] = SUCCESS;
            }
            rowSum += mat[i][j];
            colSum += mat[j][i];
        }
        if (rowSum != colSum || colSum != sumd1)
        {
            return FAILURE;
        }
    }
   return SUCCESS;
}