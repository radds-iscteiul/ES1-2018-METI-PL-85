package server;

import java.util.List;
import java.util.Scanner;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Group;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;

public class FBServer {

	private static String accessToken ="EAACfZAWzZAoH4BAF5JhYilwqvh8IbpZAvHy1K7t7dzKbA436qjEFu7XmLuACdRquT5kZCa1j1L3zlhVd2H33FRJshCZA28z0h3TB4d5eMQ8A1vMnh6TtxikB3oe5rXS8SyUdcuUoZC4qnT7n95kjAP9D1pybCluUMZD";
	private static FacebookClient fbClient = new DefaultFacebookClient(accessToken);
	
	public static void getUserGroups(){
		Connection<Group> groups = fbClient.fetchConnection("me/groups",Group.class);
		int counter =0;
		for(List<Group> groupPage : groups){
			for(Group aGroup : groupPage){
				System.out.println(aGroup.getName());
				System.out.println("fb.com/"+aGroup.getId());
				counter++;
			}
		}
		System.out.println("Number of Results "+counter);
	}
	

	public static void getLikedPages(){
		Connection<Page> result = fbClient.fetchConnection("me/likes",Page.class);
		int counter=0;
		for(List<Page> feedPage : result){
			for(Page page : feedPage){
				System.out.println(page.getName());
				System.out.println("fb.com/"+page.getId());
				counter++;
			}
		}
		System.out.println("Number of Results "+counter);
	}
	
	
	public static void getAdminPages(){
		Connection<Page> result = fbClient.fetchConnection("me/accounts",Page.class);
		int counter=0;
		for(List<Page> feedPage : result){
			for(Page page : feedPage){
				System.out.println(page.getName());
				System.out.println(page.getLikes());
				System.out.println("fb.com/"+page.getId());
			}
		}
		System.out.println("Number of Results "+counter);
	}
	
	
	public static void getUsersAdminGroups(){
		Connection<Group> groups = fbClient.fetchConnection("me/admined_groups",Group.class);
		//{user-id}/admined_groups is deprecated for versions v2.8 and higher

		for(List<Group> groupPage : groups){
			for(Group aGroup : groupPage){
				System.out.println(aGroup.getName());
				System.out.println("fb.com/"+aGroup.getId());
			}
		}
	}
	
	public static void getGroupMembers(){
		Connection<Group> groups = fbClient.fetchConnection("me/groups",Group.class);
		Scanner input = new Scanner(System.in);
		for(List<Group> groupPage : groups){
			for(Group aGroup : groupPage){
				System.out.println("Do you want to get Members of "+aGroup.getName()+" ? yes/no");
				String ans = input.nextLine();
				if(ans.equals("yes")){
					System.out.println("fb.com/"+aGroup.getId());
					Connection<User> userFeed = fbClient.fetchConnection(aGroup.getId()+"/members", User.class );
					for(List<User> userPage: userFeed){
						for(User aUser : userPage){
							System.out.println(aUser.getName());
							System.out.println("fb.com/"+aUser.getId());
						}
					}
				}
			}
		}
	}
	
	
	public static void getTimelinePosts() {
	Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
	int counter=0;
	for(List<Post> page : result){	
		for(Post aPost : page){
			System.out.println(aPost.getMessage());
			System.out.println("fb.com/"+aPost.getId());
			counter++;
		}
	}
	System.out.println("Number of Results "+counter);
}
	
	public static void getHomePosts(){
		Connection<Post> result = fbClient.fetchConnection("me/home",Post.class);
		int counter=0;
		for(List<Post> page : result){	
			for(Post aPost : page){
				System.out.println(aPost.getMessage());
				System.out.println("fb.com/"+aPost.getId());
				counter++;
			}
		}
		System.out.println("Number of Results "+counter);
	}
	
	
	public static void getPostsFromGroup(){
		Connection<Group> groups = fbClient.fetchConnection("me/groups",Group.class);
		Scanner input = new Scanner(System.in);
		for(List<Group> groupPage : groups){
			for(Group aGroup : groupPage){
				System.out.println("Do you want to get posts of "+aGroup.getName()+" ? yes/no");
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
			}
		}
	}
	
	public static void getPostsFromPage(){
		Page page = fbClient.fetchObject("ISCTEIUL", Page.class);
		Connection<Post> postFeed = fbClient.fetchConnection(page.getId()+"/feed", Post.class);
		for(List<Post> postPage : postFeed){
			for(Post aPost: postPage){
				System.out.println(aPost.getFrom().getName());
				System.out.println("-->"+aPost.getMessage());
				System.out.println("fb.com/"+aPost.getId());
			}
		}
	
	}
	
	public static void main(String[] args) {
		User me = fbClient.fetchObject("me", User.class);
		System.out.println(me.getName());
		System.out.println("-----------------------GROUPS-----------------------");
		getUserGroups();
		System.out.println("-----------------------LIKED PAGES-----------------------");
		getLikedPages();
		System.out.println("-----------------------ADMIN PAGES-----------------------");
		getAdminPages();
		System.out.println("-----------------------TIMELINE POSTS-----------------------");
		getTimelinePosts();
		
//		To use 'Page Public Content Access', your use of this endpoint must be reviewed and approved by Facebook
//		System.out.println("-----------------------POSTS FROM PAGES-----------------------");
//		getPostsFromPage();
		
//		admined_groups is deprecated for versions v2.8 and higher
//		System.out.println("-----------------------USERS ADMIN GROUPS-----------------------");
//		getUsersAdminGroups();
//		System.out.println("-----------------------GROUP MEMBERS-----------------------");
//		getGroupMembers();
//		System.out.println("-----------------------HOME POSTS-----------------------");
//		getHomePosts();
//		System.out.println("-----------------------POSTS FROM GROUP-----------------------");
//		getPostsFromGroup();
	}
	
}
	
	