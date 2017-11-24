public class Main {
    public static void main(String args[])
    {
        ServerImage serverImage = new ServerImage();
        ServerLine serverLine = new ServerLine();
        test T = new test();
        Thread t1 = new Thread(serverLine);
        Thread t2 = new Thread(serverImage);
        t1.start();
        t2.start();
    }
}
