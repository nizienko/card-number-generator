package gui;

import proc.Variables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * User: def
 * Date: 2/17/14
 * Time: 10:02 PM
 */
public class Gui extends JFrame {
    private JPanel mainPanel;
    private JPanel historyPanel;
    private JPanel topPanel;
    private JFrame history;

    private JTextField digCount;
    private JTextField bankCard;
    private JTextArea historyText;
    private JComboBox<String> bin;
    private MyButton generate;
    private MyButton show;

    private String cardBins[];
    private Variables vars;

    public Gui(ActionListener generateAction, ActionListener showAction) {
        vars = new Variables();
        mainPanel = new JPanel();
        historyPanel = new JPanel();
        digCount = new JTextField("16");
        historyText = new JTextArea();
        JScrollPane scrollV = new JScrollPane (historyText);
        scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cardBins = vars.getCardBins();
        bin = new JComboBox<String>(cardBins);
        bin.setEditable(true);
        bankCard = new JTextField("");
        generate = new MyButton(generateAction, "generate & copy");
        show = new MyButton(showAction, "h");
        history = new JFrame();
        historyPanel.setLayout(new GridLayout(1,1));
        historyPanel.add(scrollV);
        history.setContentPane(historyPanel);
        mainPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));
        topPanel.setLayout(new GridLayout(1,3));
        topPanel.add(digCount);
        topPanel.add(bin);
        topPanel.add(show);
        mainPanel.add(topPanel);
        mainPanel.add(bankCard);
        mainPanel.add(generate);
        this.setTitle("card generator");
        history.setTitle("numbers");
        this.setLocation(500, 300);
        history.setLocation(691, 300);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        history.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setPreferredSize(new Dimension(250, 100));
        history.setPreferredSize(new Dimension(170, 300));
        this.pack();
        history.pack();
        this.setVisible(true);
        history.setVisible(false);

    }
    public Integer getCount(){
        try {
            return Integer.parseInt(digCount.getText());
        }
        catch (NumberFormatException e){
            this.printCard("count error");
            return 0;
        }
    }
    public String getBin(){
        if (checkBin(bin.getSelectedItem().toString())){
            return bin.getSelectedItem().toString();
        }
        else {
            return "";
        }

    }

    public void printCard(String cardNumber){
        bankCard.setText(cardNumber);
    }

    private class MyButton extends JButton{
        public MyButton(ActionListener myListener, String text){
            this.setText(text);
            this.addActionListener(myListener);
        }
    }

    private Boolean checkBin(String bin){
        try {
            for (int i=0; i < bin.length(); i++){
                Integer.parseInt(bin.substring(i, i+1));
            }
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public void showHistory(){
        if (history.isVisible()) {
            history.setVisible(false);
        }
        else {
            history.setVisible(true);
            history.setLocation(this.getLocation().x + 251, this.getLocation().y);
        }
    }
    public void addCardNumber(String card){
        historyText.setText(historyText.getText() + "\n" + card);
    }
}
