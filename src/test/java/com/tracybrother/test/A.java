package com.tracybrother.test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.zxing.WriterException;
import com.tracybrother.utils.QRCodeUtils;

public class A extends SuperA {
	public void testA(){
		System.out.println("I'm aaaaaaaaa");
	}
	public static void main(String[] args) throws WriterException, IOException {
		 BufferedImage image = QRCodeUtils.getOut("http://www.baidu.com", 380, 380);
		 
		 String path = "E:\\FD_SMSD\\testFile.png\\";
		 QRCodeUtils.saveImageFile(image,path); 
	}
	
}
