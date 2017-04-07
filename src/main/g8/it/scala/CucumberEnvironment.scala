import com.typesafe.config.ConfigFactory

import scala.collection.mutable.{Map => mMap}
package object CucumberEnvironment {
  private val dataCache:mMap[String, Any] = mMap.empty
  private lazy val Config = ConfigFactory.load("test-integration-preprod.conf")

  def resetCtx() = {
    dataCache.empty
  }

  def hold(name:String)(value:Any) = dataCache += (name -> value)

  def pick[T]( dataName:String)(f: T => Any) = f(dataCache(dataName).asInstanceOf[T])

  lazy val UsageApiHost = Config.getInt("server.http.host")
  lazy val UsageApiPort = Config.getInt("server.http.port")
}

