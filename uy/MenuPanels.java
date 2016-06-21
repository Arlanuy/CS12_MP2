package uy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanels extends JMenuBar {

   String[ ] gameControlItems = new String[ ] { "New Game", "Save Game"};
   String[ ] settingsItems = new String[ ] { "Change Background"};

   public MenuPanels(SolitaireGUI game) {

      JMenu gameControlsMenu = new JMenu("Game Controls");
      JMenu userManualMenu = new JMenu("User Manual");
      JMenu settings = new JMenu("Settings");
      JMenu otherMenu = new JMenu("Other");
      JMenu subMenu = new JMenu("SubMenu");
      JMenu subMenu2 = new JMenu("SubMenu2");
      ActionListener printListener = new ActionListener(  ) {
         public void actionPerformed(ActionEvent event) {
           String command = event.getActionCommand();
           System.out.println(command);
           if (command.equals("New Game")) {
             JOptionPane joker = new JOptionPane();
             joker.showMessageDialog(null, "Nice");
           }

           else if (command.equals("Save Game")) {
             JOptionPane joker = new JOptionPane();
             joker.showMessageDialog(null, "Nice");
           }

           else if (command.equals("Read Manual")) {
               JOptionPane joker = new JOptionPane();
               joker.showMessageDialog(null, "Nice2");
           }
         }
      };

    // Assemble the File menus and User Manual menus with keyboard accelerators.
      for (int i=0; i < gameControlItems.length; i++) {
         JMenuItem item = new JMenuItem(gameControlItems[i]);
         item.addActionListener(printListener);
         gameControlsMenu.add(item);
        gameControlsMenu.insertSeparator(i + 1);
      }


      JMenuItem manual = new JMenuItem("Read Manual");
      manual.addActionListener(printListener);
      userManualMenu.add(manual);

      for (int i=0; i < settingsItems.length; i++) {
         JMenuItem item = new JMenuItem(settingsItems[i]);
         item.addActionListener(printListener);
         settings.add(item);
        gameControlsMenu.insertSeparator(i + 1);
      }

      // Assemble the submenus of the Other menu.
      JMenuItem item;
      subMenu2.add(item = new JMenuItem("Extra 2"));
      item.addActionListener(printListener);
      subMenu.add(item = new JMenuItem("Extra 1"));
      item.addActionListener(printListener);
      subMenu.add(subMenu2);

      // Assemble the Other menu itself.
      otherMenu.add(subMenu);
      otherMenu.add(item = new JCheckBoxMenuItem("Check Me"));
      item.addActionListener(printListener);
      otherMenu.addSeparator(  );
      ButtonGroup buttonGroup = new ButtonGroup(  );
      otherMenu.add(item = new JRadioButtonMenuItem("Radio 1"));
      item.addActionListener(printListener);
      buttonGroup.add(item);
      otherMenu.add(item = new JRadioButtonMenuItem("Radio 2"));
      item.addActionListener(printListener);
      buttonGroup.add(item);
      otherMenu.addSeparator(  );
      otherMenu.add(item = new JMenuItem("Potted Plant",
                           new ImageIcon("../graphics/Puzzle_Day_2.png")));
      item.addActionListener(printListener);
      gameControlsMenu.setToolTipText("Wow ang galing");
      // Finally, add all the menus to the menu bar.
      add(gameControlsMenu);
      add(userManualMenu);
      add(settings);
      add(otherMenu);

   }
}
