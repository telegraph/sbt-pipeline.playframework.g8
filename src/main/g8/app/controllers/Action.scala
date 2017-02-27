package controllers

import java.util.UUID

import concurrent.Execution
import org.slf4j.MDC
import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

trait MDCAction extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
    block(request)
  }

  override def executionContext: ExecutionContext = Execution.defaultContext
}

case class BasicAuthAction(username: String, password: String) extends ActionBuilder[Request] with ActionFilter[Request]{

  private val unauthorized =
    Results.Unauthorized.withHeaders("WWW-Authenticate" -> "Basic realm=Unauthorized")

  def filter[A](request: Request[A]): Future[Option[Result]] = {
    val result = request.headers.get("Authorization") map { authHeader =>
      val (user, pass) = decodeBasicAuth(authHeader)
      if (user == username && pass == password) None else Some(unauthorized)
    } getOrElse Some(unauthorized)

    Future.successful(result)
  }

  private [this] def decodeBasicAuth(authHeader: String): (String, String) = {
    try {
      val baStr = authHeader.replaceFirst("Basic ", "")
      val decoded = new sun.misc.BASE64Decoder().decodeBuffer(baStr)
      val Array(user, password) = new String(decoded).split(":")
      (user, password)
    }catch {
      case e: Exception => ("","")
    }
  }
}


object LoggedAction extends MDCAction{
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    try {
      MDC.put("eventId", UUID.randomUUID().toString)
      super.invokeBlock(request, block)
    } finally {
      MDC.clear()
    }
  }
}







