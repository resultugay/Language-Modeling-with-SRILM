package srilm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import static  srilm.Srilm.*;

public class calculateThread extends Thread{
	public void run(){
	
		try {
			//String command = "sudo cd /home/rsltgy/Desktop/srilm-1.7.1/bin/i686-m64";
			String file1Command = command + file1 +  debug;
			

			  Process proc = Runtime.getRuntime().exec(file1Command);

		        // Read the output

		        BufferedReader reader =  
		              new BufferedReader(new InputStreamReader(proc.getInputStream()));

	        String line = "";
	        String temp = "";
	        while((line = reader.readLine()) != null) {
	            TextAreaConsole.setText(TextAreaConsole.getText() + "\n" + line);
	            temp = temp + "\n"+ line;
	        }
	        
	        firstTextProb = findLogProb(temp);
		    proc.destroyForcibly();    

	        
			//String command = "sudo cd /home/rsltgy/Desktop/srilm-1.7.1/bin/i686-m64";
			String file2Command = command + file2 +  debug;
			

			  Process proc2 = Runtime.getRuntime().exec(file2Command);

		        // Read the output

		        BufferedReader reader2 =  
		              new BufferedReader(new InputStreamReader(proc2.getInputStream()));
		        
	        String line2 = "";
	        String temp2 = "";
	        while((line2 = reader2.readLine()) != null) {
	            TextAreaConsole.setText(TextAreaConsole.getText() + "\n" + line2);
	            temp2 = temp2 + "\n" + line2;
	        }
	        
	        secondTextProb = findLogProb(temp2);
	        
	        if(firstTextProb < secondTextProb)
	        {
	        	JOptionPane.showMessageDialog(null, "1.Metin 2.Metinden daha fazla Türkçeye yakın."
	        			+"\n1.Metnin şaşkınlık değeri = " + firstTextProb
	        			+"\n2.Metnin şaşkınlık değeri = " + secondTextProb);
	            TextAreaConsole.setText(TextAreaConsole.getText() + "\n" + "1.Metin 2.Metinden daha fazla Türkçeye yakın.");

	        }else if (firstTextProb > secondTextProb)
	        {
	        	JOptionPane.showMessageDialog(null, "2.Metin 1.Metinden daha fazla Türkçeye yakın."
	        			+"\n1.Metnin şaşkınlık değeri = " + firstTextProb
	        			+"\n2.Metnin şaşkınlık değeri = " + secondTextProb);
	            TextAreaConsole.setText(TextAreaConsole.getText() + "\n" + "2.Metin 1.Metinden daha fazla Türkçeye yakın.");
	        	
	        }else {
	        	JOptionPane.showMessageDialog(null, "2 metinde aynı derecede Türkçeye yakın."
	        			+"\n1.Metnin şaşkınlık değeri = " + firstTextProb
	        			+"\n2.Metnin şaşkınlık değeri = " + secondTextProb);
	            TextAreaConsole.setText(TextAreaConsole.getText() + "\n" + "2 metinde aynı derecede Türkçeye yakın");
	        	
	        	
	        }
	        
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
