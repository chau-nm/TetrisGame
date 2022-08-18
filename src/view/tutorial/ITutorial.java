package view.tutorial;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import view.high_score.IView;

public interface ITutorial extends IView {

	void show();
	
	void displayTitle();

	void displayCancleButton();

	void doActionCancleButton();
}
