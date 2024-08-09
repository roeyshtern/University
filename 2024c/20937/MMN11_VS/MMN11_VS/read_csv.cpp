#include "read_csv.h"

CsvSumPrinter::CsvSumPrinter(std::string path)
{
    // Open the input file named "input.txt" 
    std::ifstream inputFile(path);

    // Check if the file is successfully opened 
    if (!inputFile.is_open()) {
        throw new std::invalid_argument("File not found");
    }

    this->filePath = path;
}

void CsvSumPrinter::CalculateSums()
{
    std::string line, word, temp;
    std::ifstream inputFile(this->filePath);
    bool firstLine = true;
    // Check if the file is successfully opened 
    if (!inputFile.is_open()) {
        throw new std::invalid_argument("File not found");
    }
    while (std::getline(inputFile, line))
    {
        // read an entire row and
        // store it in a string variable 'line'
        
        if (firstLine)
        {
        	firstLine = false;
            if (line.size() > 2)
            {
                if (line[0] == (char)-17 && line[1] == (char)-69 && line[2] == (char)-65)
                {
                    line = line.substr(3);
                }
            }
        }
        // used for breaking words
        std::stringstream s(line);

        // read every column data of a row and
        // store it in a string variable, 'word'
        int i = 0;
        double currentRowSum = 0;
        while (std::getline(s, word, ','))
        {
            if (this->columnSum.size() == i)
            {
                this->columnSum.push_back(0);
            }

            try
            {

                // convert string to integer
                std::size_t pos;
                double num = std::stod(word, &pos);
                if (pos < word.size())
                    throw std::invalid_argument("Not a number");
                currentRowSum += num;
                // add all the column data
                this->columnSum[i] += num;
            }
            catch (const std::exception&)
            {

            }
            i++;

        }
        std::cout << "Total of line: " << currentRowSum << std::endl;
    }

    std::cout << "Sums of columns: ";
    for (double sum : this->columnSum)
    {
        std::cout << sum << " ";
    }
    std::cout << std::endl;
    
}
