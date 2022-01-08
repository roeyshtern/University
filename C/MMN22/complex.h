#include <stdio.h>
#include <math.h>

/*
 complex type that contains real and imaginary values
*/
typedef struct complex
{
    double r;
    double i;
} complex;

/*
 this function will print the complex value it get
*/
void print_comp(complex c);
/* 
 this function will do an addition action between two complex number and return the result as a complex
*/
complex add_comp(complex first, complex second);
/*
 this function will do a subtraction action between two complex numbers and return the result as a complex
*/
complex sub_comp(complex first, complex second);
/*
 this function will do a multiplication between complex number and real number and return the result as a complex number
*/
complex mult_comp_real(complex comp, double real);
/*
 this function will do a multiplication between complex number and imaginary number and return the result as a complex number
*/
complex mult_comp_img(complex comp, double img);
/*
 this function will do a multiplication between two complex numbers and return the result as a complex number
*/
complex mult_comp_comp(complex first, complex second);
/*
 this funnction will calculate the absolute value of the given complex numbner and return the result as a real number
*/
double abs_comp(complex comp);