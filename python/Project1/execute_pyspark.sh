#!/bin/bash

# Dynamically determine the base path (project root)
BASE_DIR=$(dirname $(realpath "$0"))

# Load the properties from the project.properties file
source "$BASE_DIR/src/main/resources/properties/project.properties"

# Remove the existing logs directory and recreate it
rm -rf $LOGS_DIR
mkdir -p $LOG_OUTPUT
mkdir -p $LOG_SPARK

# env variables
export SPARK_HOME=$SPARK_HOME
export PYSPARK_PYTHON=$PYSPARK_PYTHON
export PATH=$SPARK_HOME/bin:$PATH

export JAVA_HOME=$JAVA_HOME
export PATH=$JAVA_HOME/bin:$PATH

source ~/.bashrc

# Charge conda into the actual shell
source $CONDA_SHELL

# Activate the conda environment pyspark_env
conda activate $CONDA_ENVIROMENT

# Message for success
echo "###########################################"
echo "# $CONDA_ENVIROMENT conda environment activated #"
echo "###########################################"

echo ""

# Message for python executing the scrip
echo "###############################################"
echo "# executing python pyspark script for project #"
echo "###############################################"

echo ""

# Run spark-submit and pass the log file name dynamically
$SPARK_HOME/bin/spark-submit \
  --master local[*] \
  --files $LOG4J_DIR \
  --conf "spark.driver.extraJavaOptions=-Dlogfile.name=$SPARK_LOG_PATH -Dlog4j.configuration=file:$LOG4J_DIR" \
  --conf "spark.executor.extraJavaOptions=-Dlogfile.name=$SPARK_LOG_PATH -Dlog4j.configuration=file:$LOG4J_DIR" \
  $PROJECT_SCRIP $INPUT_CSV_IRIS $OUTPUT_PARQUET_PATH \
  > "$OUTPUT_LOG_PATH" 2>&1  # Redirect stdout and stderr to the output log

# Message for python final of the scrip
echo "##################################"
echo "# python pyspark script finished #"
echo "##################################"

# Check the exit status of spark-submit
if [ $? -eq 0 ]; then
  echo "Spark job completed successfully."
else
  echo "Error: Spark job failed."
  exit 1
fi