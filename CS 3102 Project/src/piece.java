import java.util.HashMap;
import java.util.HashSet;


public class piece {
	private char[][] orig; //follow the memory rule
	private char[][] flip1; //horizontally flip
	private char[][] flip2; //vertically flip
	private char[][] rotate_180; //rotate 180 degrees/flip twice
	private char[][] rotate_c90; //clockwise rotate 90 degrees
	private char[][] rotate_cc90; //counterclockwise rotate 90 degrees
	private char[][] fliprotatec;
	private char[][] fliprotatecc;
	
	public HashSet<Integer> sim = new HashSet<Integer>();
	
	
	public char[][] getRotate_180() {
		return rotate_180;
	}
	public void setRotate_180(char[][] rotate_180) {
		this.rotate_180 = rotate_180;
	}
	public char[][] getRotate_c90() {
		return rotate_c90;
	}
	public void setRotate_c90(char[][] rotate_c90) {
		this.rotate_c90 = rotate_c90;
	}
	public char[][] getRotate_cc90() {
		return rotate_cc90;
	}
	public void setRotate_cc90(char[][] rotate_cc90) {
		this.rotate_cc90 = rotate_cc90;
	}
	public char[][] getOrig() {
		return orig;
	}
	public void setOrig(char[][] orig) {
		this.orig = orig;
	}
	public char[][] getFlip1() {
		return flip1;
	}
	public void setFlip1(char[][] flip1) {
		this.flip1 = flip1;
	}
	public char[][] getFlip2() {
		return flip2;
	}
	public void setFlip2(char[][] flip2) {
		this.flip2 = flip2;
	}
	public char[][] getFliprotatec() {
		return fliprotatec;
	}
	public void setFliprotatec(char[][] fliprotatec) {
		this.fliprotatec = fliprotatec;
	}
	public char[][] getFliprotatecc() {
		return fliprotatec;
	}
	public void setFliprotatecc(char[][] fliprotatec) {
		this.fliprotatec = fliprotatec;
	}
	
	
	public piece() {
		this.orig = new char[0][0];
		this.flip1 = new char[0][0];
		this.flip2 = new char[0][0];
		this.rotate_180 = new char[0][0];
		this.rotate_c90 = new char[0][0];
		this.rotate_cc90 = new char[0][0];
		this.fliprotatec = new char[0][0];
		this.fliprotatecc = new char[0][0];
	}
	
	public piece(char[][] arr) {
		this.orig = original(arr);
		//this.orig = arr;
		this.flip1 = horiFlip();
		this.flip2 = vertFlip();
		this.rotate_180 = rotate_half();
		this.rotate_c90 = rotate_oneforth_c();
		this.rotate_cc90 = rotate_oneforth_cc();
		this.fliprotatec = flip_rotate_c();
		this.fliprotatecc = flip_rotate_cc();
		similarity();
	}
	
	private char[][] flip_rotate_cc() {
		int row = this.orig.length;
		int col = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[i][col - j - 1];
		}
		
		col = temp.length;
		row = temp[0].length;
		char[][] temp2 = new char[row][col];
		
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp2[i][j] = temp[col - j - 1][i];
		}
		
		return temp2;
	}
	private char[][] flip_rotate_c() {
		int row = this.orig.length;
		int col = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[i][col - j - 1];
		}
		
		col = temp.length;
		row = temp[0].length;
		char[][] temp2 = new char[row][col];
		
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp2[i][j] = temp[j][row - i - 1];
		}
		
		return temp2;
	}
	private char[][] original(char[][] org) {
		int row = org.length;
		int col = org[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = org[i][col - j - 1];
		}
		
		col = temp.length;
		row = temp[0].length;
		char[][] temp2 = new char[row][col];
		
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp2[i][j] = temp[j][row - i - 1];
		}
		
		return temp2;
		
	}
	
	
	private char[][] rotate_oneforth_cc() {
		int col = this.orig.length;
		int row = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[col - j - 1][i];
		}
		
		return temp;
	}
	
	private char[][] rotate_oneforth_c() {
		int col = this.orig.length;
		int row = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[j][row - i - 1];
		}
		
		return temp;
	}
	
	public char[][] horiFlip() {
		int row = this.orig.length;
		int col = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[row - i - 1][j];
		}
		
		return temp;
	}
	
	public char[][] vertFlip() {
		int row = this.orig.length;
		int col = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[i][col - j - 1];
		}
		
		return temp;
	}
	
	public char[][] rotate_half() {
		int row = this.orig.length;
		int col = this.orig[0].length;
		char[][] temp = new char[row][col];
		
		int i, j;
		for (i = 0; i < row; i ++) {
			for (j = 0; j < col; j ++)
				temp[i][j] = orig[row - i - 1][col - j - 1];
		}
		
		return temp;
	}
	
	//Similarity check
	public void similarity() {
		sim.add(1);
		HashMap<Integer, char[][]> temp = new HashMap<Integer, char[][]>();
		temp.put(1, orig);
		temp.put(2, flip1);
		temp.put(3, flip2);
		temp.put(4, rotate_180);
		temp.put(5, rotate_c90);
		temp.put(6, rotate_cc90);
		temp.put(7, fliprotatec);
		temp.put(8, fliprotatecc);
		
		int inttemp = 0;
		boolean bool1 = true;
		boolean bool2 = false;

		for (int i = 2; i <= 8; i++) {
			Object[] str = sim.toArray();
			for (int j= 0; j < str.length; j++) {
				inttemp = (Integer)(str[j]);
				
				if(temp.get(i).length == temp.get(inttemp).length) {
					for(int m = 0; m < temp.get(i).length; m++) {
						for(int n = 0; n < temp.get(i)[0].length; n++) {
							if (temp.get(i)[m][n] != temp.get(inttemp)[m][n]) {
								bool1 = false;
								m = temp.get(i).length;
								n = temp.get(i)[0].length;
							}
						}
					}
				} else
					bool1 = false;
				
				//if boo1 is false, two pieces are different
				if(bool1) {
					bool2 = true;
					j = str.length;
				}
				bool1 = true;
			}
			//if boo2 is true, at least one piece is the same as the previous pieces
			if(!bool2)
				sim.add(i);
			bool2 = false;
		}	
	}
	
	//Print the original piece
	public void printOrg() {
		for (int row = 0; row < orig.length; row ++) {
			for (int col = 0; col < orig[row].length; col++) {
				System.out.print (orig[row][col]);
			}
			System.out.println("");
		}
		Object t[] = sim.toArray();
		for(int i = 0; i < t.length; i++) {
			System.out.print((Integer)(t[i]));
		}
		System.out.println();
		System.out.println("--------------");
	}

	
	
/**
 * This tests whether flip works
 * 
 */

/*
	public static void main(String args[]) {
        char table[][] = {{'o','o',' '},{' ','o','o'}};
        
        piece p = new piece(table);
        
        char[][] temp1 = p.getOrig();
        char[][] temp2 = p.getFlip1();
        char[][] temp3 = p.getFlip2();
        char[][] temp4 = p.getRotate_180();
        char[][] temp5 = p.getRotate_c90();
        char[][] temp6 = p.getRotate_cc90();
        
        
        for (int row = 0; row < 2; row ++) {
            for (int col = 0; col < 3; col++) {
                System.out.print (temp1[row][col] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("--------------");
        
        for (int row = 0; row < 2; row ++) {
            for (int col = 0; col < 3; col++) {
                System.out.print (temp2[row][col] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("--------------");
        
        for (int row = 0; row < 2; row ++) {
            for (int col = 0; col < 3; col++) {
                System.out.print (temp3[row][col] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("--------------");
        
        for (int row = 0; row < 2; row ++) {
            for (int col = 0; col < 3; col++) {
                System.out.print (temp4[row][col] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("--------------c");
        
        for (int row = 0; row < 3; row ++) {
            for (int col = 0; col < 2; col++) {
                System.out.print (temp5[row][col] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("--------------cc");
        
        for (int row = 0; row < 3; row ++) {
            for (int col = 0; col < 2; col++) {
                System.out.print (temp6[row][col] + " ");
            }
            System.out.println("");
        }
        
	}
	*/

	
}
