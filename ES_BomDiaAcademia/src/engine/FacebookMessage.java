package engine;

import java.util.Date;

public class FacebookMessage extends MyMessage {

	/**
	 * 
	 * @param id (there's no permission to access the name of the User who posted )
	 * @param time
	 * @param message
	 */
	public FacebookMessage(String id, Date time, String message) {
		super(id, time, message);
	}
	

	@Override
	public String toString() {
		
		return "ID :"+this.getFrom() + "DATE :"+ this.getTime() + "Message :" + this.getMessage()+ "\n";
	}
	
}
