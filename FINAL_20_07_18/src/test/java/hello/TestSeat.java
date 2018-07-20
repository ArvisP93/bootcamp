package hello;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.example.cbs.Seat;

public class TestSeat {
	
	@Test
	public void testConstructor() {
		Seat obj = new Seat(-11, true);
		assertEquals(obj.getId(), 0);
		assertEquals(obj.isTaken(), true);
		obj.setId(25);
		obj.setTaken(false);
		assertEquals(obj.getId(), 25);
		assertEquals(obj.isTaken(), false);
	}

	@Test
	public void testId() {
		Seat obj = new Seat();
		obj.setId(-11);
		assertEquals(obj.getId(), 0);
		obj.setId(0);
		assertEquals(obj.getId(), 0);
		obj.setId(25);
		assertEquals(obj.getId(), 25);
	}
	
}
