#include "my_vec.h"
#include "all_vecs.h"

bool is_valid_key(const std::string& key) {
    for (char c : key) {
        if (!isalnum(c) && c != ' ') {
            return false;
        }
    }
    return true;
}

void all_vecs::add_vec(const std::string& key, const my_vec& value) {
    if (_vecs.find(key) != _vecs.end()) {
        throw std::invalid_argument("Key already exists");
    }
    if (key.empty()) {
        throw std::invalid_argument("Key cannot be empty");
    }
    if (!isalpha(key[0])) {
        throw std::invalid_argument("Key must start with a letter");
    }
    if (!is_valid_key(key)) {
        throw std::invalid_argument("Key contains invalid characters");
    }
    _vecs[key] = value;
}

my_vec all_vecs::get_vec(const std::string& key) const {
    if (_vecs.find(key) == _vecs.end()) {
		throw std::invalid_argument("Key does not exist");
	}
    return _vecs.at(key);
}