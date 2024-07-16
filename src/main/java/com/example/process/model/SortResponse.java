package com.example.process.model;

import java.util.Arrays;

public class SortResponse {

	private int[][] sortedArrays;
	private long timeNs;

	public SortResponse(int[][] sortedArrays, long timeNs) {
		this.sortedArrays = sortedArrays;
		this.timeNs = timeNs;
	}

	public int[][] getSortedArrays() {
		return sortedArrays;
	}

	public long getTimeNs() {
		return timeNs;
	}

	@Override
	public String toString() {
		return "SortResponse [sortedArrays=" + Arrays.toString(sortedArrays) + ", timeNs=" + timeNs + "]";
	}
	
}
