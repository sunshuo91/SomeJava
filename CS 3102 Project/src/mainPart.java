import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class mainPart {
	
	private InfoFrame frame;

	public void puzzleSolver() throws Exception {
		// some elemental variables initialization
		
		ArrayList<Boolean> answers;
		
		frame = new InfoFrame(this);
		
		frame.println("A puzzle solver implemented by Steve Sun!");
		frame.println("");
		frame.println("The information will be updated in this frame");
		frame.println("---------------------------------------------------------");
		
		answers = frame.beforeStart();

		int i, j, k, p, q;
		ArrayList<piece> debris = new ArrayList<piece>();
		ArrayList<coordinates> coor_col = new ArrayList<coordinates>();

		boolean ownPuzzle = answers.get(0);
		boolean multiColor = answers.get(1);
		boolean extraPiece = answers.get(2);

		if(ownPuzzle) {
			frame.println("You will be constructing your own puzzle now!");
			frame.puzzleConstruct();
		} else {
			frame.println("You have chosen a puzzle for us to solve...");
			String num = frame.readPuzzle();
			int it = num.charAt(1) - 47;
			int it2 = num.charAt(2) - 48;

			if (it2 <= 9)
				it = (it - 1)* 10 + it2;
			
			

			File file = null;
			switch(it) {
			case 1:
				file = new File("checkerboard.txt");
				frame.println("Solving puzzle 1");
				multiColor = true;
				break;
			case 2:
				file = new File("entominoes8*8fourMissingOffsetNearMiddle.txt");
				frame.println("Solving puzzle 2");
				break;
			case 3:
				file = new File("IQ_creator.txt");
				frame.println("Solving puzzle 3");
				break;
			case 4:
				file = new File("lucky13.txt");
				frame.println("Solving puzzle 4");
				break;
			case 5:
				file = new File("pentominoes3*20.txt");
				frame.println("Solving puzzle 5");
				break;
			case 6:
				file = new File("pentominoes4*15.txt");
				frame.println("Solving puzzle 6");
				break;
			case 7:
				file = new File("pentominoes5*12.txt");
				frame.println("Solving puzzle 7");
				break;
			case 8:
				file = new File("pentominoes6*10.txt");
				frame.println("Solving puzzle 8");
				break;
			case 9:
				file = new File("pentominoes8*8cornerMissing.txt");
				frame.println("Solving puzzle 9");
				break;
			case 10:
				file = new File("pentominoes8*8fourMissingCorners.txt");
				frame.println("Solving puzzle 10");
				break;
			case 11:
				file = new File("pentominoes8*8fourMissingDiagonal.txt");
				frame.println("Solving puzzle 11");
				break;
			case 12:
				file = new File("pentominoes8*8fourMissingNearCorners.txt");
				frame.println("Solving puzzle 12");
				break;
			case 13:
				file = new File("pentominoes8*8fourMissingNearMiddle.txt");
				frame.println("Solving puzzle 13");
				break;
			case 14:
				file = new File("pentominoes8*8fourMissingOffsetNearCorners.txt");
				frame.println("Solving puzzle 14");
				break;
			case 15:
				file = new File("pentominoes8*8middleMissing.txt");
				frame.println("Solving puzzle 15");
				break;
			case 16:
				file = new File("pentominoes8*8sideMissing.txt");
				frame.println("Solving puzzle 16");
				break;
			case 17:
				file = new File("thirteenhole.txt");
				frame.println("Solving puzzle 17");
				break;
			case 18:
				file = new File("zpartialCross.txt");
				frame.println("Solving puzzle 18");
				extraPiece = true;
				break;
				
			}
			
			if(file == null) {
				frame.print("No input File!");
				frame.print("System exits!");
				System.exit(-1);
			}
			
			Scanner scr = new Scanner(file);

			char current;
			String line;
			int l_num = 0;
			while (scr.hasNextLine()) {
				line = scr.nextLine();
				for (i = 0; i < line.length(); i++) {
					current = line.charAt(i);
					if (current != ' ') {
						coordinates coo = new coordinates(i, l_num, current);
						coor_col.add(coo);
					}
				}
				l_num++;
			}
			
			scr.close();

		}

		/**
		 * test inputing
		 */
		// for (i = 0; i < coor_col.size(); i++ ) {
		// frame.println("(" + coor_col.get(i).xCoor + ", " +
		// coor_col.get(i).yCoor + ") " + coor_col.get(i).val);
		// }

		frame.println("We are opganizing puzzle pieces");
		// Organize all these points
		ArrayList<ArrayList<coordinates>> org_temp = new ArrayList<ArrayList<coordinates>>();
		ArrayList<coordinates> first_al = new ArrayList<coordinates>();
		first_al.add(coor_col.get(0));
		org_temp.add(first_al);
		coor_col.remove(0);

		ArrayList<ArrayList<coordinates>> org = input(org_temp, coor_col);

		/**
		 * test Organizing points
		 */
		// for (i = 0; i < org.size(); i++) {
		// frame.println("next:");
		// for (j = 0; j < org.get(i).size();j++) {
		// frame.println("(" + org.get(i).get(j).xCoor + ", " +
		// org.get(i).get(j).yCoor + ") " + org.get(i).get(j).val);
		// }
		// }

		// Generate the each element and add into ArrayList
		int min_x, max_x, min_y, max_y, temp_x, temp_y, len, wid;
		for (i = 0; i < org.size(); i++) {
			min_x = org.get(i).get(0).xCoor;
			max_x = min_x;
			min_y = org.get(i).get(0).yCoor;
			max_y = min_y;

			for (j = 1; j < org.get(i).size(); j++) {
				temp_x = org.get(i).get(j).xCoor;
				temp_y = org.get(i).get(j).yCoor;
				if (temp_x > max_x)
					max_x = temp_x;
				if (temp_x < min_x)
					min_x = temp_x;
				if (temp_y > max_y)
					max_y = temp_y;
				if (temp_y < min_y)
					min_y = temp_y;
			}

			len = max_x - min_x + 1;
			wid = max_y - min_y + 1;
			char[][] new_element = new char[len][wid];

			// Fill in blanks
			for (p = 0; p < len; p++) {
				for (q = 0; q < wid; q++) {
					new_element[p][q] = ' ';
				}
			}

			// Fill in char
			for (k = 0; k < org.get(i).size(); k++) {
				new_element[org.get(i).get(k).xCoor - min_x][org.get(i).get(k).yCoor
						- min_y] = org.get(i).get(k).val;
			}

			// add into AL
			piece pie = new piece(new_element);
			debris.add(pie);
		}

		// Determine the base
		char[][] temp;
		int mul_max = 1;
		int r = 0;
		for (i = 0; i < debris.size(); i++) {
			temp = debris.get(i).getOrig();
			if (temp.length * temp[0].length > mul_max) {
				r = i;
				mul_max = temp.length * temp[0].length;
			}
		}
		char[][] newBase = new char[debris.get(r).getOrig().length][debris.get(
				r).getOrig()[0].length];
		for (i = 0; i < newBase.length; i++) {
			for (j = 0; j < newBase[i].length; j++) {
					newBase[i][j] = debris.get(r).getOrig()[i][j];
			}
		}

		// Delete base from collection
		for (i = 0; i < debris.size(); i++) {
			if (debris.get(i).getOrig().length == newBase.length
					&& debris.get(i).getOrig()[0].length == newBase[0].length)
				debris.remove(i);
		}

		/**
		 * Test base and pieces
		 */

		frame.println("We are solving the puzzle, Please wait~");
//		frame.println("------BASE------");
//		for (int row = 0; row < newBase.length; row++) {
//			for (int col = 0; col < newBase[row].length; col++) {
//				frame.print("" + newBase[row][col]);
//			}
//			frame.println("");
//		}
//		frame.println("-------PIECES-------");
//		for (i = 0; i < debris.size(); i++) {
//			debris.get(i).printOrg();
//		}
		
		fillin finish = null;
		ArrayList<piece> newDeb;
		if(extraPiece) {
			newDeb = new ArrayList<piece>(debris);
			for(int s = 0; s < debris.size(); s++){
				finish = new fillin(newBase, newDeb, extraPiece, multiColor, frame);
			}
		} else 
			finish = new fillin(newBase, debris, extraPiece, multiColor, frame);
		
		boolean b = finish.test(0, 0);
		
		
		
		boolean nextStep = true;
		if (b) {
			frame.println("Puzzle is solved");
			nextStep = frame.afterFirst(b);
		}
		else {
			frame.println("Puzzle is not solved");
			frame.afterFirst(b);
		}
			
		if(nextStep) {
			frame.println("You requested to see the graph");

		World puzzle = new World(800, 800, Color.white);
		Turtle gg;
		
		char[][] draw = finish.getDrawBase();
		
		for(int col = 0; col < draw[0].length; col++) {
			gg = new Turtle(puzzle, -300 + col * 50, -300);
			if(draw[0][col] == ' ')
				;
			else
				gg.goTo(-300 + (col + 1) * 50, 300);
		}
		
		for (int row = 1; row < draw.length; row++) {
			for (int col = 0; col < draw[0].length; col++) {
				gg = new Turtle(puzzle, -300 + col * 50, -300 + row * 50);
				if(draw[row - 1][col] != draw[row][col])
					gg.goTo(-300 + (col + 1) * 50, 300 - row * 50);
			}
		}
		
		for(int col = 0; col < draw[0].length; col++) {
			gg = new Turtle(puzzle, -300 + col * 50, -300 + 50 * draw.length);
			if(draw[draw.length - 1][col] == ' ')
				;
			else
				gg.goTo(-300 + (col + 1) * 50, 300 - 50 * draw.length);
		}
		
		for(int row = 0; row < draw.length; row++) {
			gg = new Turtle(puzzle, -300, -300 + row * 50);
			if(draw[row][0] == ' ')
				;
			else
				gg.goTo(-300, 300 - (row + 1) * 50);
		}
		
		for (int row = 0; row < draw.length; row++) {
			for (int col = 1; col < draw[0].length; col++) {
				gg = new Turtle(puzzle, -300 + col * 50, -300 + row * 50);
				if(draw[row][col - 1] != draw[row][col])
					gg.goTo(-300 + col * 50, 300 - (row + 1) * 50);
			}
		}
		
		for(int row = 0; row < draw.length; row++) {
			gg = new Turtle(puzzle, -300 + 50 * draw[0].length, -300 + row * 50);
			if(draw[row][draw[0].length - 1] == ' ')
				;
			else
				gg.goTo(-300 + 50 * draw[0].length, 300 - (row + 1) * 50 );
		}
		
		}
		
		int con = frame.continueOn();
		
		if (con == 0) {
			frame.println("You requested to find all the solutions to the puzzle");
			int allpiece = finish.findAllSolution();
			frame.allfinish(allpiece);
		} else {
			frame.exit();
		}
		
	}

	public ArrayList<ArrayList<coordinates>> input(
			ArrayList<ArrayList<coordinates>> org,
			ArrayList<coordinates> coor_col) {
		int i, j, k;
		boolean added = false;

		for (i = 0; i < coor_col.size(); i++) {
			coordinates c1 = coor_col.get(i);
			for (j = 0; j < org.size(); j++) {
				for (k = 0; k < org.get(j).size(); k++) {
					coordinates c2 = org.get(j).get(k);
					if ((c1.xCoor == c2.xCoor && c1.yCoor == c2.yCoor + 1)
							|| (c1.xCoor == c2.xCoor && c1.yCoor == c2.yCoor - 1)
							|| (c1.xCoor == c2.xCoor + 1 && c1.yCoor == c2.yCoor)
							|| (c1.xCoor == c2.xCoor - 1 && c1.yCoor == c2.yCoor)) {
						org.get(j).add(c1);
						k = org.get(j).size();
						added = true;
						coor_col.remove(i);
						// frame.println("(" + c1.xCoor + ", " + c1.yCoor +
						// ") " + c1.val);
					} else
						;
				}
			}
		}

		if (coor_col.size() == 0) {
			return org;
		}

		if (added) {
			return input(org, coor_col);
		} else {
			ArrayList<coordinates> add_al = new ArrayList<coordinates>();
			add_al.add(coor_col.get(0));
			coor_col.remove(0);
			org.add(add_al);
			return input(org, coor_col);
		}

	}
	
	
	public static void main(String[] args) throws Exception {
		mainPart simulator = new mainPart();
		simulator.puzzleSolver();
	}
}
