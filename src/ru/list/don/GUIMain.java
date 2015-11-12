package ru.list.don;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUIMain {
	
	JFrame mainForm;
	JPanel buttonPanel, fieldPanel, statusPanel, runPanel;
	JMenuBar menuBar;
	JButton buttonOpen, buttonRun, buttonExit, buttonRemove, buttonInfo;
	int counter;
	JLabel labelNamber, labelKontrAgent, labelPhotoPolimer, labelRoute, labelLineatura, labelCountPoliner;
	JLabel status, running;
	JTextField okStatus;
	public JTextField namber, kontrAgent, photoPolimer, route, lineatura, countPoliner;
	String[] infoLine, listik, treangle;
    JProgressBar progressBar;
    JScrollPane scroll;
    String path, pathOpen;
	int count = 0;
	OpenPath oPath = new OpenPath();
    
	DefaultListModel<Object> lm1 = new DefaultListModel<Object>();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JList listFiles = new JList(lm1);
	OpenFolderPS info1 = new OpenFolderPS();
	public boolean trFile =false;
	
	public void start() {
		
		try {
			pathOpen = oPath.out();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainForm = new JFrame("Treangle for PostScript");
		menuBar = new JMenuBar();
		mainForm.setJMenuBar(menuBar);
		JMenu fileBar = new JMenu("File");
				JMenuItem newFile = new JMenuItem("New");
				newFile.addActionListener(new FileNew());
				newFile.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.ALT_MASK));
				fileBar.add(newFile);
				
				JMenuItem openFile = new JMenuItem("Open");
				openFile.addActionListener(new FileOpen());
				openFile.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.ALT_MASK));
				fileBar.add(openFile);
		
				fileBar.addSeparator();
				
				JMenuItem runFile = new JMenuItem("Run");
				runFile.addActionListener(new FileRun());
				runFile.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_R, ActionEvent.ALT_MASK));
				fileBar.add(runFile);
				
				fileBar.addSeparator();
				JMenuItem closeFile = new JMenuItem("Exit");
				closeFile.addActionListener(new FileExit());
				closeFile.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_X, ActionEvent.ALT_MASK));
				fileBar.add(closeFile);
				
		menuBar.add(fileBar);
		
		
		JMenu preferensBar = new JMenu("Preferens");
		menuBar.add(preferensBar);
		
		JMenuItem path = new JMenuItem("Path");
		path.addActionListener(new Path());
		preferensBar.add(path);
		
		JMenu helpBar = new JMenu("Help");
		menuBar.add(helpBar);
		
		JMenuItem help = new JMenuItem("Help");
		help.addActionListener(new Help());
		helpBar.add(help);;
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new About());
		helpBar.add(about);
		
		
		buttonPanel = new JPanel();
		fieldPanel = new JPanel();
		statusPanel = new JPanel();
		runPanel = new JPanel();
		
		mainForm.setLocationRelativeTo(null);
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS)); 
		runPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS)); 
		fieldPanel.setLayout(new GridBagLayout()); 
		buttonPanel.setLayout(new GridBagLayout()); 
		GridBagConstraints cButton = new GridBagConstraints();
		GridBagConstraints cField = new GridBagConstraints();

		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cField.fill = GridBagConstraints.HORIZONTAL;
		cField.fill = GridBagConstraints.NORTH;

		//buttonCenterPanel.setLayout(new BoxLayout(buttonCenterPanel, BoxLayout.Y_AXIS));
		cField.weighty = 0.5;
		cButton.weighty = 0.5;
        
		buttonOpen = new JButton("Open");
		buttonOpen.addActionListener(new FileOpen());
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.insets = new Insets(10, 10, 0, 10);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.ipady = 20;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ  
		cButton.weighty = 0;
		cButton.gridx = 0; 
		cButton.gridy = 0; 
		buttonPanel.add(buttonOpen, cButton);
		
		String txt =""; //{"11","22","33","44","55","66"};
		//for(int x = 0; x < txt.length; x++) 
		lm1.addElement(txt);
		listFiles.setPreferredSize(new Dimension(150, 150));
		listFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFiles.setLayoutOrientation(JList.VERTICAL);

		//listFiles.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listFiles.setVisibleRowCount(0);
		scroll = new JScrollPane(listFiles, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroll.setVerticalScrollBarPolicy(0);
		//scroll.getViewport().setView(listFiles);
		//cButton.insets = new Insets(20, 5, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.ipady = 70;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ  
		cButton.ipadx = 50;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ  
		cButton.weighty = 0;
		cButton.gridy = 1; 
		buttonPanel.add(scroll, cButton);
		buttonRemove = new JButton("Remove file");
		buttonRemove.setEnabled(false);
		buttonRemove.addActionListener(new FileRemove());
		cButton.ipady = 5;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ  
		//cButton.ipadx = 50;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ  
		cButton.weighty = 0;
		cButton.gridy = 2; 
		buttonPanel.add(buttonRemove, cButton);
		
		buttonInfo = new JButton("Info");
		buttonInfo.setEnabled(false);
		buttonInfo.addActionListener(new FileInfo());
		cButton.insets = new Insets(40, 10, 0, 150);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.ipadx = 10;
		cButton.ipady = 30;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
		//cButton.weightx = 0;
		//cButton.weighty = 0;
        cButton.gridx = 0; 
		cButton.gridy = 3; 
		//runPanel.add(buttonInfo);
		buttonPanel.add(buttonInfo, cButton);
		
		buttonRun = new JButton("Run");
		buttonRun.setEnabled(false);
		buttonRun.addActionListener(new FileRun());
		//runPanel.add(buttonRun);
		cButton.insets = new Insets(40, 100, 0, 10);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.ipady = 30;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
		cButton.weighty = 0;
        cButton.gridx = 0; 
		cButton.gridy = 3; 
		buttonPanel.add(buttonRun, cButton);
		
		
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new FileExit());
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.ipady = 10;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cButton.insets = new Insets(10, 10, 0, 10);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cButton.gridwidth = 1;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cButton.gridy = 4;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		buttonPanel.add(buttonExit, cButton);
		//buttonPanel.add(BorderLayout.CENTER, buttonCenterPanel);
		
		
		
		labelNamber = new JLabel("Order namber:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(30, 0, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 2;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 1;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(labelNamber, cField);
		
		namber = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(30, 10, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 3;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 1;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(namber, cField);
		
		labelKontrAgent = new JLabel("Contractor:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 0, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 2;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 2;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(labelKontrAgent, cField);
		
		kontrAgent = new JTextField("", 20);
		cField.fill = GridBagConstraints.NORTH;
		//cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 10, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 3;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 2;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(kontrAgent, cField);
		
		labelPhotoPolimer = new JLabel("Type polimer:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 0, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 2;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 3;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(labelPhotoPolimer, cField);
		
		photoPolimer = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 10, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 3;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 3;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(photoPolimer, cField);
		
		labelRoute = new JLabel("Route Polimer:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 0, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 2;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 5;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(labelRoute, cField);
		
		route = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		route.setEditable(false);
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 10, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 3;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 5;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(route, cField);
		
		labelLineatura = new JLabel("Lineatura:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 0, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 2;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 4;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(labelLineatura, cField);
		
		lineatura = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 10, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 3;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 4;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(lineatura, cField);
		
		labelCountPoliner = new JLabel("Quantity:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 0, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 2;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 6;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(labelCountPoliner, cField);
		
		countPoliner = new JTextField("0", 10);
		countPoliner.setEditable(false);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.weighty = 1.0;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
		//cField.anchor = GridBagConstraints.SOUTH; // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
		cField.insets = new Insets(0, 10, 0, 0);  // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
        //c.gridx = 1;       // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ Button 2 
		cField.gridwidth = 3;   // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ 1 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		cField.gridy = 6;       // пїЅ 3 пїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
		fieldPanel.add(countPoliner, cField);
		
		status = new JLabel("Info status: ");
		okStatus = new JTextField("Ok");
		okStatus.setEditable(false);
		running = new JLabel("     Progress: ");
		statusPanel.add(status);
		statusPanel.add(okStatus);
		statusPanel.add(running);
		progressBar = new JProgressBar();
		   progressBar.setValue(0);
		    progressBar.setStringPainted(true);
		    
		    //Border border = BorderFactory.createTitledBorder("Reading...");
		    //progressBar.setBorder(border);
			statusPanel.add(progressBar);
		statusPanel.setBorder(BorderFactory.createEmptyBorder(15,10,5,10));
		//buttonPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		fieldPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainForm.getContentPane().setLayout(new BorderLayout(2,1));
		mainForm.getContentPane().add(BorderLayout.WEST, buttonPanel);
		mainForm.getContentPane().add(BorderLayout.CENTER, fieldPanel);
		mainForm.getContentPane().add(BorderLayout.SOUTH, statusPanel);
		//new TestForm().start(countPoliner); 
		mainForm.setBounds(300, 150, 700, 450);
		mainForm.setVisible(true);
		 lockInMinSize(mainForm);
				 
	}
	
	class FileNew implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("New");
			
			}
		
		}	
	
	class FileOpen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			lm1.clear();
			buttonRemove.setEnabled(false);
			buttonInfo.setEnabled(false);
			buttonRun.setEnabled(false);
			progressBar.setValue(0);
			
			File[] PSfile = info1.openDialog(pathOpen);
			
			for (File ps: PSfile) {lm1.addElement(ps.getName()); path= ps.getParent();}
	        path = path.replace("\\", "/")+"/";
		
			if (!lm1.isEmpty()) {buttonRemove.setEnabled(true); buttonInfo.setEnabled(true);}
			
			
			}
		
		}
	
	class FileInfo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			listik = new String[listFiles.getModel().getSize()];
		
			String c;
			CharSequence cs1 = "Прямая";
			CharSequence cs2 = "Обратная";
			
			for (int index=0; index<listFiles.getModel().getSize(); index++){
			
			listik[index]=path+(String)listFiles.getModel().getElementAt(index);
			
			//System.out.println(listik[index]);
			}

			QuantyFiles qFiles = new QuantyFiles(listik);
			try {
				count=qFiles.openInfo();
				trFile  = qFiles.tFile();
				System.out.println(count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			c=""+count;
			if(count!=0)buttonRun.setEnabled(true);
			String[] infoLine = info1.info();
			namber.setText(infoLine[1]);
			kontrAgent.setText(infoLine[0]);
			photoPolimer.setText(infoLine[2]);
			if (infoLine[4].contains(cs1)) route.setText("Forward"); else if (infoLine[4].contains(cs2)) route.setText("Revers"); 
			else {route.setText("Nothing"); JOptionPane.showMessageDialog(null, "No direction",null, JOptionPane.ERROR_MESSAGE);}
			lineatura.setText(infoLine[5]);
			countPoliner.setText(c);
			
			okStatus.setText(photoPolimer.getText()+"_"+kontrAgent.getText().substring(0, kontrAgent.getText().indexOf(" "))+"_"+
							namber.getText()+"_"+route.getText()+"_"+countPoliner.getText()+"-");
			
			kontrAgent.getDocument().addDocumentListener(new OkStatus(photoPolimer.getText(),kontrAgent.getText(),
					namber.getText(), route.getText(), countPoliner.getText()));
			//namber.getDocument().addDocumentListener(new OkStatus(photoPolimer.getText(),kontrAgent.getText(),
					//namber.getText(), route.getText(), countPoliner.getText()));
			//photoPolimer.getDocument().addDocumentListener(new OkStatus(photoPolimer.getText(),kontrAgent.getText(),
					//namber.getText(), route.getText(), countPoliner.getText()));
			//route.getDocument().addDocumentListener(new OkStatus(photoPolimer.getText(),kontrAgent.getText(),
					//namber.getText(), route.getText(), countPoliner.getText()));
			}
		
		}
	
	class OkStatus implements DocumentListener{

		private String Pol, Ag, namber, route, count;

		OkStatus(String Pol, String Ag, String namber, String route, String count){
			this.Pol = Pol;
			this.Ag = Ag;
			this.namber = namber;
			this.route = route;
			this.count = count;
			
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateLabel(e);
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateLabel(e);
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateLabel(e);
			
		}
	       private void updateLabel(DocumentEvent e) {
           //    java.awt.EventQueue.invokeLater(new Runnable() {

                //   @Override
                   //public void run() {
                       okStatus.setText(Pol+"_"+Ag.substring(0, Ag.indexOf(" "))+"_"+namber+"_"+route+"_"+count+"-");
                   }
              // });
	      
		}
	
	class FileRun implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Run");
			//okStatus.setText(photoPolimer.getText()+"_"+kontrAgent.getText().substring(0, kontrAgent.getText().indexOf(" "))+"_"+namber.getText());
			buttonRemove.setEnabled(false);
			buttonInfo.setEnabled(false);
			

				RunFiles qFiles = new RunFiles(listik, path, count, progressBar, okStatus.getText(), trFile);
				Thread t = new Thread(qFiles);
				t.start();
				//System.out.println(count);

			
			}
		
		}
	
	class FileExit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
			}
		
		}
	
	class Path implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
	
			try {
				oPath.in(JOptionPane.showInputDialog(null, "Path priority:", "Path", 1, null, null, pathOpen).toString());
				pathOpen = oPath.out();
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}

		
		}
	
	class Help implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Help");
			
			}
		
		}
	
	class About implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Made by S.Klimov\nfor OptimasmArt Ltd.\ne-mail: don@list.ru","Version 1.8", JOptionPane.CLOSED_OPTION);
			
			}
		
		}
	
	class FileRemove implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonRun.setEnabled(false);
			 int index = listFiles.getSelectedIndex();
			 if(index > -1)
			    {
			      	lm1.removeElementAt(index);
			    } else JOptionPane.showMessageDialog(null,"No selection made in List");
			  }
			
			}
		
		
	   private static void lockInMinSize(final JFrame frame) {
		      //Ensures user cannot resize frame to be smaller than frame is right now.
		      final int origX = frame.getSize().width;
		      final int origY = frame.getSize().height;
		      frame.addComponentListener(new java.awt.event.ComponentAdapter() {
		         public void componentResized(ComponentEvent event) {
		            frame.setSize(
		               (frame.getWidth() < origX) ? origX : frame.getWidth(),
		               (frame.getHeight() < origY) ? origY : frame.getHeight());
		               }
		         });
		      }



}
