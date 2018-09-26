import java.util.Scanner;

import static java.lang.System.exit;

class Player {
    String pid = "new";
    int startCredits = 100;

}


public class Main {

    public static void main(String[] args) {
        boolean menu = true;
        Player p1 = new Player();

        if(p1.pid.equals("new"))
        System.out.println("Welcome. Please enter a New player name: ");
        Scanner newPlayerName = new Scanner(System.in);
        p1.pid = newPlayerName.nextLine();

        while (menu) {
            System.out.println("-=[ Game of Chance Menu ]=-");
            System.out.println("1 - Play Pick a Number Game");
            System.out.println("2 - Play the No Match Dealer Game");
            System.out.println("3 - Play Find the Ace Game");
            System.out.println("4 - View current high score");
            System.out.println("5 - Change player name");
            System.out.println("6 - Reset Game");
            System.out.println("7 - Quit");
            Scanner menuScan = new Scanner(System.in);
            int i = menuScan.nextInt();


            if (i < 1 || i > 7) {
                System.out.println("You have made an invalid selection. Try again.");
            } else if (i < 4) {
                if (i == 1) {
                    //pickNumberGame;
                } else if (i == 2) {
                    //noMatchGame;
                } else if (i == 3) {
                    //findAce;
                }
            } else if (i == 4) {
                //viewScore;
            } else if (i == 5) {
                //changePlayer
                System.out.println("Change player name");
                System.out.println("Enter new player name");
                Scanner playerName = new Scanner(System.in);
                p1.pid = playerName.nextLine();
            } else if (i == 6) {
                //resetGame
                System.out.println("Your account has been reset to 100 credits.");
                p1.startCredits = 100;
            } else if (i == 7) {
                //quitGame
                System.out.println("Thank you for playing. Goodbye.");
                exit(1);
            }

        }
    }
}

