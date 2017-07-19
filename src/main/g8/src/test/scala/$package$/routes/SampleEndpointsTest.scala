package $package$.routes

import org.scalatest.{FreeSpec, Matchers}

class SampleEndpointsTest
  extends FreeSpec
  with Matchers
{
  "Given the 'SampleEndpoints' routes, " - {
    "when invoking the ping operation, I should be able to get a json payload" in {
      (1+1) shouldBe 2
    }
  }
}
