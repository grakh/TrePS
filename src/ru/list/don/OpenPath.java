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
	String line, path="";
	

	
	String out() throws IOException{
		path = OpenPath.class.getProtectionDomain().getCodeSource().getLocation().toString();
		path = path.substring(path.indexOf("/")+1, path.lastIndexOf("/"));

		//InputStream path = OpenPath.class.getResourceAsStream("res/Path.txt");
		
		//System.out.println(OpenPath.class.getResource("Path.txt"));
		//JOptionPane.showMessageDialog(null,OpenPath.class.getResourceAsStream("Path.txt").read(),null, JOptionPane.OK_OPTION);; 
		//path = path.substring(path.indexOf("/")+1, path.lastIndexOf("/"));
		//JOptionPane.showMessageDialog(null,path,null, JOptionPane.OK_OPTION);
		
		try {
			//path = new File("").getAbsolutePath();
			
			//System.out.println(path);
			File r = new File(path+"/Path.res");
			if(!r.exists()) {
				   //Создаем его.
				   r.createNewFile();
				}
			//File r = new File(cl.getResource("res/Path.txt"));
			//System.out.println(cl.getResource("res/Path.txt"));
			InputStream reader = new FileInputStream(r);
			
			//System.out.println(OpenPath.class.getResource("Path.txt"));
			//InputStream reader = OpenPath.class.getResourceAsStream("Path.txt");
			
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
			path = OpenPath.class.getProtectionDomain().getCodeSource().getLocation().toString();
			path = path.substring(path.indexOf("/")+1, path.lastIndexOf("/"));

			//path = new File("").getAbsolutePath();
			//FileWriter w = new FileWriter(path+"/res/Path.txt");
			FileWriter w = new FileWriter(path+"/Path.res");
			
				w.write(line);
				
				
			w.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Info File not found!",null, JOptionPane.ERROR_MESSAGE);
		} 
		
	}
}
