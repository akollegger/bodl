package bodl.sandbox.twitter.hellotweet.command;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.karaf.shell.console.OsgiCommandSupport;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Command(scope = "tweet", name = "update", description="sends a tweet")
public class StatusUpdate extends OsgiCommandSupport {

  @Argument(name="msg", description="message to tweet", required=true, multiValued=true)
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
      Status status = twitter.updateStatus(sb.substring(0, sb.length()-1));
      System.out.println("Successfully updated the status to [" + status.getText() + "].");
    } catch (Exception e) {
      System.err.println("Tweet failed, because: " + e);
    }
    return null;
  }
}

