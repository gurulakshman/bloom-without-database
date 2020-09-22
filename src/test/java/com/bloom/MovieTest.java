package com.bloom;




import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.bloom.models.Movie;
import com.bloom.resources.MovieResource;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
@ExtendWith(DropwizardExtensionsSupport.class)
public class MovieTest {
    private final Movie movie = new Movie(1,"name1", 100, "actor1", "actress1", "director1", 2020);

	private static final ResourceExtension EXT = ResourceExtension.builder()
	            .addResource(new MovieResource("testapi"))
	            .build();
	@BeforeEach
    void setup() {
	 	
    }

    @AfterEach
    void tearDown() {
       
    }
    /**
     * This method will test the get all movies.
     * If it fails it will throw exception.
     * @throws Exception
     */
    @Test
    void getAllMovies() throws Exception{
    	Movie[] movieList = EXT.target("/movies").request().get(Movie[].class);

    	assertEquals(movieList[0].getId(),1);
    }
    /**
     * This method will test the insert movie data to database.
     * If it fails it will throw exception.
     * @throws Exception
     */
    @Test
    void addMovie() {
    	final Response response = EXT.target("/movies").request().post(Entity.json(movie));
    	Movie responseMovie = response.readEntity(Movie.class);
    	assertEquals(responseMovie.getId(),1);
    	
    }
    /**
     * This method will test the get movie details by id.
     * If it fails it will throw exception.
     * @throws Exception
     */
    @Test
    public void testGetMovieId() throws Exception {
    	Movie responseMovie = EXT.target("/movies/1").request().get(Movie.class);
    	assertEquals(responseMovie.getId(),1);
    }
    /**
     * This method will test the delete movie.
     * If it fails it will throw exception.
     * @throws Exception
     */
    @Test
    public void testDeleteMovieById() throws Exception {
    	final Response response = EXT.target("/movies/1").request().delete();
    	String output = response.readEntity(String.class);
    	assertEquals(output,"1");
    }
}
