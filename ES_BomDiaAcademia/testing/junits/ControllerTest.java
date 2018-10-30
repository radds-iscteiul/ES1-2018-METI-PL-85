package junits;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	@Test
	public void DateFilterTest() throws ParseException{
		List<MyMessage> list = new ArrayList<MyMessage>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		String date1= "22-10-2007 13:20:45";
		Date d1=sdf.parse(date1);
		
		String date2= "22-10-2018 13:20:45";
		Date d2=sdf.parse(date2);
		
		String dateMessage= "05-11-2010 09:10:34";
		Date dMessage=sdf.parse(dateMessage);
		
		MyMessage msg1 = new EmailMessage("Inês", "Bárbara", "Datas", dMessage, "vamos validar esta data");		
		list.add(msg1);
		
		assertEquals(Controller.getInstance().DateFilter(list, d1, d2), list);
		
	}
	
	

}
