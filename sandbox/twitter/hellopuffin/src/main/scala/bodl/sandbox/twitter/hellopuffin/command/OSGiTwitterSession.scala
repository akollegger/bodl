package bodl.sandbox.twitter.hellopuffin.command

import scalaz._
import Scalaz._

import org.talkingpuffin.twitter.{
  AbstractAuthenticatedSession,
  AbstractUnauthenticatedSession,
  API,
  TwitterRateLimitStatus,
  Http
}

class OSGiAuthenticatedSession(user:String, password:String, apiURL:String) 
  extends AbstractAuthenticatedSession(user,password,apiURL) {

  def this(user:String, password:String) = this(user,password,API.defaultURL)

  override def httpPublisher = new Http(Some(user), Some(password)) {
    suppressLogPrefix = apiURL

    def notify(status:TwitterRateLimitStatus) = {
      // no-op
    }
  }

}

class OSGiUnauthenticatedSession(apiURL:String) 
  extends AbstractUnauthenticatedSession(apiURL) {

  def this() = this(API.defaultURL)

  override def httpPublisher = new Http(None, None) {
    suppressLogPrefix = apiURL

    def notify(status:TwitterRateLimitStatus) = {
      // no-op
    }
  }

}

object OSGiTwitterSession {
  /**
  * get an AuthenticatedSession instance with the provided user and password
  * Note that this should always succeed.  The userid and password are not (currently) checked
  */
  def apply(user: String, password: String): OSGiAuthenticatedSession = {
    new OSGiAuthenticatedSession(user,password)
  }

  def apply(user: String, password: String, apiURL: String): OSGiAuthenticatedSession = {
    new OSGiAuthenticatedSession(user,password,apiURL)
  }
  
  /**
  * get an UnauthenticatedSession instance
  */
  def apply(): OSGiUnauthenticatedSession = {
    new OSGiUnauthenticatedSession()
  }

  def apply(apiURL: String): OSGiUnauthenticatedSession = {
    new OSGiUnauthenticatedSession(apiURL)
  }
}

/**
* The base class of both OSGiTwitterSession objects
*/
abstract class OSGiTwitterSession

