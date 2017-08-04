package mainMenu;

import GridGUI.GameView;
import masterControl.MasterFrame;

/**
 * Testbed for the main menu. Main menu uses HallofBonesMain in production.
 * @author alanyork
 *
 */
public class MainMenu implements GameView {
	private static MainMenuView mainMenuView = new MainMenuView(new MainMenuController());
	
	public MainMenu(){
		MasterFrame.getInstance().setView(mainMenuView);
		System.out.println("Test");
	}
	
	public MainMenuView getMainMenuView(){
		return mainMenuView;
	}
	
	public static void main (String[] args){
		new MainMenu();
	}

	@Override
	public void startView() {
		// TODO Auto-generated method stub
		
	}

}
