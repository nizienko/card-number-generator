package proc;

import gui.Gui;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * User: def
 * Date: 2/17/14
 * Time: 10:00 PM
 */
public class Core {

    private static Gui gui;
    public static void main(String[] args) {

        ActionListener generateAction = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                generate();
            }
        };

        ActionListener showAction = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                show();
            }

        };

        gui = new Gui(generateAction, showAction);

    }

    private static void show(){
        gui.showHistory();
    }

    private static void generate() {
        Integer countDigest = gui.getCount();
        String bin = gui.getBin();
        if (countDigest >= 12) {
            Random random = new Random();
            Integer i;
            Integer sum = 0;
            Integer n;
            Integer checkSum = 0;
            String cardNumber = "";
            for (i = 1; i < countDigest; i++){
                if (i > bin.length()) {
                    n =  Math.abs(random.nextInt()) % 10;
                }
                else {
                    try{
                        n = Integer.parseInt(bin.substring(i-1,i));
                    }
                    catch (Exception e) {
                        n=0;
                    }
                }
                cardNumber = cardNumber + n;
                if (!isOdd(countDigest-i)) {
                    n = n * 2;
                    if (n >= 10) {
                        Integer sumN = 0;
                        do
                            sumN = sumN + (n % 10);
                        while((n/=10) != 0);
                        n = sumN;
                    }
                }
                sum = sum + n;

            }
            if (sum % 10 != 0) {
                checkSum = 10 - sum % 10;
            }
            cardNumber = cardNumber + checkSum;
            gui.printCard(cardNumber);
            gui.addCardNumber(cardNumber);
            copyToClipboard(cardNumber);
        }
    }
    private static boolean isOdd(Integer n){
        return (n % 2) == 0;
    }

    private static void copyToClipboard(String cardNumber){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(cardNumber);
        clipboard.setContents(strSel, null);
    }
}
