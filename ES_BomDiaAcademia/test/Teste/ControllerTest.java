package Teste;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import engine.Controller;
import engine.EmailMessage;
import engine.FacebookMessage;
import engine.MyMessage;
import junit.framework.TestCase;

public class ControllerTest extends TestCase
{


	public void wordFilterTest() {
		
		MyMessage m = new EmailMessage("Rafael", "Ines", "Test", new Date(), "Search for a word in this message");
		String palavra = "Search for";
		
		assertEquals(Controller.getInstance().wordFilter(m, palavra), true); 
	}
	

	public void userFilterTest(){
		List<MyMessage> list = new ArrayList<MyMessage>();
		list.add(new FacebookMessage("Rafael", new Date(), "Search for a USER in this message"));
		String username = "Rafael";
		
		assertEquals(Controller.getInstance().userFilter(list, username), list);
		
	}
	

	public void dateFilterTest() throws ParseException{
		List<MyMessage> list = new ArrayList<MyMessage>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		String date1= "22-10-2007 13:20:45";
		Date d1=sdf.parse(date1);
		
		String date2= "22-10-2018 13:20:45";
		Date d2=sdf.parse(date2);
		
		String dateMessage= "05-11-2010 09:10:34";
		Date dMessage=sdf.parse(dateMessage);
		
		MyMessage msg1 = new EmailMessage("Ines", "Barbara", "Datas", dMessage, "vamos validar esta data");		
		list.add(msg1);
		
		assertEquals(Controller.getInstance().dateFilter(list, d1, d2), list);
		
	}

}
