package engine;

public enum ServiceType {

	EMAIL,FACEBOOK,TWITTER;

	public String toString() {
		switch(this) {
		case EMAIL:
			return "Email";
		case FACEBOOK:
			return "Facebook";
		case TWITTER:
			return "Twitter";
		default:
			return "ERROR";
		}
	}
	
}
