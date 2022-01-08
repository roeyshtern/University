/*
    This program will verify if the input she get until EOF is balanced by each row with the correct syntax of ***********sograim*********
    and with full text by curl braclers.
*/

#include <stdio.h>
#include <ctype.h>

#define TRUE 1
#define FALSE 0
#define NON_WHITE_SPACE 0

/* This function will check the current row for its syntax*/
int check_row(char row[], int* nun_of_curl_all_text, int* is_comment, int num_of_rows);
/* This function will check the current char in row to check validation*/
int check_current_char(char current_char, char stack[], int* current_top, int* square, int* curl, int* circle, int* num_of_curl_all_text);
/* This function will check if the current char is start or end of a string in row*/
int check_is_in_string(char current_char, int* is_comment, int is_in_string);
/* This function will check if the current two chars is start or end of a comment*/
void check_is_comment(char row[], int* is_comment, int is_in_string, int i);
/* This function will calculate the final result according to different parameters*/
int check_final_results(int curl, int square, int circle, int num_of_rows, int i, int count_white_space, int is_balanced);

int main()
{
    int is_text_balanced = TRUE;
    int num_of_rows = 1;
    int num_of_curl_all_text = 0;
    char input_row[100] = {0};
    int is_comment = FALSE;
    printf("Please enter chars, to exit enter EOF\n");
    /* get each row from stdin until EOF*/
    while(fgets(input_row, sizeof(input_row), stdin) != NULL)
    {
        printf("row: %s -> ", input_row);
        /*Do an and with the cureent is_text_balanced and the result of the check_row function
            so if once in the program lifetime the text is found as non balanced it will save this state till the end*/
        is_text_balanced = check_row(input_row, &num_of_curl_all_text, &is_comment, num_of_rows) && is_text_balanced;
        num_of_rows++;
    }
    if(num_of_curl_all_text != 0)
    {
        is_text_balanced = FALSE;
    }
    printf("Is text balanced: %d\n", is_text_balanced);
    return 0;
}
/*
 This function will check the current row from input and return if it syntax is correct
 input: 
    - row = an array of chars of current row
    - num_of_curl_all_text - pointer to the number of curl counter for all text
    - num_of_rows - the number of rows processed to print the current row proccessed
 output:
    - FALSE or TRUE
*/
int check_row(char row[], int* num_of_curl_all_text, int* is_comment, int num_of_rows)
{
    char stack[100] = {0};
    int count_white_space = 0;
    int current_top = -1;
    int i = 0;
    int curl = 0;
    int square = 0;
    int circle = 0;
    int is_in_string = FALSE;
    int is_balanced = TRUE;

    /*go over the current row*/
    for (i = 0; row[i] != '\0';i++)
    {
        /*check if the current char is not inside a string ot comment in row*/
        if(is_in_string == FALSE && *is_comment == FALSE)
        {
            /* Do an and with the current is_balanced and the result of check_current_char so if it once failed it will keep
                this current state until the end of the function*/
            is_balanced = check_current_char(row[i], stack, &current_top,&square, &curl, &circle, num_of_curl_all_text) && is_balanced;
        }
        /* check if the current char is white space*/
        if(isspace(row[i]) != NON_WHITE_SPACE)
        {
            count_white_space++;
        }
        is_in_string = check_is_in_string(row[i], is_comment, is_in_string);
        check_is_comment(row, is_comment, is_in_string, i);
    }
    return check_final_results(curl, square, circle, num_of_rows, i, count_white_space, is_balanced);
}

/*
 This function will check the current char of the row and update the relevant counters and stack
 and will return the current status of the row's syntax correctnes
 input:
    - current_char - the current char to check
    - stack - array of chars that represnt the stack of the row's bracelets
    - current_top - pointer to the current top index of the stack
    - square - pointer to the square counter of current row
    - curl - pointer to the curl counter of current row
    - circle - pointer to the circle counter of current row
    - num_of_curl_all_text - pointer to the number of curl counter for all text
 output:
    return FALSE or TRUE based on the current check and row's status
*/
int check_current_char(char current_char, char stack[], int* current_top, int* square, int* curl, int* circle, int* num_of_curl_all_text)
{
    int is_balanced = TRUE;
    switch (current_char)
    {
        case '[':
            /*insert to the row stack the current bracelet and increase the counter*/
            stack[++(*current_top)] = '[';
            (*square)++;
            break;
        case ']':
        /*check if the last barcelet is the opening of the current closing, if not change the current status of row*/
            if(stack[*current_top] != '[')
            {
                is_balanced = FALSE;
            }
            else
            {
                (*current_top)--;
            }
            (*square)--;
            break;
        case '{':
            stack[++(*current_top)] = '{';
            (*curl)++;
            /*increase counter for all text*/
            (*num_of_curl_all_text)++;
            break;
        case '}':
            if(stack[*current_top] != '{')
            {
                is_balanced = FALSE;
            }
            else
            {
                (*current_top)--;
            }
            (*curl)--;
            (*num_of_curl_all_text)--;
            break;
        case '(':
            stack[++(*current_top)] = '(';
            (*circle)++;
            break;
        case ')':
            if(stack[*current_top] != '(')
            {
                is_balanced = FALSE;
            }
            else
            {
                (*current_top)--;
            }
            (*circle)--;
            break;
        default:
            break;
    }
    return is_balanced;
}
/*
 This function will check if the current char is represent a start or an end of string inside the current row,
 the result depend if the current char is inside of a comment
 input:
    - current_char - the current char to check if its a start or an end of a string
    - is_comment - pointer to the current status of the row processing, if the current char is inside comment
    - is_in_string - the current status of the row if it inside of a string
 output:
    return the new is_in_string status - TRUW or FALSE
*/
int check_is_in_string(char current_char, int* is_comment, int is_in_string)
{
    /*check if current char is end or start of string and not in comment*/
    if(current_char == '\"' && *is_comment == FALSE)
    {
        return is_in_string == TRUE ? FALSE : TRUE;
    }
    return is_in_string;
}
/*
 This function check if the current next two chars is a start or an end of a comment,
 the result depends if the current chars in is in string inside the row
 input:
    - row - array of chars of the current row
    - is_comment - pointer to the current status of the row processing, if the current char is inside comment
    - is_in_string - the current status of the row if it inside of a string
    - i - the current index of row processing
*/
void check_is_comment(char row[], int* is_comment, int is_in_string, int i)
{
    /*check if current two chars is end or start of comment and not in string*/
    if(is_in_string == FALSE && *is_comment == TRUE)
    {
        if(row[i+1] != '\0')
        {
            if(row[i] == '*' && row[i+1] == '/')
            {
                *is_comment = FALSE;
            }
        }
    }
    else if(*is_comment == FALSE)
    {
        if(row[i+1] != '\0')
        {
            if(row[i] == '/' && row[i+1] == '*')
            {
                *is_comment = TRUE;
            }
        }
    }
}
/*
 This function will check and calculate the final result with the parameters she gets, and return the final result status
 input:
    - curl - the curl counter value
    - square - the square counter value
    - circle - the circle counter value
    - num_of_rows - the current numbers of row received to print the current row status
    - i - the current index of the row/ will used an an string length
    - count_white_space - the count value of white spaces in current row
    - is_balanced - the current balanced status after processing all the chars in row
 output:
    return the final result based on the parameters, TRUE or FALSE
*/
int check_final_results(int curl, int square, int circle, int num_of_rows, int i, int count_white_space, int is_balanced)
{
    /* check if one of the counter is not reset to zero*/
    if(curl!= 0 || square != 0 || circle != 0)
    {
        printf("row %d is not correct!\n", num_of_rows);
        /*check if the curl counter is one and the other chars is white spaces*/
        if(i-count_white_space== 1 && curl != 0)
        {
            return TRUE;
        }
        return FALSE;
    }
    else
    {
        printf("row %d is correct!\n", num_of_rows);
        /*the counter is good but this line will make sure the order of the barcelets is good*/
        return is_balanced == TRUE ? TRUE : FALSE;
    }
}