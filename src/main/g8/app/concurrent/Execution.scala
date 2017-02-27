package concurrent

import scala.concurrent.ExecutionContext

object Execution {
  object Implicits {
    implicit def defaultContext: ExecutionContext = Execution.defaultContext
  }

  def defaultContext: ExecutionContext = MDCExecutionContext(play.api.libs.concurrent.Execution.defaultContext)
}
