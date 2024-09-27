package Test;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordleGame game = new WordleGame(5); // Start with Easy mode (5 letters)
            new WordleGUI();
        });
    }
}