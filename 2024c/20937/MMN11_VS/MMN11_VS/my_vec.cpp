#include "my_vec.h"

my_vec::my_vec() {
    this->x = 0;
	this->y = 0;
	this->z = 0;
}

my_vec::my_vec(double x, double y, double z){
    this->x = x;
	this->y = y;
	this->z = z;
}

my_vec::my_vec(const my_vec& other){
    this->x = other.x;
    this->y = other.y;
    this->z = other.z;
}

double my_vec::getX() const {
    return x;
}

double my_vec::getY() const {
    return y;
}

double my_vec::getZ() const {   
    return z;
}

std::ostream& operator<<(std::ostream& os, const my_vec& v) {
    return os << "(" << v.x << ", " << v.y << ", " << v.z << ")";
}

my_vec my_vec::operator+(const my_vec& v) const {
    my_vec result;
    result.x = this->x + v.x;
    result.y = this->y + v.y;
    result.z = this->z + v.z;
    return result;
}

my_vec my_vec::operator-(const my_vec& v) const {
    my_vec result;
    result.x = this->x - v.x;
    result.y = this->y - v.y;
    result.z = this->z - v.z;
    return result;
}

my_vec my_vec::operator*(double scalar) const {
    my_vec result;
    result.x = this->x * scalar;
    result.y = this->y * scalar;
    result.z = this->z * scalar;
    return result;
}

my_vec my_vec::operator*(const my_vec& v) const {
    my_vec result;
	result.x = this->x * v.x;
	result.y = this->y * v.y;
	result.z = this->z * v.z;
	return result;
}
