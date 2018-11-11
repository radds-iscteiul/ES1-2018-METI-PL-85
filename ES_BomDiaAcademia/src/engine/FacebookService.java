package engine;

public class FacebookService extends Service {

	String token;
	
	public FacebookService(int id, String n, String u, String p, boolean at,String token) {
		super(id, n, u, p, at);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
