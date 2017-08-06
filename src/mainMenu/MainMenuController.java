package mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Controller for the main menu. 
 * @author alanyork and Yi Ngan
 *
 */
public class MainMenuController implements ActionListener {
	private MainMenuView view;
	
	public MainMenuController(){
		System.out.println("Create MainMenuController");
	}

	public void setView(MainMenuView view){
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getActionCommand()) {
			case MainMenuView.PLAY_BUTTON_TEXT:
				view.getMainFrame().switchViews(view.getMainFrame().mainMenuA, "dungeon");
				System.out.print("Dungeon from MainMenu");
				break;
			case MainMenuView.INSTRUCTIONS_BUTTON_TEXT:
				InformationDialogues.showInstructions(view);
				break;
			case MainMenuView.CREDITS_BUTTON_TEXT:
				InformationDialogues.showCredits(view);
				break;
			case MainMenuView.EXIT_BUTTON_TEXT:
				System.out.print("Exit from MainMenu");
				System.exit(0);
				break;
		}
	}

}
