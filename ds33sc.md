## Data Science Unit 3 Sprint Challenge 3 — Big Data

This Sprint Challenge has four parts. You'll use [Amazon SageMaker](https://console.aws.amazon.com/sagemaker) for the first two, then [Databricks](https://community.cloud.databricks.com/login.html) for the next two.


# Part 1. SageMaker & Dask
In this part, you'll work with a dataset of [YouTube Spam Comments](https://christophm.github.io/interpretable-ml-book/spam-data.html).

> We work with 1956 comments from 5 different YouTube videos. The comments were collected via the YouTube API from five of the ten most viewed videos on YouTube in the first half of 2015. All 5 are music videos. One of them is “Gangnam Style” by Korean artist Psy. The other artists were Katy Perry, LMFAO, Eminem, and Shakira.

> The comments were manually labeled as spam or legitimate. Spam was coded with a “1” and legitimate comments with a “0”.

Start an Amazon SageMaker Notebook instance. (Any instance type is ok. This can take a few minutes.) Open Jupyter. 

## Solution:
Load the five csv files into one Dask Dataframe. It should have a length of 1956 rows, and 5 columns.
```dask

import dask.dataframe as dd

df = dd.read_csv('Youtube*.csv')
len(df), len(df.columns)
(1956, 5) 
```

Use the Dask Dataframe to compute the counts of spam (1005 comments) versus the counts of legitimate comments (951).
```dask
df['CLASS'].value_counts().compute()
Out[2]:
1    1005
0     951
Name: CLASS, dtype: int64
```

Spammers often tell people to check out their stuff! When the comments are converted to lowercase, then spam comments contain the word "check" 461 times, and legitimate comments contain the word "check" just 19 times. Use the Dask Dataframe to compute these counts.
```dask
df['CONTENT'] = df['CONTENT'].str.lower()
In [4]:
spam = df[df['CLASS'] == 1]

spam['CONTENT'].str.contains('check').value_counts().compute()
Out[4]:
False    544
True     461
Name: CONTENT, dtype: int64
In [5]:
legit = df[df['CLASS'] == 0]

legit['CONTENT'].str.contains('check').value_counts().compute()
Out[5]:
False    932
True      19
Name: CONTENT, dtype: int64
```

### Terminal
In the Jupyter dashboard, choose **New**, and then choose **Terminal.**

Run these commands in the terminal:

1. Upgrade Dask in the conda environment named python3. (This command upgrades Bokeh too, even though you don't need to use it, because the packages seem to have dependencies. This can take a few minutes.)
```
conda install -n python3 bokeh dask
```

2. Change directory to SageMaker
```
cd SageMaker
```

3. Download data
```
wget https://archive.ics.uci.edu/ml/machine-learning-databases/00380/YouTube-Spam-Collection-v1.zip
```

4. Unzip data
```
unzip YouTube-Spam-Collection-v1.zip
```

5. See there are five csv files
```
ls *.csv
```

Then you can close the terminal window. 

### Notebook
Create a new notebook, with the **conda_python3** kernel.

For this Sprint Challenge, you *don't* need to create a Dask Distributed Client. You can just use a Dask Dataframe.

Load the five csv files into one Dask Dataframe. It should have a length of 1956 rows, and 5 columns.

Use the Dask Dataframe to compute the counts of spam (1005 comments) versus the counts of legitimate comments (951).

Spammers often tell people to check out their stuff! When the comments are converted to lowercase, then 461 spam comments contain the word "check", versus only 19 legitimate comments which contain the word "check." Use the Dask Dataframe to compute these counts.

### Optional bonus
To score a 3, do extra work, such as creating the Dask Distributed Client, or creating a visualization with this dataset.


# Part 2. Big data options
You've been introduced to a variety of platforms (AWS SageMaker, AWS EMR, Databricks), libraries (Numba, Dask, MapReduce, Spark), and languages (Python, SQL, Scala, Java) that can "scale up" or "scale out" for faster processing of big data.

Write a paragraph comparing some of these technology options. For example, you could describe which technology you may personally prefer to use, in what circumstances, for what reasons.

(You can add your paragraph as a Markdown cell at the bottom of your SageMaker Notebook.)

### Optional bonus
To score a 3, create a diagram comparing some of these technology options, or a flowchart to illustrate your decision-making process. 

You can use text-based diagram tools, such as:
- https://www.tablesgenerator.com/markdown_tables
- https://mermaidjs.github.io/mermaid-live-editor/

Or you can use presentation or drawing software, and commit your diagram to your GitHub repo as an image file. Or sketch on the back of a napkin, and take a photo with your phone. (If you choose to create a diagram, then you should also consider publishing it with a blog post later, after the Sprint Challenge.)

### GitHub
Commit your SageMaker notebook for parts 1 & 2 to GitHub. You can use git directly from the SageMaker terminal. Or you can download the .ipynb file from SageMaker to your local machine, and then commit the file to GitHub.

### Stop your instance
Stop your SageMaker Notebook instance, so you don't use excessive AWS credits. 


# Part 3. Just enough Scala syntax
For this part, sign in to [Databricks Community Edition](https://community.cloud.databricks.com/login.html), and create a Scala notebook.

### Sum of squares
If we list all the square numbers below 100, we get 1, 4, 9, 16, 25, 36, 49, 64, 81. The sum of these squares is 285.
```scala
val bound = 100
(0 to bound)  // Not efficient but it's ok!
.map(x => x * x)
.filter(_ < bound)
.reduce(_ + _)
bound: Int = 100
res29: Int = 285
```
Write Scala code to find the sum of all the square numbers below 1000.
```scala
val bound = 1000
(0 to bound)  // Not efficient but it's ok!
.map(x => x * x)
.filter(_ < bound)
.reduce(_ + _)
bound: Int = 1000
res30: Int = 10416
```

### Optional bonus
To score a 3, write a Scala program that prints the numbers from 1 to 100. But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz.” For numbers which are multiples of both three and five print “FizzBuzz.”
```scala
def fizzbuzz(n: Int) = {
  if (n % 15 == 0)
    println("FizzBuzz")
  else if (n % 3 == 0)
    println("Fizz")
  else if (n % 5 == 0)
    println("Buzz")
  else
    println(n)
}

val range = 1 to 100
range.foreach(fizzbuzz)

1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
FizzBuzz
16
17
Fizz
19
Buzz
Fizz
```


# Part 4. Spark SQL / DataFrame API

In this part, you'll work with data that compares city population versus median sale prices of homes. This is an example Databricks dataset available at this path:
```
/databricks-datasets/samples/population-vs-price/data_geo.csv
```

  
Load the data into a Spark dataframe. Infer the schema and include the header.
```scala
val df = spark
  .read
  .option("inferSchema", "true")
  .option("header", "true")
  .csv("/databricks-datasets/samples/population-vs-price/data_geo.csv")
  ```
Write code to show the dataframe has 294 rows and 6 columns.
```scala
(df.count(), df.columns.size)
res33: (Long, Int) = (294,6)
```

Drop rows containing any null values. For example, if you named your dataframe `df`, you could use code like this:
```
val df2 = df.na.drop()
```


(I'm giving you this code now because I didn't teach this method earlier in the week, but it's mentioned in documentation: http://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.sql.Dataset@na:org.apache.spark.sql.DataFrameNaFunctions )

Write code to show the cleaned dataframe has 109 rows, after dropping rows with null values.

```scala
df2.count()
res35: Long = 109

display(df2)

```
The cleaned dataframe has 1 city for the state of Utah. Display the name of the city, using either Spark SQL or the DataFrame API.
```scala
df2.createOrReplaceTempView("data")

display(spark.sql("""
SELECT *
FROM data
WHERE State = 'Utah'
"""))
```

Sort the cleaned dataframe by 2015 median sales price (either ascending or descending), using either Spark SQL or the DataFrame API. Your results will display that San Jose, California was most expensive in this dataset ($900,000) and Rockford, Illinois was least expensive ($78,600). 

(With Spark SQL, you can surround column names with backtick characters. This is useful when column names contain spaces or special characters.)
```scala

display(spark.sql("""
SELECT *
FROM data 
ORDER BY `2015 median sales price` DESC
"""))
```


(If you want, you can also display a map with this query. Go to https://docs.databricks.com/user-guide/visualizations/index.html#visualization-types for the documentation. For chart type, choose Map. For Plot Options, choose Keys: State Code, Values: 2015 median sales price, Aggregation: AVG.)
```scala
display(spark.sql("""
SELECT *
FROM data 
ORDER BY `2015 median sales price` DESC
"""))
```

### Optional bonus
To score a 3, do the questions for the cleaned dataframe both with Spark SQL *and* the DataFrame API.

### Commit to GitHub
Export your Databricks notebook as an HTML file. Commit the file to your GitHub repo.
