package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, Elem, NodeSeq}

object AimlCategory {

  def apply(xml:Node) = {
    xml match {
      case <category>{_*}</category> => {
        Some(new AimlCategory(
          AimlPattern((xml\"pattern").head).get,
          (xml\"that").headOption.flatMap(AimlThat(_)),
          AimlTemplate((xml\"template").head).get
          )
        )
      }
      case _ => None
    }
  }
  
}

trait AimlCategoryElement

class AimlCategory(val pattern:AimlPattern, val that:Option[AimlThat], val template:AimlTemplate)

