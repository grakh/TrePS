package ru.list.don;

public class TrePS {

	public static void main(String[] args) {
		GUIMain guim = new GUIMain();
		TestForm Obs = new TestForm();
		guim.start();
		guim.addObserver(Obs);
		
		
	}

}
