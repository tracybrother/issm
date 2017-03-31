package com.tracybrother.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 图像工具类：改变图片大小、加水印、验证码图片
 * 
 * @author HY
 */
public class ImageEX {
	
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(ImageEX.class);
	
	// 图片输出格式
	public final static int DEFAULT = 0; // 默认,根据源图像类型转换
	public final static int JPG = 1; // 转换为jpeg格式图像
	public final static int GIF = 2; // 转换为gif格式图像
	public final static int PNG = 3; // 转换为png格式图像
	public final static int BMP = 4; // 转换为位图格式图像

	// 画图属性
	private BufferedImage bufimageOjb = null;// buffer图像

	//
	public ImageEX() {
	}

	/**
	 * 初始化对象
	 */
	public ImageEX(BufferedImage bufimage) {
		bufimageOjb = bufimage;
	}

	// 返回bufimageOjb
	public BufferedImage getbufimageOjb() {
		return bufimageOjb;
	}

	/**
	 * 根据一个输入流创建一个图像对象
	 * @param ins  输入流
	 * @throws IOException
	 */
	public ImageEX(InputStream ins) throws IOException {
		bufimageOjb = ImageIO.read(ins);
	}

	/**
	 * 根据一个文件创建一个图像对象
	 * @param f 文件
	 * @throws IOException
	 */
	public ImageEX(File f) throws IOException {
		bufimageOjb = ImageIO.read(f);
	}

	/**
	 * 根据URL 创建图像对象
	 * @param url  图像的URL地址
	 * @throws IOException
	 */
	public ImageEX(URL url) throws IOException {
		bufimageOjb = ImageIO.read(url);
	}

	/**
	 * 把图像写到输出流
	 * @param ext 格式
	 * @param out 输出流
	 * @return
	 */
	public ImageEX outPutImage(String ext, OutputStream out) {
		try {
			ImageIO.write(this.bufimageOjb, ext, out);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		} finally {
			bufimageOjb = null;
			try {
				out.close();
			} catch (IOException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(e);
				}
			}
		}
		return new ImageEX(bufimageOjb);
	}

	/**
	 * 把图像输出到文件中
	 * 
	 * @param ext
	 *            文件格式
	 * @param f
	 *            文件对象
	 * @return 图片
	 */
	public ImageEX outPutImage(String ext, File f) {
		try {
			ImageIO.write(this.bufimageOjb, ext, f);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		}
		return new ImageEX(bufimageOjb);
	}

	/**
	 * 将转换后图像结果大小
	 * 
	 * @param width 图像宽
	 * @param height 图像高
	 * @return 转换完成的图像对象
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public ImageEX chageImageSize(int width, int height) throws IOException,
			FileNotFoundException {
		if (this.bufimageOjb == null)
			return null;

		// 根据源图像读取
		BufferedImage buffbufimage = null;
		buffbufimage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB); // 创建目的图像对象
		
		Graphics2D g2d = buffbufimage.createGraphics();
		// ----------   增加下面的代码使得背景透明   -----------------
		buffbufimage = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.OPAQUE);
		g2d.dispose();
		g2d = buffbufimage.createGraphics();
		
		buffbufimage.getGraphics().drawImage(bufimageOjb.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null); // 绘制目的图像
		return new ImageEX(buffbufimage);
	}

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param targetbufimage
	 *            -- 目标文件（路径）
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 */
	public final ImageEX pressImage(Image targetbufimage, int x, int y) {
		BufferedImage image = null;
		Graphics g = null;
		try {
			// 目标文件
			int wideth = targetbufimage.getWidth(null);
			int height = targetbufimage.getHeight(null);
			image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			g = image.createGraphics();
			g.drawImage(targetbufimage, 0, 0, wideth, height, null);

			// 水印文件
			int wideth_biao = this.bufimageOjb.getWidth(null);
			int height_biao = this.bufimageOjb.getHeight(null);
			g.drawImage(this.bufimageOjb, (wideth - wideth_biao) / 2,
					(height - height_biao) / 2, wideth_biao, height_biao, null);
			// 水印文件结束
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.debug(e);
			}
		} finally {
			g.dispose();
		}
		return new ImageEX(image);
	}

	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param fontName
	 *            -- 字体名
	 * @param fontStyle
	 *            -- 字体样式
	 * @param color
	 *            -- 字体颜色
	 * @param fontSize
	 *            -- 字体大小
	 * @param x
	 *            X
	 * 
	 * @param y
	 *            Y
	 * 
	 */
	public ImageEX pressText(String pressText, String fontName, int fontStyle,
			Color color, int fontSize, int x, int y) {
		BufferedImage image = null;
		Graphics g = null;
		try {
			int wideth = this.bufimageOjb.getWidth(null);
			int height = this.bufimageOjb.getHeight(null);
			image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			g = image.createGraphics();
			g.drawImage(this.bufimageOjb, 0, 0, wideth, height, null);
			g.setColor(color);
			g.drawString(pressText, wideth - fontSize - x, height - fontSize
					/ 2 - y);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		} finally {
			g.dispose();
		}
		return new ImageEX(image);
	}

	/**
	 * 创建一个验证码的图片
	 * 
	 * @param width1
	 *            图片宽
	 * @param height1
	 *            图片高度
	 * @param str1
	 *            验证码
	 * @param cntline
	 *            线条数
	 * @return 图片
	 */
	public ImageEX createCheckCodeImage(int width1, int height1, String str1,
			int cntline) {
		BufferedImage image = null;
		Graphics g = null;
		try {
			Random sum = new Random();
			image = new BufferedImage(width1, height1,
					BufferedImage.TYPE_INT_RGB);
			g = image.getGraphics(); // 获得该图上下文
			g.setColor(Color.WHITE); // 背景色
			g.fillRect(0, 0, width1, height1);
			g.drawRect(0, 0, width1 - 1, height1 - 1);
			/* g.setColor(getRandColor(125,200)); */// 随机颜色
			// 字体样式
			for (int m = 0; m < str1.length(); m++) {
				g.setFont(new Font("Times New Roman", Font.PLAIN, 18)); // 字体大小
				g.setColor(getRandColor(0, 255));
				String cstr = str1.substring(m, m + 1);
				g.drawString(cstr, (width1 / 4 + 2) * m, (height1 / 2) + 5); // 字体间距
			}
			// 干扰点\线
			for (int i = 0; i < 3; i++) {
				int x = sum.nextInt(width1);
				int y = sum.nextInt(height1);
				g.setColor(getRandColor(50, 255));
				int lineX = sum.nextInt(height1);
				int lineY = sum.nextInt(width1);
				g.drawLine(x, y, lineX, lineY);
			}
			for (int i = 0; i < cntline; i++) {
				int x = sum.nextInt(width1);
				int y = sum.nextInt(height1);
				g.setColor(getRandColor(100, 200));
				g.drawLine(x, y, x + 2, y + 2);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
		} finally {
			g.dispose();
		}
		return new ImageEX(image);
	}

	// 设置随机颜色函数
	// 设置随机颜色
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int gc = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, gc, b);
	}
	
}
