package Teste;

import engine.Service;
import junit.framework.TestCase;


public class ServicesTest extends TestCase
{

	public void testServicesInit() {
		Service service=new Service(1, "Email", "Rafael", "pass",true);
		
		assertEquals(service.isAtive(), true); 
	}

}
