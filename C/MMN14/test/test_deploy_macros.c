#include "test.h"

int main()
{
    FILE* result;
    result = open_file("macro_test.as", "r");
    deploy_macros(result, "macro_test");
    return 0;
}