package $package$.clients

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import uk.co.telegraph.utils.client.GenericClient
import uk.co.telegraph.utils.client.http.impl.HttpClient
import uk.co.telegraph.utils.client.http.scaladsl.HttpClientImpl

trait SimpleClient extends GenericClient { this: HttpClient =>
}

object SimpleClient {

  val ConfigPath = "app.simple-client"

  def apply()(implicit system:ActorSystem, materializer:ActorMaterializer):SimpleClient =
    new HttpClientImpl(ConfigPath) with SimpleClient
}
