package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, Elem, NodeSeq}

object AimlThat {

  def apply(xml:Node) = {
    xml match {
      case <that>{tokens}</that> => {
        Some(new AimlThat(tokens.text.trim.split(' ').toSeq))
      }
      case _ => None
    }
  }
  
}

class AimlThat(var tokens:Seq[String])