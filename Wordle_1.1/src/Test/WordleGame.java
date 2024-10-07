package Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class WordleGame {
    private List<String> wordList;
    private static final String LOG_FILE = "Wordle\\src\\Test\\guessed_words.log";
    private int wordLength;
    private int maxAttempts;
    private int currentAttempt;
    private String targetWord;
    private boolean gameOver;
    private boolean won;
    private List<String> filteredWords = new ArrayList<>();


    public WordleGame(int wordLength) {
        this.wordLength = wordLength;
        this.maxAttempts = 6;
        this.currentAttempt = 0;
        this.gameOver = false;
        this.won = false;
       
        wordList = WordList.loadWords("https://raw.githubusercontent.com/redbo/scrabble/master/dictionary.txt");
        System.out.println("Loaded words: "+ wordList.size()); // Check all words
        filteredWords();
        targetWord =  selectRandomWord(wordLength).toLowerCase();
        System.out.println("correct word : "+targetWord);
    }

    private void filteredWords(){
        for(String word : wordList){
            if (word.length() == wordLength) {
                filteredWords.add(word.toLowerCase());
            }
        }
        System.out.println("Filtered words : " + filteredWords.size());
    }

    public String selectRandomWord(int wordLength) {
        for (String word : wordList) {
            if (word.length() == wordLength) {
                filteredWords.add(word);
            }
        }

        if (!filteredWords.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(filteredWords.size());
            return filteredWords.get(randomIndex);
        }
        return null; 
    }

    public String[] guess(String word) {

        word = word.toLowerCase();

        if (!isValidWord(word)) {
            System.out.println("Invalid word. Please try again.");
            return null;
        }

        if (word.length() != wordLength || !word.matches("[a-zA-Z]+")) {
            return null;
        }
        
        currentAttempt++;
        String[] result = new String[wordLength];
        
        for (int i = 0; i < wordLength; i++) {
            if (word.charAt(i) == targetWord.charAt(i)) {
                result[i] = "green";
            } else if (targetWord.contains(String.valueOf(word.charAt(i)))) {
                result[i] = " yellow";
            } else {
                result[i] = "gray";
            }
        }
        
        if (word.equals(targetWord)) {
            won = true;
            gameOver = true;
            logGuessedWord(word);
        } else if (currentAttempt >= maxAttempts) {
            gameOver = true;
        }
        
        return result;
    }

    public boolean isValidWord(String word){
        return filteredWords.contains(word.toLowerCase());
    }

    private void logGuessedWord(String word){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(word+"\n");
        } catch (IOException e) {
            System.out.println("Error logging guessed word: " + e.getMessage());
        }
        
    }
    
    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();
        state.put("attemptsLeft", maxAttempts - currentAttempt);
        state.put("gameOver", gameOver);
        state.put("won", won);
        state.put("targetWord", gameOver ? targetWord : null);
        return state;
    }
    
    public int getWordLength() {
        return wordLength;
    }
}