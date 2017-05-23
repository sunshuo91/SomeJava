import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 * @author Shuo Sun ss8ee Homework 2 101
 * @author Vivi Tran vpt3rs Homework 3 101
 * 
 */
public class PlayList implements Playable, Comparator<Playable> {

	private String name;
	private ArrayList<Playable> playableList;
	private boolean namesort;
	private boolean timesort;

	/**
	 * 
	 * default value of the name of the playlist
	 */
	public PlayList() {
		this.name = "Untitled";
		this.playableList = new ArrayList<Playable>();
	}

	/**
	 * 
	 * @param namesort
	 *            boolean for name sort
	 * @param timesort
	 *            boolean for time sort
	 */
	public PlayList(boolean namesort, boolean timesort) {
		this.namesort = namesort;
		this.timesort = timesort;
	}

	/**
	 * 
	 * default value of the name of the playlist and Playable list
	 */
	public PlayList(String name, ArrayList<Playable> songlist) {
		this.name = name;
		this.playableList = songlist;
	}

	/**
	 * 
	 * Constructor that calling when having playlist name input
	 */
	public PlayList(String newName) {
		this.name = newName;
		this.playableList = new ArrayList<Playable>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Playable> getPlayableList() {
		return playableList;
	}

	public void setPlayableList(ArrayList<Playable> playableList) {
		this.playableList = playableList;
	}

	/**
	 * 
	 * Loading Media from a text file, and add those songs into playlist
	 */

	public boolean loadMedia(String fileName1) {
		Scanner keyboard = null;

		File file = new File(fileName1);
		try {
			keyboard = new Scanner(file);
		} catch (FileNotFoundException e) {
			return false;
		}

		while (keyboard.hasNext()) {

			String title;
			String line1 = keyboard.nextLine().trim();

			int comment11 = line1.indexOf("//");
			if (comment11 != -1)
				line1 = line1.substring(0, comment11).trim();

			if (line1.isEmpty() || line1.trim().equals("")
					|| line1.trim().equals("\n")) {
				if (!(keyboard.hasNext()))
					return false;
				do {
					line1 = keyboard.nextLine().trim();

					int comment1 = line1.indexOf("//");
					if (comment1 != -1) {
						line1 = line1.substring(0, comment1).trim();
					}
				} while (line1.isEmpty() || line1.trim().equals("")
						|| line1.trim().equals("\n"));
				title = line1;
			} else {
				title = line1;
			}


			String artist;
			String line2 = keyboard.nextLine().trim();

			int comment22 = line2.indexOf("//");
			if (comment22 != -1)
				line2 = line2.substring(0, comment22).trim();

			if (line2.isEmpty() || line2.trim().equals("")
					|| line2.trim().equals("\n")) {
				do {
					line2 = keyboard.nextLine().trim();

					int comment2 = line2.indexOf("//");
					if (comment2 != -1) {
						line2 = line2.substring(0, comment2).trim();
					}
				} while (line2.isEmpty() || line2.trim().equals("")
						|| line2.trim().equals("\n"));
				artist = line2;
			} else {
				artist = line2;
			}

			String time;
			String line3 = keyboard.nextLine().trim();

			int comment33 = line3.indexOf("//");
			if (comment33 != -1)
				line3 = line3.substring(0, comment33).trim();

			if (line3.isEmpty() || line3.trim().equals("")
					|| line3.trim().equals("\n")) {
				do {
					line3 = keyboard.nextLine().trim();

					int comment3 = line3.indexOf("//");
					if (comment3 != -1) {
						line3 = line3.substring(0, comment3).trim();
					}
				} while (line3.isEmpty() || line3.trim().equals("")
						|| line3.trim().equals("\n"));
				time = line3;
			} else {
				time = line3;
			}

			String split = ":";
			String[] time1 = time.split(split);
			int min = 0;
			int sec = 0;

			int songMin = Integer.parseInt(time1[0]);
			int songSec = Integer.parseInt(time1[1]);

			if (songSec >= 60) {
				min = songMin + (songSec / 60);
				sec = songSec % 60;
			} else {
				min = songMin;
				sec = songSec;
			}

			String fileName;
			String line4 = keyboard.nextLine().trim();

			int comment44 = line4.indexOf("//");
			if (comment44 != -1)
				line4 = line4.substring(0, comment44).trim();

			if (line4.isEmpty() || line4.trim().equals("")
					|| line4.trim().equals("\n")) {
				do {
					line4 = keyboard.nextLine().trim();

					int comment4 = line4.indexOf("//");
					if (comment4 != -1) {
						line4 = line4.substring(0, comment4).trim();
					}
				} while (line4.isEmpty() || line4.trim().equals("")
						|| line4.trim().equals("\n"));
				fileName = line4;
			} else {
				fileName = line4;
			}

			String[] fileName2 = fileName.split(split);
			String youtube = fileName2[0];
			if (youtube.equalsIgnoreCase("youtube")) {
				String onlyFileName = "http://" + fileName2[1];
				Video video = new Video(artist, title, min, sec, onlyFileName);
				addPlayable(video);
			} else {
				Song song = new Song(artist, title, min, sec, fileName);
				addPlayable(song);
				File mp3 = new File(fileName);
				if (!(mp3.exists())) {
					return false;
				}
			}

			keyboard.nextLine();
		}
		return true;

	}

	/**
	 * 
	 * adds Song s to the end of the play list
	 */
	public boolean addSong(Song s) {
		if (!(playableList.contains(s))) {
			playableList.add(s);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Check if the adding playlist is in the current collection of playlists.
	 * if not, add it.
	 */
	public boolean addPlayList(PlayList pl) {
		if (pl != null) {
			if (!(playableList.contains(pl))) {
				playableList.addAll(pl.getPlayableList());
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * 
	 * returns the song at the appropriate index
	 */
	public Playable getPlayable(int index) {
		if (index < 0 || index >= playableList.size())
			return null;
		if (playableList.size() > 0) {
			Playable c = playableList.get(index);
			return c;
		}
		return null;
	}

	/**
	 * 
	 * sorts the class's song list by artist first
	 */
	public void sortByName() {
		Collections.sort(playableList, new PlayList(true, false));
	}

	/**
	 * 
	 * sorts the class's song list by time
	 */
	public void sortByTime() {
		Collections.sort(playableList, new PlayList(false, true));
	}

	/**
	 * 
	 * get total play time in seconds
	 */

	@Override
	public int getPlayTimeSeconds() {
		int time = 0;
		for (Playable s : playableList) {
			time = time + s.getPlayTimeSeconds();
		}
		return time;
	}

	/**
	 * 
	 * Compare two PlayList to see if they are equal to each other
	 */
	public boolean equals(Object o) {
		if (o instanceof Playable) {
			String a = ((Playable) o).getName();
			return (a.equals(this.getName()));
		}
		return false;
	}

	/**
	 * 
	 * plays the play list by calling play() on each Song in the play list in
	 * order
	 */
	public void play() {
		for (int i = 0; i < playableList.size(); i++) {
			Playable s = playableList.get(i);
			s.play();
		}
	}

	/**
	 * 
	 * returns the number of songs in the play list
	 */
	public int size() {
		return playableList.size();
	}

	/**
	 * 
	 * @return the playlist name and its contents
	 */
	public String toStinrg() {
		return name + playableList;
	}

	@Override
	public void play(double seconds) {
		for (int i = 0; i < playableList.size(); i++) {
			Playable s = playableList.get(i);
			s.play(seconds);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int compare(Playable arg0, Playable arg1) {
		if (namesort) {
			return (arg0.getName().compareTo(arg1.getName()));
		}
		if (timesort) {
			return (arg0.getPlayTimeSeconds() - arg1.getPlayTimeSeconds());
		}
		return 0;
	}

	/**
	 * 
	 * return true if new playable is added
	 */
	public boolean addPlayable(Playable p) {
		if (p != null) {
			if (!(playableList.contains(p))) {
				playableList.add(p);
				return true;
			}
			return false;
		}
		return false;
	}

}
