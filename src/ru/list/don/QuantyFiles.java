package ru.list.don;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class QuantyFiles {
	String[] list;
	File qFile;
	int counter=0, enumStrok=0;
	
	QuantyFiles(String[] list) {

		this.list = list;
		
	}
	
	int openInfo() throws IOException{
		for(int i = 0; i<list.length; i++){
		qFile = new File(list[i]);
		CharSequence cs1 = "%%EndPageSetup";
		CharSequence cs2 = "%TREANGL";
		 //JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		
		try {
			InputStream reader = new FileInputStream(qFile);
			InputStreamReader isr = new InputStreamReader(reader, Charset.forName("UTF-8"));
			BufferedReader buffer = new BufferedReader(isr);
			
			//System.out.println(treangleFile.exists());
			//System.out.println(treangleFile.getName());
			//System.out.println(treangleFile.getParent());

				String line;
				
			
					while ((line = buffer.readLine()) != null) {
						if (line.contains(cs2)) {JOptionPane.showMessageDialog(null, "The file was modified!",null, JOptionPane.ERROR_MESSAGE);
						counter=0; break;};
						//System.out.println(line);
						if (line.contains(cs1)) counter++;
						enumStrok++;
					
					}

				
				reader.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		}
		return counter;
	}
		int enumLine(){
			return enumStrok;
		}
}
	

