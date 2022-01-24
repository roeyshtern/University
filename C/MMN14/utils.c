#include "utils.h"

/*
    This function will check if the given argc and argv is valid
    check for count of arguments, is the file is valid
    return TRUE if valid, else FALSE
*/
int is_argc_valid(int argc)
{
    if(argc < 2)
    {
        return FALSE;
    }
    return TRUE;
}