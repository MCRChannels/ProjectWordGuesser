package Test;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new WordleGame(5); 
            new WordleGUI();
        });
    }
} 