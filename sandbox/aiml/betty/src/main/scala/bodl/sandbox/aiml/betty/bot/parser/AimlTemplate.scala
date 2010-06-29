package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, NodeSeq}

object AimlTemplate {

  def apply(xml:Node) = {
    xml match {
      case <template>{elements @ _*}</template> => {
        Some(new AimlTemplate(extractElements(elements)))
      }
      case _ => None
    }
  }

  def extractElements(elements:Seq[Node]):Seq[AimlTemplateElement] = {
    elements.flatMap({
      case think @ <think>{_*}</think> => AimlThink(think)
      case _ => Nil
    })
  }
}

trait AimlTemplateElement {}

class AimlTemplate(val contents:Seq[AimlTemplateElement]) extends AimlCategoryElement 

