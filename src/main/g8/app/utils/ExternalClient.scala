package utils

import java.util.Date

import akka.actor.ActorSystem
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.Logger
import play.api.libs.json.{Json, Writes}
import play.api.mvc.Results.Status

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

trait ExternalClient {
  val name:String = this.getClass.getName
  private var status:Status = play.api.mvc.Results.Ok
  private var date: DateTime = DateTime.now()
  private var msg: String = ""
  private var healthCheckConn: String = ""


  final def doHealthCheck()(implicit ec: ExecutionContext) = {
    Logger.debug(s"doing HealthCheck for \$name")
    try {
      healthCheck().map(statusResponse => {
        status = statusResponse
        msg = status.header.reasonPhrase.getOrElse("-")
        healthCheckConn = healthCheckConnStr()
        date = DateTime.now()
      })
    } catch {
      case e: Exception => {
        status = play.api.mvc.Results.InternalServerError
        msg = e.getMessage
        healthCheckConn = healthCheckConnStr()
        date = DateTime.now()
      }
    }
  }

  def init(system: ActorSystem)(implicit ec: ExecutionContext) = {
    ExternalClient.addClient(this)
    system.scheduler.schedule(1 second, 1 minute){
      doHealthCheck()
    }
  }

  protected def healthCheckConnStr()(): String
  protected def healthCheck()(implicit ec: ExecutionContext): Future[Status]
}

object ExternalClient {
  var externalClients: Seq[ExternalClient] = Seq()

  private def addClient(client: ExternalClient) = {
    externalClients = externalClients :+ client
  }

  implicit val externalClientWrites = new Writes[ExternalClient] {
    val dtfOut = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    def writes(externalClient: ExternalClient) = Json.obj(
      "name" -> externalClient.name,
      "status" -> externalClient.status.header.status,
      "date-time" -> externalClient.date,
      "date-time-str" -> dtfOut.print(externalClient.date),
      "healthCheckConn" -> externalClient.healthCheckConn,
      "msg" -> externalClient.msg
    )
  }
}
