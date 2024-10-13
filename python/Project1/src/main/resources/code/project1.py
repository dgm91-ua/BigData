import sys
from pyspark.sql import SparkSession
import pyspark.sql.functions as F


# Create a SparkSession
spark = SparkSession.builder \
    .appName("TestApp") \
    .master("local[*]") \
    .getOrCreate()


def delete_output_path_if_exists(spark, output_path):
    # Use Hadoop's FileSystem API to check and delete the directory
    hadoop_conf = spark._jsc.hadoopConfiguration()
    fs = spark._jvm.org.apache.hadoop.fs.FileSystem.get(hadoop_conf)
    path = spark._jvm.org.apache.hadoop.fs.Path(output_path)

    if fs.exists(path):
        fs.delete(path, True)  # True means recursive delete


def load_iris_dataframe(spark, input_path):
    print("\nReading the csv file\n")

    # Load the CSV file
    df = spark.read.csv(input_path, header=True, inferSchema=True)
    df_count = df.count()
    print("\nThe original dataframe have: ", df_count, " rows\n")

    df_petal = df.where(F.col("petal_length") > 2).orderBy(["species", "petal_length"])
    df_count = df_petal.count()

    # Show the DataFrame
    df_petal.show(df_count)
    print("\nShow the entire dataframe: ", df_count, " rows\n")

    return df_petal


def save_df_to_parquet(df, output_path):
    # Coalesce the DataFrame into a single partition and write to Parquet
    print("\nSaving dataframe to parquet files into: ", output_path, "\n")
    df.coalesce(1).write.parquet(output_path)


if __name__ == "__main__":
    # Check if sufficient arguments are provided
    if len(sys.argv) != 3:
        print("Usage: python project1.py <input_csv> <output_path>")
        sys.exit(1)
    
    # Get input and output paths from command-line arguments
    input_path = sys.argv[1]
    output_path = sys.argv[2]

    # Call the function to load the Iris dataset
    df = load_iris_dataframe(spark, input_path)

    # Check if output path exists and delete it
    delete_output_path_if_exists(spark, output_path)
    
    # Save df into a parquet file
    save_df_to_parquet(df, output_path)

    # Stop the SparkSession after the process is complete
    spark.stop()
