.PHONY: test_deploy_macros
test_deploy_macros: test_deploy_macros.c test.h ../file_utils.c ../file_utils.h ../constant.h ../deploy_macros.h ../string_utils.h
	gcc -Wall -ansi -pedantic ../file_utils.c ../deploy_macros.c test_deploy_macros.c ../string_utils.c -o test_deploy_macros

test_file_utils: test_file_utils.c test.h ../file_utils.c ../file_utils.h ../constant.h
	gcc -Wall -ansi -pedantic ../file_utils.c test_file_utils.c -o test_file_utils

