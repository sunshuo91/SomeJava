import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PlayListTest {
	private PlayList pl;
	private PlayList pl1;
	private PlayList pl2;
	private Song song;
	private Song song1;

	@Before
	public void setUp() throws Exception {
		pl1 = new PlayList();
		pl2 = new PlayList();
		song = new Song("Katy Perry", "Teenage Dream", 3, 24, "");
		song1 = new Song("Adam Lambert", "Cuckoo", 3, 2, "");
	}

	@Test
	public void testLoadMedia() {
		pl.loadMedia("testPlan.txt");
		ArrayList<Playable> list = pl.getPlayableList();
		assertNotNull(list);
		assertEquals(2, list.size());
		assertFalse(list.contains(song));
	}

	@Test
	public void testAddPlayList() {
		pl = new PlayList();
		pl1.addSong(song);
		pl2.addSong(song);
		pl.addPlayList(pl1);
		pl.addPlayList(pl2);
		assertTrue(pl.size() == 2);
	}

	@Test
	public void testAddPlayable() {
		pl = new PlayList();
		pl1.addSong(song);
		pl2.addSong(song);
		pl.addPlayList(pl1);
		pl.addPlayList(pl2);
		pl.addSong(song1);
		assertTrue(pl.size() == 3);

	}

}
