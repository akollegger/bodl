package bodl.sandbox.twitter.hellopuffin.command

import org.apache.felix.gogo.commands.Command
import org.apache.felix.gogo.commands.Option
import org.apache.felix.gogo.commands.Argument
import org.apache.felix.karaf.shell.console.OsgiCommandSupport

import java.util.{List, ArrayList}

import scalaz._
import Scalaz._

@Command(scope = "puffin", name = "details", description="user details")
class GetStatus extends OsgiCommandSupport {

  @Option(name="-u", aliases=Array("--user"), description="twitter username", required=false)
  var userId = "bodltest"

  @Option(name="-p", aliases=Array("--password"), description="twitter password", required=false)
  var password = "marwhompa"

  @Argument(name="about", description="user about whom to find details", required=true, multiValued=false)
  var aboutUser="bodltest"

  @Override
  def doExecute():Object = {
    try {
      val sess = OSGiTwitterSession(userId, password)
      
      val details = sess.getUserDetail(aboutUser)

      println(details.name + " (" + details.screenName + "," + details.id + ")")
      details.status.foreach(x => println("\"" + x.text + "\""))
      println(details.friendsCount + " friends, " + details.followersCount + " followers")
    
    } catch {
      case (e) => {
        System.err.println("Getting details failed, because: " + e)
      }
    }
    return ""
  }

}


