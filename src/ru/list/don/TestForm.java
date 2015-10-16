package ru.list.don;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestForm implements Observer {
	JTextField frame = new JTextField();

	void start(JTextField field){
		field.setText("Данные");

		//JOptionPane.showMessageDialog(null, "sfbvsfb");
	}

	
	@Override
	public void update(Observable o, Object arg) {
		frame = (JTextField)arg;

		System.out.println("Observer job: "+frame.getName());
	}
	

}
