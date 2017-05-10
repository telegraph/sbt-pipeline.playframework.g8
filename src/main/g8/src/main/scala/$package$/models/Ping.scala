package $package$.models

import java.time.ZonedDateTime.now
import io.swagger.annotations.{ApiModel, ApiModelProperty}

@ApiModel(value="Simple Ping model")
case class Ping
(
  @ApiModelProperty(name = "name",     example = "service-x",                     required = true) name    : String,
  @ApiModelProperty(name = "dateTime", example = "2017-01-01T00:00:00.000+00:00", required = true) dateTime: String = now().toString
)
