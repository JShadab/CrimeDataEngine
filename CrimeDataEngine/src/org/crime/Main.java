package org.crime;

import javax.swing.UIManager;

import org.crime.ui.GUIHandler;

public class Main {

	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new GUIHandler();

	}

}
