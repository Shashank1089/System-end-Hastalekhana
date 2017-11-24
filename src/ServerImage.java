import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class ServerImage implements Runnable {
    private int count=1;
    public void run(){
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(5001);
                System.out.println("Server is running at port 5001");
                Socket socket = serverSocket.accept();
                System.out.println("Reading: " + System.currentTimeMillis());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                byte[] sizeAr = new byte[4];
                in.readFully(sizeAr);
                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                byte[] imageAr = new byte[size];
                in.readFully(imageAr);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
                System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                String name = "test" + count + ".png";
                ImageIO.write(image, "png", new File(name));
                count++;
                new CopyCut(name);
                serverSocket.close();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
