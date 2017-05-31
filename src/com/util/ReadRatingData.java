package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadRatingData {
	public static double [][] readRatingFile(String filePath,int countUser,int countItem){
    	//The full u data set, 100000 ratings by 943 users on 1682 items
	     //,int countUser,int countItem
    	double data[][]=new double [countUser][countItem];
    	for(int i=0;i<data.length;i++){
    		for(int j=0;j<data[i].length;j++){
    			data[i][j]=-1;
    		}
    	}
    	int useri=0;
    	int itemj=0;
    	int rate=0;
    	try {
    		File file=new File(filePath);
            if(file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null){
                    String str[]=lineTxt.split("	");
                    useri=Integer.parseInt(str[0]);
                    itemj=Integer.parseInt(str[1]);
                    rate=Integer.parseInt(str[2]);
                    data[useri-1][itemj-1]=rate;
                }
                
                read.close();
             
            }else{
            	  System.out.println("文件不存在");
             }
            
       } catch (Exception e) {
          System.out.println("读数据异常！");
          e.printStackTrace();
       }
		return data;
    }
}
