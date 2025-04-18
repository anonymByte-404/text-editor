import java.io.*;
import javax.swing.*;

public class FileOperations {
  private final JTextArea textArea;
  private final JFrame frame;
  private final JFileChooser fileChooser;

  public FileOperations(JTextArea textArea, JFrame frame) {
    this.textArea = textArea;
    this.frame = frame;
    this.fileChooser = new JFileChooser();
  }

  public JMenu getFileMenu() {
    JMenu fileMenu = new JMenu("File");

    JMenuItem openItem = new JMenuItem("Open");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenuItem newFileItem = new JMenuItem("New File");
    JMenuItem exitItem = new JMenuItem("Exit");

    openItem.addActionListener(e -> openFile());
    saveItem.addActionListener(e -> saveFile());
    newFileItem.addActionListener(e -> newFile());
    exitItem.addActionListener(e -> System.exit(0));

    fileMenu.add(openItem);
    fileMenu.add(saveItem);
    fileMenu.add(newFileItem);
    fileMenu.add(exitItem);

    return fileMenu;
  }

  private void openFile() {
    int returnValue = fileChooser.showOpenDialog(frame);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        textArea.read(reader, null);
      } catch (IOException ex) {
        showError("Error opening file!", ex);
      }
    }
  }

  private void saveFile() {
    int returnValue = fileChooser.showSaveDialog(frame);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      file = ensureFileExtension(file);

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        textArea.write(writer);
      } catch (IOException ex) {
        showError("Error saving file!", ex);
      }
    }
  }

  private void newFile() {
    int returnValue = fileChooser.showSaveDialog(frame);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      file = ensureFileExtension(file);

      String newFileName = JOptionPane.showInputDialog(frame, "Enter a new name for the file:", file.getName());
      if (newFileName != null && !newFileName.trim().isEmpty()) {
        file = new File(file.getParent(), newFileName);
      }

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        textArea.write(writer);
      } catch (IOException ex) {
        showError("Error saving new file!", ex);
      }
    }
  }

  private File ensureFileExtension(File file) {
    if (!file.getName().contains(".")) {
      int option = JOptionPane.showConfirmDialog(frame,
        "The file doesn't have an extension. Do you want to add a '.txt' extension?",
        "No Extension Warning", JOptionPane.YES_NO_OPTION);

      if (option == JOptionPane.YES_OPTION) {
        file = new File(file.getAbsolutePath() + ".txt");
      }
    }
    return file;
  }

  private void showError(String message, Exception ex) {
    JOptionPane.showMessageDialog(frame, message + "\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
}
