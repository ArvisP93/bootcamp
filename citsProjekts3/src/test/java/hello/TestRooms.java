package hello;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cbs.Cinemas;
import com.example.cbs.Movies;
import com.example.cbs.Rooms;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestRooms {
	
	@Test
	public void testConstructor(){
		try {
			Rooms obj = new Rooms(0, 101, "Room", 40);
			fail("Exception was expected for 0 id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(-4, 101, "Room", 40);
			fail("Exception was expected for negative id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(1, 0, "Room", 40);
			fail("Exception was expected for 0 cinema_id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(1, -101, "Room", 40);
			fail("Exception was expected for negative cinema_id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(1, 404, "", 40);
			fail("Exception was expected for empty name input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(1, 404, "Vevryverylongroomnametoolongfortesetter", 40);
			fail("Exception was expected for invalid name input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(1, 404, "Room", 0);
			fail("Exception was expected for 0 seats input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Rooms obj = new Rooms(1, 404, "Room", -40);
			fail("Exception was expected for negative seats input");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testRoomId() {
		Rooms obj = new Rooms();
		try {
			obj.setRoom_id(0);
			fail("Exception was expected for 0 room_id input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setRoom_id(-11);
			fail("Exception was expected for negative room_id input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCinemaId() {
		Rooms obj = new Rooms();
		try {
			obj.setCinema_id(0);
			fail("Exception was expected for 0 cinema_id input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setCinema_id(-11);
			fail("Exception was expected for negative cinema_id input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testName() {
		Rooms obj = new Rooms();
		try {
			obj.setName("");
			fail("Exception was expected for empty name input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setName("Vevryverylongroomnametoolongfortesetter");
			fail("Exception was expected for invalid name input");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testSeats() {
		Rooms obj = new Rooms();
		try {
			obj.setSeats(0);
			fail("Exception was expected for 0 seats input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setSeats(-40);
			fail("Exception was expected for negative seats input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	

}
