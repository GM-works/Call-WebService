package myorg.com

import scala.concurrent.{Future,Await, ExecutionContext}
import scala.concurrent.duration._
import scala.util._

import dispatch.Defaults._
import dispatch.{Http, url}


object CallWebService 
{
  
  def main(args:Array[String]): Unit=
  {
      val request = url("http://api.hostip.info/get_json.php?ip=12.215.42.19")
      val requestAsGet = request.GET //not required but lets be explicit

    
       val content = Await.ready(Http(requestAsGet), 5.seconds)
       content onSuccess {

    
      case x if x.getStatusCode() == 200 =>
            println(x.getResponseBody)
      case y => 
        println("Failed with status code" + y.getStatusCode())
    }
    
    content onFailure {
      case x =>
        println("Failed but"); println(x.getMessage)
    }
  }
}
