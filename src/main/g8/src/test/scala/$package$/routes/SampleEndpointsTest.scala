package $package$.routes

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, FreeSpec, Matchers, OneInstancePerTest}
import play.api.Configuration
import play.api.http.MimeTypes
import play.api.test.FakeRequest
import play.api.test.Helpers._
import SampleEndpointsTest._

class SampleEndpointsTest
  extends FreeSpec
  with OneInstancePerTest
  with Matchers
  with BeforeAndAfterAll
{

  val endpoint = new SampleEndpoints(Configuration(Config))

  override protected def afterAll(): Unit = {
    ActorMaterializerTest.shutdown()
    ActorSystemTest.terminate()
  }

  "Given the 'SampleEndpoints' routes, " - {

    "when invoking the ping operation, I should be able to get a json payload" in {
      val request  = FakeRequest(GET, "/sampletest/ping")
      val response = endpoint.ping.apply(request)

      val content = contentAsString(response)
      val result  = await(response)

      result.body.contentType shouldBe Some(MimeTypes.JSON)
      result.header.status shouldBe OK
      content should include (AppName)
    }

  }
}

object SampleEndpointsTest {

  val Config  = ConfigFactory.load("application.tst.conf")
  val AppName = Config.getString("app.name")

  implicit val ActorSystemTest       = ActorSystem("sample-system", Config)
  implicit val ActorMaterializerTest = ActorMaterializer()
}
