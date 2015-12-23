package com.dv.shellsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShellSort {
	
	public static int shellSort(double[] A, List<Integer> hvalues){
		int n=A.length;
		int h;
		int numberOfComparisons=0;
		for(int hIndex=hvalues.size()-1;hIndex>=0;hIndex--){
			h=hvalues.get(hIndex).intValue();
			for(int j=h;j<n;j++){
				double key = A[j];
				int i=j-h;
				while(i>=0){
					if(A[i]>key){
						numberOfComparisons++;
						A[i+h] = A[i];
						i=i-h;
					} else {
						numberOfComparisons++;
						break;
					}
				}
				A[i+h]=key;
			}
		}
		
		return numberOfComparisons;
	}
	
	public static List<Integer> getGapSequence(int n, int choice) {
		switch(choice){
		case 1: return gapSequence1(n);
		case 2: return gapSequence2(n);
		case 3: return gapSequence3(n);
		default: return gapSequence4(n);
		}
	}
	
	public static List<Integer> gapSequence3(long n){
		List<Integer> hvalues = new ArrayList<Integer>();
		int h=1;
		int i=0;
		while(h<n){
			h = (int) ((Math.pow(3, i+1) - 1)/2);
			hvalues.add(i, h);
			i++;
		}
		
		return hvalues;
	}
	
	/*public static List<Integer> gapSequence4(long n){
		List<Integer> hvalues = new ArrayList<Integer>();
		double temp = 0;
		Random random = new Random();
		int h=1;
		hvalues.add(0, h);
		for(int i=1;h<n;i++){
			for(int j=0;j<10;j++){
				temp += random.nextDouble();
			}
			double av = temp/10;
			h = (int) (Math.pow(10, i)*av);
			hvalues.add(i, h);
		}
		for(int i=0;i<hvalues.size();i++){
			System.out.println(hvalues.get(i));
		}
		return hvalues;
	}*/
	
	public static List<Integer> gapSequence4(long n){
		List<Integer> hvalues = new ArrayList<Integer>();
		int h=1;
		for(int i=0;h<n;i++){
			hvalues.add(i, h);
			h= (int) Math.pow(2.71828182846, i+1);
		}
		return hvalues;
	}
	
	public static List<Integer> gapSequence1(long n){
		List<Integer> hvalues = new ArrayList<Integer>();
		int h=1;
		int i=0;
		while(h<n){
			hvalues.add(i, h);
			h=2*h+1;
			i++;
		}
		return hvalues;
	}
	
	public static List<Integer> gapSequence2(long n){
		List<Integer> hvalues = new ArrayList<Integer>();
		int h=1;
		int i=0;
		while(h<n){
			hvalues.add(i, h);
			h = (int)(Math.pow(4, i+1) + 3*Math.pow(2, i) + 1);
			i++;
		}
		return hvalues;
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		for(int j=10;j<=1000000;j=j*10){
			
			for (int gapSequenceCode = 1; gapSequenceCode <= 4; gapSequenceCode++) {
				List<Integer> hvalues = getGapSequence(j, gapSequenceCode);
				int total = 0;
				for (int i = 0; i < 10; i++) {
					total = total + shellSort(createRandomArray(j),hvalues);
				}
				double avNOC = total / 10;
				System.out.println("average number of comparisons for input "+ j + " and gap sequence code "+gapSequenceCode+ " is = " + avNOC);
			}
		}
		
	}
	
	public static double[] createRandomArray(int size){
		double[] A = new double[size];
		Random random = new Random();
		for(int i=0;i<size;i++){
			A[i]=random.nextDouble();
		}
		return A;
	}

}
