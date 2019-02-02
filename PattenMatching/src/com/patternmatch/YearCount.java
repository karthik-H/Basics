package com.patternmatch;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearCount {
		public static void main(String s[]) {
			
			Set<String> str = extractYear("hello this is 13-09-1997 and 04-04-1994 and 04-04-1994");
			for(String a :str){
				System.out.println(a);
			}
		}
		public static Set<String> extractYear(String str) {
			//regular expression where \d represents digit
			String pattern = "\\d*[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]*\\d";
			//year string contain all year from given string
			Set<String> year = new LinkedHashSet<String>();
			//Pattern class is provided by java regx which is used to construct pattern from 
			//given string
			Pattern p = Pattern.compile(pattern);
			//Matcher class is provided by java regx
			Matcher m = p.matcher(str);
			//matcher will create iterator and loop through every pattern from given string
			while(m.find()) {
			//m.start and m.end will give the start and end index of given pattern
			year.add(str.substring(m.start()+6, m.end()));
			
			}
			return year;
		}

}
