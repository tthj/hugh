package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class Test9{
    public static void main(String[]args) throws Exception{
        
        //1.jpg����� ��ͼƬ��·��
        InputStream is = new FileInputStream("d:\\Webshots129.jpg");
        
        
        //ͨ��JPEGͼ��������JPEG������������
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        //���뵱ǰJPEG������������BufferedImage����
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        //�õ����ʶ���
        Graphics g = buffImg.getGraphics();
        
        //������Ҫ���ӵ�ͼ��
        //2.jpg�����СͼƬ��·��
        ImageIcon imgIcon = new ImageIcon("d:\\clock.gif"); 
        
        //�õ�Image����
        Image img = imgIcon.getImage();
        
        //��СͼƬ�浽��ͼƬ�ϡ�
        //5,300 .��ʾ���СͼƬ�ڴ�ͼƬ�ϵ�λ�á�
        g.drawImage(img,5,330,null);
        
        
        
        //������ɫ��
        g.setColor(Color.BLACK);
        
        
        //���һ������������������Ĵ�С
        Font f = new Font("����",Font.BOLD,30);
        
        g.setFont(f);
        
        //10,20 ��ʾ���������ͼƬ�ϵ�λ��(x,y) .��һ���������õ����ݡ�
        g.drawString("Ĭ��555555��������������",100,300);
        
        g.dispose();
        
        
        
        OutputStream os = new FileOutputStream("d:\\union.jpg");
        
        //���������������ڱ����ڴ��е�ͼ�����ݡ�
        
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        
        
        is.close();
        os.close();
        
        System.out.println ("�ϳɽ�������������������");
        
        
    }    
    
}
