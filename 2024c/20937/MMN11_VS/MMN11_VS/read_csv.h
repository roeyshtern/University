#pragma once

#include <string>
#include <vector>
#include <fstream> 
#include <iostream>
#include <istream>
#include <sstream>


class CsvSumPrinter {
private:
	std::string filePath;
	std::vector<double> columnSum;
public:
	CsvSumPrinter(std::string path);
	void CalculateSums();
};
