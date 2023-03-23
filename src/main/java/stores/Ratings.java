package stores;

import java.util.Arrays;
import java.util.Calendar;

import interfaces.IRatings;
import structures.*;

public class Ratings implements IRatings {

    private BinarySearchTree binarySearchTree;
    private HashTable userHashTable;
    private HashTable movieHashTable;
    private SimpleArrayList users;
    private SimpleArrayList movies;
    /**
     * The constructor for the Ratings data store. This is where you should
     * initialise your data structures.
     */
    public Ratings() {
        this.userHashTable = new HashTable("userID");
        this.movieHashTable = new HashTable("movieID");
        this.users = new SimpleArrayList();
        this.movies = new SimpleArrayList();
        this.binarySearchTree = new BinarySearchTree<>();
    }

    /**
     * Adds a rating to the data structure. The rating is made unique by its user ID
     * and its movie ID
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The rating gave to the film by this user (between 0 and 5
     *                  inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int userID, int movieID, float rating, Calendar timestamp) {
      
        SimpleMap listOfProperties = new SimpleMap(3);
        listOfProperties.add(new KeyValuePair<String, Integer>("userID", userID));
        listOfProperties.add(new KeyValuePair<String, Integer>("movieID", movieID));
        listOfProperties.add(new KeyValuePair<String, Float>("rating", rating));

        KeyValuePair<Calendar, SimpleMap> dateAndProperties = new KeyValuePair<Calendar,SimpleMap>(timestamp, listOfProperties); 
        binarySearchTree.add(dateAndProperties);

        users.add(userID);
        movies.add(movieID);

        SimpleMap userListOfProperties = new SimpleMap(3);
        userListOfProperties.add(new KeyValuePair<String, Integer>("userID", userID));
        userListOfProperties.add(new KeyValuePair<String, SimpleArrayList>("movies", movies));
        userListOfProperties.add(new KeyValuePair<String, Float>("ratings", rating));

        SimpleMap movieListOfProperties = new SimpleMap(3);
        movieListOfProperties.add(new KeyValuePair<String, Integer>("movieID", movieID));
        movieListOfProperties.add(new KeyValuePair<String, SimpleArrayList>("users", users));
        movieListOfProperties.add(new KeyValuePair<String, Float>("rating", rating));
        
        System.out.println("adding: " + ((Calendar) timestamp).getTime());
        
        userHashTable.add(userListOfProperties);
        movieHashTable.add(movieListOfProperties);
        binarySearchTree.printNodesInOrder();
        
        return true;
        
          
        
    }

    /**
     * Removes a given rating, using the user ID and the movie ID as the unique
     * identifier
     * 
     * @param userID  The user ID
     * @param movieID The movie ID
     * @return TRUE if the data was removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int userID, int movieID) {
        // TODO Build this function
        return false;
    }

    /**
     * Sets a rating for a given user ID and movie ID. Therefore, should the given
     * user have already rated the given movie, the new data should overwrite the
     * existing rating. However, if the given user has not already rated the given
     * movie, then this rating should be added to the data structure
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The new rating to be given to the film by this user (between
     *                  0 and 5 inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added/updated, FALSE otherwise
     */
    @Override
    public boolean set(int userID, int movieID, float rating, Calendar timestamp) {

        add(userID, movieID, rating, timestamp);

        return true;
    }

    /**
     * Find all ratings between a given start date and end date. If a rating falls
     * exactly on a given start date or a given end date, then this should not be
     * included
     * 
     * @param start The start time for the range
     * @param end   The end time for the range
     * @return An array of ratings between start and end. If there are no ratings,
     *         then return an empty array
     */
    @Override
    public float[] getRatingsBetween(Calendar start, Calendar end) {
        return binarySearchTree.getFloatPropertiesInRange(start, end, "rating", null);
    }

    /**
     * Find all ratings for a given film, between a given start date and end date.
     * If a rating falls exactly on a given start date or a given end date, then
     * this should not be included
     * 
     * @param movieID The movie ID
     * @param start   The start time for the range
     * @param end     The end time for the range
     * @return An array of ratings between start and end for a given film. If there
     *         are no ratings, then return an empty array
     */
    @Override
    public float[] getMovieRatingsBetween(int movieID, Calendar start, Calendar end) {
        KeyValuePair<String, Integer> moviePair = new KeyValuePair<String, Integer>("movieID", movieID);
        return binarySearchTree.getFloatPropertiesInRange(start, end, "rating", moviePair);
    }

    /**
     * Find all ratings for a given user, between a given start date and end date.
     * If a rating falls exactly on a given start date or a given end date, then
     * this should not be included
     * 
     * @param userID The user ID
     * @param start  The start time for the range
     * @param end    The end time for the range
     * @return An array of ratings between start and end for a given user. If there
     *         are no ratings, then return an empty array
     */
    @Override
    public float[] getUserRatingsBetween(int userID, Calendar start, Calendar end) {
        KeyValuePair<String, Integer> userPair = new KeyValuePair<String, Integer>("userID", userID);
        return binarySearchTree.getFloatPropertiesInRange(start, end, "rating", userPair);
    }

    /**
     * Get all the ratings for a given film
     * 
     * @param movieID The movie ID
     * @return An array of ratings. If there are no ratings or the film cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getMovieRatings(int movieID) {
        System.out.println("ooof");
        float[] result = (float[]) userHashTable.getPropertyByID(movieID, "ratings", "movieID");
        if (result == null){
            float[] empty = {};
            return empty;
        }
        else{
            return result;
        }
        
    }

    /**
     * Get all the ratings for a given user
     * 
     * @param userID The user ID
     * @return An array of ratings. If there are no ratings or the user cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getUserRatings(int userID) {
        float[] result = (float[]) userHashTable.getPropertyByID(userID, "ratings", "userID");
        if (result == null){
            float[] empty = {};
            return empty;
        }
        else{
            return result;
        }
    }

    /**
     * Get the average rating for a given film
     * 
     * @param movieID The movie ID
     * @return Produces the average rating for a given film. If the film cannot be
     *         found, or there are no rating, return 0
     */
    @Override
    public float getMovieAverageRatings(int movieID) {
        float[] array = getMovieRatings(movieID);

        if (array.length > 0){
            int total = 0;
            for (int i = 0; i < array.length; i++){
                total += array[i];
            }
            return total/array.length;
        }
        else{
            return 0;
        }
    }

    /**
     * Get the average rating for a given user
     * 
     * @param userID The user ID
     * @return Produces the average rating for a given user. If the user cannot be
     *         found, or there are no rating, return 0
     */
    @Override
    public float getUserAverageRatings(int userID) {
        float[] array = getUserRatings(userID);
        if (array.length > 0){
            int total = 0;
            for (int i = 0; i < array.length; i++){
                total += array[i];
            }
            return total/array.length;
        }
        else{
            return 0;
        }
    }

    /**
     * Gets the top N films with the most ratings, in order from most to least
     * 
     * @param num The number of films that should be returned
     * @return A sorted array of film IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num films in the store,
     *         then the array should be the same length as the number of films
     */
    @Override
    public int[] getTopMovies(int num) {
        // TODO Build this function
        return new int[0];
    }

    /**
     * Gets the top N users with the most ratings, in order from most to least
     * 
     * @param num The number of users that should be returned
     * @return A sorted array of user IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num users in the store,
     *         then the array should be the same length as the number of users
     */
    @Override
    public int[] getMostRatedUsers(int num) {
        // TODO Build this function
        return new int[0];
    }

    /**
     * Gets the number of ratings in the data structure
     * 
     * @return The number of ratings in the data structure
     */
    @Override
    public int size() {
        return binarySearchTree.getSize();
    }

}
