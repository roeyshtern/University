#include "assembler.h"

void handle_file(char* file_name);

int main(int argc, char** argv)
{
    int i = 0;
    if(is_argc_valid(argc) == FALSE)
    {
        return ERROR;
    }
    for (i = 1; i < argc; i++)
    {
        //open file, make sure hw is open
        handle_file(argv[i]);
    }
    
    return 0;
}

void handle_file(char* file_name)
{
    FILE* fp = NULL;
    fp = open_file_to_read(file_name);
    if(is_file_open(fp) != TRUE)
    {
        //TODO: make error for this file
    }


}