// Databricks notebook source
val df = spark
  .read
  .option("inferSchema","true")
  .option("header","true")
  .csv("/databricks-datasets/data.gov/farmers_markets_geographic_data/data-001/market_data.csv")

// COMMAND ----------

df.columns.length

// COMMAND ----------

// rows of observations
df.count()

// COMMAND ----------

// header columns
df.columns

// COMMAND ----------

df.createOrReplaceTempView("market_data")

// COMMAND ----------

//How many farmer's markets are in your zip code? In your state? In the country?
val marketzip = spark.sql("""
  SELECT MarketName, COUNT(MarketName)
  FROM market_data
  WHERE zip == "94015"
  GROUP BY MarketName
""").show()



// COMMAND ----------

val market_by_state = spark.sql("""
  SELECT State, COUNT(MarketName)
  FROM market_data
  WHERE State == "California"
  GROUP BY State
""").show()

// COMMAND ----------

// Number of Market in the country
import org.apache.spark.sql.functions.countDistinct
df.agg(countDistinct('MarketName)).show()


// COMMAND ----------

//What farmer's market has the longest name?
spark.sql("""
  SELECT MarketName
  FROM market_data
  ORDER BY Length(MarketName) desc
  LIMIT 1
""").show(100, false)

// COMMAND ----------

//What are the top ten states with the most farmer's markets?

val d = spark.sql("""
  SELECT State, count("MarketName")
  FROM market_data
  GROUP BY State
  ORDER BY count("MarketName") DESC
  LIMIT 10
""")

display(d)

// COMMAND ----------

//Display visualizations. Explore plot options.

display(df)
