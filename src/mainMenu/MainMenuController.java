package mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the main menu. 
 * @author alanyork
 *
 */
public class MainMenuController implements ActionListener {
	private MainMenuView view;
	
	public MainMenuController(){}

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
				System.out.print("Instructions from MainMenu");
				break;
			case MainMenuView.CREDITS_BUTTON_TEXT:
				System.out.print("Credits from MainMenu");
				break;
			case MainMenuView.EXIT_BUTTON_TEXT:
				System.out.print("Exit from MainMenu");
				System.exit(0);
				break;
		}
	}

}
