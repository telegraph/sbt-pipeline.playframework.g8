package controllers

import javax.inject.Inject

import concurrent.Execution.Implicits._
import io.swagger.annotations._
import play.api.Logger
import play.api.http.HttpEntity
import play.api.libs.Files.TemporaryFile
import play.api.libs.json.{Json, Writes}
import play.api.libs.ws.{StreamedResponse, WSResponse}
import play.api.mvc._
import utils.{ExternalClient}

import scala.concurrent.Future

@Api(value = "Admin")
class Admin @Inject()(configuration: play.api.Configuration) extends Controller {

  private val WithBasicAuth = new BasicAuthAction(configuration.getString("auth.user").get, configuration.getString("auth.pwd").get)
  private val appVersion = configuration.getString("api.version").getOrElse("0.0.0")

  @ApiOperation(value = "Indicate the health value of the service and the services that it connects to")
  @ApiResponses(Array(new ApiResponse (code = 200, message = "a Json object containing the healthcheck of the service")))
  def health() = Action.async{
    Future{
      Ok(Json.toJson(new HealthResponse(appVersion, ExternalClient.externalClients)))
    }
  }

  @ApiOperation(value = "Obtain the configuration of the service")
  @ApiResponses(Array(new ApiResponse (code = 200, message = "a Json object containing the configuration of the service")))
  def conf() = WithBasicAuth.async{
    Future{
      Ok(Json.toJson(configuration.toString))
    }
  }

  case class HealthResponse(version: String, externalClients: Seq[ExternalClient])
  implicit val healthResponseWrites = new Writes[HealthResponse] {
    def writes(healthResponse: HealthResponse) = Json.obj(
      "version" -> healthResponse.version,
      "clients" -> healthResponse.externalClients
    )
  }
}