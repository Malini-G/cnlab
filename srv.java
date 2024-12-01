import javax.swing.*; 
import java.net.*; 
import java.awt.image.*; 
import javax.imageio.*; 
import java.io.*;
class srv{ 
@SuppressWarnings("resource")
public static void main(String args[]) throws IOException{ 
ServerSocket server=null;
Socket socket;
server=new ServerSocket(12345); System.out.println("Server waiting for image"); 
socket=server.accept(); 
System.out.println("Client connected"); 
InputStream in=socket.getInputStream(); 
DataInputStream dis=new DataInputStream(in); 
int len=dis.readInt();
System.out.println("Image Size:"+len/1024+"KB"); 
byte[] data = new byte[len];
dis.readFully(data); 
dis.close();
in.close();
InputStream ian = new ByteArrayInputStream(data); 
BufferedImage blmage = ImageIO.read(ian); 
JFrame f =new JFrame("Server");
ImageIcon icon = new ImageIcon(blmage); 
JLabel l = new JLabel();
l.setIcon(icon); f.add(l);
f.pack(); f.setVisible(true);
}
}
