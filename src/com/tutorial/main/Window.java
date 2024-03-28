package com.tutorial.main;
import java.awt.Canvas; // Use Canvas import (line 4)
import java.awt.Dimension;

import javax.swing.JFrame;

// Class for creating the window for game
public class Window extends Canvas{ // Inherit Canvas Class
	
	//Generate a serial version UID
	private static final long serialVersionUID = -240840600533728354L;
	
	// Window Constructor
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title); // Frame of the window
		
		frame.setPreferredSize(new Dimension(width,height)); // Set window size
		frame.setMaximumSize(new Dimension(width,height)); // Maximum size of window
		frame.setMinimumSize(new Dimension(width,height)); // Minimum size of window
		
		// Set to close when clicking x button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // Makes the window unresizable
		frame.setLocationRelativeTo(null); // Sets the window to center
		frame.add(game); // Add the game object from our Game class
		frame.setVisible(true); // Make the window visible
		game.start();
	}
}
