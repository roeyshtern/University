/*
This program will get from user string. max 80 chars, and then the program will print the contracted
string, any alphaNumeric דequence of more than 2 chars in ascending order will be shortend, for ex abc -> a-c
*/

#include <stdio.h>
#include <string.h>

/* Assuming the user won't enter a string longer then 80 chars*/
#define USER_INPUT_MAX_SIZE 81
#define CONTRACT_STR_MAX_SIZE 81
#define ASCII_OF_SMALL_A 97
#define ASCII_OF_CAP_A 65
#define ASCII_OF_ZERO 48
#define NUMBER_OF_ENGLISH_CHARS 26
#define TRUE 1
#define FALSE 0

/* this function will contract the given s1 string and put the contracted string in s2 */
void contract(char s1[], char s2[]);
/* This function will return TRUE if the char is alpha numeric and FALSE if not*/
int is_alpha_numeric(char c);

int main()
{
    /* Buffers for the user and output strings*/
    char user_input[USER_INPUT_MAX_SIZE] = {0};
    char contract_string[CONTRACT_STR_MAX_SIZE] = {0};

    printf("Please enter string you want to contract: ");
    
    fgets(user_input, USER_INPUT_MAX_SIZE, stdin);
    /* remove trailing newline from user_input */
    user_input[strcspn(user_input, "\n")] = '\0';
    
    
    contract(user_input, contract_string);

    printf("The string you entered: %s\n", user_input);
    printf("The contract string: %s\n", contract_string);

    return 0;
}

/* This function will contract the given s1 string and put the contracted string in s2
    algorithm: 
    1. check if the current char is alpha numeric
        a. if not just add the current char to the output string and init the minimum to current+1
    2. compare the current char vs the next one or the next char is the end of the string
        a. if it is the same continue in loop
    3. if it is not the same
        a. if 'i' is the same as the current minimum sequence index then add the current char to output string
        b. if 'i' is bigger by one as the current minimum sequence index then add the current char to output string so it will be in
            in the out put string 'first_sequnce_char,second_sequence_char'
        c. if 'i' is 2 or more bigger than minimum index, then replcae the current second char of the sequence in output string 's2'
            with '-' and add the current last char of the sequence to the out put string

*/
void contract(char s1[], char s2[])
{
    int i;
    int j = -1;

    int current_minimum_index = 0;
    /* loop until end of user string */
    for (i = 0; s1[i] != '\0' ; i++)
    {
        /* check if char is alpha_numeric*/
        if(is_alpha_numeric(s1[i]) == TRUE)
        {
            /* check if the nexrt char is in the sequence or if the next is the last*/
            if(s1[i+1]  != s1[current_minimum_index] + (i - current_minimum_index)+1 || s1[i+1] == '\0')
            {
                /* check if the sequence end with more than 2 in the sequence*/
                if(i -current_minimum_index > 1)
                {
                    s2[j] = '-';
                }
                /*update j by one and insert current user string char to output string*/
                s2[++j] = s1[i];
                /* update the current minimun to the next char index*/
                current_minimum_index = i+1;
            }
            /*check if the sequence is start is in length of one*/
            else if(i-current_minimum_index == 0)
            {
                s2[++j] = s1[i];
            }
            /*check if the sequence is start is in length of two*/
            else if(i -current_minimum_index == 1)
            {
                s2[++j] = s1[i];
            }
        }
        /*if not alpha numeric just update output string anf current minimum*/
        else
        {
            s2[++j] = s1[i];
            current_minimum_index = i+1;
        }
    }
}

/*
 This function will check if the char c is between the alpha numeric ASCII values
*/
int is_alpha_numeric(char c)
{
    if((c >= ASCII_OF_SMALL_A && c < ASCII_OF_SMALL_A + NUMBER_OF_ENGLISH_CHARS) ||
        (c >= ASCII_OF_CAP_A && c < ASCII_OF_CAP_A + NUMBER_OF_ENGLISH_CHARS) ||
        (c >= ASCII_OF_ZERO && c < ASCII_OF_ZERO + 10))
    {
        return TRUE;
    }   
    return FALSE;
}