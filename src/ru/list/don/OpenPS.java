package ru.list.don;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

class OpenPS {
	JFileChooser fileopen = new JFileChooser();    
	FileNameExtensionFilter filter = new FileNameExtensionFilter("PS & ps Files", "PS", "ps", "txt");
    String[] treangleLine;
	String[] infoLine;
	private JFrame form;
	
	String[] openDialog(){
		this.form = form;
		String path, line;
		fileopen.setFileFilter(filter);  
    int ret = fileopen.showOpenDialog(fileopen);               
    if (ret == JFileChooser.APPROVE_OPTION) {
        File file = fileopen.getSelectedFile();
        System.out.println(file.getName());

        path = file.getParent();
        //System.out.println(path);
        path = path.replace("\\", "/");
        //System.out.println(path);
        path = path.substring(0, path.lastIndexOf("/"))+"/tmp/info.txt";
        System.out.println(path);
        
       InfoFile ifile = new InfoFile();
        try {
			line = ifile.onLoad(path);
	        infoLine = line.split(";");
	        //System.out.println(infoLine[0]);
	        
	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        
        TreangleFile treangle = new TreangleFile();
        try {
			treangleLine = treangle.onLoad();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No exists treangle");
		}
 
        
     }
    return infoLine;
	}
	
}
