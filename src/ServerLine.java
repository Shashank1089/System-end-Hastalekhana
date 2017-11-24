import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLine implements Runnable
{
    public void run() {
        try {
            while(true){

                ServerSocket ss = new ServerSocket(5000);
                System.out.println("Server is running at port 5000");
                Socket s = ss.accept();
                InputStreamReader isr = new InputStreamReader(s.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String message = br.readLine();
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
