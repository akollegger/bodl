package bodl.sandbox.aiml.betty.bot.parser

import xml.{Node, Elem, NodeSeq}

object AimlPattern{

  def apply(xml:Node) = {
    xml match {
      case <pattern>{_}</pattern> => {
        Some(new AimlPattern(xml.text.trim.split(' ').toSeq))
      }
      case _ => None
    }
  }
  
}

class AimlPattern(var tokens:Seq[String]) extends AimlCategoryElement