package server;

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

public class FBServer {

	private static String accessToken ="EAACfZAWzZAoH4BAF5JhYilwqvh8IbpZAvHy1K7t7dzKbA436qjEFu7XmLuACdRquT5kZCa1j1L3zlhVd2H33FRJshCZA28z0h3TB4d5eMQ8A1vMnh6TtxikB3oe5rXS8SyUdcuUoZC4qnT7n95kjAP9D1pybCluUMZD";
	private static FacebookClient fbClient = new DefaultFacebookClient(accessToken);


	/**
	 * This method returns the feeds published in the timeline
	 * Each post is identified by its ID
	 * Finally, count the total number of posts
	 */
	public static void getTimelinePosts() {
		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to get timeline posts (yes/no)");
		String ans = input.nextLine();
		int counter=0;
		if(ans.equalsIgnoreCase("yes")){
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		
		for(List<Post> page : result){	
			for(Post aPost : page){
				System.out.println(aPost.getMessage());
				System.out.println("fb.com/"+aPost.getId());
				counter++;
			}
		}
		}
		System.out.println("Number of Results "+counter);
	}


	/**
	 * This method returns the posts of the selected group or page
	 * Requires either admin permissions or member using installed app
	 */
	public static void getPostsFrom(){
		Connection<Group> groups = fbClient.fetchConnection("me/groups",Group.class);
		Scanner input = new Scanner(System.in);
		outerloop:
		for(List<Group> groupPage : groups){
			for(Group aGroup : groupPage){
				System.out.println("Do you want to get posts of "+aGroup.getName()+" ? (yes/no) press x to exit");
				String ans = input.nextLine();
				if(ans.equals("yes")){
					System.out.println("fb.com/"+aGroup.getId());
					System.out.println("Posts from this Group");
					Connection<Post> postFeed = fbClient.fetchConnection(aGroup.getId()+"/feed", Post.class );
					for(List<Post> postPage: postFeed){
						for(Post aPost : postPage){
							System.out.println(aPost.getFrom().getName());
							System.out.println("fb.com/"+aPost.getMessage());
							System.out.println("fb.com/"+aPost.getId());
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
				System.out.println(aPost.getFrom().getName());
				System.out.println("-->"+aPost.getMessage());
				System.out.println("fb.com/"+aPost.getId());
			}
		}
	}

/**
 *  This method allows a new post in a group, on a page or in the personal feed	
 *  Scrolls through a list of groups and liked pages
 */
	public static void postStatusToFacebook(){
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
					System.out.println("fb.com/"+response2.getId());
				}
			}
		}
//		Requires either publish_to_groups permission and app being installed in the group, or manage_pages and publish_pages as an admin with sufficient administrative permission
		System.out.println("Do you want to post in your feed? (yes/no)");
		String ans = input.nextLine();
		if(ans.equalsIgnoreCase("yes")){
			System.out.println("What's n your mind? ");
			String ans2 = input.nextLine();
			FacebookType response = fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", ans2));
			System.out.println("fb.com/"+response.getId());
		}
	}

	public static void main(String[] args) {
		User me = fbClient.fetchObject("me", User.class);
		System.out.println(me.getName());

		System.out.println("-----------------------TIMELINE POSTS-----------------------");
		getTimelinePosts();

		System.out.println("-----------------------POSTS FROM GROUPS OR PAGES-----------------------");
		getPostsFrom();

		System.out.println("-----------------------POST STATUS TO GROUP OR PAGE-----------------------");
		postStatusToFacebook();
	}

}

