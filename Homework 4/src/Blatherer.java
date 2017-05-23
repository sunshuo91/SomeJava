import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * 
 * @author Shuo Sun ss8ee Homework 4 101
 * 
 */
public class Blatherer implements Iterable<String> {

	private int number;
	private Map<String, ArrayList<String>> gram = new LinkedHashMap<String, ArrayList<String>>();

	/**
	 * 
	 * @param args
	 *            main method to test the methods in this class and baltheIter
	 *            class
	 */
	public static void main(String[] args) {

		Blatherer b = new Blatherer(4);
		Scanner smallText = new Scanner(
				"1 2 3 4 5 6 7");
		b.feed(smallText);
		BlatherIter abc = b.iterator(5, "1~2~3");
		abc.moreWords(1);
		System.out.println(abc.next());
		System.out.println(abc.next());
		System.out.println(abc.next());
		System.out.println(abc.next());
		System.out.println(abc.next());
		System.out.println(abc.next());
		System.out.println(abc.next());
		System.out.println(abc.next());

		// System.out.println(b.getGram());
		// b.removeDeadEnds();
		// System.out.println(b.getGram());

//		 System.out.println(b.runLengths());
//		 b.removeRunsLongerThan(3);
//		 System.out.println(b.getGram());

		// b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/98.txt.utf-8"));
		// // Tale
		// // of
		// // Two
		// // Cities
		// System.out.println("Fed pages");
		/*
		 * b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/1400.txt.utf-8"
		 * )); // Great Expectations System.out.println("Fed pages");
		 * b.feed(scannerForWebPage
		 * ("http://www.gutenberg.org/ebooks/730.txt.utf-8")); // Oliver Twist
		 * System.out.println("Fed pages");
		 * b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/766.txt.utf-8"
		 * )); // David Copperfield System.out.println("Fed pages");
		 * b.feed(scannerForWebPage
		 * ("http://www.gutenberg.org/ebooks/1023.txt.utf-8")); // Bleak House
		 * System.out.println("Fed pages");
		 * b.feed(scannerForWebPage("http://www.gutenberg.org/ebooks/580.txt.utf-8"
		 * )); // The Pickwick Papers System.out.println("Fed pages");
		 * b.feed(scannerForWebPage
		 * ("http://www.gutenberg.org/ebooks/967.txt.utf-8")); // Nicolas
		 * Nickleby System.out.println("Fed pages");
		 */
	}

	/**
	 * This helper method can print text neatly wrapped to 80 columns with
	 * indented paragraphs.
	 * 
	 * @param iter
	 *            an iterator that gives an empty string for a paragraph break
	 * @author Luther Tychonievich (lat7h)
	 */
	public static void prettyPrint(Iterator<String> iter) {
		int col = 0;
		while (iter.hasNext()) {
			String w = iter.next();
			col += 1 + w.length();
			if (w.length() == 0) {
				System.out.print("\n    ");
				col = 0;
			} else if (col > 80) {
				System.out.print(w + "\n");
				col = 0;
			} else {
				System.out.print(w + " ");
			}
		}
	}

	/**
	 * This helper method can print text neatly wrapped to 80 columns with
	 * indented paragraphs.
	 * 
	 * @param iter
	 *            an iterable that gives an empty string for a paragraph break
	 * @author Luther Tychonievich (lat7h)
	 */
	public static void prettyPrint(Iterable<String> iter) {
		prettyPrint(iter.iterator());
	}

	/**
	 * A helper method to download pages only once, reducing network traffic. It
	 * also returns only words, ignoring non-word stuff. It returns
	 * Gutenberg.org-style paragraph breaks (1+ blank lines) the empty string ""
	 * This is already finished; you don't need to tweak it.
	 * 
	 * @author Luther Tychonievich (lat7h)
	 * @param page
	 *            the URL to open
	 * @return a Scanner ready to read from a local copy of that page
	 */
	public static Scanner scannerForWebPage(String page) {
		// A regular expression, or Pattern in Java-speak, for splitting text
		// and finding paragraph breaks
		final String abcd = "[^-A-Za-z,.?!\"']*\\n[^-A-Za-z,.?!\"']*(?=\\n)" // 1+
				// newline
				// followed
				// by
				// newline
				+ "|" + "[^-A-Za-z,.?!\"'\\n]*\\n[^-A-Za-z,.?!\"'\\n]*" // one
				// new
				// line
				+ "|" + "[^-A-Za-z,.?!\"'\\n]+" // no new line
		;
		try {
			String filename = page.substring(page.lastIndexOf('/') + 1);
			File local = new File(filename);
			if (local.isFile())
				return new Scanner(local).useDelimiter(abcd);
			URL u = new URL(page);
			FileOutputStream save = new FileOutputStream(local);
			InputStream read = u.openStream();
			byte[] got = new byte[1024];
			int have = read.read(got);
			while (have > 0) {
				save.write(got, 0, have);
				have = read.read(got);
			}
			save.close();
			read.close();
			return new Scanner(local).useDelimiter(abcd);
		} catch (IOException e) {
			System.err.println("ERROR reading " + page);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * a non-text character like ~, @, `, or the like. Used at least in nGrams()
	 * and runLengths() below; you may also use it in your own code if you want.
	 */
	public static final char GRAM_DELIMITER = 126;

	/**
	 * Creates a new Blatherer with the given value of n. This value is used by
	 * the feed method below to decide how many words go into a single gram.
	 */
	public Blatherer(int n) {

		this.number = n;
	}

	/**
	 * Adds the contents of the Scanner to the map of n-grams. Note that it does
	 * not clear contents that might already have been there, it just adds more.
	 */
	public void feed(Scanner scanner) {

		ArrayList<String> texts = new ArrayList<String>();
		while (scanner.hasNext()) {
			String t = scanner.next().trim();
			if (!(t.equals("")))
				texts.add(t);
		}

		String eText = "";
		for (int i = 0; i < texts.size(); i++) {
			eText = eText + texts.get(i) + " ";
		}

		String[] textWords = eText.split(" ");

		// new part here

		for (int j = 0; j < textWords.length - number + 1; j++) {
			String newName = "";
			for (int i = 0; i < number - 2; i++)
				newName = newName + textWords[j + i] + "~";
			newName = newName + textWords[j + number - 2];
			if (gram.keySet().contains(newName)) {
				ArrayList<String> a = gram.get(newName);
				a.add(textWords[j + number - 1]);
				gram.put(newName, a);
				System.out.println(newName);
			} else {
				ArrayList<String> b = new ArrayList<String>();
				b.add(textWords[j + number - 1]);
				gram.put(newName, b);
			}
		}

		int len = textWords.length;
		String lastKey = "";
		for (int i = len - number + 1; i < len - 1; i++) {
			lastKey = lastKey + textWords[i] + "~";
		}
		lastKey = lastKey + textWords[len - 1];

		if (!(gram.keySet().contains(lastKey)))
			gram.put(lastKey, new ArrayList<String>());

		scanner.close();
	}

	/**
	 * Removes all dead-ends from the Blatherer's map of n-grams. Also removes
	 * any values that lead to no valid key, as discussed above.
	 */
	public void removeDeadEnds() {
		while (helperMethod()) {
			// do nothing; all the work happens in the parentheses above.
		}
	}

	/**
	 * do whatever a single pass of the fixed-point algorithm is return true if
	 * something changed, false if nothing changed.
	 */
	private boolean helperMethod() {

		if (!(gram.containsValue(new ArrayList<String>())))
			return false;

		ArrayList<String> reverse = new ArrayList<String>(gram.keySet());
		String thekey2 = "";
		for (int i = reverse.size() - 1; i >= 0; i--) {
			if (gram.get(reverse.get(i)).isEmpty()
					|| gram.get(reverse.get(i)) == null) {
				thekey2 = reverse.get(i);
				gram.remove(reverse.get(i));
				i = 0;
			}
		}

		String[] sk2 = thekey2.split("~");

		String nextvalue = sk2[number - 2];
		for (int i = reverse.size() - 2; i >= 0; i--) {
			String thekey1 = reverse.get(i);
			String[] sk1 = thekey1.split("~");
			int size = gram.get(thekey1).size();
			for (int k = 0; k < size; k++) {
				String thevalue = gram.get(thekey1).get(k);

				if (thevalue.equals(nextvalue)) {
					boolean check = true;
					for (int j = 0; j < number - 2; j++) {
						if (!(sk2[j].equals(sk1[j + 1])))
							check = false;
					}

					if (check) {
						if (k == 0) {
							gram.remove(reverse.get(i));
							gram.put(reverse.get(i), new ArrayList<String>());
						} else {
							ArrayList<String> nnn = gram.get(reverse.get(i));
							nnn.remove(k);
							gram.remove(reverse.get(i));
							gram.put(reverse.get(i), nnn);
						}
						i = 0;

					}
				}
			}

		}

		return true;
	}

	/**
	 * Returns the run length of each key in the current map.It is possible for
	 * runLengths to get stuck in a loop if a key only leads in to itself or one
	 * of its parents. If the run length gets up to 100 or more, just use 100.
	 */
	public Map<String, Integer> runLengths() {

		Map<String, Integer> lengths = new LinkedHashMap<String, Integer>();

		// ArrayList<String> reverse = new ArrayList<String>(gram.keySet());
		//
		// int a = 0;
		// for (int i = reverse.size() - 1; i >= 0; i--) {
		// if (gram.get(reverse.get(i)).isEmpty()
		// || gram.get(reverse.get(i)) == null) {
		// lengths.put(reverse.get(i), 0);
		// } else {
		// int vsize = gram.get(reverse.get(i)).size();
		// if (vsize > 1) {
		// a = 0;
		// lengths.put(reverse.get(i), 0);
		// } else {
		// a++;
		// lengths.put(reverse.get(i), a);
		// }
		// }
		// }
		// return lengths;
		for (String key : new ArrayList<String>(gram.keySet())) {
			if (gram.get(key).size() > 1 || gram.get(key) == null
					|| gram.get(key).isEmpty()) {
				lengths.put(key, 0);
			} else {
				lengths.put(key, runsLengthHelper(key));
			}
		}
		return lengths;

	}

	private int runsLengthHelper(String k) {
		if (gram.get(k).size() == 1) {
			String[] spt = k.split("~");
			String nk = "";
			int c = 1;
			while (c < number - 1) {
				nk = nk + spt[c] + "~";
				c++;
			}
			nk = nk + gram.get(k).get(0);
			return 1 + runsLengthHelper(nk);
		} else {
			return 0;
		}
	}

	/**
	 * Removes all keys for which the run-length is larger than the specified
	 * number. (observation: this will often create new dead ends.)
	 */
	public void removeRunsLongerThan(int l) {

		for (String key : new ArrayList<String>(runLengths().keySet())) {
			if (runLengths().get(key) > l)
				gram.remove(key);
		}
	}

	/**
	 * Returns an unmodifiable map of key to Collections of Strings. This will
	 * probably be a single-line method: return Collections.unmodifiableMap(
	 * this.nameOfYourMap ); You are welcome to have this return a morespecific
	 * type, such as Map<String, List<String>> or HashMap<String,
	 * TreeSet<String>>, if you wish.
	 */
	public Map<String, ? extends Collection<String>> nGrams() {

		return Collections.unmodifiableMap(this.gram);
	}

	/**
	 * Returns a key whose collection of values has the most entries. In the
	 * example above, it would return "Sam~I~am" because it has 3 entries in its
	 * value while other keys have only 1. If there is a tie, any of the tied
	 * keys may be returned (we don't care which one).
	 */
	public String mostCommonKey() {

		String most = "";
		int num = 0;
		for (String key : new ArrayList<String>(gram.keySet())) {
			int valuesize = gram.get(key).size();
			if (valuesize > num) {
				most = key;
				num = valuesize;
			}
		}
		return most;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Map<String, ArrayList<String>> getGram() {
		return gram;
	}

	public void setGram(Map<String, ArrayList<String>> gram) {
		this.gram = gram;
	}

	/**
	 * Returns an iterator that will generate the specified number of words
	 * (unless it hits a dead-end first), starting at the given key. Simply
	 * returning a new BlatherIter should be sufficient.
	 */
	public BlatherIter iterator(int num, String str) {

		return new BlatherIter(this, str, num);
	}

	/**
	 * Returns an iterator that will generate the specified number of words
	 * (unless it hits a dead-end first), starting at the mostCommonKey. Simply
	 * returning a new BlatherIter should be sufficient.
	 */
	public BlatherIter iterator(int num) {

		String ck = mostCommonKey();
		return new BlatherIter(this, ck, num);
	}

	/**
	 * Returns an iterator that will generate 100 words, starting at the
	 * mostCommonKey. Simply returning a new BlatherIter should be sufficient.
	 */
	@Override
	public BlatherIter iterator() {

		String ck = mostCommonKey();
		return new BlatherIter(this, ck, 100);
	}
}