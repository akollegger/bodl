package bodl.sandbox.twitter.hellopuffin.command

import scalaz._
import Scalaz._

import org.talkingpuffin.twitter.{
  AbstractAuthenticatedSession,
  API,
  TwitterRateLimitStatus,
  Http
}

class NonSwingSession(user:String, password:String, apiURL:String) 
  extends AbstractAuthenticatedSession(user,password,apiURL) {

  def this(user:String, password:String) = this(user,password,API.defaultURL)

  override def httpPublisher = new Http(Some(user), Some(password)) {
    suppressLogPrefix = apiURL

    def notify(status:TwitterRateLimitStatus) = {
      // no-op
    }
  }

}

