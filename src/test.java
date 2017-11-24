import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Base64;

import javax.imageio.ImageIO;

public class test implements Runnable {
    String name="";
    int count=1;
    private static ServerSocket  ss;
    private static Socket s;
    private static BufferedReader br;
    private static InputStreamReader isr;
    private static String message ="";
    public void run()
    {
        try {
            while (true) {
                ss = new ServerSocket(5000);
                System.out.println("Server is running at port 5001");
                //Socket socket = serverSocket.accept();
                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();
                //String size= br.readLine();
                System.out.println(message);
                //System.out.println(size);
                //new Typing(message);
                isr.close();
                br.close();
                ss.close();
                s.close();

                /*byte[] sizeAr = new byte[4];
                inputStream.read(sizeAr);
                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                byte[] imageAr = new byte[size];
                inputStream.read(imageAr);*/
                byte[] imageAr = Base64.getDecoder().decode(new String(message).getBytes("UTF-8"));
                System.out.print(imageAr);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

                System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                name = "test" + count + ".png";
                ImageIO.write(image, "png ", new File(name));
                count++;
                new CopyCut(name);
                //serverSocket.close();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
