import static org.junit.Assert.*;

import org.junit.Test;

public class ControllerProcuraTest {

	@Test
	public void test() {
		String m = "vamos procurar uma palavra nesta mensagem";
		String palavra = "vamos procurar";
		
		FilterWindow filter = new FilterWindow();
		assertEquals(filter.procurar(m, palavra), true); 
	}

}
