package test;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

public interface MarkService {
	public  final static String MARK_TEXT="���׻�";
	public  final static String FONT_NAME="΢���ź�";
	public  final static int FONT_STYLE=Font.BOLD;
	public  final static int FONT_SIZE=30;
	public  final static Color FONT_COLOR=Color.RED;
	public static final int X = 10;
	public static final int Y = 10;
	public static final float ALPHA = 0.3f;
	public static final String LOGO="logo.jpg";
	public  final static int FONT_DISTANCE=200;
	public final static String MARK_NOOK="��ͼƬ�����׻�����";
 public String watermark(File image,String FileName,String UploadPath,String RealUplaodPath);
 
}
