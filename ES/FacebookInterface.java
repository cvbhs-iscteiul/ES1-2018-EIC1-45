package facebookApp;

public interface FacebookInterface {

	
	void connectToFacebook();//needs args like Token
	void getFeed();
	void filterFeed(); //args like time
	//Page returnFilteredFeed();
	void postNewPost();
	void postReply();
	
}
