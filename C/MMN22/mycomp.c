#include <stdio.h>
#include "complex.h"
#include <string.h>
#include <ctype.h>

#define INPUT_LEN 80
#define NUM_OF_VARS 6
#define TRUE 1
#define FALSE 0

void init_comp(complex* comp);
void print_menu();
void handle_print_comp();
void handle_read_comp();
void handle_add_comp();
void handle_sub_comp();
void handle_mult_comp_real();
void handle_mult_comp_img();
void handle_mult_comp_comp();
void handle_abs_comp();
void handle_stop();
char read_until_no_white_space();
int check_for_end_of_command();
char get_variable(int is_comma_before);
int get_number(double* number);
int try_skip_comma();

complex comp_arr[NUM_OF_VARS];

int main()
{
    init_comp(comp_arr);

    char user_input[INPUT_LEN] = {0};
    print_menu();
    while(EOF != scanf("%s", user_input))
    {
        if(strcmp(user_input, "read_comp") == 0)
        {
            handle_read_comp();
        }
        else if(strcmp(user_input, "print_comp") == 0)
        {
            handle_print_comp();
        }
        else if(strcmp(user_input, "add_comp") == 0)
        {
            handle_add_comp();
        }
        else if(strcmp(user_input, "sub_comp") == 0)
        {
            handle_sub_comp();
        }
        else if(strcmp(user_input, "mult_comp_real") == 0)
        {
            handle_mult_comp_real();
        }
        else if(strcmp(user_input, "mult_comp_img") == 0)
        {
            handle_mult_comp_img();
        }
        else if(strcmp(user_input, "mult_comp_comp") == 0)
        {
            handle_mult_comp_comp();
        }
        else if(strcmp(user_input, "abs_comp") == 0)
        {
            handle_abs_comp();
        }
        else if(strcmp(user_input, "stop") == 0)
        {
            handle_stop();
        }
        else
        {
            printf("Undefined command name\n");
        }
        print_menu();
    }
    return 0;
}
void init_comp(complex comp_arr[])
{
    for (int i = 0; i < NUM_OF_VARS; i++)
    {
        comp_arr[i].r = 0;
        comp_arr[i].i = 0;
    }
}
void print_menu()
{
    printf("MENU for commands:\n");
    printf("There are 6 variables you can use, named A-F, for each command you need to use this variables\n\n");
    printf("\t1. read_comp <VAR_NAME>, <real_value>, <imaginary_value>\n");
    printf("\t2. print_comp <VAR_NAME>\n");
    printf("\t3. add_comp <VAR_NAME>, <VAR_NAME>\n");
    printf("\t4. sub_comp <VAR_NAME>, <VAR_NAME>\n");
    printf("\t5. mult_comp_real <VAR_NAME>, <real_value>\n");
    printf("\t6. mult_comp_img <VAR_NAME>, <imaginary_value>\n");
    printf("\t7. mult_comp_comp <VAR_NAME>, <VAR_NAME>\n");
    printf("\t8. abs_comp <VAR_NAME>\n");
    printf("\t9. stop\n");
}
/* 
*/
void handle_read_comp()
{
    double real_number = 0;
    double img_number = 0;
    char var = get_variable(FALSE);
    if(var != EOF)
    {
        if(try_skip_comma() != TRUE)
        {
            printf("Missing comma\n");
            return;
        }
        if(get_number(&real_number) != TRUE)
        {
            return;
        }
        if(try_skip_comma() != TRUE)
        {
            printf("Missing comma\n");
            return;
        }
        if(get_number(&img_number) != TRUE)
        {
            return;
        }
        if(check_for_end_of_command() != TRUE)
        {
            return;
        }
        comp_arr[var - 'A'].r = real_number;
        comp_arr[var - 'A'].i = img_number;
    }
}
/* 
*/
void handle_print_comp()
{
    char var = get_variable(FALSE);
    if(var != EOF)
    {
        int result = check_for_end_of_command();
        if(result == TRUE)
        {
            print_comp(comp_arr[var - 'A']);
        }
    }
}
/* 
 this function will do an addition action between two complex number and return the result as a complex
*/
void handle_add_comp()
{
    printf("in handle_add_comp\n");
}
/*
 this function will do a subtraction action between two complex numbers and return the result as a complex
*/
void handle_sub_comp()
{
    printf("in handle_sub_comp\n");
}
/*
 this function will do a multiplication between complex number and real number and return the result as a complex number
*/
void handle_mult_comp_real()
{
    printf("in void handle_mult_comp_real()\n");
}
/*
 this function will do a multiplication between complex number and imaginary number and return the result as a complex number
*/
void handle_mult_comp_img()
{
    printf("in handle_mult_comp_img\n");
}
/*
 this function will do a multiplication between two complex numbers and return the result as a complex number
*/
void handle_mult_comp_comp()
{
    printf("in handle_mult_comp_comp\n");
}
/*
 this funnction will calculate the absolute value of the given complex numbner and return the result as a real number
*/
void handle_abs_comp()
{
    printf("in handle_abs_comp\n");
}
/*
*/
void handle_stop()
{

}
char read_until_no_white_space()
{
    /*printf("In read_until_no_white_space\n");*/
    char c = '\0';
    int result = 0;
    while((result = scanf("%c", &c)) != EOF && isspace(c) && c != '\n')
    {
        /*printf("found: %c| result: %d\n", c, result);*/
    }
    if(result == EOF)
    {
        return EOF;
    }
    return c;
}
int check_for_end_of_command()
{
    char c = read_until_no_white_space();
    if(c != '\n' && c != EOF)
    {
        printf("Extraneous text after end of command\n");
        return FALSE;
    }
    return TRUE;
}
char get_variable(int is_comma_before)
{
    char c = read_until_no_white_space();
    if(c != EOF)
    {
        if(c >= 'A' && c < 'A' + NUM_OF_VARS)
        {
            return c;
        }
        else if(c == '\n')
        {
            printf("Missing parameter\n");
        }
        else if(c == ',' && is_comma_before == TRUE)
        {
            printf("Multiple consecutive commas\n");
        }
        else
        {
            printf("Undefined complex variable\n");
        }
    }
    return EOF;
}
int get_number(double* number)
{
    char c = '\0';
    int result = scanf("%lf", number);
    if(result == EOF || (c = read_until_no_white_space()) == '\n')
    {
        printf("Missing parameter\n");
        return FALSE;
    }
    else if(result == 0)
    {
        if(c == ',')
        {
            printf("Multiple consecutive commas\n");
            return FALSE;
        }
        else
        {
            printf("Invalid parameter - not a number\n");
            return FALSE;
        }
    }
    return TRUE;
}
int try_skip_comma()
{
    char c = read_until_no_white_space();
    if(c != EOF)
    {
        if(c == ',')
        {
            return TRUE;
        }
        else
        {
            return FALSE;
        }
    }
}