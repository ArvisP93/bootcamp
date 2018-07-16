package hello;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hello.Cinemas;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCinemas {
	
	@Test
	public void testConstructor(){
		try {
			Cinemas obj = new Cinemas(0, "KinoRio", 10.2, 15.7);
			fail("Exception was expected for 0 id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Cinemas obj = new Cinemas(1010, "", 10.2, 15.7);
			fail("Exception was expected for empty name input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Cinemas obj = new Cinemas(1010, "KinoRio", -100.0, 15.7);
			fail("Exception was expected for latitude input out of bondaries");
		} catch(IllegalArgumentException e) {
		}
		try {
			Cinemas obj = new Cinemas(1010, "KinoRio", 10.2, -200.0);
			fail("Exception was expected for longitude input out of bondaries");
		} catch(IllegalArgumentException e) {
		}
		
	}
	
	@Test
	public void testId() {
		Cinemas obj = new Cinemas();
		try {
			obj.setCinema_id(0);
			fail("Exception was expected for 0 id input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setCinema_id(-10);
			fail("Exception was expected for negative id input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testName() {
		Cinemas obj = new Cinemas();
		try {
			obj.setName("");
			fail("Exception was expected for empty name input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testLatitude() {
		Cinemas obj = new Cinemas();
		
	}
	
	@Test
	public void testLongitude() {
		Cinemas obj = new Cinemas();
		
	}
}
