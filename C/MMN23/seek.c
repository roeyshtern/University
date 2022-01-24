#include "seek_to.h"

int main(int argc, char** argv)
{
    int index = 0;
    int i = 0;
    int result = 0;
    char n_char = '\0';
    if(argc < 2)
    {
        printf("Not enough parameters received, exit...\n");
        return -1;
    }
    
    result = sscanf(argv[1],"%d",&index);
    if(result != 1 || result == EOF || index <= 0)
    {
        printf("Error while reading index parameter, exiting...\n");
        return -1;
    }

    for(i = 2; i < argc; i++)
    {
        printf("The ASCII value of the %d char in file: %s is: \n", index, argv[i]);
        n_char = get_n_char_of_file(argv[i], index);
        if(n_char == EOF)
        {
            continue;
        }
        printf("ASCII value: %d\n", n_char);
    }
    return 0;
}