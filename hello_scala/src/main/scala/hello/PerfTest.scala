package hello

import breeze.linalg.{DenseVector, linspace}
import breeze.signal.fourierTr
import breeze.linalg
import java.lang.Math.PI
import java.util.concurrent.{ExecutorService, Executors, FutureTask}

import breeze.math.Complex
import breeze.numerics.sin
import jcuda._
import jcuda.runtime._
import jcuda.jcufft.{JCufft, cufftHandle, cufftType}
import org.jtransforms.fft.FloatFFT_1D

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}
import scala.collection.parallel.CollectionConverters._
object PerfTest {

  val size = 1000
  implicit val times = 100000*3

  implicit val executor :ExecutionContext = ExecutionContext.parasitic

  def main(args: Array[String]): Unit = {
    test(testBreeze)
    test(testJTrans)
    test(testCUDAFft2)
  }

  def test(f: (Int)=>Unit):Unit = {
    val t0 = System.currentTimeMillis()
    f(times)
    val t9 = System.currentTimeMillis()
    println(s"$f ${t9-t0}ms")
  }

  def testJTrans(times: Int): Unit = {
    var x: Array[Float] = linspace(0, 1, size*2).toArray.map( _.toFloat)
    val res = Array.ofDim[Float](size*2)
    val fft =new FloatFFT_1D(size)
    (0 to times).par.map { i =>
      fft.complexForward(res)
    }
  }

  def testCUDAFft2(times: Int): Unit = {
    var x: Array[Float] = linspace(0, 1, size*2).toArray.map( _.toFloat)
    val res = Array.ofDim[Float](size*2)
    val plan =new cufftHandle()
    JCufft.cufftPlan1d(plan, size, cufftType.CUFFT_C2C, 1)
    (0 to times).map { i =>
      JCufft.cufftExecC2C(plan, res, res, JCufft.CUFFT_FORWARD)
    }
    JCufft.cufftDestroy(plan)
  }

  def testBreeze(times: Int): Unit ={

    val res = Promise[DenseVector[Complex]]
    val x = linspace(0, 1, size*2)
    (0 to times).par.map { i =>
      Future {
        val data = fourierTr(x)
        res.success(data)
      }
    }
    res.future.onComplete {
      case Success(value) => println(s"succese: $value")
      case Failure(exception) => println("error")
    }

  }

}
