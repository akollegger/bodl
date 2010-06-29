package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, Elem, NodeSeq}

object Aiml {

  def apply(xml:Node) = {
    xml match {
      case <aiml>{categories @ _*}</aiml> => {
        Some(new Aiml(for(category @ <category>{_*}</category> <- categories) yield {
          AimlCategory(category).get
        }))
      }
      case _ => None
    }
  }
  
}

class Aiml(var categories:Iterable[AimlCategory]) {
  def this() = this(Seq[AimlCategory]())
}