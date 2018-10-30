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

	private static String accessToken ="EAAEEfx58VooBAK5arrrQPq2PYDfPYAMsk2psrTY6wG4ZBXCG13AOu1Er9cKshpMDvxLugZAuT7fzg8NkbGZBbgU1FMvNITZAzKTtvYpZB9BZCzbvZBzXOfcHrALT9VKP3ZCTRrA21ImazM7cNG5WkTWkxUj6trQvUwvVxnyEN5naUfDu9gcH7pqzMVnjzTlu3MKfLOVndyVLZCAZDZD";
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
	public List<FacebookMessage> getPostsFrom(String choose, String name){
		List<FacebookMessage> fb = new ArrayList<FacebookMessage>();
		Connection<Group> groups = fbClient.fetchConnection("me/groups",Group.class);
		if(choose.equals("group")){
			for(List<Group> groupPage : groups){
				for(Group aGroup : groupPage){
					if(name.equals(aGroup.getName())){
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
				}
			}
		}
		if(choose.equals("page")){
			Page page = fbClient.fetchObject(name, Page.class);
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
		}
		return fb;
	}

	/**
	 * 
	 * @param choose
	 * @param name
	 * @param msg
	 * 
	 * This method allows a new post in a GROUP, PAGE or in the personal FEED	
	 */
	public void postStatusToFacebook(String choose, String name, String msg){
		List<FacebookMessage> fb = new ArrayList<FacebookMessage>();
		Connection<Group> result = fbClient.fetchConnection("me/groups", Group.class);
		Connection<Page> result2 = fbClient.fetchConnection("me/likes", Page.class);

		if(choose.equals("group")){
			for(List<Group> GroupPage :result){
				for(Group aGroup :GroupPage){
					if(name.equals(aGroup.getName())){
						System.out.println("encontrou grupo");
						FacebookType response = fbClient.publish(aGroup.getId()+"/feed",  FacebookType.class,
								Parameter.with("message", msg)	
								);

						fb.add(new FacebookMessage(response.getId(),new Date(System.currentTimeMillis()) ,msg));
						System.out.println("fb.com/"+response.getId());
					}
				}
			}
		}
		if(choose.equals("page")){
			for(List<Page> Page :result2){
				for(Page aPage :Page){
					if(name.equals(aPage.getName())){
						FacebookType response2 = fbClient.publish(aPage.getId()+"/feed",  FacebookType.class,
								Parameter.with("message", msg)	
								);
						fb.add(new FacebookMessage(response2.getId(),new Date(System.currentTimeMillis()) ,msg));
						System.out.println("fb.com/"+response2.getId());
					}
				}
			}
		}
		if(choose.equals("feed")){
			//		Requires either publish_to_groups permission and app being installed in the group, or manage_pages and publish_pages as an admin with sufficient administrative permission
			FacebookType response3 = fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", msg));
			fb.add(new FacebookMessage(response3.getId(),new Date(System.currentTimeMillis()) ,msg));
			System.out.println("fb.com/"+response3.getId());
		}

	}

	public static void main(String[] args) {
		User me = fbClient.fetchObject("me", User.class);
		System.out.println(me.getName());

		FacebookServer f= new FacebookServer();
		f.getTimelinePosts();
		f.getPostsFrom("group", "ES");
		f.postStatusToFacebook("group", "ES", "Testar Facebook java API");

	}

}

