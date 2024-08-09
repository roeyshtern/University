#include <iostream>

class my_vec {
    private:
        double _x;
        double _y;
        double _z;
    public:
        my_vec(){
            _x = 0;
            _y = 0;
            _z = 0;
        };
        my_vec(double x, double y, double z){
            _x = x;
            _y = y;
            _z = z;
        };
        my_vec(my_vec& other){
            _x = other._x;
            _y = other._y;
            _z = other._z;
        };
        double getX() {
            return _x;
        };
        double getY() {
            return _y;
        };
        double getZ() {
            return _z;
        };
        void setX(double x) {
            _x = x;
        };
        void setY(double y) {
            _y = y;
        };
        void setZ(double z) {
            _z = z;
        };
        friend std::ostream& operator<<(std::ostream &os, const my_vec& v){
            return os << "(" << v._x << ", " << v._y << ", " << v._z << ")";
        };
        my_vec operator+(const my_vec& v){
            my_vec result(_x+v._x, _y+v._y, _z+v._z);
            return result;
        };
        my_vec operator-(const my_vec& v){
            my_vec result(_x-v._x, _y-v._y, _z-v._z);
            return result;
        };
        my_vec operator*(double scalar){
            my_vec result(_x*scalar, _y*scalar, _z*scalar);
            return result;
        };
        my_vec operator*(int scalar) const {
            my_vec result(_x*scalar, _y*scalar, _z*scalar);
            return result;
        }
        my_vec operator*(const my_vec& v) const {
            my_vec result(_x*v._x, _y*v._y, _z*v._z);
            return result;
        };

};

int main()
{
    my_vec a(1, -4, 6);
    my_vec b(0, -8, 6);
    my_vec z;
    
    z.setY(14);

    std::cout << "a+b is" << a+b << std::endl;
    //std::cout << "8 * b is" << b*8 << std::endl;
    //std::cout << "but z * b is" << a*b << std::endl; 
    
    
    
    return 0;
}