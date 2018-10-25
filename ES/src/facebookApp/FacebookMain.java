package facebookApp;

import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class FacebookMain {
	
	String accessToken = "EAAIFIIEgfxIBAHhghzXQejRISCdLblNef9b4WMIA9jdjVvobsW4HE3RGduZBuNTkR2nZC75z8FeATl3MMMyYjYXtxWlfLxVlPI6aRez4Xz5e5WqPQ9DlMSa2JATFngRmsl2BSooiHGCmVImGNnZCV2mrkxnVfwDNOMM0429tz7cZCndHtobu2x1bBuKjQd3vuZBFbyhNQxUzNuTmN6s3R0WGN0sB7UfMZD";
	FacebookClient fbClient ;
	User me;
	
	
	public FacebookMain() {

		super();
		fbClient = new DefaultFacebookClient(accessToken);
		me = fbClient.fetchObject("me", User.class);	
	
	}
	
	
	/**
	 * 
	 * Initialize variables/Start Connection
	 * 
	 */
	
	public void init() {
		fbClient = new DefaultFacebookClient(accessToken);
		me = fbClient.fetchObject("me", User.class);	
	}
	
	
	/**
	 * Used mostly for debug or to get the entire User
	 * 
	 * @return User object
	 */
	public User getMe() {
			return me;
	}
	
	/**
	 * 
	 * @return Currently logged in user's Facebook ID
	 */
	
	public String getMyID() {
		return me.getId();
	}
	

	/**
	 * 
	 * @return	Currently logged in user's Username
	 */
	
	public String getMyUsername() {
		return me.getUsername();
	}
	
	
	
	public Connection<Post> getPosts() {
		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		return result;
	
	}
	
	/*
	  public List<Post> getPostsList() {
	 
			
			Connection<Post> result = fbClient.fetchConnection("me/posts",Post.class);
			
			List<Post> page = result;
			return page;
		}
		
	*/
	
	
	
	
	/**
	 * 
	 * Method to print what's being obtained from the current fetchConnection command.
	 * Printing to console - Currently being used to 'debug' the API. 
	 * 
	 */
	
	public void printSys() {
		

		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		
		for (List<Post> page : result) {
			
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getLikesCount()>=0) {
					
					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					System.out.println("Picture: "+aPost.getPicture());
					System.out.println("Attachments: "+aPost.getAttachments());
					System.out.println("Likes: "+aPost.getLikesCount());
					System.out.println("Comments: "+aPost.getCommentsCount());
					System.out.println("Full Picture: "+aPost.getFullPicture());
					
					counter5++;
				}
				counterTotal++;
			}
		}
		
		System.out.println("-------------\nNº of Results: " + counter5+"/"+counterTotal);
		
	}
	
	public static void main(String[] args) {	
		/* 
		 * Facebook API Tutorials in Java # 5 | Get User Timeline Posts
		 * https://www.youtube.com/watch?v=wiFif4gOdFE&index=6&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		*/ 	
		
		FacebookMain fb = new FacebookMain();
		fb.init();
		fb.printSys();
		
		
	}
}
