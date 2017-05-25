import com.waioeka.sbt.runner.CucumberRunner
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features"),
  plugin   = Array(
    "html:./target/html",
    "json:./target/test-report.json"
  )
)
class SampleTest extends CucumberRunner{
}
