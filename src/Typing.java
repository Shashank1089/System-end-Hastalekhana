import java.awt.*;
import java.awt.event.KeyEvent;

public class Typing {
    public Typing(String s)
    {
        byte[] bytes = s.getBytes();
        try{
            Robot robot = new Robot();
            for (byte b : bytes)
            {
                int code = b;
                // keycode only handles [A-Z] (which is ASCII decimal [65-90])
                if (code > 96 && code < 123) code = code - 32;
                robot.delay(40);
                robot.keyPress(code);
                robot.keyRelease(code);
            }
            robot.keyPress(KeyEvent.VK_SPACE); robot.keyRelease(KeyEvent.VK_SPACE);
        }
        catch(Exception e){
            //
        }
    }
}
