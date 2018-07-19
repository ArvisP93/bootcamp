package hello;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cbs.Cinemas;


//@RunWith(SpringRunner.class)
//@SpringBootTest
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
			Cinemas obj = new Cinemas(1010, "KinoRio", -10.2, 15.7);
			fail("Exception was expected for negative latitude input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Cinemas obj = new Cinemas(1010, "KinoRio", 10.2, -15.7);
			fail("Exception was expected for negative longitude input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Cinemas obj = new Cinemas(1010, "KinoRio", 0, 15.7);
			fail("Exception was expected for 0 latitude input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Cinemas obj = new Cinemas(0, "KinoRio", 10.2, 0);
			fail("Exception was expected for 0 longitude input");
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
		try {
			obj.setLatitude(0);
			fail("Exception was expected for 0 latitude input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setLatitude(-10.6);
			fail("Exception was expected for negative latitude input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testLongitude() {
		Cinemas obj = new Cinemas();
		try {
			obj.setLongitude(0);
			fail("Exception was expected for 0 longitude input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setLongitude(-10.6);
			fail("Exception was expected for negative longitude input");
		}catch(IllegalArgumentException e) {
		}
	}
}
