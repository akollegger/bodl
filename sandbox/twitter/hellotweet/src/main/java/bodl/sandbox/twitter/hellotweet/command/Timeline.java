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

@Command(scope = "tweet", name = "list", description="gets timeline")
public class Timeline extends OsgiCommandSupport {

  @Argument(name="timeline", description="which timeline to retrieve [public, friends, *mine]", required=false)
  String timeline = "mine";

  @Option(name="-u", aliases="--user", description="twitter username", required=false)
  String userId = "bodltest";

  @Option(name="-p", aliases="--password", description="twitter password", required=false)
  String password = "marwhompa";


  @Override
  protected Object doExecute() throws Exception {
    try {
      if ("public".equals(timeline)) {
        Twitter unauthenticatedTwitter = new TwitterFactory().getInstance();
        System.out.println("Showing public timeline.");
        List<Status> statuses = unauthenticatedTwitter.getPublicTimeline();
        for (Status status : statuses) {
          System.out.println(status.getUser().getName() + ":" +
            status.getText());
        }
      }

      if ("friends".equals(timeline)) {
        Twitter twitter = new TwitterFactory().getInstance(userId, password);
        List<Status> statuses = twitter.getFriendsTimeline();
        System.out.println("Showing " + userId + "'s friends timeline.");
        for (Status status : statuses) {
          System.out.println(status.getUser().getName() + ":" +
          status.getText());
        }
      }
      
      if ("mine".equals(timeline)) {
        Twitter twitter = new TwitterFactory().getInstance(userId, password);
        List<Status> statuses = twitter.getUserTimeline();
        System.out.println("------------------------------");
        System.out.println("Showing " + userId + "'s timeline.");
        for (Status status : statuses) {
          System.out.println(status.getUser().getName() + ":" +
            status.getText());
        }
      }
    } catch (Exception e) {
      System.err.println("Timeline list failed, because: " + e);
    }
    return null;
  }
}

