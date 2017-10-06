import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class test implements ActionListener {
	
	private static ServerSocket  ss;
	private static Socket s;
	private static BufferedReader br;
	private static InputStreamReader isr;
	private static String message ="";
	
    public test() {
        JFrame jfrm = new JFrame("Hastalekhana");
        jfrm.setLayout(new BorderLayout());
        jfrm.setSize(720, 700);

        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        JTextArea edit = new JTextArea("HI I AM A TEXTAREA");
        edit.setEditable(true);
        JScrollPane sta = new JScrollPane(edit);

        sta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        

        JMenuBar jmb = new JMenuBar();

        JMenu jmfile = new JMenu("File");
        JMenuItem jmiopen = new JMenuItem("Open");
        JMenuItem jmiclose = new JMenuItem("Close");
        JMenuItem jmisave = new JMenuItem("Save");
        JMenuItem jmiexit = new JMenuItem("Exit");
        jmfile.add(jmiopen);
        jmfile.add(jmiclose);
        jmfile.add(jmisave);
        jmfile.add(jmiexit);
        jmb.add(jmfile);
        
        JMenu jmedit = new JMenu("Edit");
        
        JMenu jmcolor = new JMenu("color");
        JMenuItem jmiRed = new JMenuItem("Red");
        JMenuItem jmiBlue = new JMenuItem("Blue");
        JMenuItem jmigreen = new JMenuItem("Green");
        jmcolor.add(jmiRed);
        jmcolor.add(jmiBlue);
        jmcolor.add(jmigreen);
        jmedit.add(jmcolor);
        
        JMenu jmfont = new JMenu("Font");
        JMenuItem jmin = new JMenuItem("New times roman");
        JMenuItem jmi = new JMenuItem("italics");
        jmfont.add(jmin);
        jmfont.add(jmi);
        jmedit.add(jmfont);
        jmb.add(jmedit);
        
        JMenu jmhelp = new JMenu("help");
        JMenuItem jma = new JMenuItem("About");
        JMenuItem jmt = new JMenuItem("Help topics");
        jmhelp.add(jma);
        jmhelp.add(jmt);
        jmb.add(jmhelp);
        
        JMenu jml = new JMenu("listen");
        JMenuItem jmil = new JMenuItem("start");
        jml.add(jmil);
        jmb.add(jml);
        
        jmiopen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {

                    int returnvalue = jfc.showOpenDialog(null);

                    if (returnvalue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = jfc.getSelectedFile();
                        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                            String line = reader.readLine();

                            while (line != null) {
                                edit.append(line + "\n");
                                line = reader.readLine();
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        jmiclose.addActionListener(this);
        /*jmisave.addActionListener(new ActionListener()
        		{
        	     public void actionPerformed(ActionEvent ae) {
        	    	 try{
        	    	 int rtvalue = jfc.showSaveDialog(null);
        	    	
        	    	 //File select = jfc.getSelectedFile();
        	    	 BufferedReader b = new BufferedReader(new FileReader(jfc.getSelectedFile()));
        	     }
        	    	 catch(IOException e2)
        	    	 {
        	    		 e2.printStackTrace();
        	    	 }
        	     }
        		});*/
        jmiexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        jmil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		try {
    		
    			ss = new ServerSocket(5000);
    			System.out.println("Server is running");
    			s = ss.accept();
    			isr = new InputStreamReader(s.getInputStream());
    			br = new BufferedReader(isr);
    			message = br.readLine();
    			edit.append(message);
    			System.out.println(message);
    			
    			isr.close();
    			br.close();
    			ss.close();
    			s.close();
    			
    			
    				
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
        	}
        });
        

        jfrm.add(sta);
        jfrm.setJMenuBar(jmb);
        jfrm.setVisible(true);
        
        
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new test();

            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}