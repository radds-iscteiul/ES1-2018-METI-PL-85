

import static org.junit.Assert.*;
import org.junit.Test;
import engine.Service;


public class ServicesTest {

	@Test
	public void testAtiveServices() {
		Service service=new Service(1, "Email", "192.168.0.0", "Rafael", "pass");
		assertEquals(service.isAtive(), true); 
		//so para testar (como os serviços são criados como "Ativos", será sempre TRUE)

//		assertEquals(service.isAtive(), false); 
//		erro (como previsto)
	}

}
