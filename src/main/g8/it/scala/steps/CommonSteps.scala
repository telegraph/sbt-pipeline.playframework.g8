package steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import org.hamcrest.Matchers._
import CucumberEnvironment._
import io.restassured.RestAssured._
import io.restassured.http.Header
import io.restassured.specification.RequestSpecification
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse
import io.restassured.response.ValidatableResponseOptions

class CommonSteps extends ScalaDsl with EN with Matchers{

  Before(){ _ =>
    resetCtx()
  }

  Given("""^the service$"""){ () =>
    hold("request"){
      given().baseUri(UsageApiHost).port(UsageApiPort)
    }
  }

  When("""^I invoke GET '(.+)'$""") { endpoint: String =>
    hold("response"){
      pick[RequestSpecification]("request"){ _
        .when()
        .get(endpoint)
        .Then()
      }
    }
  }

  When("""I invoke form POST '(.+)' with payload '(.+)'""") { (endpoint:String, payload:String) =>
    hold("response"){
      pick[RequestSpecification]("request"){ _
        .when()
        .body(payload)
        .header(new Header("Content-Type", "application/x-www-form-urlencoded"))
        .header(new Header("Accept", "application/json"))
        .post(endpoint)
        .Then()
      }
    }
  }

  Then("I should get a status code ([\\d]+)"){ (statusCode:Int) =>
    pick[ValidatableResponseOptions[_,_]]("response") { response =>
      response.statusCode(statusCode)
    }
  }

  Then("I should get a '(.+)' Content-Type"){ (contentType:String) =>
    pick[ValidatableResponseOptions[_,_]]("response") { response =>
      response.header("content-type", contentType)
    }
  }

  Then("the payload should match"){ payload:String =>
    val expected = payload.replaceAll("\\s", "")

    pick[ValidatableResponseOptions[_,_]]("response") { response =>
      response.body(equalTo(expected))
    }
  }
}
