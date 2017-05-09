import static org.junit.Assert.*;

import org.junit.*;

public class TestMessage {
	
	
	@Test
	public void testMessage(){
		String m = "hola desde el Test";
		assertEquals("hola",m);
	}

}
