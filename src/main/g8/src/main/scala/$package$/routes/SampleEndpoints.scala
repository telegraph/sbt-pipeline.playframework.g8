package $package$.routes

import javax.inject.Inject

import io.swagger.annotations._
import play.api.http.MimeTypes
import play.api.mvc._
import $package$.models.Ping
import play.api.Configuration

class SampleEndpoints @Inject()
(
  controllerComponents  :ControllerComponents,
  config:Configuration
) extends AbstractController(controllerComponents) {

  val serviceName = config.get[String]("app.name")

  @ApiOperation(value = "Endpoint Example")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "simple ping example", response = classOf[Ping])
  ))
  def ping = Action { _ =>
    val ping = Ping( name = serviceName )
    Ok(ping.toString).as(MimeTypes.JSON)
  }
}
