package engine;
/**
 * 
 * @author Rafael Dias
 *
 */
public class TwitterService extends Service {

	private String watch;
	
	public TwitterService(int id, String n, String u, String p,boolean at, String w) {
		super(id, n, u, p,at);
		this.watch = w;
	}
	
	public String getWatch() {
		return this.watch;
	}

}
