#include "file_utils.h"
#include <stdio.h>

FILE* open_file_to_write(char* file_path)
{
    return open_file(file_path, WRITE_MODE);
}
FILE* open_file_to_read(char* file_path)
{
    return open_file(file_path, READ_MODE);
}

FILE* open_file(char* file_path, char* mode)
{
    FILE* fp = NULL;
    /*char* file_path_with_ext = NULL;*/
    /*TODO: create new open file to open as files*/
    /*file_path_with_ext = get_file_name_with_extension(file_path, ASSEMBLY_FILE_EXTENSION);*/
    fp = fopen(file_path, mode);
    return fp;
}

char* get_file_name_with_extension(char* file_name, char* extension)
{
    /*TODO: need to allocate or think abetter way*/
    char* file_path_with_ext = (char*)malloc(sizeof(char)*MAX_FILE_NAME_SIZE);
    if(strlen(file_name) >= MAX_FILE_NAME_SIZE)
    {
        return NULL;
    }
    sprintf(file_path_with_ext, "%s.%s", file_name, extension);
    return file_path_with_ext;
}

int is_file_open(FILE* fp)
{
    if(fp != NULL)
    {
        return TRUE;
    }
    return FALSE;
}

int try_write_line_to_file(FILE* fp, char* line)
{
    int result = 0;
    result = fprintf(fp, "%s\n", line);
    if(result < 0)
    {
        return FALSE;
    }
    return TRUE;
}
