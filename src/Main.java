import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Main {

    public static String sponge(String s){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if (Character.isLetter(c)){
                if (i %2 == 0){
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        JFrame window = new JFrame("SpOnGeCASE");
        window.setSize(450,600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(2,1));
        JTextArea inputText = new JTextArea();
        JButton sendButton = new JButton("Convert");
        JButton copyButton = new JButton("Copy");
        JTextArea outputText = new JTextArea();
            outputText.setEditable(false);
            outputText.setBackground(Color.lightGray);
        JPanel convertPanel = new JPanel(new BorderLayout());
        JPanel upperButtonPanel = new JPanel(new GridLayout(1,2));
        JButton clearButton = new JButton("Clear");
            upperButtonPanel.add(clearButton);
            upperButtonPanel.add(sendButton);
            convertPanel.add(inputText, BorderLayout.CENTER);
            convertPanel.add(upperButtonPanel, BorderLayout.SOUTH);
        JPanel copyPanel = new JPanel(new BorderLayout());
            copyPanel.add(outputText, BorderLayout.CENTER);
            copyPanel.add(copyButton, BorderLayout.SOUTH);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputText.setText(sponge(inputText.getText()));
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringselection = new StringSelection(outputText.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringselection, null);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputText.setText("");
                inputText.requestFocus();
            }
        });

        mainPanel.add(convertPanel);
        mainPanel.add(copyPanel);
        inputText.requestFocus();
        window.add(mainPanel);
        window.setVisible(true);

    }
}
