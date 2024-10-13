package com.project1.scala

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.functions._

object CSVToParquet {

  def main(args: Array[String]): Unit = {
    // Check if sufficient arguments are provided
    if (args.length != 2) {
      println("Usage: CSVToParquet <input_csv> <output_path>")
      System.exit(1)
    }

    // Get input and output paths from command-line arguments
    val inputPath = args(0)
    val outputPath = args(1)

    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("TestApp")
      .master("local[*]")
      .getOrCreate()

    // Call the function to load the Iris dataset
    val df = loadIrisDataFrame(spark, inputPath)

    // Check if output path exists and delete it
    deleteOutputPathIfExists(spark, outputPath)

    // Save the DataFrame into a Parquet file
    saveDFToParquet(df, outputPath)

    // Stop the SparkSession after the process is complete
    spark.stop()
  }

  // Function to load the Iris DataFrame
  def loadIrisDataFrame(spark: SparkSession, inputPath: String): DataFrame = {
    println("\nReading the CSV file\n")

    // Load the CSV file
    val df = spark.read.option("header", "true").option("inferSchema", "true").csv(inputPath)
    val dfCount = df.count()
    println(s"\nThe original DataFrame has: $dfCount rows\n")

    // Filter and order the DataFrame
    val dfPetal = df.filter(col("petal_length") > 2).orderBy("species", "petal_length")
    val dfPetalCount = dfPetal.count()

    // Show the entire DataFrame
    dfPetal.show(dfPetalCount.toInt)
    println(s"\nShowing the entire DataFrame: $dfPetalCount rows\n")

    dfPetal
  }

  // Function to check if the output path exists and delete it
  def deleteOutputPathIfExists(spark: SparkSession, outputPath: String): Unit = {
    val hadoopConf = spark.sparkContext.hadoopConfiguration
    val fs = FileSystem.get(hadoopConf)
    val path = new Path(outputPath)

    if (fs.exists(path)) {
      fs.delete(path, true) // true for recursive delete
    }
  }

  // Function to save the DataFrame to a Parquet file
  def saveDFToParquet(df: DataFrame, outputPath: String): Unit = {
    println(s"\nSaving DataFrame to Parquet files at: $outputPath\n")
    df.coalesce(1).write.parquet(outputPath)
  }
}