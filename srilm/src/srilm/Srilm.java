package srilm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Srilm  extends JFrame implements ActionListener{
	public static File file1;
	public static File file2;
	public static JPanel general_panel;
	public static JTextArea TextArea1;
	public static JScrollPane ScrollPaneOfTextArea1;
	public static JTextArea TextArea2;
	public static JScrollPane ScrollPaneOfTextArea2;
	public static JTextArea TextAreaConsole;
	public static JScrollPane ScrollPaneOfTextAreaConsole;
	public static JButton file1Button;
	public static JButton file2Button;
	public static JButton calculateButton;
	public static String command = "srilm-1.7.1/bin/i686-m64/ngram -lm model/model-tr.bo -ppl ";
	public static String debug = " -debug 4";
	public static double firstTextProb;
	public static double secondTextProb;
	
	public Srilm(){
		//General frame configurations.
		JFrame schema = new JFrame("Language  Modeling with SRILM toolkit NLP ITU TURKEY");
		schema.setLayout(null);
		general_panel = new JPanel();
		general_panel.setLayout(null);
		
		Dimension screen =  Toolkit.getDefaultToolkit().getScreenSize();		
		schema.setSize((int)screen.getWidth(),(int)screen.getHeight());
		schema.setLocation(0,0);
		schema.setContentPane(general_panel);	
		
		TextArea1 = new JTextArea();
		TextArea1.setSize((int)screen.getWidth()/2-50, (int)screen.getHeight()-350);
		TextArea1.setLocation(10, 50);
		TextArea1.setEditable(false);
		
		ScrollPaneOfTextArea1 = new JScrollPane(TextArea1);
		ScrollPaneOfTextArea1.setSize(TextArea1.getSize());
		ScrollPaneOfTextArea1.setLocation(10, 50);
		ScrollPaneOfTextArea1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		TextArea2 = new JTextArea();
		TextArea2.setSize((int)screen.getWidth()/2-50, (int)screen.getHeight()-350);
		TextArea2.setLocation(TextArea1.getWidth()+10, 50);
		TextArea2.setEditable(false);

		
		ScrollPaneOfTextArea2 = new JScrollPane(TextArea2);
		ScrollPaneOfTextArea2.setSize(TextArea2.getSize());
		ScrollPaneOfTextArea2.setLocation(TextArea1.getWidth()+30, 50);
		ScrollPaneOfTextArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
		TextAreaConsole = new JTextArea();
		TextAreaConsole.setSize((int)screen.getWidth()-80, 200);
		TextAreaConsole.setLocation(TextAreaConsole.getWidth()+10, 50);
		TextAreaConsole.setEditable(false);

		
		ScrollPaneOfTextAreaConsole = new JScrollPane(TextAreaConsole);
		ScrollPaneOfTextAreaConsole.setSize(TextAreaConsole.getSize());
		ScrollPaneOfTextAreaConsole.setLocation(10,ScrollPaneOfTextArea2.getHeight()+60);
		ScrollPaneOfTextAreaConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		file1Button = new JButton("1.Metni Seç");
		file1Button.setSize(250,30);
		file1Button.setLocation(TextArea1.getLocation().x+200,TextArea1.getLocation().y-40);
		file1Button.addActionListener(this);
		
		file2Button = new JButton("2.Metni Seç");
		file2Button.setSize(250,30);
		file2Button.setLocation(TextArea2.getLocation().x+200,TextArea2.getLocation().y-40);
		file2Button.addActionListener(this);
		
		calculateButton = new JButton("Hesapla");
		calculateButton.setSize(250,30);
		calculateButton.setLocation(file1Button.getLocation().x+300,file1Button.getLocation().y);
		calculateButton.addActionListener(this);
		calculateButton.setEnabled(false);
		
		general_panel.add(ScrollPaneOfTextArea1);
		general_panel.add(ScrollPaneOfTextArea2);
		general_panel.add(ScrollPaneOfTextAreaConsole);
		general_panel.add(file1Button);
		general_panel.add(file2Button);
		general_panel.add(calculateButton);


		menu menu = new menu();
		schema.setJMenuBar(menu.menubar);
		schema.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		schema.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Srilm obj = new Srilm();
		TextAreaConsole.setText("Lütfen metinleri seçiniz..");

	/*
		
	
		// TODO Auto-generated method stub
		try {
			//String command = "sudo cd /home/rsltgy/Desktop/srilm-1.7.1/bin/i686-m64";
			String command = "/home/rsltgy/Desktop/srilm-1.7.1/bin/i686-m64/ngram -lm /home/rsltgy/Desktop/es-en/model-en.bo -ppl /home/rsltgy/Desktop/es-en/es-test.es";
			

			  Process proc = Runtime.getRuntime().exec(command);

		        // Read the output

		        BufferedReader reader =  
		              new BufferedReader(new InputStreamReader(proc.getInputStream()));


	        String line = "";
	        while((line = reader.readLine()) != null) {
	            System.out.print(line + "\n");
	        }
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == file1Button){
			 JFileChooser fileChooser = new JFileChooser();
			 int return_val = fileChooser.showOpenDialog(null);
			 
			 if(return_val == JFileChooser.APPROVE_OPTION) {
				 file1 = fileChooser.getSelectedFile();
				 TextAreaConsole.setText(TextAreaConsole.getText() + "\n1.metin seçildi.");
				 writeToTextArea(file1, TextArea1);					 
		     }
			 
			 if(file2 != null)
				 calculateButton.setEnabled(true);
		}
		if(e.getSource() == file2Button){
			 JFileChooser fileChooser = new JFileChooser();
			 int return_val = fileChooser.showOpenDialog(null);
			 
			 if(return_val == JFileChooser.APPROVE_OPTION) {
				 file2 = fileChooser.getSelectedFile();
				 TextAreaConsole.setText(TextAreaConsole.getText() + "\n2.metin seçildi.");
				 writeToTextArea(file2, TextArea2);			
		     }
			 if(file1 != null)
				 calculateButton.setEnabled(true);
		}
		
		if(e.getSource() == calculateButton){
			TextAreaConsole.setText(TextAreaConsole.getText() + "\nLütfen Bekleyiniz.Olasılıklar Hesaplanıyor");
			calculateThread th = new calculateThread();
			th.start();		
			
		}
		
		
	}
	
	public static Double findLogProb(String text){

	        final Matcher matcher = Pattern.compile(".*ppl1=").matcher(text);
	        String subString = "";
	        if(matcher.find()){
	            System.out.println(text.substring(matcher.end()).trim());
	            subString = subString + text.substring(matcher.end()).trim();
	        }
	
	        final Matcher matcher2 = Pattern.compile("\n").matcher(subString);
	        String log = "";
	        if(matcher2.find()){
	        	log = subString.substring(0,matcher2.start()).trim();
	        }
	        
	        if(log.equals("undefined"))
	        	log = "99999999999999999.0";
	        
	        
	        double logProg = Double.parseDouble(log);
	        
        return logProg;
        		
	}
	
	public void writeToTextArea(File file,JTextArea textArea){
		if(file != null){
			try {
				Scanner inputFile = new Scanner(new FileReader(file));
				while (inputFile.hasNext()){
					textArea.setText(textArea.getText() + inputFile.nextLine() +"\n");					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}
