import java.util.ArrayList;

/**
 * Source Code from http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html#mergesort_implementation
 * @author Lars Vogel
 * Version 2.1
 * Copyright � 2009, 2010, 2011, 2012 Lars Vogel
 * 15.11.2012
 * Modified by Steve Sun
 */

public class MergeSort {

	private ArrayList<coordinate> numbers;
	private ArrayList<coordinate> helper;

	boolean isX;
	private int number;

	public void sort(ArrayList<coordinate> values, boolean isX) {
		this.numbers = values;
		this.helper = new ArrayList<coordinate>(values);
		this.isX = isX;
		mergesort(0, values.size() - 1);
	}

	private void mergesort(int low, int high) {
		// check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private void merge(int low, int middle, int high) {

		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper.set(i, numbers.get(i));
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		if (isX) {
			while (i <= middle && j <= high) {
				if (helper.get(i).x <= helper.get(j).x) {
					numbers.set(k, helper.get(i));
					i++;
				} else {
					numbers.set(k, helper.get(j));
					j++;
				}
				k++;
			}

		} else {
			while (i <= middle && j <= high) {
				if (helper.get(i).y <= helper.get(j).y) {
					numbers.set(k, helper.get(i));
					i++;
				} else {
					numbers.set(k, helper.get(j));
					j++;
				}
				k++;
			}

		}

		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			numbers.set(k, helper.get(i));
			k++;
			i++;
		}

	}

	public ArrayList<coordinate> getNumbers() {
		return numbers;
	}
}