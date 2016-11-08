package srilm;



import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class menu implements ActionListener{
	JMenuBar menubar;
	JMenu file;
	JMenuItem openFile;
	JMenuItem command;
	JMenuItem debug;
	JMenuItem exit;
	JButton commandButton;
	JTextArea commandTextArea;
	JButton debugButton;
	JTextArea debugTextArea;
	JFrame commandFrame;
	JFrame debugFrame;
  public menu(){
	  
	menubar = new JMenuBar();
	//File menu
	file = new JMenu("File");
			
	//Exit menuItem under the file menu
	exit = new JMenuItem("Exit");
	exit.addActionListener(this);
	
	openFile = new JMenuItem("Open File...");
	openFile.addActionListener(this);
	
	command = new JMenuItem("srilm command...");
	command.addActionListener(this);
	
	debug = new JMenuItem("debug option...");
	debug.addActionListener(this);
	
	file.add(openFile);
	file.add(exit);
	file.add(command);
	file.add(debug);

	
	
	menubar.add(file);
  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exit){
			System.exit(0);
		}

		
		if(e.getSource() == openFile){
			 JFileChooser fileChooser = new JFileChooser();
			 int return_val = fileChooser.showOpenDialog(null);
			 
			 //When we choose a file, file is being written to the LeftTextArea.
			 if(return_val == JFileChooser.APPROVE_OPTION) {
				 Srilm.file1= fileChooser.getSelectedFile();
		     }

		}
		
		if(e.getSource() == command){
			commandFrame = new JFrame();
			commandFrame.setLayout(null);
			commandFrame.setTitle("Set Command");
			commandFrame.setSize(450, 300);
			Dimension screen =  Toolkit.getDefaultToolkit().getScreenSize();		

			commandTextArea =  new JTextArea();
			commandTextArea.setSize(370,120);
			commandTextArea.setLocation(50, 50);
			commandTextArea.setText("srilm-1.7.1/bin/i686-m64/ngram \n -lm /home/rsltgy/Desktop/es-en/model-en.bo -ppl ");
			
			JScrollPane scroll = new JScrollPane(commandTextArea);
			scroll.setSize(commandTextArea.getSize());
			scroll.setLocation(50,50);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			
			commandButton = new JButton("Tamam");
			commandButton.setSize(200,30);
			commandButton.setLocation(commandTextArea.getLocation().x,commandTextArea.getLocation().y+150);
			commandButton.addActionListener(this);
			
			commandFrame.add(scroll);
			commandFrame.add(commandButton);

			
			commandFrame.setLocation((int)screen.getWidth()/2-100,(int)screen.getHeight()/2-100);
			commandFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			commandFrame.setVisible(true);
		}
		if(e.getSource() == debug){
			debugFrame = new JFrame();
			debugFrame.setLayout(null);
			debugFrame.setTitle("Set Debug Option");
			debugFrame.setSize(450, 300);
			Dimension screen =  Toolkit.getDefaultToolkit().getScreenSize();		

			debugTextArea =  new JTextArea();
			debugTextArea.setSize(370,120);
			debugTextArea.setLocation(50, 50);
			debugTextArea.setText(" -debug 4");
			
			JScrollPane scroll = new JScrollPane(debugTextArea);
			scroll.setSize(debugTextArea.getSize());
			scroll.setLocation(50,50);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			
			debugButton = new JButton("Tamam");
			debugButton.setSize(200,30);
			debugButton.setLocation(debugTextArea.getLocation().x,debugTextArea.getLocation().y+150);
			debugButton.addActionListener(this);
			
			debugFrame.add(scroll);
			debugFrame.add(debugButton);

			
			debugFrame.setLocation((int)screen.getWidth()/2-100,(int)screen.getHeight()/2-100);
			debugFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			debugFrame.setVisible(true);
		}
		if(e.getSource() == commandButton){
			Srilm.command = commandTextArea.getText();
			JOptionPane.showMessageDialog(null, "İşlem tamamlandı.");
			commandFrame.dispose();
			Srilm.TextAreaConsole.setText(Srilm.TextAreaConsole.getText()+ "\n"+
					"komut = " + commandTextArea.getText() + " olarak ayarlandı.");
		}
		if(e.getSource() == debugButton){
			Srilm.debug = debugTextArea.getText();
			JOptionPane.showMessageDialog(null, "İşlem tamamlandı.");
			debugFrame.dispose();
			Srilm.TextAreaConsole.setText(Srilm.TextAreaConsole.getText()+ "\n"+
			"debug = " + debugTextArea.getText() + " olarak ayarlandı.");
		}
	}


}

