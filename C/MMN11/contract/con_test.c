#include <stdio.h> /* printf */
#include <string.h> /* strcmp */
#include <ctype.h>

#define RESET   "\033[0m"
#define BLACK   "\033[30m"      /* Black */
#define RED     "\033[31m"      /* Red */
#define GREEN   "\033[32m"      /* Green */
#define MAX_LENGTH 81


#define USER_INPUT_MAX_SIZE 81
#define CONTRACT_STR_MAX_SIZE 81
#define TRUE 1
#define FALSE 0
#define ASCII_OF_SMALL_A 97
#define ASCII_OF_CAP_A 65
#define ASCII_OF_ZERO 48


void contract(char s1[], char s2[]);


static void Test_Contract_Empty_String();
static void Test_Sequence();
static void Test_Non_Alpha_Numeric_Sequence();
static void Test_Non_Sequence();
static void Test_Reversed_Sequence();
static void Test_Full_String();

int main()
{
	Test_Contract_Empty_String();
	Test_Sequence();
	Test_Non_Alpha_Numeric_Sequence();
	Test_Non_Sequence();
	Test_Reversed_Sequence();
	Test_Full_String();
	
	return 0;
}

void contract(char s1[], char s2[])
{
	printf("hi\n");
    int i;
    int j = -1;

    int current_minimum_index = 0;
    /* loop until end of user string */
    for (i = 0; s1[i] != '\0' ; i++)
    {
        /* check if char is alpha_numeric*/
        if(isalnum(s1[i]) == TRUE)
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
	printf("s2: %s\n", s2);
}

static void Test_Contract_Empty_String()
{
	char str_input[MAX_LENGTH] = "";
	char str_output[MAX_LENGTH] = {0};
	const char *expected_output = "";
	
	contract(str_input, str_output);
	
	if (strcmp(str_output, expected_output) != 0)
	{
		printf(RED "Failed Test Test_Contract_Empty" RESET);
		printf(" output: %s   expected_output: %s\n", str_output, expected_output); 
	}
	
	else
	{
		printf(GREEN "Test_Contract_Empty [OK]\n" RESET);
	}	
}


static void Test_Sequence()
{
	char str_input[MAX_LENGTH] = "123456789 abcdefghijklmnopqrstuvwxyz";
	char str_output[MAX_LENGTH] = {0};
	const char *expected_output = "1-9 a-z";
	
	contract(str_input, str_output);
	printf("hi: %s\n", str_output);
	
	if (strcmp(str_output, expected_output) != 0)
	{
		printf(RED "Failed Test Test_Sequence" RESET);
		printf(" output: %s   expected_output: %s\n", str_output, expected_output); 
	}
	
	else
	{
		printf(GREEN "Test_Sequence [OK]\n" RESET);
	}	
}

static void Test_Non_Alpha_Numeric_Sequence()
{
	char str_input[MAX_LENGTH] = " !\"#$%&'()*+,-./ :;<=>?@AB [\\]^_` {|}~";
	char str_output[MAX_LENGTH] = {0};
	const char *expected_output = " !\"#$%&'()*+,-./ :;<=>?@AB [\\]^_` {|}~";
	
	contract(str_input, str_output);
	
	if (strcmp(str_output, expected_output) != 0)
	{
		printf(RED "Failed Test Test_Non_Alpha_Numeric_Sequence" RESET);
		printf(" output: %s   expected_output: %s\n", str_output, expected_output);
	}
	
	else
	{
		printf(GREEN "Test_Non_Alpha_Numeric_Sequence [OK]\n" RESET);
	}	
}

static void Test_Non_Sequence()
{
	char str_input[MAX_LENGTH] = "ab cd ef gh ij kl mn op qr st uv wx yz 12 34 56 78 9101112 13579";
	char str_output[MAX_LENGTH] = {0};
	const char *expected_output = "ab cd ef gh ij kl mn op qr st uv wx yz 12 34 56 78 9101112 13579";
	
	contract(str_input, str_output);
	
	if (strcmp(str_output, expected_output) != 0)
	{
		printf(RED "Failed Test Test_Non_Sequence" RESET);
		printf(" output: %s   expected_output: %s\n", str_output, expected_output);
	}
	
	else
	{
		printf(GREEN "Test_Non_Sequence [OK]\n" RESET);
	}	
}

static void Test_Reversed_Sequence()
{
	char str_input[MAX_LENGTH] = "987654321 zyxwvutsrqponmlkjihgfedcba";
	char str_output[MAX_LENGTH] = {0};
	const char *expected_output = "987654321 zyxwvutsrqponmlkjihgfedcba";
	
	contract(str_input, str_output);
	
	if (strcmp(str_output, expected_output) != 0)
	{
		printf(RED "Failed Test Test_Reversed_Sequence" RESET);
		printf(" output: %s   expected_output: %s\n", str_output, expected_output);
	}
	
	else
	{
		printf(GREEN "Test_Reversed_Sequence [OK]\n" RESET);
	}
}

static void Test_Full_String()
{
	char str_input[MAX_LENGTH] = "abcdef#LLMN  67890123#HIJKMNOpqrstu(?@AB,1124-8) 	789101112131415 abylmno 456789";
	char str_output[MAX_LENGTH] = {0};
	const char *expected_output = "a-f#LL-N  6-90-3#H-KM-Op-u(?@AB,1124-8) 	7-9101112131415 abyl-o 4-9";
	
	contract(str_input, str_output);
	
	if (strcmp(str_output, expected_output) != 0)
	{
		printf(RED "Failed Test Test_Full_String" RESET);
		printf(" output: %s   expected_output: %s\n", str_output, expected_output);
	}
	
	else
	{
		printf(GREEN "Test_Full_String [OK]\n" RESET);
	}
}

