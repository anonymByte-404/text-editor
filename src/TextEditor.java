import javax.swing.*;
import java.awt.*;

public class TextEditor {
  private final JFrame frame;
  private final JTextArea textArea;

  public TextEditor() {
    frame = new JFrame("Text Editor");
    textArea = new JTextArea();

    setupMenuBar();
    setupTextArea();
    setupFrame();
  }

  private void setupMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(new FileOperations(textArea, frame).getFileMenu());
    menuBar.add(new EditActions(textArea).getEditMenu());
    menuBar.add(new ThemeManager(textArea, frame).getThemeMenu());
    frame.setJMenuBar(menuBar);
  }

  private void setupTextArea() {
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
  }

  private void setupFrame() {
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(TextEditor::new);
  }
}
