import java.util.Scanner;
import java.util.Random;

import static java.lang.System.exit;

class Player {
    private String pid = "new";
    private int credits = 100;

    //Setter and getter for player name
    public void setName(String name) {
        this.pid = name;
    }

    public String getName() {
        return pid;
    }

    //Setter for player credits
    public int getCredits() {
        return credits;
    }

/*    public int updateCredits(int update) {
    credits = update;
    }*/

}

class UpdateCredits extends Player {
    public void updateCredits(int wlCredits, String operation) {
        if (operation.equals("win")) {
            int credits = getCredits() + wlCredits;
            updateCredits(credits);
        } else {
            int credits = getCredits() - wlCredits;
            updateCredits(credits);
        }
        System.out.println("Your current credit account is: " + getCredits() + getName());
    }
}


class PickANumber extends UpdateCredits {

    PickANumber inner = new PickANumber() {

        private Random rand = new Random();
        int winNumber = rand.nextInt(30);

        System.out.println("-=[ Welcome to Pick a Number Game ]=-");
        System.out.println("This game costs 10 credits to play.");
        System.out.println("Simply pick a number between 1 and 30 inclusive.");
        System.out.println("If you pick the correct number you win 100 credits.");

        System.out.println("Please enter your pick: ");
        int guess = input.nextInt();

        if(guess == winNumber)

        {

        }
    };
}

class NoMatchDealer extends UpdateCredits {

}

class AceGame extends UpdateCredits {

}

public class Main {

    public static void main(String[] args) {
        boolean menu = true;
        String newName;
        Player p1 = new Player();

        if (p1.getName().equals("new"))
            System.out.println("Welcome. Please enter a New player name: ");
        Scanner newPlayerName = new Scanner(System.in);
        newName = newPlayerName.nextLine();
        p1.setName(newName);

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
                    p1.
                } else if (i == 2) {
                    //noMatchGame;
                } else if (i == 3) {
                    //findAce;
                }
            } else if (i == 4) {
                //viewScore;
            } else if (i == 5) {
                //changePlayer
                //System.out.println(p1.getName());
                System.out.println("Change player name");
                System.out.println("Enter new player name");
                Scanner playerName = new Scanner(System.in);
                newName = playerName.nextLine();
                p1.setName(newName);
                System.out.println(p1.getName());

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

}