package ru.list.don;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

class OpenFolderPS {
	JFileChooser fileopen;   
	FileNameExtensionFilter filter;
    String[] treangleLine;
	String[] infoLine, infoFile;
	File[] path1;
	String line, path, openPath="";
	//private JFrame form;

	//"/home/sega/Documents/Test/PS"

	File[] openDialog(String openPath){
		
		fileopen = new JFileChooser(openPath);
		filter = new FileNameExtensionFilter("PS & ps Files", "PS", "ps");
		FilenameFilter filter1 = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".ps")) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		//this.form = form;

		//Path[] lFile = null;
		
		fileopen.setFileFilter(filter);  
		
		fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//fileopen.setMultiSelectionEnabled(true);
		fileopen.setAcceptAllFileFilterUsed(false); 
    int ret = fileopen.showOpenDialog(fileopen);               
    if (ret == JFileChooser.APPROVE_OPTION) {
        
        //System.out.println(fileopen.);

       path1 = fileopen.getCurrentDirectory().listFiles(filter1);
   	path=fileopen.getCurrentDirectory().toString();
   //	System.out.println(path);
      
       for (File file1: path1) { 
    	  
       System.out.println(file1.getName());
       //infoFile[i] = file1.getName();
      
       } ;
       /* 
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path))) {
            for (Path file1: stream) {
                if(!file1.toFile().isDirectory()) {
                    System.out.println(file1.getFileName());
                	//if (file1.toFile().getName().endsWith(".ps")) lFile[0] = file1.getFileName();
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
                System.err.println(x);
        }
        */

        path = path.replace("\\", "/");
       // System.out.println(path);
        path = path.substring(0, path.lastIndexOf("/"))+"/tmp/info.txt";
       // System.out.println(path);
        
       InfoFile ifile = new InfoFile();
        try {
			line = ifile.onLoad(path);
	        infoLine = line.split(";");
	        //System.out.println(infoLine[0]);
	        
	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    
       
     } else JOptionPane.showMessageDialog(null, "Open Dialog canceled",null, JOptionPane.WARNING_MESSAGE);
    return path1;
	}
	
	   String[] info(){
		   return infoLine;
	   }
	   
	   String[] treangle(){
	        TreangleFile treangle = new TreangleFile();
	        try {
				treangleLine = treangle.onLoad(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("No exists treangle");
			}
			return treangleLine;
	   }
}
