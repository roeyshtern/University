#pragma once

#include <iostream>

class my_vec {
public:
    my_vec();
    my_vec(double x, double y, double z);
    my_vec(const my_vec& other);

    double getX() const;
    double getY() const;
    double getZ() const;

    friend std::ostream& operator<<(std::ostream& os, const my_vec& v);

    my_vec operator+(const my_vec& v) const;
    my_vec operator-(const my_vec& v) const;
    my_vec operator*(double scalar) const;
    my_vec operator*(const my_vec& v) const;

private:
    double x;
    double y;
    double z;
};