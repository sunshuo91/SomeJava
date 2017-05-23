import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class mainF {
	public static Distance bruteForce(ArrayList<coordinate> points) {
		int size = points.size();
		if (size < 2) {
			System.out.println("numbere of points are not in range");
			return null;
		}
		Distance d = new Distance(points.get(0), points.get(1));
		return d;
	}

	public static Distance divideAndConquer(ArrayList<coordinate> points) {
		ArrayList<coordinate> pointsSortedByX = new ArrayList<coordinate>(
				points);
		MergeSort ms = new MergeSort();
		ms.sort(pointsSortedByX, true);
		pointsSortedByX = ms.getNumbers();

		ArrayList<coordinate> pointsSortedByY = new ArrayList<coordinate>(
				points);
		MergeSort ms2 = new MergeSort();
		ms2.sort(pointsSortedByY, false);
		pointsSortedByY = ms2.getNumbers();

		return divideAndConquer(pointsSortedByX, pointsSortedByY);
	}

	private static Distance divideAndConquer(
			ArrayList<coordinate> pointsSortedByX,
			ArrayList<coordinate> pointsSortedByY) {
		int numPoints = pointsSortedByX.size();
		if (numPoints < 3)
			return bruteForce(pointsSortedByX);

		int dividingIndex = numPoints >>> 1;
		List<coordinate> lc = pointsSortedByX.subList(0, dividingIndex);
		ArrayList<coordinate> leftOfCenter = new ArrayList<coordinate>(lc);
		List<coordinate> rc = pointsSortedByX.subList(dividingIndex, numPoints);
		ArrayList<coordinate> rightOfCenter = new ArrayList<coordinate>(rc);

		ArrayList<coordinate> tempList = new ArrayList<coordinate>(leftOfCenter);
		MergeSort ms3 = new MergeSort();
		ms3.sort(tempList, false);
		pointsSortedByY = ms3.getNumbers();
		Distance closestDist = divideAndConquer(
				(ArrayList<coordinate>) leftOfCenter, tempList);

		tempList.clear();
		tempList.addAll(rightOfCenter);
		MergeSort ms4 = new MergeSort();
		ms4.sort(pointsSortedByY, false);
		pointsSortedByY = ms4.getNumbers();
		Distance closestDistRight = divideAndConquer(
				(ArrayList<coordinate>) rightOfCenter, tempList);

		if (closestDistRight.distance < closestDist.distance)
			closestDist = closestDistRight;

		tempList.clear();
		double shortestDistance = closestDist.distance;
		double centerX = rightOfCenter.get(0).x;
		for (coordinate point : pointsSortedByY)
			if (Math.abs(centerX - point.x) < shortestDistance)
				tempList.add(point);

		for (int i = 0; i < tempList.size() - 1; i++) {
			coordinate point1 = tempList.get(i);
			for (int j = i + 1; j < tempList.size(); j++) {
				coordinate point2 = tempList.get(j);
				if ((point2.y - point1.y) >= shortestDistance)
					break;
				double distance = ((new Distance(point1, point2)).distance);
				if (distance < closestDist.distance) {
					closestDist.update(point1, point2, distance);
					shortestDistance = distance;
				}
			}
		}
		return closestDist;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("test2.txt");
		Scanner scr = new Scanner(file);
		int n;
		ArrayList<coordinate> points = new ArrayList<coordinate>();

		double xValue, yValue;

		while ((n = scr.nextInt()) != 0) {
			String[] nextLine = scr.nextLine().split("\\s+");
			for (int i = 0; i < n; i++) {
				nextLine = scr.nextLine().split("\\s+");
				xValue = Double.parseDouble(nextLine[0]);
				yValue = Double.parseDouble(nextLine[1]);
				points.add(new coordinate(xValue, yValue));
			}
			if (n < 3) {
				Distance bruteForceClosestPair = bruteForce(points);
				System.out.println(bruteForceClosestPair);
			} else {
				Distance dqClosestPair = divideAndConquer(points);
				System.out.println(dqClosestPair);
			}
			points.clear();
		}
	}
}
