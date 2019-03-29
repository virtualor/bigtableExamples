package cn.rojao.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Date:2019/3/29
  * Author:jianxian mao
  * Describe:
  */
object WordCount_Online {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("WordCount_Online")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("WARN")
    val line = sparkContext.textFile(args(0))
    val words = line.flatMap(_.split(" "))
    val wordAndOne:  RDD[(String, Int)] = words.map((_,1))
    val result: RDD[(String, Int)] = wordAndOne.reduceByKey(_+_)
    val finalResult: RDD[(String, Int)] = result.sortBy(_._2,false)
    finalResult.saveAsTextFile(args(1));
    sparkContext.stop();

  }

}
