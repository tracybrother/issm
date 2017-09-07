package com.tracybrother.test;

import java.util.Scanner;

class Solution  {
	long findUnlockKey(long findUnlockKey){
		if (findUnlockKey >=0){
			for (int i = 0 ; i<=100000 ; i++){
				if (i <10){
					return i;
				}else if(i>10){
					
					String s  = String.valueOf(i);
					byte[] bytes = s.getBytes();
					for (int j = 0; j < bytes.length; j++) {
						byte b = bytes[j];
						
					}
					
				}
			}
		}
		return 0;
		
	}
	public static void main(String[] args) {
		Scanner out = new Scanner(System.in);
		System.out.print("请输入锁定密钥：");
		long a= out.nextInt();
		if (a<-100000||a>10000){
			System.out.println("您输入有误,锁定密钥范围为 -100000至100000");
			return;
		}
		Solution a2 = new Solution(); //改成你的类名
		long findunlockkey = a2.findUnlockKey(a);
		System.out.println("解密密钥为:" + findunlockkey );
		
	}
	
	
	private  Integer a(int f ,double d,double x ,int p) {
//		int f;//已有f个水果
//		double d=0; // 已有d元
//		double p; //每个水果p元
//		double x=0; //每天房租 x元
		double tem = d/x;
		// 如 果钱多于水果
		if(tem>f){
			double amount = tem -f;
			Double  day = amount/(x+p);
			int daysum = Integer.valueOf(day.toString())+f;
			return daysum;
		}else{
			Double dd = d/x;
			return Integer.valueOf(dd.toString());
		}
	}
	
	
	
}
