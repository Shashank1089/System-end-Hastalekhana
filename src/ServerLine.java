import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLine implements Runnable {
    int i=0;
    private static ServerSocket  ss;
    private static Socket s;
    private static BufferedReader br;
    private static InputStreamReader isr;
    private static String message ="";
    public void run() {
        try {
            while(true){

                ss = new ServerSocket(5000);
                System.out.println("Server is running at port 5000");
                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();
                System.out.println(message);
                new Typing(message);
                isr.close();
                br.close();
                ss.close();
                s.close();

            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
