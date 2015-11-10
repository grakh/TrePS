package ru.list.don;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

public class OpenPath {
	String line;
	
    URL url = OpenPath.class.getProtectionDomain().getCodeSource().getLocation();
	//String path = OpenPath.class.getResourceAsStream("res/Path.txt").toString();
   String path = url.toString();

	
	String out() throws IOException{
		//System.out.println(path);
		try {
			path = new File("").getAbsolutePath();
			File r = new File(path+"/res/Path.txt");
			//System.out.println(infoFile.exists());
			InputStream reader = new FileInputStream(r);
			InputStreamReader isr = new InputStreamReader(reader, Charset.forName("UTF-8"));
			BufferedReader buffer = new BufferedReader(isr);
			
				line = buffer.readLine();
				
				
			reader.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Info File not found!",null, JOptionPane.ERROR_MESSAGE);
		} 
		

		return line;
	}

	void in(String line) throws IOException{
		try {
			path = new File("").getAbsolutePath();
			FileWriter w = new FileWriter(path+"/res/Path.txt");
		
			
				w.write(line);
				
				
			w.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Info File not found!",null, JOptionPane.ERROR_MESSAGE);
		} 
		
	}
}
