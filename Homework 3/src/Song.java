

/**
 * 
 * @author Shuo Sun ss8ee Homework 2 101
 * @author Vivi Tran vpt3rs Homework 3 101
 * 
 */
public class Song implements Playable {

	private String artist; // the artist performing the song
	private String title; // the title of the song
	private int minutes; // number of min in length
	private int seconds; // number of seconds of length of the song (aleways
							// less than 60)
	private String fileName;

	/**
	 * 
	 * Constructor with initial values of artist and title
	 */
	public Song(String artist, String title, String fileName) {
		this.artist = artist;
		this.title = title;
		this.fileName = fileName;
	}

	/**
	 * 
	 * Constructor with initial values of artist, title, minutes and seconds
	 */
	public Song(String artist, String title, int minutes, int seconds,
			String fileName) {
		this.artist = artist;
		this.title = title;
		this.minutes = minutes;
		this.seconds = seconds;
		this.fileName = fileName;
	}

	/**
	 * 
	 * Constructor with initial values of a song
	 */
	public Song(Song s) {
		this.artist = s.getArtist();
		this.title = s.getTitle();
		this.minutes = s.getMinutes();
		this.seconds = s.getSeconds();

	}

	/**
	 * 
	 * @Override
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * 
	 * @Override
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * 
	 * @Override
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @Override
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * `
	 * 
	 * @Override
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * 
	 * @Override
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * 
	 * @Override
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * 
	 * @Override
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String getFilename() {
		return fileName;
	}

	/**
	 * 
	 * Compare to songs to see if they are the same song
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Song))
			return false;
		else
			return (this.compareTo((Song) o) == 0);
	}

	/**
	 * 
	 * implement a compareTo interface
	 */
	public int compareTo(Song song2) {
		if (this.getArtist().compareTo(song2.getArtist()) == 0)
			if (this.getTitle().compareTo(song2.getTitle()) == 0)
				if (this.getMinutes() == (song2.getMinutes()))
					if (this.getSeconds() == (song2.getSeconds()))
						if (this.getFilename().compareTo(song2.getFilename()) == 0)
							return 0;
						else
							return this.getFilename().compareTo(
									song2.getFilename());
					else
						return this.getSeconds() - song2.getSeconds();
				else
					return this.getMinutes() - song2.getMinutes();
			else
				return this.getTitle().compareTo(song2.getTitle());
		else
			return this.getArtist().compareTo(song2.getArtist());
	}

	/**
	 * 
	 * return the Song with minutes and seconds
	 */
	public String toString() {
		return minutes + ":" + seconds;
	}

	/**
	 * 
	 * Play the song with it artist and title, also only 5 seconds
	 */
	@Override
	public void play() {

	}

	@Override
	public void play(double scd) {
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public String getName() {
		return title;
	}

	@Override
	public int getPlayTimeSeconds() {
		return minutes * 60 + seconds;
	}

}
