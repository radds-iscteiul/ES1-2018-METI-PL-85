package server;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.User;

public class ExtendUserAccessToken {

	public static void main(String[] args) {
		String accessToken ="EAACfZAWzZAoH4BAIuewAAFK32QkQXjL8mudSndEmZAktljCz0FlqcFYk9UChwmueD8W2i9FFWkckrnN7139Q5CY1WOBYewz4D9QuhA04cE1rjWZBkuNcGwWIQmlAgjMpd9ZCUxM6fwWYIrXRyUhY1aISEki36oCtIC5mUnZAxxzwZDZD";

		FacebookClient fbClient = new DefaultFacebookClient(accessToken);

		AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("175257966715006","d326f07bd9736f4f8bc28f040776ab85");
		
		System.out.println(exAccessToken.getAccessToken());
		System.out.println(exAccessToken.getExpires());
	}
}
