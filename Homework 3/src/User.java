
/**
 * 
 * @author Shuo Sun ss8ee Homework 2 101
 * @author Vivi Tran vpt3rs Homework 3 101
 * 
 */
public interface User {

	/**
	 * Method that creates playList
	 */
	public void createNew(String name);

	/**
	 * Method that loads song or video
	 */
	public void loadMedia(String fileName, PlayList pl);

	/**
	 * Method that lets user add songs directly
	 */
	public boolean addSong(String mp3Name, PlayList pl);

	/**
	 * Method that lets user add a playlist
	 */
	public boolean addPlayList(PlayList pl1, PlayList pl2);

	/**
	 * Method that plays all songs, videos, or playlists
	 */
	public void playAll(PlayList pl);

	/**
	 * Method that plays all, with option of giving seconds
	 */
	public void playAll(PlayList pl, double sds);

	/**
	 * Method that plays a given mp3 file
	 */
	public void playmp3(String mp3Name);

	/**
	 * Method that plays a given mp3 file, with specification to how many
	 * seconds
	 */
	public void playmp3(String mp3Name, double sds);

	/**
	 * Method that prints the playlist
	 */
	public boolean printPl(PlayList pl);

	/**
	 * Method that prints playlists from the default playlist
	 */
	public boolean printDef();

	/**
	 * Method that prints name of all playlists
	 */
	public boolean printAllPl();

	/**
	 * Method that calls quits on the application
	 */
	public void quit();

}