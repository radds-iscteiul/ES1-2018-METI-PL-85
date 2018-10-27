package engine;
/**
 * 
 * @author Rafael Dias
 *
 */
public class TwitterService extends Service {

	private String watch;
	
	public TwitterService(int id, String n, String u, String p, String w) {
		super(id, n, u, p);
		this.watch = w;
	}
	
	public String getWatch() {
		return this.watch;
	}

}
