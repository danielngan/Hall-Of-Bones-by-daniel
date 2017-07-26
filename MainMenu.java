package GridGUI;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.*;

/**
 * The main menu. For testing purposes only. Replace with CardLayout class as
 * discussed by kevinj22.
 * 
 * @author Pwner
 *
 */
public class MainMenu {
	private static MainMenuView mainMenuView;
	private static String mainMenuViewComponentName;

	private class MainMenuFrame extends JFrame implements ComponentListener {
		public MainMenuFrame(){
			this.addComponentListener(this);
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {
		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		@Override
		public void componentResized(ComponentEvent e) {
			mainMenuView.handleResize();

		}

		@Override
		public void componentShown(ComponentEvent e) {
		}
		
	}

	public static void main (String[] args){
		MainMenu thing = new MainMenu();
		JFrame frame = thing.new MainMenuFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainMenuView = new MainMenuView(new MainMenuController());
		String mainMenuViewComponentName = mainMenuView.getName();
		
		frame.add(mainMenuView);
		frame.setSize(new Dimension(mainMenuView.getWidth(), mainMenuView.getHeight()));
		frame.setVisible(true);
	}

}
