package com.example.process.model;

import java.util.Arrays;

public class SortRequest {

	private int[][] toSort;

	public int[][] getToSort() {
		return toSort;
	}

	public void setToSort(int[][] toSort) {
		this.toSort = toSort;
	}

	@Override
	public String toString() {
		return "SortRequest [toSort=" + Arrays.toString(toSort) + "]";
	}

}
