
import org.scalatest.{BeforeAndAfter, FeatureSpec, OneInstancePerTest, Matchers}

class FakeTestSpec
  extends FeatureSpec
  with Matchers
{
  info("As a User")
  info("I want to be able to test 1+1=2")

  feature("Validate Math") {
    scenario("test 1+1 = 2") {
      (1+1) shouldBe 2
    }
  }

}
