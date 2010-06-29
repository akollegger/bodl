package bodl.services.storage.core

import org.specs._
import scala.io.Source

import AnnotatedSource._

class AnnotatedSourceTest extends SpecificationWithJUnit("testing the ContentStorage trait") {

  "annotated source" should {
    
    "convert from a Source" in {
      val originalSource = Source.fromString("foo")
      val annotatedSource = originalSource.asAnnotated
      annotatedSource must haveClass[AnnotatedSource]
    }
    "recognize plain text" in {
      val annotatedSource = Source.fromString("foo").asAnnotated
      annotatedSource.contentType must beMatching("application/octet-stream")
    }
  }

}