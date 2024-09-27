package Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WordleGUI extends JFrame {
    private WordleGame game;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel keyboardPanel;
    private JLabel[][] cells;
    private Map<Character, JButton> keyButtons;
    private int currentRow;
    private int currentCol;
    
    public WordleGUI() {
        setTitle("Word Guesses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 800));
        
        mainPanel = new JPanel(new CardLayout());
        setContentPane(mainPanel);
        
        createModeSelector();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void createModeSelector() {
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        modePanel.setBackground(new Color(255, 245, 238)); // Light peach background
        
        JLabel titleLabel = new JLabel("Word Guesses");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel instructionLabel = new JLabel("You have 5 chances to guess");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel modeLabel = new JLabel("There 3 modes ; Easy, Normal, Hard");
        modeLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        modePanel.add(Box.createVerticalGlue());
        modePanel.add(titleLabel);
        modePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        modePanel.add(instructionLabel);
        modePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        modePanel.add(modeLabel);
        modePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        String[] modes = {"Easy", "Normal", "Hard"};
        Color[] colors = {new Color(144, 238, 144), new Color(255, 255, 224), new Color(255, 99, 71)};
        
        for (int i = 0; i < modes.length; i++) {
            JButton modeButton = new JButton(modes[i]);
            modeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            modeButton.setMaximumSize(new Dimension(200, 50));
            modeButton.setFont(new Font("Arial", Font.BOLD, 30));
            modeButton.setBackground(colors[i]);
            modeButton.setFocusPainted(false);
            int wordLength = (i == 0) ? 5 : (i == 1) ? 6 : 7;
            modeButton.addActionListener(e -> startGame(wordLength));
            modePanel.add(modeButton);
            modePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        modePanel.add(Box.createVerticalGlue());
        mainPanel.add(modePanel, "ModeSelector");
    }
    
    private void startGame(int wordLength) {
        game = new WordleGame(wordLength);
        createGameBoard();
        createKeyboard();
        
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(boardPanel, BorderLayout.CENTER);
        gamePanel.add(keyboardPanel, BorderLayout.SOUTH);
        
        mainPanel.add(gamePanel, "GameBoard");
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "GameBoard");
        
        currentRow = 0;
        currentCol = 0;
    }
    
    private void createGameBoard() {
        boardPanel = new JPanel(new GridLayout(6, game.getWordLength(), 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cells = new JLabel[6][game.getWordLength()];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < game.getWordLength(); j++) {
                cells[i][j] = new JLabel();
                cells[i][j].setPreferredSize(new Dimension(50, 50));
                cells[i][j].setOpaque(true);
                cells[i][j].setBackground(Color.WHITE);
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                cells[i][j].setHorizontalAlignment(JLabel.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                boardPanel.add(cells[i][j]);
            }
        }
    }
    
    private void createKeyboard() {
        keyboardPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        keyboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        keyButtons = new HashMap<>();
        String[] rows = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        
        for (int i = 0; i < rows.length; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
            
            if (i == 2) {
                JButton enterButton = createSpecialButton("ENTER");
                enterButton.addActionListener(e -> submitGuess());
                rowPanel.add(enterButton);
            }
            
            for (char c : rows[i].toCharArray()) {
                JButton button = createLetterButton(String.valueOf(c));
                button.addActionListener(e -> keyPress(c));
                rowPanel.add(button);
                keyButtons.put(c, button);
            }
            
            if (i == 2) {
                JButton backspaceButton = createSpecialButton("Backspace");
                backspaceButton.addActionListener(e -> backspace());
                rowPanel.add(backspaceButton);
            }
            
            keyboardPanel.add(rowPanel);
        }
    }

    private JButton createLetterButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setMargin(new Insets(8, 12, 8, 12));
        button.setBackground(new Color(211, 214, 218));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(60, 70));
        return button;
    }

    private JButton createSpecialButton(String text) {
        JButton button = createLetterButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setPreferredSize(new Dimension(75, 65));
        return button;
    }
    
    private void keyPress(char c) {
        if (currentCol < game.getWordLength() && !game.getGameState().get("gameOver").equals(true)) {
            cells[currentRow][currentCol].setText(String.valueOf(c));
            currentCol++;
        }
    }
    
    private void backspace() {
        if (currentCol > 0 && !game.getGameState().get("gameOver").equals(true)) {
            currentCol--;
            cells[currentRow][currentCol].setText("");
        }
    }
    
    private void submitGuess() {
        if (currentCol == game.getWordLength() && !game.getGameState().get("gameOver").equals(true)) {
            StringBuilder guess = new StringBuilder();
            for (int i = 0; i < game.getWordLength(); i++) {
                guess.append(cells[currentRow][i].getText().toLowerCase());
            }
            
            String[] result = game.guess(guess.toString());
            
            if (result != null) {
                updateBoard(result);
                updateKeyboard(guess.toString(), result);
                currentRow++;
                currentCol = 0;
                
                Map<String, Object> gameState = game.getGameState();
                if (gameState.get("gameOver").equals(true)) {
                    endGame(gameState);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid word.", "Invalid Word", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateBoard(String[] result) {
        for (int i = 0; i < result.length; i++) {
            cells[currentRow][i].setOpaque(true);
            switch (result[i]) {
                case "green":
                    cells[currentRow][i].setBackground(new Color(106, 170, 100));
                    break;
                case "yellow":
                    cells[currentRow][i].setBackground(new Color(201, 180, 88));
                    break;
                default:
                    cells[currentRow][i].setBackground(new Color(120, 124, 126));
            }
            cells[currentRow][i].setForeground(Color.WHITE);
        }
    }
    
    private void updateKeyboard(String guess, String[] result) {
        for (int i = 0; i < guess.length(); i++) {
            char c = Character.toUpperCase(guess.charAt(i));
            JButton button = keyButtons.get(c);
            Color currentColor = button.getBackground();
            Color newColor = getColorForResult(result[i]);
            if (shouldUpdateColor(currentColor, newColor)) {
                button.setBackground(newColor);
                button.setForeground(Color.WHITE);
            }
        }
    }
    
    private Color getColorForResult(String result) {
        switch (result) {
            case "green":
                return new Color(106, 170, 100);
            case "yellow":
                return new Color(201, 180, 88);
            default:
                return new Color(120, 124, 126);
        }
    }
    
    private boolean shouldUpdateColor(Color currentColor, Color newColor) {
        if (currentColor.equals(new Color(106, 170, 100))) return false;
        if (currentColor.equals(new Color(201, 180, 88)) && newColor.equals(new Color(120, 124, 126))) return false;
        return true;
    }
    
    private void endGame(Map<String, Object> gameState) {
        String message;
        if (gameState.get("won").equals(true)) {
            message = "Congratulations! You guessed the word: " + gameState.get("targetWord");
        } else {
            message = "Game Over. The word was: " + gameState.get("targetWord");
        }
        JOptionPane.showMessageDialog(this, message);
        
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "ModeSelector");
        } else {
            System.exit(0);
        }
    }
}