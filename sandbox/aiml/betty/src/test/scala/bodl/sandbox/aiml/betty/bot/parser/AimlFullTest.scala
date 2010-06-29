package bodl.sandbox.aiml.betty.bot.parser

import org.specs.SpecificationWithJUnit

class AimlFullTest extends SpecificationWithJUnit("try to parse all aiml tags") {

  description = "parse the simplist aiml that contains all tags"

  val FULLY_TAGGED_BRAIN = <aiml>
    <category>
      <pattern>FROM *</pattern>
      <that>WHERE DO YOU GET YOUR IDEAS</that>
      <template>
        <think>
          <set name="it">
            <set name="topic">
              <person/>
            </set>
          </set>
        </think> Standing on the shoulders of giants, eh?
      </template>
    </category>
    <category>
      <pattern>FROM A BOOK</pattern>
      <template>
        <srai>IN A BOOK</srai>
      </template>
    </category>
    <category>
      <pattern>IN A BOOK</pattern>
      <template>You can learn many things from books.</template>
    </category>
  </aiml>

  "the aiml model" should {
    "accept all the tags" in {
      val aiml = Aiml(FULLY_TAGGED_BRAIN)
      aiml must beSome[Aiml]
    }
  }

}