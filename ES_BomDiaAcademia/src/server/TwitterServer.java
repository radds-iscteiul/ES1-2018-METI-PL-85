package server;

import java.util.ArrayList;
import java.util.List;

import engine.TwitterMessage;
import engine.TwitterService;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterServer {

	Twitter client;

	public TwitterServer(TwitterService twitterService) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(twitterService.getKey())
		.setOAuthConsumerSecret(twitterService.getSecret())
		.setOAuthAccessToken(twitterService.getToken())
		.setOAuthAccessTokenSecret(twitterService.getTokenSecret());
		TwitterFactory tf = new TwitterFactory(cb.build());
		this.client = tf.getInstance();

	}

	public List<TwitterMessage> getTweetsFromUser( String user, int numberOfTweets) {
		Paging p = new Paging();
		p.setCount(numberOfTweets);
		List<Status> statuses;
		List<TwitterMessage> tweets = new ArrayList<TwitterMessage>();
		try {
			statuses = client.getUserTimeline(user,p);
			//System.out.println("------------------------\n Showing home timeline \n------------------------");
			//int counter=0;
			//int counterTotal = 0;
			for (Status status : statuses) {
				if (status.getUser().getName() != null /*&& status.getText().contains("")*/) {
					//System.out.println(status.getUser().getName() + ":" + status.getText());
					tweets.add(new TwitterMessage(status.getUser().getName(),status.getUser().getCreatedAt(),status.getText()));
					//counter++;
				}
				//counterTotal++;
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;
	}

	
	public void tweetar() throws TwitterException{
		String message = "O Gouveia vai ver isto a funcionar :) ";
		try {

			try {
				// Lança IllegalStateException se o token de acesso estiver disponível

				client.getOAuthRequestToken();

				// Se não ocorrer significa que o acesso a conta não foi permitida
				System.out.println("Acesso Negado.");

			} catch (IllegalStateException ie) {
				// Verifica se possui autorização
				if (!client.getAuthorization().isEnabled()) {
					System.out.println("OAuth Consumer key/secret inválido.");
				} else {
					Status status = client.updateStatus(message);
					System.out.println("Tweet publicado! [" + status.getText() + "].");
					// client.sendDirectMessage("MetiG85", message);
				}
			}
		} catch (TwitterException te) {
			System.out.println("Falha ao obter a timeline: " + te.getMessage());
		}
	}

}
