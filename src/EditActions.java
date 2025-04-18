import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionListener;

public class EditActions {
  private final JTextArea textArea;
  private final UndoManager undoManager;

  public EditActions(JTextArea textArea) {
    this.textArea = textArea;
    this.undoManager = new UndoManager();
    textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
  }

  public JMenu getEditMenu() {
    JMenu editMenu = new JMenu("Edit");

    JMenuItem cutItem = createMenuItem("Cut", e -> textArea.cut());
    JMenuItem copyItem = createMenuItem("Copy", e -> textArea.copy());
    JMenuItem pasteItem = createMenuItem("Paste", e -> textArea.paste());
    JMenuItem undoItem = createMenuItem("Undo", e -> undoAction());
    JMenuItem redoItem = createMenuItem("Redo", e -> redoAction());

    editMenu.add(cutItem);
    editMenu.add(copyItem);
    editMenu.add(pasteItem);
    editMenu.add(undoItem);
    editMenu.add(redoItem);

    return editMenu;
  }

  private JMenuItem createMenuItem(String text, ActionListener actionListener) {
    JMenuItem menuItem = new JMenuItem(text);
    menuItem.addActionListener(actionListener);
    return menuItem;
  }

  private void undoAction() {
    if (undoManager.canUndo()) {
      undoManager.undo();
    }
  }

  private void redoAction() {
    if (undoManager.canRedo()) {
      undoManager.redo();
    }
  }
}
