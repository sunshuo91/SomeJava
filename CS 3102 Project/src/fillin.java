import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import javax.swing.JFrame;

public class fillin {
	private char[][] puzzleBase;
	private char[][] clearBase;
	private ArrayList<piece> puzzlePiece;
	private Stack<piece> rPiece = new Stack<piece>();
	private boolean extraPiece;
	private boolean multiColor;
	private char dummy = '*';
	private int dummyInt = 49;
	private InfoFrame info;
	
	private int solcount = 1;
	
	private char[][] drawBase;
	
	public fillin(char[][] b, ArrayList<piece> p, boolean xp, boolean mc, InfoFrame ifr) {
		this.puzzleBase = b;
		this.puzzlePiece = p;
		this.rPiece.addAll(puzzlePiece);
		this.extraPiece = xp;
		this.multiColor = mc;
		this.drawBase = new char[puzzleBase.length][puzzleBase[0].length];
		this.clearBase = new char[puzzleBase.length][puzzleBase[0].length];
		this.info = ifr;
		
		// Fill in blanks
		for (int dp = 0; dp < drawBase.length; dp++) {
			for (int dq = 0; dq < drawBase[0].length; dq++) {
				drawBase[dp][dq] = ' ';
			}
		}
		//Generate ClearBase;
		for (int dp = 0; dp < clearBase.length; dp++) {
			for (int dq = 0; dq < clearBase[0].length; dq++) {
				clearBase[dp][dq] = b[dp][dq];
			}
		}
	}

	// Out of Bound check
	public boolean check1(char[][] n, char[][] org, int x, int y) {
		if (x + org.length > puzzleBase.length
				|| y + org[0].length > puzzleBase[0].length) {
			//System.out.println("check1");
			return false;
		}

		// Collision check
		for (int i = 0; i < org.length; i++) {
			for (int j = 0; j < org[i].length; j++) {
				if ((n[x + i][y + j] == '*' || n[x + i][y + j] == ' ') && org[i][j] != ' ') {
					//System.out.println("x:" + x +" y:" + y + " i:" + i + " j:" + j);
					//System.out.println("check2");
					return false;
				}
			}
		}
		
		//match check when multicolor
		if(multiColor) {
			for (int i = 0; i < org.length; i++) {
				for (int j = 0; j < org[i].length; j++) {
					if (org[i][j] != ' ' && n[x + i][y + j] != org[i][j]) {
						//System.out.println("check3");
						return false;
					}
				}
			}
		}
		
		
		return true;
	}

	public int check2_helper(char[][] n, int p, int q) {

		if (p < n.length && p >= 0 && q < n[0].length && q >= 0
				&& (n[p][q] != dummy || n[p][q] == ' ')) {
			n[p][q] = dummy;
			return check2_helper(n, p + 1, q) + check2_helper(n, p, q + 1)
					+ check2_helper(n, p - 1, q) + check2_helper(n, p, q - 1)
					+ 1;
		} else
			return 0;

	}

	public boolean check2(char[][] n) {
		// Minimum Area check
		int count;
		int piecen = 0;
		int maxp = 0;
		char[][] n2 = new char[n.length][n[0].length];
		for (int p = 0; p < n.length; p++) {
			for (int q = 0; q < n[0].length; q++) {
				n2[p][q] = n[p][q];
			}
		}

		for (int p = 0; p < n2.length; p++) {
			for (int q = 0; q < n2[0].length; q++) {
				if (n2[p][q] != dummy && n2[p][q] != ' ') {
					count = check2_helper(n2, p, q);
					// System.out.print(p + ", " + q);
					Stack<piece> temp = new Stack<piece>();
					temp.addAll(rPiece);
					for (int i = 0; i < rPiece.size(); i++) {
						piece pie = temp.pop();
						for (int j = 0; j < pie.getOrig().length; j++) {
							for (int k = 0; k < pie.getOrig()[0].length; k++)
								if (pie.getOrig()[j][k] != ' ')
									piecen++;
						}

						if (piecen > maxp)
							maxp = piecen;
						piecen = 0;
					}

					//System.out.println("count: " + count + " maxp: " + maxp);
					if (count < maxp) {
						//System.out.print(p + ", " + q);
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean test_helper(char[][] piece, int row, int col) {
		//System.out.println("check1");
		if (check1(puzzleBase, piece, row, col)) {
			// System.out.println("check1");
			putInBase(piece, row, col);
			boolean bool = check2(puzzleBase);
			if (bool) {
				if (test(0, 0)) {
					return true;
				} else {
					// System.out.println("row: " + row + "; col: " + col +
					// " p_num: " + p_num);
				}
			}
			moveOutBase(piece, row, col);
		}
		return false;
	}

	public boolean test(int row, int col) {

		// char[][] temp = puzzleBase;
		boolean bool = true;
		//print();
		//System.out.println("piece number: " + rPiece.size());

		// check if solved
		if (extraPiece) {
			for (int x = 0; x < puzzleBase.length; x++) {
				for (int y = 0; y < puzzleBase[0].length; y++) {
					if (puzzleBase[x][y] != dummy)
						bool = false;
				}
			}
			if (bool)
				return true;
		} else if (rPiece.empty()) {
			// this.puzzleBase = puzzleBase;
			return true;
		}

		piece p = rPiece.pop();
		
		while(true) {
			if (p.sim.contains(1) && test_helper(p.getOrig(), row, col)) {
				return true;
			} else if (p.sim.contains(2) && test_helper(p.getFlip1(), row, col)) {
				return true;
			} else if (p.sim.contains(3) && test_helper(p.getFlip2(), row, col)) {
				return true;
			} else if (p.sim.contains(5) && test_helper(p.getRotate_c90(), row, col)) {
				return true;
			} else if (p.sim.contains(6) && test_helper(p.getRotate_cc90(), row, col)) {
				return true;
			} else if (p.sim.contains(4) && test_helper(p.getRotate_180(), row, col)) {
				return true;
			} else if (p.sim.contains(7) && test_helper(p.getFliprotatec(), row, col)) {
				return true;
			} else if (p.sim.contains(8) && test_helper(p.getFliprotatecc(), row, col)) {
				return true;
			} else {
				if (row == puzzleBase.length - 1 && col == puzzleBase[0].length - 1) {
					break;
				}

				if (col >= puzzleBase[row].length - 1) {
					//System.out.println("row: " + row + "; col: " + col);
					col = 0;
					row++;

				} else {
					//System.out.println("row: " + row + "; col: " + col);
					col++;
				}
			}
		}
		rPiece.push(p);

		return false;
	}

	// put the piece in new base
	public void putInBase(char[][] p, int x, int y) {
		dummyInt++;
		for (int m = 0; m < p.length; m++) {
			for (int n = 0; n < p[m].length; n++) {
				if (p[m][n] != ' ') {
					puzzleBase[x + m][y + n] = dummy;
					drawBase[x + m][y + n] = (char)(dummyInt);
				}
			}
		}
	}

	public void moveOutBase(char[][] p, int x, int y) {
		dummyInt--;
		for (int m = 0; m < p.length; m++) {
			for (int n = 0; n < p[m].length; n++) {
				if (p[m][n] != ' ') {
					puzzleBase[x + m][y + n] = p[m][n];
					// System.out.println("moving?");
				}
			}
		}
	}

	public char[][] getDrawBase() {
		return drawBase;
	}

	public void setDrawBase(char[][] drawBase) {
		this.drawBase = drawBase;
	}

	public void print() {
		for (int m = 0; m < puzzleBase.length; m++) {
			for (int n = 0; n < puzzleBase[m].length; n++) {
				System.out.print(puzzleBase[m][n] + " ");
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~~~~~~~");
		
		for (int m = 0; m < puzzleBase.length; m++) {
			for (int n = 0; n < puzzleBase[m].length; n++) {
				System.out.print(drawBase[m][n]);
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~~~~~~~");
	}
	
	public int findAllSolution() {
		
		ArrayList<char[][]> solArr = new ArrayList<char[][]>();
		solArr.add(drawBase);
		

		
		for(int pie = 1; pie < puzzlePiece.size(); pie++) {
			//System.out.println(pie);
			for(int first = pie; first < puzzlePiece.size(); first++) {
				rPiece.push(puzzlePiece.get(first));
			}
			for(int second = 0; second < pie; second++) {
				rPiece.push(puzzlePiece.get(second));
			}
			
			//clear puzzleBase
			for (int row = 0; row < puzzleBase.length; row++) {
				for (int col = 0; col < puzzleBase[0].length; col++) {
					puzzleBase[row][col] = clearBase[row][col];
				}
			}
			
			boolean same = false;
			if(test(0,0)) {
				piece newsol = new piece(drawBase);
				for(int i = 0; i < solArr.size(); i++) {
					if( compare(solArr.get(i), newsol.getOrig())
							|| compare(solArr.get(i), newsol.getFlip1())
							|| compare(solArr.get(i), newsol.getFlip2())
							|| compare(solArr.get(i), newsol.getRotate_c90())
							|| compare(solArr.get(i), newsol.getRotate_cc90())
							|| compare(solArr.get(i), newsol.getRotate_180())
							|| compare(solArr.get(i), newsol.getFliprotatec())
							|| compare(solArr.get(i), newsol.getFliprotatecc())
							) {
						i = solArr.size();
						same = true;
					}
					
				}
				if (!same) {
					this.solcount++;
					info.println("We found one more solution!");
				}
			}
			
			while(!rPiece.empty()){
				rPiece.pop();
			}
			
		}
		
		return solcount;
	}
	
	public boolean compare(char[][] sol, char[][] newSol) {
		if(sol.length != newSol.length)
			return false;
		boolean same1, same2;
		for(int row = 0; row < sol.length; row++) {
			for (int col = 0; col < sol[0].length - 1; col++) {
				same1 = (sol[row][col] == sol[row][col+1]);
				same2 = (newSol[row][col] == newSol[row][col+1]);
				if (same1 != same2)
					return false;
			}
		}
		return true;
	}

}
