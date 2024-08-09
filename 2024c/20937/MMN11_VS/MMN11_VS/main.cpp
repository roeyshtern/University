#include <iostream>
#include "my_vec.h"
#include "all_vecs.h"
#include <assert.h>
#include "read_csv.h"

void TestQ2();
void TestConstructor();
void TestMathFunctions();
void TestQ3();
bool TestIsExceptionThrown(void (*function)());
void TestExceptions();
void TestVectorAlreadyExist();
void TestEmptyKey();
void TestKeyNotStartWithLetter();
void TestInvalidKey();
void TestGetKey();

int main()
{
    std::cout << "************************************" << std::endl;
    std::cout << "Start to test Q2" << std::endl;
    std::cout << "************************************" << std::endl;
    TestQ2();
    std::cout << "************************************" << std::endl;
    std::cout << "Done Testing Q2" << std::endl;
    std::cout << "************************************" << std::endl;
    std::cout << "Start to test Q3" << std::endl;
    std::cout << "************************************" << std::endl;
    TestQ3();
    std::cout << "************************************" << std::endl;
    std::cout << "Done Testing Q3" << std::endl;
    std::cout << "************************************" << std::endl;
    std::cout << "Finished All tests successfully" << std::endl;

    // Call the read_csv function
    std::string filePath = "c:\\tmp\\20240809\\example.csv";
    CsvSumPrinter csvPrinter(filePath);
    csvPrinter.CalculateSums();

    return 0;
}

void TestQ2()
{
    TestConstructor();
    TestMathFunctions();
}
void TestConstructor()
{
    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing empty constructor" << std::endl;
    my_vec empty;
    assert(empty.getX() == 0);
    assert(empty.getY() == 0);
    assert(empty.getZ() == 0);
    
    std::cout << "Done Testing empty constructor successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing regular constructor" << std::endl;
    my_vec v1(1, 2, 3);
    assert(v1.getX() == 1);
    assert(v1.getY() == 2);
    assert(v1.getZ() == 3);
    std::cout << "Done Testing regular constructor successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing copy constructor" << std::endl;
    my_vec v2(v1);
    assert(v2.getX() == 1);
    assert(v2.getY() == 2);
    assert(v2.getZ() == 3);
    std::cout << "Done Testing copy constructor successfully" << std::endl;
}
void TestMathFunctions()
{
    my_vec v1(1, 1, 1);
    my_vec v2(2, 2, 2);
    
    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Add function" << std::endl;
    my_vec v3 = v1 + v2;
    assert(v3.getX() == 3);
    assert(v3.getY() == 3);
    assert(v3.getZ() == 3);
    std::cout << "Done Testing Add function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Subtract function" << std::endl;
    my_vec v4 = v2 - v1;
    assert(v4.getX() == 1);
    assert(v4.getY() == 1);
    assert(v4.getZ() == 1);
    std::cout << "Done Testing Subtract function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Multiple by double function" << std::endl;
    double d = 5;
    my_vec v5 = v1*d;
    assert(v5.getX() == v1.getX()*d);
    assert(v5.getY() == v1.getY() * d);
    assert(v5.getZ() == v1.getZ() * d);
    std::cout << "Done Testing Multiple by double function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Multiple by float function" << std::endl;
    float f = 8;
    my_vec v6 = v1 * f;
    assert(v6.getX() == v1.getX() * f);
    assert(v6.getY() == v1.getY() * f);
    assert(v6.getZ() == v1.getZ() * f);
    std::cout << "Done Testing Multiple by float function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Multiple by another vector function" << std::endl;
    my_vec v7(3, 3, 3);
    my_vec v8(4, 4, 4);
    my_vec v9 = v7 * v8;
    assert(v9.getX() == v7.getX() * v8.getX());
    assert(v9.getY() == v7.getY() * v8.getY());
    assert(v9.getZ() == v7.getZ() * v8.getZ());
    std::cout << "Done Testing Multiple by another vector function successfully" << std::endl;
    
}
void TestQ3()
{
    TestExceptions();
    TestGetKey();
}

bool TestIsExceptionThrown(void (*function)())
{
    bool isExceptionThrown = false;
    try {
        
        function();
    }
    catch (...)
    {
        isExceptionThrown = true;
    }
    return isExceptionThrown;
}

void TestExceptions()
{
    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Adding existing key function" << std::endl;

    assert(TestIsExceptionThrown(TestVectorAlreadyExist) == true);

    std::cout << "Done Testing Adding existing key function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Adding empty key function" << std::endl;

    assert(TestIsExceptionThrown(TestEmptyKey) == true);

    std::cout << "Done Testing Adding empty key function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Adding key not start with letter function" << std::endl;

    assert(TestIsExceptionThrown(TestKeyNotStartWithLetter) == true);

    std::cout << "Done Testing Adding key not start with letter function successfully" << std::endl;

    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Adding key with invalid chars function" << std::endl;

    assert(TestIsExceptionThrown(TestInvalidKey) == true);

    std::cout << "Done Testing Adding key with invalid chars function successfully" << std::endl;
}

void TestVectorAlreadyExist()
{
    all_vecs vecs;
    vecs.add_vec("key", my_vec(1, 2, 3));
    vecs.add_vec("key", my_vec(1, 2, 3));
}

void TestEmptyKey()
{
    all_vecs vecs;
	vecs.add_vec("", my_vec(1, 2, 3));
}

void TestKeyNotStartWithLetter()
{
    all_vecs vecs;
	vecs.add_vec("1key", my_vec(1, 2, 3));
}

void TestInvalidKey()
{
    all_vecs vecs;
	vecs.add_vec("key!", my_vec(1, 2, 3));
}

void TestGetKey()
{
    std::cout << "--------------------------------------" << std::endl;

    std::cout << "Testing Get key function" << std::endl;
    
    all_vecs vecs;
    my_vec v1(1, 2, 3);
    my_vec v2(4, 5, 6);
	vecs.add_vec("key", v1);
    vecs.add_vec("key2", v2);
	my_vec v3 = vecs.get_vec("key");
    assert(v1.getX() == v3.getX());
    assert(v1.getY() == v3.getY());
    assert(v1.getZ() == v3.getZ());

    my_vec v4 = vecs.get_vec("key2");
    assert(v1.getX() != v4.getX());
    assert(v1.getY() != v4.getY());
    assert(v1.getZ() != v4.getZ());

    std::cout << "Done Testring Get Key function successfully" << std::endl;
}
