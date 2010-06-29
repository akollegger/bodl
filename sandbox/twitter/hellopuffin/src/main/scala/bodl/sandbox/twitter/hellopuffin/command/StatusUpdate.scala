package bodl.sandbox.twitter.hellopuffin.command

import org.apache.felix.gogo.commands.Command
import org.apache.felix.gogo.commands.Option
import org.apache.felix.gogo.commands.Argument
import org.apache.felix.karaf.shell.console.OsgiCommandSupport

import java.util.{List, ArrayList}

import scalaz._
import Scalaz._

@Command(scope = "puffin", name = "update", description="sends a tweet")
class StatusUpdate extends OsgiCommandSupport {

  @Argument(name="msg", description="message to tweet", required=true, multiValued=true)
  var msg:List[String] = new ArrayList[String]()

  @Option(name="-u", aliases=Array("--user"), description="twitter username", required=false)
  var userId = "bodltest"

  @Option(name="-p", aliases=Array("--password"), description="twitter password", required=false)
  var password = "marwhompa"

  @Override
  def doExecute():Object = {
    try {
      val sess = OSGiTwitterSession(userId, password)
      
      sess.updateStatus(msg.mkString)
    
      System.out.println("Successfully updated the status.")
    } catch {
      case (e) => {
        System.err.println("Tweet failed, because: " + e)
      }
    }
    return ""
  }

}


