/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package opencv_test;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
/**
 *
 * @author 07009
 */
public class OpenCV_test
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        
        System.out.println(System.getProperty("os.arch"));
        
        System.out.println(Core.NATIVE_LIBRARY_NAME);
        
        
        Mat frame=new Mat();
        
        //VideoCapture camera = new VideoCapture("face.png");
        String filename="faces4.jpg";
        
        VideoCapture camera=new VideoCapture(filename);
         
         CascadeClassifier faceDetector = new CascadeClassifier("C:\\OpenCV\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
         
          if (camera.read(frame))
          {  
            System.out.println("Captured Frame Width " + frame.width() + " Height " + frame.height());  
                      
            MatOfRect faceDetections = new MatOfRect();  
            System.out.println("Detecting Start");
            faceDetector.detectMultiScale(frame, faceDetections); 
            
            Rect[] rects=faceDetections.toArray();          
            System.out.println("Detecting End");
            
            
            Image img;
            try {
                img = ImageIO.read(new File(filename));
                 JFrame window=new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setAlwaysOnTop(true);
                JPanel panel=new JPanel()
                {
                    @Override
                    public void paint(Graphics g)
                    {
                        super.paint(g); //To change body of generated methods, choose Tools | Templates.
                        Graphics2D g2=(Graphics2D) g;
                        g2.drawImage(img, 0, 0,null);
                        
                        for (Rect rect : rects ) 
                        {  
                             g2.drawRect(rect.x, rect.y, rect.width, rect.height);
                        }  
                    }
                    
                };
                window.getContentPane().add(panel);
                window.setSize(img.getWidth(null)+10, img.getHeight(null)+24);
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                
                
            } catch (IOException ex) {
                Logger.getLogger(OpenCV_test.class.getName()).log(Level.SEVERE, null, ex);
            }
             
               
            
            
         }
    }
}
