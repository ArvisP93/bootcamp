package hello;

import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cbs.Shows;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestShows {
	
	@Test
	public void testShowId() {
		Shows obj = new Shows();
		try {
			obj.setShow_id(0);
			fail("Exception was expected for 0 show_id input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setShow_id(-11);
			fail("Exception was expected for negative show_id input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCinemaId() {
		Shows obj = new Shows();
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
	public void testMovieId() {
		Shows obj = new Shows();
		try {
			obj.setMovie_id(0);
			fail("Exception was expected for 0 movie_id input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setMovie_id(-10);
			fail("Exception was expected for negative movie_id input");
		}
		catch(IllegalArgumentException e) {
		}
	
	}
	
	@Test
	public void testRoomId() {
		Shows obj = new Shows();
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
	public void testCinema_name() {
		Shows obj = new Shows();
		try {
			obj.setCinema_name("");
			fail("Exception was expected for empty cinema_name input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setCinema_name("Veryveryverylongcinemanameshouldcouseerrors");
			fail("Exception was expected for invalid cinema_name input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testMovie_name() {
		Shows obj = new Shows();
		try {
			obj.setMovie_name("");
			fail("Exception was expected for empty movie_name input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setMovie_name("Veryveryverylongmovienameshouldcouseerrors");
			fail("Exception was expected for invalid movie_name input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testRoom_name() {
		Shows obj = new Shows();
		try {
			obj.setRoom_name("");
			fail("Exception was expected for empty room_name input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setRoom_name("Veryveryverylongroomnameshouldcouseerrors");
			fail("Exception was expected for invalid room_name input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testMovie_genre() {
		Shows obj = new Shows();
		try {
			obj.setMovie_genre("");
			fail("Exception was expected for empty movie_genre input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setRoom_name("Veryveryverylonggenrenameshouldcouseerrors");
			fail("Exception was expected for invalid movie_genre input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testTotal_seats() {
		Shows obj = new Shows();
		try {
			obj.setTotal_seats(0);
			fail("Exception was expected for 0 total_seats input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setTotal_seats(-11);
			fail("Exception was expected for negative total_seats input");
		}
		catch(IllegalArgumentException e) {
		}
	}

}
