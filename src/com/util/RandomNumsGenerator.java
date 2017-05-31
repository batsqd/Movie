/** 
 * 随机指定范围内N个不重复的数 
 * 最简单最基本的方法 
 * @param min 指定范围最小值 
 * @param max 指定范围最大值 
 * @param n 随机数个数 
 */  
package com.util;

import java.util.ArrayList;
import java.util.List;

public class RandomNumsGenerator {
	public static void main(String args[]){
		
		List<Integer> list=RandomNumsGenerator.randomCommon(0, 5, 3);
		if(list!=null){
			System.out.print(list.toString());
		}else{
			System.out.print("空");
		} 
		
		
	}
	
	public static List<Integer> randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    ArrayList<Integer> result=new ArrayList<Integer>();
	    //int[] result = new int[n];  
	    int count = 0;  
	    //try_time可以避免无法获取指定个数的随机值而产生是循环的情况
	    int try_time=10; 
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        if(!result.contains(num)){
	        	result.add(num);
	        	count++;
	        }
	        if(try_time<0){
	        	break;
	        }
	        try_time--;
	    }  
	    return result;  
	}
}

