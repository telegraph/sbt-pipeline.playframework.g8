package $package$.routes

import javax.inject.Inject

import io.swagger.annotations._
import org.json4s.DefaultFormats
import play.api.http.MimeTypes
import play.api.mvc._
import $package$.models.Ping
import org.json4s.jackson.Serialization._
import play.api.Configuration

class SampleEndpoints @Inject() (config:Configuration ) extends Controller {

  val serviceName = config.getString("app.name").getOrElse("unknown-service")

  @ApiOperation(value = "Endpoint Example")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "simple ping example", response = classOf[Ping])
  ))
  def ping = Action { _ =>
    implicit val formats = DefaultFormats

    val ping = Ping( name = serviceName )
    Ok(write(ping)).as(MimeTypes.JSON)
  }
}
