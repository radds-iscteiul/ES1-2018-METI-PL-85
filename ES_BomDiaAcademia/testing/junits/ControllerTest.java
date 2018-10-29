package junits;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import engine.Controller;
import engine.EmailMessage;
import engine.MyMessage;

public class ControllerTest {

	@Test
	public void wordFilterTest() {
		
		MyMessage m = new EmailMessage("Rafael", "In�s", "Test", new Date(), "Search for a word in this message");
		String palavra = "Search for";
		
		assertEquals(Controller.getInstance().wordFilter(m, palavra), true); 
	}
	
	@Test
	public void userFilterTest(){
		List<MyMessage> list = new ArrayList<MyMessage>();
		list.add(new EmailMessage("Rafael", "Barbara", "Test2", new Date(), "Search for a USER in this message"));
		String username = "Rafael";

		assertEquals(Controller.getInstance().UserFilter(list, username), list);
		
	}

}
