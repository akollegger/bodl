package bodl.sandbox.twitter.hellotweet.command;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.karaf.shell.console.OsgiCommandSupport;

import java.util.List;

import twitter4j.Status;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Command(scope = "tweet", name = "search", description="search through tweets")
public class SearchCommand extends OsgiCommandSupport {

  @Argument(name="query", description="twitter search terms", required=true, multiValued=true)
  List<String> terms;

  @Option(name="-u", aliases="--user", description="twitter username", required=false)
  String userId = "bodltest";

  @Option(name="-p", aliases="--password", description="twitter password", required=false)
  String password = "marwhompa";


  @Override
  protected Object doExecute() throws Exception {
    StringBuilder sb = new StringBuilder();
    for (String s : terms)
    {
      sb.append(s);
      sb.append(" ");
    }
    String searchTerm=sb.substring(0,sb.length()-1);
 
    try {
      Twitter twitter = new TwitterFactory().getInstance();
      Query query = new Query(searchTerm);
      QueryResult result = twitter.search(query);
      System.out.println("hits:");
      for (Tweet tweet : result.getTweets()) {
        System.out.println(tweet.getFromUser() + ":" + tweet.getText());
      }
    } catch (Exception e) {
      System.err.println("Twitter search failed, because: " + e);
    }
    return null;
  }
}

