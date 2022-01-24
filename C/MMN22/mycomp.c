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
void handle_stop(int* is_stop);
char read_until_no_white_space();
int check_for_end_of_command();
char get_variable(int is_comma_before);
int get_number(double* number);
int try_skip_comma();
int get_two_variables(char* first, char* second);
int get_variable_and_number_no_validate_end(char* var, double* number);
void clear_stdin();


complex comp_arr[NUM_OF_VARS];

int main()
{
    int is_stop = FALSE;
    init_comp(comp_arr);

    char user_input[INPUT_LEN] = {0};
    print_menu();
    while(is_stop != TRUE && EOF != scanf("%s", user_input))
    {
        printf("The current command is: %s", user_input);
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
            handle_stop(&is_stop);
        }
        else if(user_input[strlen(user_input)-1] == ',')
        {
            printf("Illegal comma\n");
        }
        else
        {
            printf("Undefined command name\n");
            clear_stdin();
        }
        if(is_stop != TRUE)
        {
            //print_menu();
        }
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
    char var = '\0';
    if(get_variable_and_number_no_validate_end(&var, &real_number) != TRUE)
    {
        return;
    }
    if(try_skip_comma() != TRUE)
    {
        return;
    }
    if(get_number(&img_number) != TRUE)
    {
        clear_stdin();
        return;
    }
    if(check_for_end_of_command() != TRUE)
    {
        clear_stdin();
        return;
    }
    comp_arr[var - 'A'].r = real_number;
    comp_arr[var - 'A'].i = img_number;
}
/* 
*/
void handle_print_comp()
{
    printf("in print\n");
    char var = get_variable(FALSE);
    if(var != EOF)
    {
        int result = check_for_end_of_command();
        if(result != TRUE)
        {
            clear_stdin();
            return;
        }
        print_comp(comp_arr[var - 'A']);
    }
    else
    {
        clear_stdin();
    }
}
/* 
 this function will do an addition action between two complex number and return the result as a complex
*/
void handle_add_comp()
{
    char first_var = '\0';
    char second_var = '\0';
    if(get_two_variables(&first_var, &second_var) != TRUE)
    {
        clear_stdin();
        return;
    }
    print_comp(add_comp(comp_arr[first_var - 'A'], comp_arr[second_var - 'A']));
}
/*
 this function will do a subtraction action between two complex numbers and return the result as a complex
*/
void handle_sub_comp()
{
    char first_var = '\0';
    char second_var = '\0';
    if(get_two_variables(&first_var, &second_var) != TRUE)
    {
        clear_stdin();
        return;
    }
    print_comp(sub_comp(comp_arr[first_var - 'A'], comp_arr[second_var - 'A']));
}
/*
 this function will do a multiplication between complex number and real number and return the result as a complex number
*/
void handle_mult_comp_real()
{
    printf("in void handle_mult_comp_real()\n");
    char var = '\0';
    double real = 0;
    if(get_variable_and_number_no_validate_end(&var, &real) != TRUE)
    {
        return;
    }
    if(check_for_end_of_command() != TRUE)
    {
        clear_stdin();
        return;
    }
    print_comp(mult_comp_real(comp_arr[var - 'A'], real));
}
/*
 this function will do a multiplication between complex number and imaginary number and return the result as a complex number
*/
void handle_mult_comp_img()
{
    printf("in handle_mult_comp_img\n");
    char var = '\0';
    double img = 0;
    if(get_variable_and_number_no_validate_end(&var, &img) != TRUE)
    {
        return;
    }
    if(check_for_end_of_command() != TRUE)
    {
        clear_stdin();
        return;
    }
    print_comp(mult_comp_real(comp_arr[var - 'A'], img));
}
/*
 this function will do a multiplication between two complex numbers and return the result as a complex number
*/
void handle_mult_comp_comp()
{
    printf("in handle_mult_comp_comp\n");
    char first_var = '\0';
    char second_var = '\0';
    if(get_two_variables(&first_var, &second_var) != TRUE)
    {
        clear_stdin();
        return;
    }
    print_comp(mult_comp_comp(comp_arr[first_var - 'A'], comp_arr[second_var - 'A']));
}
/*
 this funnction will calculate the absolute value of the given complex numbner and return the result as a real number
*/
void handle_abs_comp()
{
    printf("in handle_abs_comp\n");
    char var = get_variable(FALSE);
    if(var != EOF)
    {
        int result = check_for_end_of_command();
        if(result != TRUE)
        {
            clear_stdin();
            return;
        }
        print_comp(abs_comp(comp_arr[var - 'A']));
    }
    else
    {
        clear_stdin();
    }
}
/*
*/
void handle_stop(int* is_stop)
{
    printf("In stop\n");
    if(check_for_end_of_command() != TRUE)
    {
        clear_stdin();
        return;
    }
    printf("Stopping...\n");
    *is_stop = TRUE;
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
            clear_stdin();
            printf("Undefined complex variable\n");
        }
    }
    return EOF;
}
int get_number(double* number)
{
    char c = '\0';
    int result = scanf("%lf", number);
    /*
     NEED TO HANDLE WHEN THERE IS NOT A NUMBER ENTERED BUT A NEWLINE
    */
    //printf("in get_number, got result: %d\n", result);
    if(result == EOF)
    {
        printf("Missing parameter\n");
        return FALSE;
    }
    else if(result == 0)
    {
        if((result = scanf("%c", &c)) != EOF && c == ',')
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
        else if(c == '\n')
        {
            printf("Missing parameter\n");
            return FALSE;
        }
        else
        {
            printf("Missing comma\n");
            clear_stdin();
            return FALSE;
        }
    }
    return FALSE;
}
int get_two_variables(char* first, char* second)
{
    char first_var = get_variable(FALSE);
    if(first_var != EOF)
    {
        if(try_skip_comma() != TRUE)
        {
            return FALSE;
        }
        char second_var = get_variable(TRUE);
        if(second_var != EOF)
        {
            if(check_for_end_of_command() != TRUE)
            {
                return FALSE;
            }
            *first = first_var;
            *second = second_var;
            return TRUE;
        }
    }
    return FALSE; 
}
int get_variable_and_number_no_validate_end(char* var, double* number)
{
    double temp_number = 0;
    char temp_var = get_variable(FALSE);
    if(temp_var != EOF)
    {
        if(read_until_no_white_space() != ',')
        {
            printf("Missing comma\n");
            return FALSE;
        }
        if(get_number(&temp_number) != TRUE)
        {
            return FALSE;
        }
        *var = temp_var;
        *number = temp_number;
        return TRUE;
    }
    return FALSE;
}
void clear_stdin()
{
    char temp_c = '\0';
    while((temp_c = getchar()) != '\n' && temp_c !=EOF){};
}