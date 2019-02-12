package com.kata;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UnknownNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String expr = "123?45*?=?";
		Set<String> numberList = getNumberList(expr);
		
		Pattern p = Pattern.compile("\\?");
		Matcher m = p.matcher(expr);
		for(String n : numberList) {
			System.out.println(n);
			System.out.println(m.replaceAll(n));
			if(evalExpr(m.replaceAll(n))) {
			System.out.println("value is "+ n);
			System.exit(1);
			}
		}
		System.out.println("value is -1");
	}
	public static Set<String> getNumberList(String exp) {
		Set<String> numbers = new LinkedHashSet<String>();
		Set<String> notThere = new LinkedHashSet<String>();
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(exp);
		while(m.find()) {
			numbers.add(exp.substring(m.start(), m.end()));
			//System.out.println(exp.substring(m.start(), m.end())+ "t\n");
		}
		for(Integer i = 0;i <= 9;i++) {
			if(i == 0 && (exp.charAt(0) == '?')) continue;
			if(!(numbers.contains(i.toString()))) {
				notThere.add(i.toString());
			}
		}
		return notThere;
		
	}
	public static boolean evalExpr(String expr) {
		
		String[] value = expr.split("=");
		String operator[] = new String[2];
		int value3 = Integer.parseInt(value[1]);
		int value1,value2;
		/*
		Pattern p = Pattern.compile("\\W");
		Matcher m = p.matcher(value[0]);
		m.find();
		value1 = Integer.parseInt(value[0].substring(0,m.start()));
		value2 = Integer.parseInt(value[0].substring(m.start() + 1,value[0].length()));
		System.out.println(value2 +" t" + value1); 
		switch(expr.charAt(m.start())) {
		case '+':	if(value1 + value2 == value3)
			return true;
			break;
		case '-': if(value1 - value2 == value3)
			return true;
			break;
		case '*':if(value1 * value2 == value3)
			return true;
			break;
		default : return false;
			
			
		}
		*/
		System.out.println(value[0]);
		if(expr.contains("+")) {	
			operator = value[0].split("\\+");
			value1 = Integer.parseInt(operator[0]);
			value2 = Integer.parseInt(operator[1]);
			if(value1 + value2 == value3) {
				return true;
			}
		}
		else if(expr.contains("*")) {
			operator = value[0].split("\\*");
			value1 = Integer.parseInt(operator[0]);
			value2 = Integer.parseInt(operator[1]);
			if(value1 * value2 == value3) {
				return true;
			}
		}
		else if(expr.contains("-")) {
			String v1 = value[0].substring(0, expr.indexOf("-"));
			String v2 = value[0].substring(expr.indexOf("-") + 1, value[0].length());
			System.out.println(v1+"d"+v2);
			value1 = Integer.parseInt(v1);
			value2 = Integer.parseInt(v2);
			System.out.println(value1+" "+value2);
			System.out.println(value1 - value2);
			
		if(value1 - value2 == value3) {
				return true;
			}
		}
		
		return false;
	}

}
