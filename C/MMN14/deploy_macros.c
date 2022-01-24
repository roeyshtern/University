#include "deploy_macros.h"

void deploy_macros(FILE* fp_before_deploy, char* file_name)
{
    int is_macro = FALSE;
    char* first_word = NULL;
    int current_row_of_macro = 0;
    char macro_name[MAX_LEN_MACRO_NAME] = {0};
    macro* head = NULL;
    macro* current_macro = NULL;
    char currrent_line[FILE_ROW_LENGTH + SAFETY_BUFFER] = {0};
    macro* found_macro = NULL;
    FILE* am_file = NULL;

    if(fp_before_deploy == NULL)
    {
        printf("NULL\n");
        return;
    }
    am_file = open_file_to_write(get_file_name_with_extension(file_name, AM_EXTENSION));
    printf("first!\n");
    while(fgets(currrent_line, FILE_ROW_LENGTH + SAFETY_BUFFER, fp_before_deploy) != NULL)
    {
        printf("after first!\n");
        first_word = strtok(currrent_line, SPACE_VALUES_STRING);
        printf("first_word: %s!\n", first_word);
        if(is_equals(first_word, MACRO_ENDING_COMMAND))
        {
            printf("is equals!\n");
            is_macro = FALSE;
            current_row_of_macro = 0;
            continue;
        }
        if(is_macro == TRUE)
        {    
            printf("In is_macro == TRUE\n");
            strcpy(current_macro->instructions[current_row_of_macro], currrent_line);
            current_row_of_macro++;
            continue;
        }
        if(is_equals(first_word, MACRO_START_COMMAND) == TRUE)
        {
            printf("Start of macro\n");
            strcpy(macro_name, strtok(NULL, SPACE_VALUES_STRING));
            if(head == NULL)
            {
                head = create_macro(macro_name, NULL);
            }
            else
            {
                current_macro = create_macro(macro_name, current_macro);
            }
            printf("After of macro\n");
            is_macro = TRUE;
            continue;
        
        }
        printf("before get existing\n");
        found_macro = get_existing_macro(first_word, head);
        printf("after get existing\n");
        if(found_macro != NULL)
        {
            printf("before writing\n");
            if(try_write_macro_lines_to_file(am_file, found_macro) != TRUE)
            {
                /*TODO: error, mayber change to return to try for true and false*/
                /* raise error*/
                break;
            }
            printf("after writing\n");
        }
        else if(try_write_line_to_file(am_file, currrent_line) != TRUE)
        {
            /*TODO: error, mayber change to return to try for true and false*/
            /* raise error*/
            break;
        }
    }
    /*free macros if needed*/
}
macro* create_macro(char macro_name[MAX_LEN_MACRO_NAME], macro* current_macro)
{
    macro* m = (macro*)malloc(sizeof(macro));
    strcpy(m->name, macro_name);
    if(current_macro != NULL)
    {
        current_macro->next = m;
    }
    return m;
}
macro* get_existing_macro(char* macro_name, macro* head)
{
    macro* temp = head;
    if(head == NULL)
    {
        return NULL;
    }
    while(temp != NULL)
    {
        if(strcmp(macro_name, temp->name) == 0)
        {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}
int try_write_macro_lines_to_file(FILE* fp, macro* macro)
{
    int i = 0;
    for (i = 0; i < MAX_ROWS_MACRO; i++)
    {
        if(strlen(macro->instructions[i]) > 0)
        {
            if(try_write_line_to_file(fp, macro->instructions[i]) != TRUE)
            {
                return FALSE;
            }
        }
    }
    return TRUE;
}