package com.bloom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import com.bloom.models.Movie;
/**
 * This is the DAO class for Movie table.
 * All the queries are defined here.
 * @author Guru
 *
 */
public class MovieDao {
	
	public static List<Movie> movieList = new ArrayList<Movie>();
	/**
	 * This method will return all the movies 
	 * @return List of Movies
	 */
	public List<Movie> getAllMovies() {
		return movieList;
	}
	/**
	 * This method will add new movie. 
	 * @param movie
	 * @return id of new movie
	 */
	public int  addMovie( Movie movie) {
		// get last element
		if(movieList.size() == 0) {
			movie.setId( 1);
			
		} else {
			Movie lastMovie = movieList.get(movieList.size() - 1);
			movie.setId(lastMovie.getId() + 1);
		}
		movieList.add(movie);
		return movie.getId();
	}
	/**
	 * This method will return MOvie details by id
	 * @param id
	 * @return Movie by id
	 */
	public Movie getMovieById( int id) {
		
		for (Movie movie : movieList) {
			if(movie.getId() == id) {
				return movie;
			}
		} 
		return null;
	}
	/**
	 * This methjod will remove the movie.
	 * @param id
	 * @return id of deleted movie
	 */
	public int removeMovieById( int id) {
		 for (Movie movie : movieList) {
			 if(movie.getId() == id) {
				 movieList.remove(movie);
				 return id;
	         }
		 } 
		 return 0;
	}
	/**
	 * This method will update movie details 
	 * @param id
	 * @param movieParam
	 * @return id of updated movie
	 */
	public int updateMovie( int id, Movie movieParam) {
		
		 for (Movie movie : movieList) {
			 if(movie.getId() == id) {
				 movieList.remove(movie);
				 movieParam.setId(id);
				 movieList.add(movieParam);
				 return id;
	         }
		 } 
		 return 0;
	}
	/**
	 * This method will return list of movies based on filed and values
	 * @param fieldname
	 * @param fieldvalue
	 * @return list of movies matches
	 */
	public List<Movie> getMovieByField(String fieldname,String fieldvalue) {
		List<Movie> movieListFiltered =  new ArrayList<Movie>();
		 for (Movie movie : movieList) {
			 if(fieldname.equals("name")) {
				 if(movie.getName().equals(fieldvalue)) {
					 movieListFiltered.add(movie);
				 }
			 }
			 if(fieldname.equals("duration")) {
				 if(movie.getDuration() == new Integer(fieldvalue)) {
					 movieListFiltered.add(movie);
				 }
			 }
			 if(fieldname.equals("actor")) {
				 if(movie.getActor().equals(fieldvalue)) {
					 movieListFiltered.add(movie);
				 }
			 }
			 if(fieldname.equals("actress")) {
				 if(movie.getActress().equals(fieldvalue)) {
					 movieListFiltered.add(movie);
				 }
			 }
			 if(fieldname.equals("director")) {
				 if(movie.getDirector().equals(fieldvalue)) {
					 movieListFiltered.add(movie);
				 }
			 }
			 if(fieldname.equals("releaseYear")) {
				 if(movie.getReleaseYear() == new Integer(fieldvalue)) {
					 movieListFiltered.add(movie);
				 }
			 }
		 } 
		return movieListFiltered;
	}
}
