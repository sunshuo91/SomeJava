import java.net.URI;
import java.awt.Desktop;
import java.lang.Thread;

/**
 * 
 * @author Shuo Sun ss8ee Homework 2 101
 * @author Vivi Tran vpt3rs Homework 3 101
 * 
 */
public class Video implements Playable {

	private String videoName;
	private double minutes;
	private double seconds;
	private String user;
	private String title;

	// BLOCK_ADJUSTMENT used to increase time we block when playing a song to
	// allow for
	// time it takes to get video to start up in browser. Adjust for your system
	// if needed.
	private static final int BLOCK_ADJUSTMENT = 3; // units are seconds

	/**
	 * Constructor with the following parameters
	 * @param user
	 * @param title
	 * @param min
	 * @param sec
	 * @param videoName
	 */
	public Video(String user, String title, int min, int sec, String videoName) {
		this.user = user;
		this.title = title;
		this.minutes = min;
		this.seconds = sec;
		this.videoName = videoName; // must in this form:
									// http://www.youtube.com/embed/FzRH3iTQPrk

		if (!videoName.toLowerCase()
				.startsWith("http://www.youtube.com/embed/")) {
			System.out.println("* Constructor given videoName " + videoName
					+ " which is not the proper form.");
			System.out.println("* This video will almost certainly not play.");
		}
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * it plays video
	 */
	public void play() {
		this.play(this.minutes * 60 + this.seconds);
	}

	/**
	 * 
	 * Compare videos to see if they are the same song
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Video))
			return false;
		else
			return (this.compareTo((Video) o) == 0);
	}

	/**
	 * 
	 * implement a compareTo method
	 */
	public int compareTo(Video song2) {
		if (this.getUser().compareTo(song2.getUser()) == 0)
			if (this.getTitle().compareTo(song2.getTitle()) == 0)
				if (this.getMinutes() == (song2.getMinutes()))
					if (this.getSeconds() == (song2.getSeconds()))
						if (this.getVideoName().compareTo(song2.getVideoName()) == 0)
							return 0;
						else
							return this.getVideoName().compareTo(
									song2.getVideoName());
					else
						return (int) (this.getSeconds() - song2.getSeconds());
				else
					return (int) (this.getMinutes() - song2.getMinutes());
			else
				return this.getTitle().compareTo(song2.getTitle());
		else
			return this.getUser().compareTo(song2.getUser());
	}

	/**
	 * 
	 * play each piece for certain seconds
	 */
	public void play(double sec) {
		try {
			Desktop.getDesktop().browse(new URI(videoName + "?autoplay=1"));
			Thread.sleep((int) (1000 * (sec + BLOCK_ADJUSTMENT))); // block
																		// for
																		// length
																		// of
																		// song
		} catch (Exception e) {
			System.out.println("* Error: " + e + " when playing YouTube video "
					+ videoName);
		}
	}
	
	/**
	 * 
	 * main method to test the video
	 */
	public static void main(String[] args) {
		Video v1 = new Video("jimvwmoss", "The Sneezing Baby Panda", 0, 17,
				"http://www.youtube.com/embed/4hpEnLtqUDg");
		System.out.println("* Playing video for 10 seconds.");
		v1.play(10);

		Video v2 = new Video("jimvwmoss", "The Sneezing Baby Panda", 0, 17,
				"http://www.youtube.com/embed/FzRH3iTQPrk");
		System.out.println("* Playing video for full length.");
		v2.play();

		System.out.println("* Should be done when this prints.");

	}

	@Override
	public String getName() {
		return this.videoName;
	}

	@Override
	public int getPlayTimeSeconds() {
		return 0;
	}
}