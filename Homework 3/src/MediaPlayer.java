import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import edu.virginia.cs2110.Mp3FilePlayer;


/**
 * 
 * @author Shuo Sun ss8ee Homework 2 101
 * @author Vivi Tran vpt3rs Homework 3 101
 * 
 */
public class MediaPlayer implements User {

	/**
	 * 
	 * It holds the play lists
	 */
	ArrayList<PlayList> playlistHolder = new ArrayList<PlayList>();
	/**
	 * 
	 * creates new object
	 */
	PlayList playlist = new PlayList();

	public PlayList getDefaultPlayList() {
		return playlist;

	}

	/**
	 * 
	 * return certain playlist
	 */
	public ArrayList<PlayList> getPlayLists() {
		playlistHolder.add(playlist);
		return playlistHolder;
	}

	/**
	 * 
	 * main method to run the program
	 */
	public static void main(String[] args) {
		MediaPlayer m = new MediaPlayer();
		m.start();
	}

	/**
	 * 
	 * to start the program
	 */
	public void start() {
		Scanner scanner = new Scanner(System.in);
		while (playlistHolder.size() >= 0) {
			// Create new play list with given name
			System.out.println("What would you like to call"
					+ " your new playlist?: ");
			String answer = scanner.nextLine();
			PlayList list = new PlayList(answer);

			// Do something with play list
			System.out.println("Would you like to add anything"
					+ " to your playlist? (Enter Y or N): ");
			String ans2 = scanner.nextLine();
			if (ans2.equals("Y")) {
				System.out
						.println("You can load media, including songs and videos, from a file, add an existing playlist to your playlist, OR add a Song. What would you like to do? (Please enter load, song, or playlist.) ");

				String ans3 = scanner.nextLine();
				if (ans3.equals("load")) {
					System.out.println("Enter the name of "
							+ "the file in which you would like to load "
							+ "media from: ");
					String loadFile = scanner.nextLine();
					System.out.println("Media is now being "
							+ "added to your current playlist. YAY. ");
					loadMedia(loadFile, list);
					printPl(list);
				} else if (ans3.equals("song")) {
					System.out.println("Please nter the file name "
							+ "for the mp3 that you'd like to add "
							+ "to your current playlist.: ");
					String mp3name = scanner.nextLine();
					addSong(mp3name, list);
				} else if (ans3.equals("playlist")) {
					System.out.println("Enter the name of the "
							+ "playlist that you would like to add "
							+ "to your current playlist: ");
					String playlist2 = scanner.nextLine();
					PlayList pl3 = new PlayList(playlist2);
					addPlayList(playlist, pl3);
				} else {
					System.out
							.println("You didn't enter one of the choices listed..");

				}
			} else if (ans2.equals("N")) {
				System.out
						.println("What would you like to do next? There are plenty of options! "
								+ "You can play a playlist, play an MP3 file, "
								+ "print information about a playlist, or exit "
								+ "the application. (Please enter play, play mp3, "
								+ "print, or exit): ");
				String ans4 = scanner.nextLine();
				if (ans4.equals("play")) {
					System.out.println("Would you like to play each "
							+ "object in your playlist for a certain "
							+ "number of seconds? (Enter Y or N): ");
					String ans5 = scanner.nextLine();
					if (ans5.equals("Y")) {
						System.out.println("For how many seconds would "
								+ "you like to play each object?: ");
						double seconds = scanner.nextDouble();
						System.out.println("We are now playing your "
								+ "play-list!");
						playAll(list, seconds);
					} else if (ans5.equals("N")) {
						System.out.println("We are now playing your "
								+ "playlist!");
						System.out.println(list.toString());
						playAll(list);
					}
				} else if (ans4.equals("play mp3")) {
					System.out.println("What's the name of the mp3 "
							+ "file you would like to play?: ");
					String mp3name = scanner.nextLine();
					System.out.println("Would you like to play the "
							+ "mp3 for a certain number of seconds? "
							+ "(Enter Y or N): ");
					String ans6 = scanner.nextLine();
					if (ans6.equals("Y")) {
						System.out.println("For how many seconds would you "
								+ "like to play the song?: ");
						double seconds = scanner.nextDouble();
						System.out.println("We are now playing your mp3 file!");
						playmp3(mp3name, seconds);
					} else if (ans6.equals("N")) {
						System.out.println("We are now playing your mp3 file!");
						playmp3(mp3name);
					} else {
						System.out
								.println("You didn't enter one of the choices listed..");
					}
				} else if (ans4.equals("print")) {
					System.out
							.println("What would you like to do? Print out your "
									+ "current playlist info, the info of the default "
									+ "playlist, or the info of all the play-lists "
									+ "currently in the application? (Enter current, "
									+ "default, or all): ");
					String ans7 = scanner.nextLine();
					if (ans7.equals("current")) {
						printPl(list);
					} else if (ans7.equals("default")) {
						printDef();
					} else if (ans7.equals("all")) {
						printAllPl();
					} else {
						System.out
								.println("You didn't enter one of the choices listed..");
					}
				} else if (ans4.equals("exit")) {
					System.out.println("You are now exiting the application.");
					quit();
				} else {
					System.out
							.println("You didn't enter one of the choices listed..");
				}
			} else {
				System.out
						.println("You didn't enter one of the choices listed..");
			}
			System.out.println("Would you like to do anything else? "
					+ "(Enter Y or N): ");
			String reply = scanner.nextLine();
			if (reply.equals("Y")) {
				System.out
						.println("What do you want to do next? Your options include playing a playlist, playing an mp3 file, printing information about a playlist, or exiting the application. (Enter play, play mp3, "
								+ "print, or exit): ");

				String ans4 = scanner.nextLine();
				if (ans4.equals("play")) {
					System.out.println("Do you want to play each "
							+ "object in your playlist for a certain "
							+ "number of seconds? (Enter Y or N): ");
					String ans5 = scanner.nextLine();
					if (ans5.equals("Y")) {
						System.out.println("For how many seconds would "
								+ "you like to play each object?: ");
						double seconds = scanner.nextDouble();
						System.out.println("We are now playing your "
								+ "playlist! YAY! ");
						playAll(list, seconds);
					} else if (ans5.equals("N")) {
						System.out.println("We are now playing your "
								+ "playlist! YAY! ");
						playAll(list);
					}
				} else if (ans4.equals("play mp3")) {
					System.out.println("What's the name of the mp3 "
							+ "file you would like to play?: ");
					String mp3name = scanner.nextLine();
					System.out.println("Would you like to play the "
							+ "mp3 for a certain number of seconds? "
							+ "(Enter Y or N): ");
					String ans6 = scanner.nextLine();
					if (ans6.equals("Y")) {
						System.out.println("For how many seconds would you "
								+ "like to play the song?: ");
						double seconds = scanner.nextDouble();
						System.out.println("We are now playing your mp3 file!");
						playmp3(mp3name, seconds);
					} else if (ans6.equals("N")) {
						System.out.println("We are now playing your mp3 file!");
						playmp3(mp3name);
					} else {
						System.out
								.println("You didn't enter one of the choices listed..");
					}
				} else if (ans4.equals("print")) {
					System.out
							.println("What would you like to do next? Your options include printing out your current playlist info, the info of the default playlist, or the info from all your playlists. (Please enter current, default, all): ");

					String ans7 = scanner.nextLine();
					if (ans7.equals("current")) {
						printPl(list);
					} else if (ans7.equals("default")) {
						printDef();
					} else if (ans7.equals("all")) {
						printAllPl();
					} else {
						System.out
								.println("You didn't enter one of the choices listed..");
					}
				} else if (ans4.equals("exit")) {
					System.out.println("You are now exiting the application.");
					quit();
				} else {
					System.out
							.println("You didn't enter one of the choices listed..");
				}
			} else {
				quit();
			}
		}
	}

	/**
	 * 
	 * It loads media
	 */
	@Override
	public void loadMedia(String fileName, PlayList pl) {
		if (!fileNotExist(fileName)) {
			pl.loadMedia(fileName);
		}
	}

	/**
	 * 
	 * It adds songs
	 */
	@Override
	public boolean addSong(String mp3FileName, PlayList pl) {
		if (playListNotExist(pl)) {
			return false;
		} else {
			Song song = new Song("", "", 0, 0, mp3FileName);
			pl.addSong(song);
			return true;
		}
	}

	/**
	 * 
	 * It adds playlist
	 */
	@Override
	public boolean addPlayList(PlayList p1, PlayList p2) {
		if (playListNotExist(p1) || playListNotExist(p2)) {
			return false;
		} else {
			if (p1.equals(p2)) {
				return false;
			} else {
				p1.addPlayList(p2);
				return true;
			}
		}
	}

	/**
	 * 
	 * It plays all the play lists
	 */
	@Override
	public void playAll(PlayList pl) {
		pl.play();
	}

	/**
	 * 
	 * It plays all the play lists for certain seconds
	 */
	@Override
	public void playAll(PlayList pl, double seconds) {
		pl.play(seconds);
	}

	/**
	 * 
	 * It plays a mp3 file with a certain name
	 */
	@Override
	public void playmp3(String mp3FileName) {
		File filename = new File(mp3FileName);
		if (!filename.exists()) {
			System.out.println("The MP3 file for that song cannot be found!");
		} else {
			Mp3FilePlayer mp3 = new Mp3FilePlayer(mp3FileName);
			mp3.playAndBlock();
		}
	}

	/**
	 * 
	 * It plays a mp3 file with a certain name for certain seconds
	 */
	@Override
	public void playmp3(String mp3FileName, double seconds) {
		File filename = new File(mp3FileName);
		if (!filename.exists()) {
			System.out.println("The MP3 file for that song cannot be found!");
		} else {
			Mp3FilePlayer mp3 = new Mp3FilePlayer(mp3FileName);
			mp3.playAndBlock(seconds);
		}
	}

	/**
	 * 
	 * It prints the playlist
	 */
	@Override
	public boolean printPl(PlayList pl) {
		if (playListNotExist(pl)) {
			return false;
		} else {
			System.out.println(pl);
			return true;
		}
	}

	/**
	 * 
	 * It prints the Default play list
	 */
	@Override
	public boolean printDef() {
		for (Playable p : playlist.getPlayableList()) {
			System.out.println(p.getName());
		}
		return true;
	}

	/**
	 * 
	 * It prints all the play list
	 */
	@Override
	public boolean printAllPl() {
		ArrayList<String> list = new ArrayList<String>();
		for (PlayList pl : playlistHolder) {
			list.add(pl.getName());
			list.add(playlist.getName());
		}
		System.out.println(list);
		return true;
	}

	/**
	 * 
	 * Helper method when file does not exist
	 */
	public boolean fileNotExist(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("Error: This file does not exist!");
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Helper method when playlist does not exist
	 */
	public boolean playListNotExist(PlayList pl) {
		for (PlayList pl1 : playlistHolder) {
			if (!pl1.equals(pl)) {
				System.out.println("Error: This  does not exist!");
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * exit the app
	 */
	@Override
	public void quit() {
		System.exit(0);
	}

	/**
	 * 
	 * it creates a new pl
	 */
	@Override
	public void createNew(String name) {
		PlayList pl = new PlayList(name);
		playlistHolder.add(pl);

	}
}
