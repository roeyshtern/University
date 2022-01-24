#include<stdio.h>

/*
    This function will get the n index of a given file_path
    this function will return EOF in case the file is not open successfully 
    or the index is bigger than file size
*/
char get_n_char_of_file(char* file_path, int index);
/*
    This file will return the size of the a file
*/
int get_file_size(FILE* fp);