package concurrent

import org.slf4j.MDC

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}


object MDCExecutionContext {
  def apply(delegate: ExecutionContext): ExecutionContextExecutor = new MDCExecutionContext(MDC.getCopyOfContextMap, delegate)
}

class MDCExecutionContext(mdcContext: java.util.Map[String,String], delegate: ExecutionContext) extends ExecutionContextExecutor {
  def reportFailure(cause: Throwable) = delegate.reportFailure(cause)

  def execute(command: Runnable) = delegate.execute(new Runnable {
    def run() = {
      val oldMDC = MDC.getCopyOfContextMap
      setContextMap(mdcContext)
      try{
        command.run()
      } finally {
        setContextMap(oldMDC)
      }
    }
  })

  private[this] def setContextMap(context: java.util.Map[String,String]) = {
    if(context == null){
      MDC.clear()
    }else{
      MDC.setContextMap(context)
    }
  }
}
