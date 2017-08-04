package MainMenuMVC;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;

import GridGUI.GameView;
import GridGUI.GameViewPanel;
import HallOfBonesMain.HallOfBonesMain;

/**
 * View for placeholder main menu. Deprecated.
 * @author Kevin
 *
 */
@SuppressWarnings("serial")
public class MainMenuView extends GameViewPanel implements ActionListener, GameView{

    // buttons
    private JButton start;
    private JButton exit;
	private HallOfBonesMain mainJFrame;
	
    public MainMenuView(HallOfBonesMain mainJFrame, Image image) throws IOException
    {
    	super(image);
    	super.setLayout(new GridBagLayout());
    	this.mainJFrame = mainJFrame;
		createMainMenu();
    }

    public void createMainMenu() throws IOException {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        // add
        this.add(start = new JButton("Start"), c);
        start.setActionCommand("start");
        start.addActionListener(this);

        // find
        this.add(exit = new JButton("Exit"), c);
        exit.setActionCommand("exit");
        exit.addActionListener(this);
    }

    public void startView()
    {
    	revalidate();
    	repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
        case "start":
            mainJFrame.switchViews(mainJFrame.mainMenu, "dungeon");
            break;

        case "exit":
        	System.exit(0);
        	break;
        }
    }

}
