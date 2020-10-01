package br.com.ever.springbootmockito.business;

import java.util.Arrays;

import br.com.ever.springbootmockito.data.SomeDataService;

public class SomeBusinessImpl {

	private SomeDataService someDataService;

	public void setSomeDataService(SomeDataService someDataService) {
		this.someDataService = someDataService;
	}

	public int calculateSum(int[] data) {
		int sum = 0;
		for (int value : data) {
			sum += value;
		}
		return sum;
	}

	public int calculateSum_WithStream(int[] data) {
		return Arrays.stream(data).reduce(Integer::sum).orElse(0);
	}

	public int calculateSumUsingDataService() {
		int sum = 0;
		int[] data = someDataService.retrieveAllData();
		for (int value : data) {
			sum += value;
		}

		// someDataService.storeSum(sum);
		return sum;
	}

	public int calculateSumUsingDataService_WithStream() {
		int[] data = someDataService.retrieveAllData();
		return Arrays.stream(data).reduce(Integer::sum).orElse(0);
	}

}
