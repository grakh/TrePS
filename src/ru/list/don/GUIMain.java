package ru.list.don;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.Border;

public class GUIMain extends Observable{
	
	JFrame mainForm;
	JPanel buttonPanel, fieldPanel, statusPanel;
	JMenuBar menuBar;
	JButton buttonOpen, buttonRun, buttonExit;
	int counter;
	JLabel labelNamber, labelKontrAgent, labelPhotoPolimer, labelRoute, labelLineatura, labelCountPoliner;
	JLabel status, running;
	JTextField okStatus;
	public JTextField namber, kontrAgent, photoPolimer, route, lineatura, countPoliner;
	String[] infoLine;
    JProgressBar progressBar;
	
	public void start() {
		
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
		
		mainForm.setLocationRelativeTo(null);
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS)); 
		fieldPanel.setLayout(new GridBagLayout()); 
		buttonPanel.setLayout(new GridBagLayout()); 
		GridBagConstraints cButton = new GridBagConstraints();
		GridBagConstraints cField = new GridBagConstraints();
		// ����������� ������, ������������ ������ 
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cField.fill = GridBagConstraints.HORIZONTAL;
		cField.fill = GridBagConstraints.NORTH;

		//buttonCenterPanel.setLayout(new BoxLayout(buttonCenterPanel, BoxLayout.Y_AXIS));
		cField.weighty = 0.5;
		cButton.weighty = 0.5;
        
		buttonOpen = new JButton("Open");
		buttonOpen.addActionListener(new FileOpen());
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.insets = new Insets(30, 10, 0, 10);  // ��������� �������� 
		cButton.ipady = 20;       // ������� ��� ������ �������  
		cButton.weighty = 0;
		cButton.gridx = 0; 
		cButton.gridy = 0; 
		buttonPanel.add(buttonOpen, cButton);
		
		buttonRun = new JButton("Run");
		buttonRun.addActionListener(new FileRun());
		cButton.insets = new Insets(50, 10, 0, 10);  // ��������� �������� 
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.ipady = 10;       // ���������� �������������� ������ ������ 
		cButton.weighty = 0;
        //c.gridx = 0; 
		cButton.gridy = 1; 
		buttonPanel.add(buttonRun, cButton);
		
		
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new FileExit());
		cButton.fill = GridBagConstraints.HORIZONTAL;
		cButton.ipady = 0;       // ���������� �������������� ������ ������ 
		cButton.weighty = 1.0;   // ���������� ������ 
		cButton.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cButton.insets = new Insets(10, 10, 10, 10);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cButton.gridwidth = 1;   // ���������� � 1 ������� 
		cButton.gridy = 2;       // � 3 ������� 
		buttonPanel.add(buttonExit, cButton);
		//buttonPanel.add(BorderLayout.CENTER, buttonCenterPanel);
		
		
		
		labelNamber = new JLabel("Order namber:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(30, 0, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 2;   // ���������� � 1 ������� 
		cField.gridy = 1;       // � 3 ������� 
		fieldPanel.add(labelNamber, cField);
		
		namber = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(30, 10, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 3;   // ���������� � 1 ������� 
		cField.gridy = 1;       // � 3 ������� 
		fieldPanel.add(namber, cField);
		
		labelKontrAgent = new JLabel("Contractor:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 0, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 2;   // ���������� � 1 ������� 
		cField.gridy = 2;       // � 3 ������� 
		fieldPanel.add(labelKontrAgent, cField);
		
		kontrAgent = new JTextField("", 20);
		cField.fill = GridBagConstraints.NORTH;
		//cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 10, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 3;   // ���������� � 1 ������� 
		cField.gridy = 2;       // � 3 ������� 
		fieldPanel.add(kontrAgent, cField);
		
		labelPhotoPolimer = new JLabel("Type polimer:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 0, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 2;   // ���������� � 1 ������� 
		cField.gridy = 3;       // � 3 ������� 
		fieldPanel.add(labelPhotoPolimer, cField);
		
		photoPolimer = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 10, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 3;   // ���������� � 1 ������� 
		cField.gridy = 3;       // � 3 ������� 
		fieldPanel.add(photoPolimer, cField);
		
		labelRoute = new JLabel("Route Polimer:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 0, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 2;   // ���������� � 1 ������� 
		cField.gridy = 4;       // � 3 ������� 
		fieldPanel.add(labelRoute, cField);
		
		route = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 10, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 3;   // ���������� � 1 ������� 
		cField.gridy = 4;       // � 3 ������� 
		fieldPanel.add(route, cField);
		
		labelLineatura = new JLabel("Lineatura:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 0, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 2;   // ���������� � 1 ������� 
		cField.gridy = 5;       // � 3 ������� 
		fieldPanel.add(labelLineatura, cField);
		
		lineatura = new JTextField("", 20);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 10, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 3;   // ���������� � 1 ������� 
		cField.gridy = 5;       // � 3 ������� 
		fieldPanel.add(lineatura, cField);
		
		labelCountPoliner = new JLabel("Quantity:");
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 0, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 2;   // ���������� � 1 ������� 
		cField.gridy = 6;       // � 3 ������� 
		fieldPanel.add(labelCountPoliner, cField);
		
		countPoliner = new JTextField("0", 10);
		countPoliner.setEditable(false);
		cField.fill = GridBagConstraints.HORIZONTAL; 
		cField.anchor = GridBagConstraints.NORTH;
		cField.ipady = 0;       // ���������� �������������� ������ ������ 
		cField.weighty = 1.0;   // ���������� ������ 
		//cField.anchor = GridBagConstraints.SOUTH; // ���������� ������ ���� ���� 
		cField.insets = new Insets(0, 10, 0, 0);  // ��������� �������� 
        //c.gridx = 1;       // ��������� ��������� �� Button 2 
		cField.gridwidth = 3;   // ���������� � 1 ������� 
		cField.gridy = 6;       // � 3 ������� 
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
		    
		    
		    notifyObservers(lineatura);
		    
		    //Border border = BorderFactory.createTitledBorder("Reading...");
		    //progressBar.setBorder(border);
			statusPanel.add(progressBar);
		statusPanel.setBorder(BorderFactory.createEmptyBorder(15,10,5,10));
		//buttonPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		fieldPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainForm.getContentPane().add(BorderLayout.WEST, buttonPanel);
		mainForm.getContentPane().add(BorderLayout.CENTER, fieldPanel);
		mainForm.getContentPane().add(BorderLayout.SOUTH, statusPanel);
		//new TestForm().start(countPoliner); 
		
		mainForm.setBounds(300, 150, 600, 400);
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
			infoLine = new OpenPS().openDialog();
			namber.setText(infoLine[1]);
			kontrAgent.setText(infoLine[0]);
			photoPolimer.setText(infoLine[2]);
			route.setText(infoLine[4]);
			lineatura.setText(infoLine[5]);
			countPoliner.setText(infoLine[10]);
			}
		
		}
	
	class FileRun implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Run");
			okStatus.setText(photoPolimer.getText()+"_"+kontrAgent.getText().substring(0, kontrAgent.getText().indexOf(" "))+"_"+namber.getText());
			
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
			System.out.println("Path");
			
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
			System.out.println("About");
			
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
