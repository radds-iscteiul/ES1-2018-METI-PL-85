package junits;



import static org.junit.Assert.*;
import org.junit.Test;
import engine.Service;


public class ServicesTest {

	@Test
	public void testServicesInit() {
		Service service=new Service(1, "Email", "Rafael", "pass");
		
		assertEquals(service.isAtive(), true); 
	}

}
