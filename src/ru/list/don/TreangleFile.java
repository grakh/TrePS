package ru.list.don;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

 class TreangleFile {

	File treangleFile;
	String trF = "TREANGLE.txt";	
		
		int i=0;

		String[] line = new String[100];

        URL url = TreangleFile.class.getProtectionDomain().getCodeSource().getLocation();
        String path = url.toString();


				
		String[] onLoad(boolean trFile) throws IOException{
			
			if (trFile) trF = "OTREANGLE.txt";
			
			path = new File("").getAbsolutePath();
			System.out.println(trF);

			//System.out.println(path+"/res/TREANGLE.txt");

			//path = new TreangleFile().getClass().getResource("/").toString();
			//path = path.substring(path.indexOf("/"), path.lastIndexOf("/"));
			//path = path.substring(path.indexOf("/"), path.lastIndexOf("/"));
			//System.out.println(path+"/res/TREANGLE.txt");
			
			treangleFile = new File(path+"/res/"+trF);
			//System.out.println(treangleFile.exists());
			//System.out.println(treangleFile.getName());
			
			try {
				//InputStream reader = new FileInputStream(treangleFile);
				InputStream reader = TreangleFile.class.getResourceAsStream(trF);
				InputStreamReader isr = new InputStreamReader(reader, Charset.forName("UTF-8"));
				BufferedReader buffer = new BufferedReader(isr);
				
				//System.out.println(treangleFile.exists());
				//System.out.println(treangleFile.getName());
				//System.out.println(treangleFile.getParent());

					while ((line[i] = buffer.readLine()) != null) {
						i++;
					
					}
					
					reader.close();	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			

			return line;
		}
		
		int count(){
			return line.length;
			
		}
	
}
