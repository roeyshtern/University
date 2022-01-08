/*
 This program will get a mat and call the is_magic function to
*/
#include <stdio.h>
#include "is_magic.h"

#define SUCCESS 1
#define FAILURE 0

/*
this function will get input from stdin until it get exactly N*N values
*/
int get_mat(int mat[][N]);
/*
for a given mat will print the mat
*/
void print_mat(int mat[][N]);

int main()
{
    int mat[N][N] = {0};
    int result = FAILURE;
    int is_magic_square = FAILURE;
    
    result = get_mat(mat);
    if(result != SUCCESS)
    {
        printf("Cannot get mat need to exit now!\n");
        return 0;
    }
    print_mat(mat);

    is_magic_square = is_magic(mat);
    if(is_magic_square == SUCCESS)
    {
        printf("A magic square!!!\n");
    }
    else
    {
        printf("Not a magic square\n");
    }
    return 0;
}
/*
this function will get input from stdin until it get exactly N*N values
if there is an eof or not a valid int it will exit the function or if there is more then N*N values
put the values in the given mat and will return SUCCESS or FAILURE if the insertion is completed successfully or not
*/
int get_mat(int mat[][N])
{
    int i = 0;
    int input = 0;
    int result = 0;
    
    printf("Please enter %d numbers\n", N*N);
    
    for (i = 0; i < N*N; i++)
    {
        result = scanf("%d", &input);
        if(result == 1)
        {
            mat[i/N][i%N] = input;
        }
        else
        {
            printf("You enter an unvalid value\n");
            return FAILURE;
        }
    } 
    /*check if there is exactly N*N values and nothing else*/
    result = scanf("%d", &input);
    if(result != EOF)
    {
        printf("You enter more than allowed!\n");
        return FAILURE;
    }
    return SUCCESS;
}
void print_mat(int mat[][N])
{
    int i = 0;
    int j = 0;
    for (i = 0; i < N; i++)
    {
        for (j = 0; j < N; j++)
        {
            printf("%d ", mat[i][j]);
        }
        printf("\n");
    }
    
}
