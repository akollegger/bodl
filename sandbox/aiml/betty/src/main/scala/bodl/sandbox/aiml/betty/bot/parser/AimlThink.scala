package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, NodeSeq}

object AimlThink {

  def apply(xml:Node) = {
    xml match {
      case <think>{elements @ _*}</think> => {
        Some(new AimlThink(AimlTemplate.extractElements(elements)))
      }
      case _ => None
    }
  }

}


class AimlThink(contents:Seq[AimlTemplateElement]) extends AimlTemplate(contents) with AimlTemplateElement