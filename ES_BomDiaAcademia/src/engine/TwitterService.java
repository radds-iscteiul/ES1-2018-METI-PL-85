package engine;
/**
 * 
 * @author Rafael Dias
 *
 */
public class TwitterService extends Service {

	private String watch;
	
	private String key;
	private String secret;
	private String token;
	private String tokenSecret;
	
	public TwitterService(int id, String n, String u, String p,boolean at, String w, String key, String secret,String token, String tokenSecret) {
		super(id, n, u, p,at);
		this.watch = w;
		this.key = key;
		this.secret = secret;
		this.token = token;
		this.tokenSecret = tokenSecret;
	}
	
	public String getWatch() {
		return this.watch;
	}

	public String getKey() {
		return key;
	}

	public String getSecret() {
		return secret;
	}

	public String getToken() {
		return token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

}
