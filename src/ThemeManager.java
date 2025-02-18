import java.awt.*;
import javax.swing.*;

public class ThemeManager {
  private final JTextArea textArea;
  private final JFrame frame;
  private boolean darkMode = false;

  public ThemeManager(JTextArea textArea, JFrame frame) {
    this.textArea = textArea;
    this.frame = frame;
  }

  public JMenu getThemeMenu() {
    JMenu themeMenu = new JMenu("Theme");
    JMenuItem toggleThemeItem = new JMenuItem("Toggle Dark Mode");

    toggleThemeItem.addActionListener(e -> toggleDarkMode());

    themeMenu.add(toggleThemeItem);
    return themeMenu;
  }

  private void toggleDarkMode() {
    if (darkMode) {
      textArea.setBackground(Color.WHITE);
      textArea.setForeground(Color.BLACK);
      frame.getContentPane().setBackground(Color.LIGHT_GRAY);
    } else {
      textArea.setBackground(Color.BLACK);
      textArea.setForeground(Color.WHITE);
      frame.getContentPane().setBackground(Color.DARK_GRAY);
    }
    darkMode = !darkMode;
  }
}
