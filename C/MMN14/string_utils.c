#include "string_utils.h"

char* remove_empty_chars_from_beginning(char* str)
{
    while(isspace(str[0]) != 0)
    {
        str++;
    }
    return str;
}
int is_equals(char* first_str, char* second_str)
{
    if(strcmp(first_str, second_str) == 0)
    {
        return TRUE;
    }
    return FALSE;
}