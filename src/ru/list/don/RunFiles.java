package ru.list.don;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class RunFiles implements Runnable{
	String[] list, treangle;
	File qFile;
	byte bytein;
	Byte[] byteAr = null;
	//FileWriter dFile;
	int counter=0, bar=0, bar1=0;
	String line, listps, status, linePlate;
	  //FileChannel srcChannel = null;
	  //FileChannel dstChannel = null;
	RandomAccessFile dFile;
	private String pathik;
	JProgressBar progressBar;
	private boolean br2=false, br3=false, trFile=false;
	String lineRotate = "%-90 rotate", scaleM = "1 1 scale";
	//private RandomAccessFile buffer;
	
	
	RunFiles(String[] list, String pathik, int bar, JProgressBar progressBar,String status, boolean trFile) {

		this.list = list;
		this.pathik= pathik;
		this.bar = bar;
		this.progressBar = progressBar;
		this.status = status;
		this.trFile = trFile;
	}
	
	public void run(){

		pathik = pathik.substring(0, pathik.lastIndexOf("/"));
		pathik = pathik.substring(0, pathik.lastIndexOf("/"))+"/PS/";
		
		bar=(int)(100/bar);
	
		//System.out.println(pathik);
		for(int i = 0; i<list.length; i++){

			listps = pathik+list[i].substring(list[i].lastIndexOf("/"), list[i].lastIndexOf("."))+"_mod.ps";
		qFile = new File(list[i]);
		//dFile = new FileWriter(list[i]+"_");

		CharSequence cs1 = "%%EndPageSetup";
		CharSequence cs2 = "%END TREANGL";
		CharSequence cs3 = "%%BeginSetup";
		CharSequence cs4 = "%%EndSetup";
		CharSequence cs9 = "%%BeginPageSetup";
		CharSequence cs10 = "%%PlateColor:";
		CharSequence cs11 = "%%EndDefaults";
		CharSequence cs12 = "%%BeginDefaults";
		char cs = '\n';
		

		boolean breakBool = false;
		 //JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		
		TreangleFile tFile = new TreangleFile();
		treangle = new String[tFile.count()];
		try {
			treangle = tFile.onLoad(trFile);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		
		try {
			//InputStream reader = new FileInputStream(qFile);
			//InputStreamReader isr = new InputStreamReader(reader, Charset.forName("US-ASCII"));
			//BufferedReader buffer = new BufferedReader(isr);
			
			DataInputStream buffer = new DataInputStream( new BufferedInputStream(new FileInputStream(qFile)));
			DataOutputStream dFile = new DataOutputStream( new BufferedOutputStream(new FileOutputStream(listps)));
			
	       /*  OutputStream f = new FileOutputStream("File.txt", true);
	            OutputStreamWriter writer = new OutputStreamWriter(f);
	            BufferedWriter out = new BufferedWriter(writer);
	            for(int i = 0; i < str.size(); i++)
	            {
	                out.write(str.get(i));
	                out.flush();
	            }*/
			
			//dFile = new RandomAccessFile (listps, "rw");
			//buffer = new RandomAccessFile (qFile, "r");
			
			
				String line="";
				boolean br = false;
				br2=false;
				//int j=0; 
				//long sin=0, sout=0;
				//byte clear = 0;
				//byte[] byteAr = new byte[50];
				
					try {
						while (buffer.available() > 0) {
							// byte bytein = buffer.readByte();
						
							 line=buffer.readLine()+"\n";;
							 
							//byteAr[j] = bytein;
							//j++;
							
					//	if(bytein == 13){
							//sin = buffer.getFilePointer();
							//byteAr = new byte [j];
							//sout = sin;
							//j=0;
						
							//byteAr = new byte[len.length()];
							//for (int k = 0; k<len.length(); k++) {

						
									
				
				//line=new String(byteAr);
				//System.out.println(line);
	
							if (line.contains(cs4)) br=false;
							if (br) trimLine(line);
							if (line.contains(cs3)) br=true;
							
							if (line.contains(cs11)) br2=false;
							if (br2) trimLineIn(line);
							if (line.contains(cs12)) br2=true;
							
							if (line.contains(cs10)) linePlate = line.substring(12);
														
							dFile.write(line.getBytes());
																					
							if (line.contains(cs1)){
							
								counter++;
								treangle[4] = "/infoFile ("+status+counter+linePlate+") def";
								treangle[23] = lineRotate;
								treangle[26] = scaleM;
								//System.out.println(bar1);
								bar1=bar1+bar;
								setBar(bar1);
								for(String date: treangle) {dFile.write((date+"\n").getBytes());
															if (date.contains(cs2)) break;
										}
									}
							//Arrays.fill(byteAr, clear);
							
							//	j = 0;
							//	}
						}
						dFile.flush();
						buffer.close();
						dFile.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
				
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		}
		setBar(100);
		//return counter;

	};

	
	void setBar(int bart){
		
		progressBar.setValue(bart);
		//System.out.println(bart);
	}
	
	void trimLine(String line){
				
		CharSequence cs5 = "Tl";
		CharSequence cs6 = "rotate";
		CharSequence cs61 = "90.0 rotate";
		//System.out.println(line);
		/*if (line.contains(cs5)) {
			String[] lR = line.split(" ");
			lineRotate = lR[0]+" floor neg "+lR[1]+" floor neg translate\n";
			System.out.println(lineRotate);
		};
		//if (line.contains(cs6)) line = "\n";
		//if (line.contains(cs61)) trFile=true;
		*/
		
		if (line.contains(cs6)) lineRotate = "-90 rotate"; 
		
		//return lineRotate;
	}
	
	void trimLineIn(String line){
		//CharSequence cs7 = "Adobe_AGM_Core/begin";
		//CharSequence cs71 = "Adobe_AGM_Utils begin";
		//CharSequence cs8 = "Adobe_AGM_Core/end";
		//CharSequence cs81 = "Adobe_AGM_Image/ps gx";
		CharSequence cs72 = "%%ViewingOrientation:";
		//if (line.contains(cs7)||line.contains(cs71)) br3 = true;
		//if (br3) {if (line.contains(cs8)||line.contains(cs81)) br3 = false; line = "\n";}
		//if (line.contains(cs7)) br3 = true;
		//if (br3) {if (line.contains(cs8)) br3 = false; line = "\n";}
				//trFile=true;
		//System.out.println(line);
		if (line.contains(cs72)) {
			String[] l = line.split(" ");
			if (l[1] == "1") scaleM = "1 -1 scale"; 
		}
		//return line;
	}
	
}
