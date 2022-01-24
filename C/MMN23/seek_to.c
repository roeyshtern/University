#include "seek_to.h"

/*
    This function will get the n index of a given file_path
    this function will return EOF in case the file is not open successfully 
    or the index is bigger than file size
*/
char get_n_char_of_file(char* file_path, int index)
{
    FILE* f = NULL;
    char char_index = '\0';
    f = fopen(file_path, "r");
    if (!f) 
    {
        printf("Can't open file\n");
        return EOF;
    }
    if(get_file_size(f) < index)
    {
        printf("The file is smaller than the given index\n");
        fclose(f);
        return EOF;
    }
    fseek(f, (index-1) * sizeof(char), SEEK_SET);
    fread(&char_index, sizeof(char), 1, f);
    fclose(f);
    return char_index;
}
/*
    This file will return the size of the a file
*/
int get_file_size(FILE* fp)
{
    int size = 0;
    /*seek to end of file*/
    fseek(fp, 0, SEEK_END); 
    size = ftell(fp); 
    fseek(fp, 0L, SEEK_SET);
    return size;
}