package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, Elem, NodeSeq}

object AimlPatternThat {

  def apply(xml:Node) = {
    xml match {
      case <that>{_}</that> => {
        Some(new AimlPatternThat(xml.text.trim.split(' ').toSeq))
      }
      case _ => None
    }
  }
  
}

class AimlPatternThat(var tokens:Seq[String]) extends AimlCategoryElement