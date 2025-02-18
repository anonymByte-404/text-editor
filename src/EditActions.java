import javax.swing.*;
import javax.swing.undo.UndoManager;

public class EditActions {
  private final JTextArea textArea;
  private UndoManager undoManager;

  public EditActions(JTextArea textArea) {
    this.textArea = textArea;
    this.undoManager = new UndoManager();
    textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
  }

  public JMenu getEditMenu() {
    JMenu editMenu = new JMenu("Edit");

    JMenuItem cutItem = new JMenuItem("Cut");
    JMenuItem copyItem = new JMenuItem("Copy");
    JMenuItem pasteItem = new JMenuItem("Paste");
    JMenuItem undoItem = new JMenuItem("Undo");
    JMenuItem redoItem = new JMenuItem("Redo");

    cutItem.addActionListener(e -> textArea.cut());
    copyItem.addActionListener(e -> textArea.copy());
    pasteItem.addActionListener(e -> textArea.paste());

    undoItem.addActionListener(e -> {
      if (undoManager.canUndo()) undoManager.undo();
    });

    redoItem.addActionListener(e -> {
      if (undoManager.canRedo()) undoManager.redo();
    });

    editMenu.add(cutItem);
    editMenu.add(copyItem);
    editMenu.add(pasteItem);
    editMenu.add(undoItem);
    editMenu.add(redoItem);

    return editMenu;
  }
}
