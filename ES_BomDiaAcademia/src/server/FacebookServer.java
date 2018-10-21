package server;

import java.util.Iterator;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.restfb.types.Page;

public class FacebookServer {
 
	AccessToken token;
	FacebookClient client;
	
	public FacebookServer(AccessToken token) {
		this.token = token;
		this.client = new DefaultFacebookClient(this.token.getAccessToken());
	}
	
	public void setToken(AccessToken token) {
		this.token = token;
	}
	
	public static AccessToken getExtendedToken(String token) {
		//String accessToken4 = "EAAE52EaM4SABAKtt8HCPYBChej1fF1T2Og8zPh7Bz4m25qKi6QljdqHqyjdUBJRui0MssgGN5yxZAaxipDHz9q2LsBEbxComjLmqvlgfSTNZARmCzxGrSLwHGjZBMMF0tdh647SeCMMf1H9OUsAS15zhNiNoufI1I8gasHKoHO3CA5W11VBrTxzikHsOJEmWA8fdcaztwZDZD";
		FacebookClient fbClient4 = new DefaultFacebookClient(token);
		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("345076036067616","69e570a1a8cb764301f1c365e32e1a72");
		System.out.println("ExtendedAccessToken: " + extendedAccessToken4.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken4.getExpires());
		
		return extendedAccessToken4;
	}
	
	public void printNameID() {
		User me = this.client.fetchObject("me", User.class );
		System.out.println(me.getId());
		System.out.println(me.getName());
	}

	public void findAndPrintPost(String wordToFind) {
		Connection<Post> result = client.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null && aPost.getMessage().contains(wordToFind)) {
					counter5++;
					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
									}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5+"/"+counterTotal);
	}
	
	public static void main(String[] args) {
	
	String accessToken4 = "EAAE52EaM4SABAJYmsL5FkH9zbY2oWyepLTFy0F7AJqBg05ho6JUGkDYwceG5WYa0YmZBuDwMFP9fpqY67vZAdGFZBPySNqO3nVwTBvdr7ZCqVBzz28l4hK4e6e9zJgpFq7BmjvAdXhWLvGZAR0oloBUp8P2bSYbf0F4HzVP3VSzjx09kcc3FY4oldB1d8PxYfoJ4oZB0S5wQZDZD";
	FacebookServer fserver = new FacebookServer(getExtendedToken(accessToken4));
	fserver.printNameID();
	fserver.findAndPrintPost("ISCTE");
	
	}
}

