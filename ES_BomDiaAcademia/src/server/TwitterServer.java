package server;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterServer {

	Twitter client;
	
	public TwitterServer() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("W1f0VvgWPfT8OBqVxvy4Mw")
    	  .setOAuthConsumerSecret("zKH2yAtRyefwsgOO8h8Szc4kru68iEm95QmIG7svw")
    	  .setOAuthAccessToken("36481851-VhzByC4f9MSsZES1QZQ4e4iBvA9bWGLyv9HKFpy7c")
    	  .setOAuthAccessTokenSecret("OahDuXF2Lhl5xlNYALhYZir6xSflAxKP9Zh89T05po");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	this.client = tf.getInstance();
	}
	
	public void getTweetsFromUser( String user, int numberOfTweets) {
		Paging p = new Paging();
		p.setCount(numberOfTweets);
        List<Status> statuses;
		try {
			statuses = client.getUserTimeline("iscteiul",p);
			System.out.println("------------------------\n Showing home timeline \n------------------------");
			//int counter=0;
			//int counterTotal = 0;
	        for (Status status : statuses) {
				// Filters only tweets from user "catarina"
				if (status.getUser().getName() != null && status.getText().contains("")) {
					System.out.println(status.getUser().getName() + ":" + status.getText());
					//counter++;
				}
				//counterTotal++;
		}
	    } catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        }
	
}