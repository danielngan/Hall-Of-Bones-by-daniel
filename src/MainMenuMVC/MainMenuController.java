package MainMenuMVC;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import GridGUI.GameViewPanel;

/**
 * Controller for placeholder main menu. Deprecated.
 * @author Kevin
 *
 */
public class MainMenuController {
	
	private MainMenuView view;
	
	public MainMenuController(MainMenuView view)
	{
		this.view = view;
	}
	
   private JPanel signalCreateMainMenu() throws IOException {
	   view.createMainMenu();
    	String filename = "/FFIV_PSP_Final_Dungeon_Background_2.png";
 		GameViewPanel mainMenu = new GameViewPanel(ImageIO.read(this.getClass().getResource(filename)));
 		mainMenu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        // add
        mainMenu.add(start = new JButton("Start"), c);
        start.setActionCommand("start");
        start.addActionListener(this);

        // find
        mainMenu.add(exit = new JButton("Exit"), c);
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        this.add(mainMenu);
        return mainMenu;
    }
}
