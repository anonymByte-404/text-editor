import java.awt.*;
import javax.swing.*;

public class TextEditor {
  private final JFrame frame;
  private final JTextArea textArea;
  private final FileOperations fileOps;
  private final EditActions editActions;
  
  public TextEditor() {
    frame = new JFrame("Java Text Editor");
    textArea = new JTextArea();

    fileOps = new FileOperations(textArea, frame);
    editActions = new EditActions(textArea);

    JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileOps.getFileMenu());
    menuBar.add(editActions.getEditMenu());

    frame.setJMenuBar(menuBar);
    frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

    frame.setSize(600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(TextEditor::new);
  }
}
