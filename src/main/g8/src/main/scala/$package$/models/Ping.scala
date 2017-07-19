package $package$.models

import java.time.ZonedDateTime.now
import io.swagger.annotations.{ApiModel, ApiModelProperty}
import org.json4s.jackson.JsonMethods
import org.json4s.{DefaultFormats, Formats, Extraction}

@ApiModel(value="Simple Ping model")
case class Ping
(
  @ApiModelProperty(name = "name",     example = "service-x",                     required = true) name    : String,
  @ApiModelProperty(name = "dateTime", example = "2017-01-01T00:00:00.000+00:00", required = true) dateTime: String = now().toString
){

  override def toString: String = {
    implicit val formats:Formats = DefaultFormats
    JsonMethods.compact( Extraction.decompose(this))
  }
}
