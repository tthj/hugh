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

public class MoreTextMarkService  implements MarkService{

	@Override
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
			g.setFont(new Font(FONT_NAME,FONT_STYLE,15));
			g.drawString(MARK_NOOK, bufferedImage.getWidth()-getTextLength(MARK_NOOK)*15-10, bufferedImage.getHeight()-3);
			g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
			g.setColor(FONT_COLOR);
			int width1=FONT_SIZE*getTextLength(MARK_TEXT);
			int height1=FONT_SIZE;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
		   g.rotate(Math.toRadians(-30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
		   int x=-width/2;
		   int y=-height/2;
		   while(x<width*1.5){
			   y=-height/2;
			   while(y<height*1.5){
			   g.drawString(MARK_TEXT, x, y);
			   y+=height1+FONT_DISTANCE;
			   }
			   x+=width1+FONT_DISTANCE;
		   }
		    g.dispose();
		    os=new FileOutputStream(UploadPath);
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
	public void Watermark(File file,String name) {
		MoreTextMarkService textMarkService=new MoreTextMarkService();
		textMarkService.watermark(file, name, "", "");
	}

}
