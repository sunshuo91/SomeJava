/**
 * 
 * @author Shuo Sun ss8ee Homework 1 101
 * 
 */
public interface Playable {
	/**
	 * 
	 */
	public void play();

	/**
	 * 
	 */
	public void play(double seconds); // play this many secodns of each song

	/**
	 * 
	 */
	public String getName(); // returns the name or title of Playable object.

	/**
	 * 
	 */
	public int getPlayTimeSeconds();

}
