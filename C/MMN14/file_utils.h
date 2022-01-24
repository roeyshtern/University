#include "constant.h"
#include <string.h>
#include <stdio.h>
/*TODO: fix it*/
#include <stdlib.h>

#define WRITE_MODE "w"
#define READ_MODE "r"

FILE* open_file(char* file_path, char* mode);
int is_file_open(FILE* fp);
char* get_file_name_with_extension(char* file_name, char* extension);
FILE* open_file_to_read(char* file_path);
FILE* open_file_to_write(char* file_path);
int try_write_line_to_file(FILE* fp, char* line);