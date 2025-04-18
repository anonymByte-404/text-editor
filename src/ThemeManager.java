import java.awt.*;
import javax.swing.*;

public class ThemeManager {
  private final JTextArea textArea;
  private final JFrame frame;
  private boolean darkMode = false;

  private static final Color DARK_BACKGROUND = Color.BLACK;
  private static final Color LIGHT_BACKGROUND = Color.WHITE;
  private static final Color DARK_TEXT = Color.WHITE;
  private static final Color LIGHT_TEXT = Color.BLACK;
  private static final Color DARK_FRAME = Color.DARK_GRAY;
  private static final Color LIGHT_FRAME = Color.LIGHT_GRAY;

  public ThemeManager(JTextArea textArea, JFrame frame) {
    this.textArea = textArea;
    this.frame = frame;
  }

  public JMenu getThemeMenu() {
    JMenu themeMenu = new JMenu("Theme");
    JMenuItem toggleThemeItem = new JMenuItem("Toggle Dark Mode");

    toggleThemeItem.addActionListener(e -> toggleDarkMode(toggleThemeItem));

    themeMenu.add(toggleThemeItem);
    return themeMenu;
  }

  private void toggleDarkMode(JMenuItem toggleThemeItem) {
    if (darkMode) {
      applyLightTheme();
      toggleThemeItem.setText("Toggle Dark Mode");
    } else {
      applyDarkTheme();
      toggleThemeItem.setText("Toggle Light Mode");
    }
    darkMode = !darkMode;
  }

  private void applyDarkTheme() {
    textArea.setBackground(DARK_BACKGROUND);
    textArea.setForeground(DARK_TEXT);
    frame.getContentPane().setBackground(DARK_FRAME);
  }

  private void applyLightTheme() {
    textArea.setBackground(LIGHT_BACKGROUND);
    textArea.setForeground(LIGHT_TEXT);
    frame.getContentPane().setBackground(LIGHT_FRAME);
  }
}
