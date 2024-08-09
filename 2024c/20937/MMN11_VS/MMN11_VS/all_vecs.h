#pragma

#include "my_vec.h"
#include <iostream>
#include <string>
#include <map>

class all_vecs {
private:
	std::map<std::string, my_vec> _vecs;
public:
	void add_vec(const std::string& key, const my_vec& value);
	my_vec get_vec(const std::string& key) const;
};