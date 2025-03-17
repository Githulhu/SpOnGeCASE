import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        window.setSize(300,300);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(4,1));

        JPanel inputPanel = new JPanel();

        JPanel outputPanel = new JPanel();

        JTextArea inputText = new JTextArea("input");
        JButton sendButton = new JButton("Convert");
        JButton copyButton = new JButton("Copy");
        JTextArea outputText = new JTextArea("output");
            outputText.setEditable(false);



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

        mainPanel.add(inputText);
        mainPanel.add(sendButton);
        mainPanel.add(outputText);
        mainPanel.add(copyButton);


        window.add(mainPanel);
        window.setVisible(true);

    }
}
