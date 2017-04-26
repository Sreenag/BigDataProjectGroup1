import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

object grp1_ann{
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("prg1_ann").getOrCreate()
    // Load the data stored in LIBSVM format as a DataFrame.
    val data = spark.read.format("libsvm").load(args(0))
    // Split the data into train and test
    val splits = data.randomSplit(Array(0.7, 0.3), seed = 1234L)
    val train = splits(0)
    val test = splits(1)
    // specify layers for the neural network:
    // input layer of size 4 (features), two intermediate of size 5 and 4
    // and output of size 3 (classes)
    val layers = Array[Int](4, 5, 4, 3)
    // create the trainer and set its parameters
    val trainer = new MultilayerPerceptronClassifier()
      .setLayers(layers)
      .setBlockSize(128)
      .setSeed(1234L)
      .setMaxIter(100)
    // train the model
    val model = trainer.fit(train)
    // compute precision on the test set
    val result = model.transform(test)
    val predictionAndLabels = result.select("prediction", "label")
result.select("prediction", "label").show()
    val evaluator = new MulticlassClassificationEvaluator()
      .setMetricName("accuracy")
    println("Precision:" + evaluator.evaluate(predictionAndLabels))
    spark.stop()
  }
}

