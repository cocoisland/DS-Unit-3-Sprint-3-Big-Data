// Databricks notebook source
def sq(x:Int) = x*x

// COMMAND ----------

var x = 1
var sum_of_square = 0
while (sq(x) < 1000){
  sum_of_square += sq(x)
  x += 1
}
println(sum_of_square)

// COMMAND ----------

val df = spark
  .read
  .option("inferSchema","true")
  .option("header","true")
  .csv("/databricks-datasets/samples/population-vs-price/data_geo.csv")

// COMMAND ----------

df.columns

// COMMAND ----------

df.count()

// COMMAND ----------

val df2 = df.na.drop()

// COMMAND ----------

df2.count()

// COMMAND ----------

df2.createOrReplaceTempView("home_data")

// COMMAND ----------

spark.sql("""
  SELECT *
  FROM home_data
  WHERE `State Code` == "UT"
  LIMIT 5
""").show()

// COMMAND ----------

val top_sales_price = spark sql("""
  SELECT *
  FROM home_data
  ORDER BY (`2015 median sales price`) desc
  LIMIT 10
""")

// COMMAND ----------

top_sales_price.show()

// COMMAND ----------

spark.sql("""
  SELECT *
  FROM home_data
  ORDER BY (`2015 median sales price`) asc
  LIMIT 10
""").show()
