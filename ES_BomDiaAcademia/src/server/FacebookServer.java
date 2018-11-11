package server;

import java.util.ArrayList;
import java.util.List;

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
import engine.FacebookService;

public class FacebookServer {

	private static String accessToken;
	public static FacebookClient fbClient;

	private List<Group> userGroups;
	private List<Page> userPages;


	public FacebookServer(FacebookService fbs) {
		accessToken = fbs.getToken();
		fbClient = new DefaultFacebookClient(accessToken);
		
		userGroups = fbClient.fetchConnection("me/groups",Group.class).getData();
		userPages = fbClient.fetchConnection("me/likes", Page.class).getData();
	}

	public List<Group> getUserGroups() {
		return userGroups;
	}

	public List<Page> getUserPages() {
		return userPages;
	}

	/**
	 * This method returns the feeds published in the timeline
	 * Each post is identified by its ID
	 * Finally, count the total number of posts
	 */
	public List<FacebookMessage>  getTimelinePosts() {
		List<FacebookMessage> fb = new ArrayList<FacebookMessage>();
		int counter=0;
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

	public void postStatusToFacebookGroup(String name, String msg){
		for(Group group: userGroups){
			if(name.equals(group.getName())){
				System.out.println("encontrou grupo");
				FacebookType response = fbClient.publish(group.getId()+"/feed",  FacebookType.class,
						Parameter.with("message", msg)	
						);
				System.out.println("fb.com/"+response.getId());
			}
		}
	}


	public void postStatusToFacebookPage(String name, String msg){
		for(Page page: userPages){
			if(name.equals(page.getName())){
				System.out.println("encontrou página");
				FacebookType response = fbClient.publish(page.getId()+"/feed",  FacebookType.class,
						Parameter.with("message", msg)	
						);
				System.out.println("fb.com/"+response.getId());
			}
		}
	}

	public void postStatusToFacebookTimeline(String msg){
//		Requires either publish_to_groups permission and app being installed in the group, or manage_pages and publish_pages as an admin with sufficient administrative permission
		FacebookType response3 = fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", msg));
		System.out.println("fb.com/"+response3.getId());
	}

}

