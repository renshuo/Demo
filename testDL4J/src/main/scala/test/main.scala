package test

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator
import org.deeplearning4j.nn.api.OptimizationAlgorithm
import org.deeplearning4j.nn.conf.inputs.InputType
import org.deeplearning4j.nn.conf.layers.{ConvolutionLayer, DenseLayer, OutputLayer, SubsamplingLayer}
import org.deeplearning4j.nn.conf.{MultiLayerConfiguration, NeuralNetConfiguration}
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork
import org.deeplearning4j.nn.weights.WeightInit
import org.deeplearning4j.optimize.listeners.{EvaluativeListener, ScoreIterationListener}
import org.nd4j.evaluation.classification.Evaluation
import org.nd4j.linalg.activations.Activation
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator
import org.nd4j.linalg.learning.config.Nadam
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction
import org.nd4j.linalg.api.ndarray.INDArray
import org.slf4j.{Logger, LoggerFactory}

object main {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    log.info("hello world")
    val batchSize = 28
    val rngSeed = 123
    val numRows= 28
    val numColumns = 28
    val outputNum = 10
    val numEpochs = 15

    val mnistTrain =new MnistDataSetIterator(batchSize, true, rngSeed)
    val mnistTest  =new MnistDataSetIterator(batchSize, false, rngSeed)

    val conf =new NeuralNetConfiguration.Builder()
      .cudnnAlgoMode(ConvolutionLayer.AlgoMode.NO_WORKSPACE)
      .seed(rngSeed)
      .updater(new Nadam())
      .l2(1e-4)
      .list()
      .setInputType(InputType.convolutionalFlat(28,28,1))
      .layer(new ConvolutionLayer.Builder()
          .kernelSize(5,5)
          .nIn(1)
          .stride(1, 1)
          .nOut(20)
          .activation(Activation.IDENTITY)
        .build())
      .layer(new SubsamplingLayer.Builder()
          .kernelSize(2, 2)
          .stride(2, 2)
        .build())
      .layer(new DenseLayer.Builder() // 全连接层
          .nOut(1000)
          .activation(Activation.RELU)
          .weightInit(WeightInit.XAVIER)
          .build())
      .layer(new OutputLayer.Builder()
          .lossFunction(LossFunction.NEGATIVELOGLIKELIHOOD)
          .nIn(1000)
          .nOut(outputNum)
          .activation(Activation.SOFTMAX)
          .weightInit(WeightInit.XAVIER)
          .build())
      .build()

    val model =new MultiLayerNetwork(conf)
    model.init()

    print("Training ")
    model.setListeners(new ScoreIterationListener(1), new EvaluativeListener(mnistTest, 300))
    model.fit(mnistTrain, numEpochs)

    print("evaluate ")
    val eval: Evaluation = model.evaluate(mnistTest)
    print(s"eval: ${eval.stats()}")

    print("finised")

//      conf.setIterationCount(1)
//
//
//      .seed(rngSeed)
//      .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)




  }
}
