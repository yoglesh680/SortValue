package com.example.process.controller;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.process.model.SortRequest;
import com.example.process.model.SortResponse;

@RestController
public class SortController {

	@PostMapping("/processSingle")
	public SortResponse processSingle(@RequestBody SortRequest request) {
		long startTime = System.nanoTime();

		int[][] sortedArrays = Arrays.stream(request.getToSort()).map(this::sortArray).toArray(int[][]::new);

		long endTime = System.nanoTime();

		return new SortResponse(sortedArrays, endTime - startTime);
	}

	@PostMapping("/processConcurrent")
	public SortResponse processConcurrent(@RequestBody SortRequest request)
			throws ExecutionException, InterruptedException {
		long startTime = System.nanoTime();

		CompletableFuture<int[]>[] futures = Arrays.stream(request.getToSort()).map(this::sortArrayAsync)
				.toArray(CompletableFuture[]::new);

		CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
		allOf.get(); // Wait for all futures to complete

		int[][] sortedArrays = Arrays.stream(futures).map(CompletableFuture::join).toArray(int[][]::new);

		long endTime = System.nanoTime();

		return new SortResponse(sortedArrays, endTime - startTime);
	}

	private int[] sortArray(int[] array) {
		int[] sortedArray = Arrays.copyOf(array, array.length);
		Arrays.sort(sortedArray);
		return sortedArray;
	}

	private CompletableFuture<int[]> sortArrayAsync(int[] array) {
		return CompletableFuture.supplyAsync(() -> sortArray(array));
	}
}
