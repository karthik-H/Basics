package com.patternmatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decompress {

	static int global = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(decompress("2[avb]",1,0));
		//decompress("4[avb2[k]]",1,0);
	}
	public static String decompress(String args,int times,int index) {
		String result = "";
		int i = 0,j = 0;
		while(times > 0) {
			j = index;
			while(args.charAt(j) != ']' && j < args.length()) {
				System.out.println(j + "j");
				if(Character.isAlphabetic(args.charAt(j))) {
					result+=args.charAt(j);
					System.out.println(result);
				}
				else if(Character.isDigit(args.charAt(j))) {
					i = Character.getNumericValue(args.charAt(j));
					System.out.println(i + "num " + j +"j");
					result += decompress(args, i,j + 2);
					
				}
				else {
					j++;
				}
				j++;
			}
			times--;
		}
		
		return result;
		
	}

}
