package hello;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cbs.Movies;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestMovies {
	
	@Test
	public void testConstructor(){
		try {
			Movies obj = new Movies(0, "Batman", "Thriller");
			fail("Exception was expected for 0 id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Movies obj = new Movies(-15, "Batman", "Thriller");
			fail("Exception was expected for negative id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Movies obj = new Movies(11, "", "Thriller");
			fail("Exception was expected for empty name input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Movies obj = new Movies(11, "Veryverylongmovienametoolongforthesetter", "Thriller");
			fail("Exception was expected for invalid name input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Movies obj = new Movies(11, "Batman", "");
			fail("Exception was expected for empty genre input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Movies obj = new Movies(11, "Batman", "Veryverylonggenrenametoolongforthesetter");
			fail("Exception was expected for invalid genre input");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testId() {
		Movies obj = new Movies();
		try {
			obj.setMovie_id(0);
			fail("Exception was expected for 0 id input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setMovie_id(-10);
			fail("Exception was expected for negative id input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testName() {
		Movies obj = new Movies();
		try {
			obj.setName("");
			fail("Exception was expected for empty name input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setName("Veryverylongmovienametoolongforthesetter");
			fail("Exception was expected for invalid name input");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testGenre() {
		Movies obj = new Movies();
		try {
			obj.setGenre("");
			fail("Exception was expected for empty genre input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setGenre("Veryverylonggenrenametoolongforthesetter");
			fail("Exception was expected for invalid genre input");
		} catch(IllegalArgumentException e) {
		}
	}

}
