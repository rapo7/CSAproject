package com.project;

import javax.swing.*;

/**
 * The main class is the entry point to the project.
 * has initialisation of MainScreen class
 */
public class Main {
	public static void main(String[] args) {
		        SwingUtilities.invokeLater(MainScreen::new);
	}

}
