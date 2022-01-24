#include "test.h"

void test_file_utils_open_file();

int main()
{
    printf("Starting to test file_utils\n");
    test_file_utils_open_file();
    return 0;
}

void test_file_utils_open_file()
{
    FILE* result = NULL;
    /*check for not*/
    result = open_file("test", "r");
    assert(result != NULL && "should not be NULL");

    result = open_file("testaaa", "r");
    assert(result == NULL && "should be NULL");
}