
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
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
import java.io.OutputStreamWriter;
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

        final JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        final JTextArea edit = new JTextArea("HI I AM A TEXTAREA");
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
        jmisave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		int retval = jfc.showSaveDialog(null);
        		
        		
        		if(retval == JFileChooser.APPROVE_OPTION)
        		{
        			File f1 = jfc.getSelectedFile();
        			if(f1 == null)
        			{
        			 return;
        			}
        			if(!f1.getName().toLowerCase().endsWith(".txt"))
        			{
        				f1 = new File(f1.getParentFile(),f1.getName() + ".txt");
        			}
        			try
        			{
        				edit.write(new OutputStreamWriter(new FileOutputStream(f1),"utf-8"));
        				Desktop.getDesktop().open(f1);
        			}
        			catch(Exception e)
        			{
        				e.printStackTrace();
        			}
        		}
        	}
        });
        jmiexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        jfrm.add(sta);
        jfrm.setJMenuBar(jmb);
        jfrm.setVisible(true);

        Runnable r = new Runnable() {
        	int i=0;
            public void run() {
            	try {
        			while(true){
        
        			ss = new ServerSocket(5000);
        			System.out.println("Server is running");
        			s = ss.accept();
        			isr = new InputStreamReader(s.getInputStream());
        			br = new BufferedReader(isr);
        			message = br.readLine();
        			if(message.equals(null))
        			{
        				message="\n";
        			}
        			if(i<7)
        			{
        				i++;
        			}
        			else{
        				edit.append(message);
        				i=0;
        			}
        			System.out.println(message);
        			
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
        };
        new Thread(r).start();
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

