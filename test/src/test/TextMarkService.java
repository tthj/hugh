package test;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class TextMarkService implements MarkService {
	public String watermark(File image, String FileName, String UploadPath,
			String RealUplaodPath) {
		String logoFileName="logo_"+FileName;
		OutputStream os=null;
		try {
			Image image2=ImageIO.read(image);
			int width=image2.getWidth(null);
			int height=image2.getHeight(null);
			BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g=bufferedImage.createGraphics();
			g.drawImage(image2, 0, 0,width,height,null);
			g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
			g.setColor(FONT_COLOR);
			int width1=FONT_SIZE*getTextLength(MARK_TEXT);
			int height1=FONT_SIZE;
			int widthDiff=width-width1;
			int heightDiff=height-height1;
			int x=X;
			int y=Y;
			if(x>width1){
			x=widthDiff;	
			}
			if(y>height1){
				x=heightDiff;	
				}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
		    g.drawString(MARK_TEXT, x, y+FONT_SIZE);
		    g.dispose();
		    os=new FileOutputStream("F:\\image/22.jpg");
		    JPEGImageEncoder en=JPEGCodec.createJPEGEncoder(os);
		    en.encode(bufferedImage);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return UploadPath+"/"+logoFileName;
	}
	public int getTextLength(String text){
		int length=text.length();
		for (int i = 0; i <text.length(); i++) {
			String s=String.valueOf(text.charAt(i));
			if(s.getBytes().length>1){
				length++;	
			}
		}
		length=length%2==0?length/2:length/2+1;
		return length;
		
	}
	public static void main(String[] args) {
		TextMarkService textMarkService=new TextMarkService();
		File file=new File("e:/11.jpg");
		textMarkService.watermark(file, "11", "", "");
	}

}
