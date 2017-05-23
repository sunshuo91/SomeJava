package cs4102hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class hw5 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[0]);
		Scanner scr = new Scanner(file);

		String line = scr.nextLine();
		int r, c, n;
		r = Integer.parseInt(line.split("\\s+")[0]);
		c = Integer.parseInt(line.split("\\s+")[1]);
		n = Integer.parseInt(line.split("\\s+")[2]);

		String studentName, courseName;
		ArrayList<edge> edgeList1 = new ArrayList<edge>();
		ArrayList<edge> edgeList2 = new ArrayList<edge>();
		ArrayList<edge> edgeList3 = new ArrayList<edge>();
		for (int i = 0; i < r; i++) {
			line = scr.nextLine();
			studentName = line.split("\\s+")[0];
			courseName = line.split("\\s+")[1];
			edgeList2.add(new edge(studentName, courseName, 1));

			if (edgeList1.size() == 0
					|| !(studentName.equals(edgeList1.get(edgeList1.size() - 1)
							.getEnd()))) {
				edgeList1.add(new edge("resource", studentName, n));
			}
		}

		for (int i = 0; i < c; i++) {
			line = scr.nextLine();
			courseName = line.split("\\s+")[0];
			int enrollCap = Integer.parseInt(line.split("\\s+")[1]);
			edgeList3.add(new edge(courseName, "sink", enrollCap));
		}
		
		
		
		
		
		
		
		

	}

}
