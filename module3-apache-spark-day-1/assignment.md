_Lambda School Data Science - Big Data_

# Apache Spark, day 1 - Assignment

### 1. Sign up for Databricks Community Edition

Today we’ll continue our tour of different cloud notebook platforms!

We’ll use **Databricks Community Edition** today, because it’s:

- Free! (Unlimited credits)
- Preconfigured, convenient
- Preloaded with datasets
- Made for Spark, by its creators

[**Please submit the form here,**](https://databricks.com/signup#signup/community) to sign up for your free account.

- _“Company Name” = “Lambda School”_
- _“What is your intended use case?” = “Personal - Learning Spark”_

### 2. [Sign in to Databricks Community Edition](https://community.cloud.databricks.com/login.html)

### 3. Explore the Quickstart tutorial

From the **Welcome to databricks** page, click on **Explore the Quickstart tutorial.**

This will open a notebook. Follow the instructions to Create a quickstart cluster, Attach the notebook to the cluster and run all commands in the notebook.

(You don't have to type the commands yourself from scratch. The purpose here is just to see a preview of what you can do with Spark and Databricks, and verify it's working for you. Note that [Databricks documentation](https://docs.databricks.com/getting-started/quick-start.html) has more information about this tutorial.)

### 4. [Create a notebook](https://docs.databricks.com/user-guide/notebooks/notebook-manage.html#create-a-notebook) (Scala)

### 5. Estimate Pi

In your notebook, run the Pi Estimation example (in Scala) from the [Apache Spark Examples](https://spark.apache.org/examples.html). 

(First you'll need to assign an integer value to the `NUM_SAMPLES` constant.)

```scala
  val NUM_SAMPLES = math.pow(10,7).toInt

  val count = sc.parallelize(1 to NUM_SAMPLES).filter{ _ =>
  val x = math.random
  val y = math.random
  x*x + y*y < 1
}.count()
println(s"Pi is rough ${4.0 * count/NUM_SAMPLES}")
```
```scala
Pi is rough 3.1406896
count: Long = 7851724
Command took 6.24 seconds -- by ttony10101@gmail.com at 2/27/2019, 1:20:13 PM on scala_c2
```

How does the code compare to the `monte_carlo_pi` example on [Numba's homepage](http://numba.pydata.org/)?
import random

```python
from numba import njit
@njit
def monte_carlo_pi(nsamples):
    acc = 0
    for _ in range(int(nsamples)):
        x = random.random()
        y = random.random()
        if (x**2 + y**2) < 1.0:
            acc += 1
    return 4.0 * acc / nsamples
    
%%time
monte_carlo_pi(1e7)
```

```python
CPU times: user 283 ms, sys: 13.3 ms, total: 296 ms
Wall time: 1.44 s
Out[9]:
3.1412544

```


(Regarding the performance: Note that [Databricks Community Edition](https://databricks.com/try-databricks) is a "Single cluster limited to 6GB and no worker nodes.")

### 6. Do exercises from _Spark: The Definitive Guide_

#### Use nbviewer.jupyter.org to view the html file.
[html viewer](https://nbviewer.jupyter.org)

First, read [_Spark: The Definitive Guide_ excerpts](https://pages.databricks.com/rs/094-YMS-629/images/Apache-Spark-The-Definitive-Guide-Excerpts-R1.pdf), **Pages 1-21.**

Then, in your notebook, do the [code exercises from _Spark: The Definitive Guide_, **Chapter 2: A Gentle Introduction to Spark**](https://github.com/databricks/Spark-The-Definitive-Guide/blob/master/code/A_Gentle_Introduction_to_Spark-Chapter_2_A_Gentle_Introduction_to_Spark.scala).

**Important!** Note these instructions from the [repo README](https://github.com/databricks/Spark-The-Definitive-Guide/blob/master/README.md):
> Rather than you having to upload all of the data yourself, you simply have to **change the path in each chapter from `/data` to `/databricks-datasets/definitive-guide/data/flight-data/csv/2015-summary.csv`**. Once you've done that, all examples should run without issue. 

Next, read [_Spark: The Definitive Guide_ excerpts](https://pages.databricks.com/rs/094-YMS-629/images/Apache-Spark-The-Definitive-Guide-Excerpts-R1.pdf), **Pages 26-31.**

Then, in your notebook, do the [code exercises from _Spark: The Definitive Guide_, **Chapter 3: A Tour of Spark’s Toolset**](https://github.com/databricks/Spark-The-Definitive-Guide/blob/master/code/A_Gentle_Introduction_to_Spark-Chapter_3_A_Tour_of_Sparks_Toolset.scala), **only lines 28-105.** (You don't need to do the Datasets exercise at the beginning, or the Machine Learning exercise at the end.)

Do the exercises [**"the hard way"**](https://learnpythonthehardway.org/python3/intro.html):

> You will do the incredibly simple things that all programmers do to learn a programming language:
> 1. Go through each exercise.
> 2. Type in each _exactly._
> 3. Make it run.

### 7. Export and commit your notebook

[Export your notebook](https://docs.databricks.com/user-guide/notebooks/notebook-manage.html#export-a-notebook) as an HTML file. Commit the file to your GitHub repo.
