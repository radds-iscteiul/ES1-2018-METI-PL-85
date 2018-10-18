package junits;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import engine.Controller;
import engine.MyMessage;

public class ControllerTest {

	@Test
	public void wordFilterTest() {
		
		MyMessage m = new MyMessage("Rafael", "Inês", new Date(), "Test", "Search for a word in this message");
		String palavra = "Search for";
		
		assertEquals(Controller.getInstance().wordFilter(m, palavra), true); 
	}

}
