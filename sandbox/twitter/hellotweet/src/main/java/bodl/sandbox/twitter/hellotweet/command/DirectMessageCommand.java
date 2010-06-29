package bodl.sandbox.twitter.hellotweet.command;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.karaf.shell.console.OsgiCommandSupport;

import java.util.List;

import twitter4j.Status;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Command(scope = "tweet", name = "to", description="sends a direct message")
public class DirectMessageCommand extends OsgiCommandSupport {

  @Argument(name="to", index = 0, description="recipient of direct message", required=true, multiValued=false)
  String to;

  @Argument(name="msg", index = 1, description="message to tweet", required=true, multiValued=true)
  List<String> msg;

  @Option(name="-u", aliases="--user", description="twitter username", required=false)
  String userId = "bodltest";

  @Option(name="-p", aliases="--password", description="twitter password", required=false)
  String password = "marwhompa";


  @Override
  protected Object doExecute() throws Exception {
    try {
      StringBuilder sb = new StringBuilder();
      for (String s : msg)
      {
        sb.append(s);
        sb.append(" ");
      }
      
      Twitter twitter = new TwitterFactory().getInstance(userId, password);
      DirectMessage message = twitter.sendDirectMessage(to, sb.substring(0, sb.length()-1));
      System.out.println("Direct message successfully sent to " +
        message.getRecipientScreenName());
      
    } catch (Exception e) {
      System.err.println("Tweet failed, because: " + e);
    }
    return null;
  }
}

