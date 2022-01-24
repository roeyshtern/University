#include <stdio.h>
#include "complex.h"

#define TRUE 1
#define FALSE 0

/*
 this function will print the complex value it get
*/
void print_comp(complex c)
{
    printf("\n");
    if(c.i < 0)
    {
        printf("%g - (%g)i\n", c.r, -1*c.i);
    }
    else
    {
        printf("%g + (%g)i\n", c.r, c.i);
    }
}
/* 
 this function will do an addition action between two complex number and return the result as a complex
*/
complex add_comp(complex first, complex second)
{
    complex ret;
    ret.r = first.r + second.r;
    ret.i = first.i + second.i;
    return ret;
}
/*
 this function will do a subtraction action between two complex numbers and return the result as a complex
*/
complex sub_comp(complex first, complex second)
{
    complex ret;
    ret.r = first.r - second.r;
    ret.i = first.i - second.i;
    return ret;
}
/*
 this function will do a multiplication between complex number and real number and return the result as a complex number
*/
complex mult_comp_real(complex comp, double real)
{
    complex ret;
    ret.r = real * comp.r;
    ret.i = real * comp.i;
    return ret;
}
/*
 this function will do a multiplication between complex number and imaginary number and return the result as a complex number
*/
complex mult_comp_img(complex comp, double img)
{
    complex ret;
    ret.r = -1 * img * comp.i;
    ret.i = img * comp.r;
    return ret;
}
/*
 this function will do a multiplication between two complex numbers and return the result as a complex number
*/
complex mult_comp_comp(complex first, complex second)
{
    complex ret;
    ret.r = first.r * second.r - first.i * second.i;
    ret.i = first.r * second.i + first.i * second.r;
    return ret;
}
/*
 this funnction will calculate the absolute value of the given complex numbner and return the result as a real number
*/
complex abs_comp(complex comp)
{
    complex ret;
    ret.r = sqrt(comp.r*comp.r + comp.i*comp.i);
    ret.i = 0;
    return ret;
}