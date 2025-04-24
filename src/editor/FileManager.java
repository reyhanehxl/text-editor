package editor;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try{
                Scanner scn = new Scanner(file);
                String content = "";
                while(scn.hasNextLine()){
                    content += scn.nextLine() + "\n";
                }
                scn.close();
                textArea.setText(content);
                textEditor.currentFile = file;
                textEditor.setTitle(file.getName());
            }catch (Exception e){
                JOptionPane.showMessageDialog(textEditor, "cannot open the file" + e.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.write(textArea.getText());
                    writer.close();
                    textEditor.currentFile = file;
                    textEditor.setTitle(file.getName());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(textEditor, "Error saving the file: " + e.getMessage());
                }
            }
        }
        else{
            try {
            PrintWriter writer = new PrintWriter(textEditor.currentFile);
            writer.write(textArea.getText());
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(textEditor, "Error saving the file: " + e.getMessage());
        }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
        textEditor.setTitle("Untitled");
    }
}
