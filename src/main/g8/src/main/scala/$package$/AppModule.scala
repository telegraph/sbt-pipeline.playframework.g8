package $package$

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.{AbstractModule, Provides}
import com.google.inject.Key.get
import com.google.inject.multibindings.Multibinder.newSetBinder
import $package$.clients.SimpleClient
import uk.co.telegraph.utils.client.GenericClient

class AppModule extends AbstractModule{

  override def configure(): Unit = {
    val genericClients = newSetBinder(binder(), get(classOf[GenericClient]))
    //TODO: Set here the clients to be monitored
    genericClients.addBinding().to(classOf[SimpleClient])
  }

  @Provides
  def materializerProvider(implicit system:ActorSystem):ActorMaterializer =
    ActorMaterializer()
  
  //TODO: Create different client providers here!
  @Provides
  def simpleClientProvider(implicit system:ActorSystem, materializer:ActorMaterializer):SimpleClient =
    SimpleClient()
}
