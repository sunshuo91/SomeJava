import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * 
 * @author Shuo Sun ss8ee Homework 4 101
 * 
 */
public class BlatherIter implements Iterator<String> {
	// //
	private int numw;
	private int numr;
	private String key;
	private Blatherer bla;
	private String current;

	/**
	 * 
	 * @param bl
	 *            the Blatherer
	 * @param st
	 *            key
	 * @param in
	 *            number of times setting the values and construct the first
	 *            current return values
	 */
	public BlatherIter(Blatherer bl, String st, int in) {

		this.numw = in;
		this.key = st;
		this.bla = bl;
		this.numr = 1;
		this.current = "";

		Map<String, ArrayList<String>> gram = bla.getGram();
		System.out.println(gram);

		if (gram.containsKey(st)) {
			ArrayList<String> c = gram.get(st);
			if (c.isEmpty() || c == null) {
				this.current = "";
			} else if (c.size() > 1) {
				this.current = randomvalueHelper(c);
			} else if (c.size() == 1) {
				this.current = c.get(0);
			} else {
				this.current = "";
			}
		}
	}

	private String randomvalueHelper(ArrayList<String> t) {
		return t.get((int) (Math.random() * t.size()));
	}

	/**
	 * 
	 * check if there is the next element
	 */
	public boolean hasNext() {
		if (numr > numw) {
			return false;
		}
		if (!(bla.getGram().containsKey(key))) {
			return false;
		}
		return (!(current.equals("")));
	}

	/**
	 * 
	 * return the next element if this is one.
	 */
	public String next() {

		if (!(hasNext()))
			throw new NoSuchElementException();

		if (numr == 1) {
			this.numr += 1;
			return current;
		}
		Map<String, ArrayList<String>> gram = bla.getGram();

		String newwkey = keySetterHelper(key, current);
		this.key = newwkey;
		ArrayList<String> cc = gram.get(key);
		if (cc.isEmpty() || cc == null) {
			throw new NoSuchElementException();
		} else if (cc.size() > 1) {
			this.current = randomvalueHelper(cc);
		} else if (cc.size() == 1) {
			this.current = cc.get(0);
		} else
			throw new NoSuchElementException();
		this.numr += 1;

		return current;

	}

	private String keySetterHelper(String k, String v) {
		String[] sp = k.split("~");
		String setkey = "";
		for (int i = 1; i < bla.getNumber() - 1; i++) {
			setkey = setkey + sp[i] + "~";
		}
		setkey = setkey + v;

		return setkey;
	}

	/**
	 * 
	 * remove the last element
	 */
	public void remove() throws UnsupportedOperationException {
	}

	/**
	 * 
	 * @param num
	 *            , the number added to extend the number of getting data from
	 *            Blatherer
	 */
	public void moreWords(int num) {
		this.numw += num;
	}
}
