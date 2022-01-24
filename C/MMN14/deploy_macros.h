#include "constant.h"
#include "string_utils.h"
#include "file_utils.h"
#include <stdio.h>
#include <string.h>

#define MAX_ROWS_MACRO 6
#define MAX_LEN_MACRO_NAME 31
#define AM_EXTENSION "am"
#define MACRO_START_COMMAND "macro"
#define MACRO_ENDING_COMMAND "endm"

typedef struct macros {
    char name[MAX_LEN_MACRO_NAME];
    char instructions[MAX_ROWS_MACRO][FILE_ROW_LENGTH + SAFETY_BUFFER];
    struct macros* next;
} macro;

void deploy_macros(FILE* fp, char* file_name);
macro* create_macro(char macro_name[MAX_LEN_MACRO_NAME], macro* current_macro);
macro* get_existing_macro(char* macro_name, macro* head);
int try_write_macro_lines_to_file(FILE* fp, macro* macro);