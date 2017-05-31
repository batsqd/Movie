package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//这里读的数据用于用户相似性分类
public class ReadUserData {
	public static int[][] readUserFile(String filePath,int countUser,int numOffeature){
		
		int data[][]=new int [countUser][numOffeature];
		for(int i=0;i<data.length;i++){
    		for(int j=0;j<data[i].length;j++){
    			data[i][j]=0;
    		}
    	}
    	int user_id=0;
    	String gender=" ";
    	int age=0;
    	//int occupation;
    	try {
    		File file=new File(filePath);
            if(file.isFile() && file.exists()){ 
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String str[]=lineTxt.split("\\|");//分割符为 |
                    user_id=Integer.parseInt(str[0]);
                    age=Integer.parseInt(str[1]);
                    gender=str[2];
                    //occupation=Integer.parseInt(str[3]);
                    /*
                        *  1:  "Under 18"
						* 18:  "18-24"
						* 25:  "25-34"
						* 35:  "35-44"
						* 45:  "45-49"
						* 50:  "50-55"
						* 56:  "56+"
                     */
                   //性别
                    if("F".equals(gender)){
                    	data[user_id-1][0]=1;
                    }else{
                    	data[user_id-1][0]=0;
                    }
                    //年龄
                    if(age<18){
                    	data[user_id-1][1]=1;
                    }else if(age<24){
                    	data[user_id-1][2]=1;
                    }else if(age<34){
                    	data[user_id-1][3]=1;
                    }else if(age<44){
                    	data[user_id-1][4]=1;
                    }else if(age<49){
                    	data[user_id-1][5]=1;
                    }else if(age<55){
                    	data[user_id-1][6]=1;
                    }else{
                    	data[user_id-1][7]=1;
                    }
                }
                read.close();
              }else{
            	  System.out.println("文件不存在！");
              }
       
    } catch (Exception e) {
        System.out.println("读数据出异常！");
         e.printStackTrace();
       }
		return data;
    }
}
