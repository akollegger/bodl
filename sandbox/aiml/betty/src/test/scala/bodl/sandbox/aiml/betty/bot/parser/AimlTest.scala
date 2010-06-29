package bodl.sandbox.aiml.betty.bot.parser

import org.specs.SpecificationWithJUnit

class AimlTest extends SpecificationWithJUnit("try to parse some aiml snippets") {

  description = "parse simple snippets of aiml into model"

  val SINGLE_BRAIN = <aiml>
    <category>
        <pattern>WHAT ARE YOU</pattern>
        <template>
            <think><set name="topic">Me</set></think>
            I am the latest result in artificial intelligence
        </template>
    </category>
  </aiml>

  "the aiml model" should {
    "accept aiml xml" in {
      val aiml = Aiml(SINGLE_BRAIN)
      aiml must beSome[Aiml]
    }
    "ignore non-aiml xml" in {
      val notAiml = Aiml(<foo><bar>of course</bar></foo>)
      notAiml must beNone 
    }
    "create categories" in {
      val aiml = Aiml(SINGLE_BRAIN).get
      aiml.categories must notBeEmpty[Iterable[AimlCategory]]
    }
    "create pattern for WHAT ARE YOU category" in {
      val aiml = Aiml(SINGLE_BRAIN).get
      val whatAreYou = aiml.categories.head
      whatAreYou.pattern must notBeNull
    }
    "populate pattern with word sequence" in {
      val aiml = Aiml(SINGLE_BRAIN).get
      val whatAreYou = aiml.categories.head
      whatAreYou.pattern.tokens must notBeEmpty[Seq[String]]
      whatAreYou.pattern.tokens must containAll(List("WHAT", "ARE", "YOU"))
    }
    "create template for the category" in {
      val aiml = Aiml(SINGLE_BRAIN).get
      val whatAreYou = aiml.categories.head
      val template = whatAreYou.template
      template must notBeNull
    }
    "template must contain the think section" in {
      val aiml = Aiml(SINGLE_BRAIN).get
      val whatAreYou = aiml.categories.head
      val template = whatAreYou.template
      template.contents must notBeEmpty[Iterable[AimlTemplateElement]]
      template.contents must contain(AimlThink(<think><set name="topic">Me</set></think>).get)
    }
  }

}