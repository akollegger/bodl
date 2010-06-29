package bodl.sandbox.aiml.betty.bot.parser

import org.specs.SpecificationWithJUnit

class AimlPatternTest extends SpecificationWithJUnit("parse aiml\\category\\pattern") {

  val SINGLE_PATTERN = <pattern>WHAT ARE YOU</pattern>

  "aiml pattern" should {
    "accept an aiml snippet" in {
      val pattern = AimlPattern(SINGLE_PATTERN)
      pattern must beSome[AimlPattern]
    }
  }

}