package Test;

import java.util.Scanner;

public class Wordle_CheckLogic {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int attemps = 0;
      String correct = "SHAKE";   // word
      boolean check = true;

      while (true) {
          System.out.print("\nYour text : ");
          String word = sc.nextLine();
          attemps++;

          if (word.length() != 5) {
              System.out.println("PLEASE INSERT WORD ONLY 5 WORD");
              attemps--;
              continue;
          }

          for (int i = 0; i < word.length(); i++) {
            if(word.substring(i,i+1).equalsIgnoreCase(correct.substring(i,i+1))){
                System.out.print(" + "+word.substring(i,i+1));
            }else if(correct.indexOf(word.substring(i,i+1))>-1){
                System.out.print(" ? "+word.substring(i,i+1));
                check = false;
            }else{
                System.out.print(word.substring(i,i+1)+" ");
                check = false;
            }
          }

          if (check && word.equals(correct)){ // is correct
                System.out.println("\nYOU WIN BRO U PRO! BRO U SO CRAZY WORD IS : " +correct);
                break;
          }

          if (attemps == 5) { // if not correct
              System.out.println("\nYOU LOSE TRY AGAIN! ARE U IDIOT OR STUPID? HUH? WORD IS : "+correct);
              break;
          }
 
      }
      sc.close();
    }
}
