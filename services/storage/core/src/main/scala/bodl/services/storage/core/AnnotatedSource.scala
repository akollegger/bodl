package bodl.services.storage.core

import io.Source

/**
 *
 */
object AnnotatedSource {
  implicit def annotateSource(s:Source) = new AnnotatedSource(s)
}

/**
 * AnnotatedSource adds properties to a Source
 */
class AnnotatedSource(val source:Source, val contentType:String) {

  def this(source:Source) = this(source, "application/octet-stream")
  
  def asAnnotated = { this }
}
