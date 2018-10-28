package server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Group;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;
import engine.FacebookMessage;

public class FacebookServer {

	private static String accessToken ="EAAEEfx58VooBAN87DvScAvhPfW5DAyaozoaqkrGRtbWGdcAVZAdAT0hnZAZBDl90kDPZB2P3uuloFMj89nemZA3OEMhmlPoF63r597M0ZAT8eKrb1GpygeU5AYpcEsGreikn3IHPeqsCGJZAUbwr0IYZCw5tWxLmOSnVN7W7alqEPUTZA8ZBqwZCEkZBbhajvlzW9cZCRMtu23ZAECrAZDZD";
	private static FacebookClient fbClient = new DefaultFacebookClient(accessToken);


	/**
	 * This method returns the feeds published in the timeline
	 * Each post is identified by its ID
	 * Finally, count the total number of posts
	 */
	public List<FacebookMessage>  getTimelinePosts() {
		List<FacebookMessage> fb = new ArrayList<FacebookMessage>();
		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to get timeline posts (yes/no)");
		String ans = input.nextLine();
		int counter=0;
		if(ans.equalsIgnoreCase("yes")){
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		
		for(List<Post> page : result){	
			for(Post aPost : page){
				fb.add(new FacebookMessage("fb.com/"+aPost.getId(), aPost.getCreatedTime(),aPost.getMessage()));
				System.out.println("ID: fb.com/"+aPost.getId());
				System.out.println("Date :"+aPost.getCreatedTime());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("\n");
				counter++;
			}
		}
		}
		System.out.println("Number of Results "+counter);
		return fb;
	}


	/**
	 * This method returns the posts of the selected group or page
	 * Requires either admin permissions or member using installed app
	 */
	public List<FacebookMessage> getPostsFrom(){
		List<FacebookMessage> fb = new ArrayList<FacebookMessage>();
		Connection<Group> groups = fbClient.fetchConnection("me/groups",Group.class);
		Scanner input = new Scanner(System.in);
		outerloop:
		for(List<Group> groupPage : groups){
			for(Group aGroup : groupPage){
				System.out.println("Do you want to get posts of "+aGroup.getName()+" ? (yes/no) press x to exit");
				String ans = input.nextLine();
				if(ans.equals("yes")){
					Connection<Post> postFeed = fbClient.fetchConnection(aGroup.getId()+"/feed", Post.class );
					for(List<Post> postPage: postFeed){
						for(Post aPost : postPage){
							fb.add(new FacebookMessage(aPost.getId(), aPost.getCreatedTime(),aPost.getMessage()));
							System.out.println("ID: fb.com/"+aPost.getId());
							System.out.println("Date :"+aPost.getCreatedTime());
							System.out.println("Message: "+aPost.getMessage());
							System.out.println("\n");
						}
					}
				}
				if(ans.equals("x")){
					break outerloop;
				}
			}
		}
		System.out.println("Do you want to get posts from which page ?");
		String pageName= input.nextLine();
		Page page = fbClient.fetchObject(pageName, Page.class);
		Connection<Post> postFeed = fbClient.fetchConnection(page.getId()+"/feed", Post.class);
		for(List<Post> postPage : postFeed){
			for(Post aPost: postPage){
				fb.add(new FacebookMessage(aPost.getId(), aPost.getCreatedTime(),aPost.getMessage()));
				System.out.println("ID: fb.com/"+aPost.getId());
				System.out.println("Date :"+aPost.getCreatedTime());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("\n");
			}
		}
		return fb;
	}

/**
 *  This method allows a new post in a group, on a page or in the personal feed	
 *  Scrolls through a list of groups and liked pages
 */
	public void postStatusToFacebook(){
		List<FacebookMessage> fb = new ArrayList<FacebookMessage>();
		Scanner input = new Scanner(System.in);
		Connection<Group> result = fbClient.fetchConnection("me/groups", Group.class);
		Connection<Page> result2 = fbClient.fetchConnection("me/likes", Page.class);

		for(List<Group> GroupPage :result){
			for(Group aGroup :GroupPage){
				System.out.println("Do you want to post in "+aGroup.getName()+" group? (yes/no)");
				String ans = input.nextLine();
				if(ans.equalsIgnoreCase("yes")){
					System.out.println("What do you want to post? ");
					String msg = input.nextLine();
					FacebookType response = fbClient.publish(aGroup.getId()+"/feed",  FacebookType.class,
							Parameter.with("message", msg)	
							);
					
					fb.add(new FacebookMessage(response.getId(),new Date(System.currentTimeMillis()) ,msg));
					System.out.println("fb.com/"+response.getId());
				}
			}
		}
		for(List<Page> Page :result2){
			for(Page aPage :Page){
				System.out.println("Do you want to post in "+aPage.getName()+" page? (yes/no)");
				String ans = input.nextLine();
				if(ans.equalsIgnoreCase("yes")){
					System.out.println("What do you want to post? ");
					String msg2 = input.nextLine();
					FacebookType response2 = fbClient.publish(aPage.getId()+"/feed",  FacebookType.class,
							Parameter.with("message", msg2)	
							);
					fb.add(new FacebookMessage(response2.getId(),new Date(System.currentTimeMillis()) ,msg2));
					System.out.println("fb.com/"+response2.getId());
				}
			}
		}
//		Requires either publish_to_groups permission and app being installed in the group, or manage_pages and publish_pages as an admin with sufficient administrative permission
		System.out.println("Do you want to post in your feed? (yes/no)");
		String ans = input.nextLine();
		if(ans.equalsIgnoreCase("yes")){
			System.out.println("What's n your mind? ");
			String msg3 = input.nextLine();
			FacebookType response3 = fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", msg3));
			fb.add(new FacebookMessage(response3.getId(),new Date(System.currentTimeMillis()) ,msg3));
			System.out.println("fb.com/"+response3.getId());
		}
	}

	public static void main(String[] args) {
		User me = fbClient.fetchObject("me", User.class);
		System.out.println(me.getName());

		FacebookServer f= new FacebookServer();
		f.getTimelinePosts();
		f.getPostsFrom();
		f.postStatusToFacebook();

	}

}

