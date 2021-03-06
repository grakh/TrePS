package ru.list.don;

import java.io.*;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

class InfoFile {
	
	File infoFile;
	String line;
	
	String onLoad(String path) throws IOException{
		try {
			infoFile = new File(path);
			System.out.println(infoFile.exists());
			InputStream reader = new FileInputStream(infoFile);
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

}
