# PySpark Project for Data Processing

This is a PySpark project designed to perform data processing tasks, including reading a CSV file, filtering data, and saving the results to Parquet format.

## Prerequisites

To run this project, you'll need:

- [Python 3.5+](https://www.python.org/)
- [Apache Spark 2.2.0+](https://spark.apache.org/)
- [Java 8+](https://www.oracle.com/java/)
- [PySpark](https://spark.apache.org/docs/latest/api/python/)

### Project Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/dgm91-ua/BigData.git
   cd scala
   cd project1-scala
   ```

### Installing Dependencies

It is recommended to use a virtual environment. You can install the required dependencies by running the following command:

```bash
pip install pyspark
pip install py4j
```
### Create environment using conda

In this repository, you have a folder (pyspark_environment) with conda environment. You have to install conda and then in a therminal run:

```bash
conda env create -f environment.yml
conda activate pyspark_env
```

## Project Structure
```
├── files
│   ├── input_files
│   └── output_files
├── logs
│   ├── output
│   └── spark
├── src
│   └── main
│          ├── code
│          └── properties
├── .gitignore
├── README.md
└── execute_spark.sh
```
