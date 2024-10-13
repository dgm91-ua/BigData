# BigData Project: PySpark and Scala Spark

This project is a comprehensive data processing pipeline utilizing both Python (with PySpark) and Scala (with Spark) for data manipulation and transformation. It demonstrates how to read, filter, and write data using both PySpark and Scala with Apache Spark.

## Project Structure
```
├── python
│        ├── pyspark_environment
│        ├── project1
│        │          ├── files
│        │          │       ├── input_files
│        │          │       └── output_files
│        │          ├── logs
│        │          │      ├── output
│        │          │      └── spark
│        │          ├── src
│        │          └── main
│        │                 ├── code
│        │                 └── properties
│        ├── .gitignore
│        ├── README.md
│        └── execute_spark.sh
├── scala
│       ├── scala-maven.txt
│       └── project1-scala
│                        ├── src
│                        ├── main
│                        │      ├── assembly
│                        │      ├── java
│                        │      ├── test
│                        │      └── resources
│                        │                  ├── code
│                        │                  ├── properties
│                        │                  └── files
│                        │                          ├── input_files
│                        │                          └── output_files
│                        ├── .gitignore
│                        └── README.md
├── .gitignore
└── README.md
```

### Explanation of `README.md`:
- **Project Structure**: Provides a high-level overview of the folder organization for both the Python (PySpark) and Scala Spark projects.
- **Setting Up the Python Project**: Describes how to create and activate the Python environment using Conda, and how to run the PySpark script using `spark-submit`.
- **Setting Up the Scala Project**: Describes how to build and run the Scala Spark project using Maven and `spark-submit`.
- **Logging**: Mentions how logging is handled using the `log4j.properties` file, with an example configuration.
- **Development and Contribution**: Details how to contribute to the project by forking, cloning, making changes, and submitting pull requests.

This `README.md` provides a clear, organized way to explain how to work with both the Python and Scala Spark projects within the same repository.