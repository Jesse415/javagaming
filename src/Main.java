import java.util.Scanner;
import java.util.Random;

import static java.lang.System.exit;

/*Player information and parent classes*/
class Player {
    private String pid;
    private int credits, wager;

    Player() {
        pid = "new";
        credits = 100;
        wager = 0;
    }

    Scanner input = new Scanner(System.in);

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

    public void resetCredits() {
        credits = 100;
    }

    public void updateCredits(int wlCredits, String operation) {
        if (operation.equals("win")) {
            credits += wlCredits;
        } else {
            credits -= wlCredits;
        }
        System.out.println("Your current credit account is: " + getCredits() + "\n");
    }

    public void takeWager() {
        System.out.println("How much would you like to wager of your: " + getCredits() + " Credits?");
        wager = input.nextInt();
        credits -= wager;
        System.out.println("You have: " + getCredits() + " Credits remaining.");
    }

    public int getWager() {
        return wager;
    }

}

/*This Is the Pick a Number game.*/
class PickANumber extends Player {
    private int winNumber;

    PickANumber() {
        Random rand = new Random();
        winNumber = rand.nextInt(30);
    }


    public void runGame() {
        Scanner input = new Scanner(System.in);

        System.out.println("-=[ Welcome to Pick a Number Game ]=-");
        System.out.println("This game costs 10 credits to play.");
        System.out.println("Simply pick a number between 1 and 30 inclusive.");
        System.out.println("If you pick the correct number you win 100 credits.");
        System.out.println("Please enter your pick: ");
        int guess = input.nextInt();

        while (guess < 1 || guess > 30) {
            System.out.println("Your pick was out of the range of 1-30. Please try again. ");
            guess = input.nextInt();
        }

        if (guess == winNumber) {
            System.out.println("Congratulations you are a Winner!");
            System.out.println("You received 100 credits!");
            System.out.println(" ");
            this.updateCredits(100, "win");
        } else {
            System.out.println("Oh, That sucks! You picked: " + guess);
            System.out.println("The winning number was: " + winNumber);
            System.out.println(" ");
            this.updateCredits(10, "lose");
        }
    }
}

/*This is the No Match Dealer game.*/
class NoMatchDealer extends Player {
    private int i, j, wager =-1,match =-1;
    private int[] numbers = new int[16];

    public void runGame() {

        System.out.println("---==[ No Match Dealer ]==---");
        System.out.println("In this game, you can wager up to all of your credits.");
        System.out.println("The dealer will deal out 16 random numbers between 1 and 99.");
        System.out.println("If there are no matches among them, you double your money!");

        if (getCredits() == 0) {
            System.out.println("You don't have any credits to wager!");
            return;
        }
        takeWager();
        wager = getWager();

        Random rand = new Random();

        System.out.println("---==[ Dealing out 16 random numbers ]==---");
        for (i = 0; i < 16; i++) {
            int ace =
            numbers[i] = rand.nextInt(100);
            System.out.print(numbers[i] + "\t");
            if (i % 8 == 7)
                System.out.println(" ");
        }
        System.out.println(" ");
        for (i = 0; i < 15; i++) {
            j = i + 1;
            while (j < 16) {
                if (numbers[i] == numbers[j])
                    match = numbers[i];
                j++;
            }
        }
        if (match != -1) {
            System.out.println("The dealer matched the number " + match + "!");
            System.out.println("You lose " + wager + " credits.");
            this.updateCredits(wager, "lose");
        } else {
            System.out.println("There were no matches! You win " + wager + " credits!\n");
            wager += wager;
            this.updateCredits(wager, "win");
        }
    }
}

/*This is the Find the Ace game.*/
class AceGame extends Player {
    private int i;
    private int pick = -1;
    private char[] cards = new char[]{
            'X', 'X', 'X'
    };

    private void printCards(char[] cards, int pick) {

        System.out.println("\n\t*** Here are the Cards ***\n");
        System.out.println("Cards:\t|" + cards[0] + "|\t|" + cards[1] + "|\t|" + cards[2] + "|\t");
        System.out.println("      \t:-:\t:-:\t:-:");

        if (pick == -1) {
            System.out.println("\t\t 1 \t 2 \t 3\n");
        } else {
            pick++;
            for (i = 0; i <= pick; i++)
                System.out.print("\t");
            System.out.print(" ^-- your pick\n");
        }
    }

    public void runGame() {

        Random rand = new Random();
        int ace = rand.nextInt(3);
        Scanner input = new Scanner(System.in);

        System.out.println("---==[ Find the Ace ]==---");
        System.out.println("In this game, you can wager up to all of your credits.");
        System.out.println("Three cards will be dealt out, two queens and one ace.");
        System.out.println("If you find the ace, you will win your wager.");
        System.out.println("After choosing a card, one of the queen will be revealed.");
        System.out.println("At this point, you may either select a different card or");
        System.out.println("increase your wager.\n\n");

        if (getCredits() == 0) {
            System.out.println("You don't have any credits to wager!");
            return;
        }

        takeWager();
        int wager = getWager();

        System.out.println("Dealing cards");
        printCards(cards, -1);

        while ((pick < 1) || (pick > 3)) {
            System.out.println("Select a card: 1, 2, or 3:  ");
            pick = input.nextInt();
        }
        pick--;
        i = 0;
        while (i == ace || i == pick)
            i++;
        cards[i] = 'Q';
        printCards(cards, pick);

        boolean invalidChoice = true;
        while (invalidChoice) {
            System.out.println("Would you like to:\n[c]hange your pick, [k]eep your current pick, or [i]crease your wager?\n");
            System.out.println("Select k, c, or i:  ");
            char choiceTwo = '\n';
            while (choiceTwo == '\n')
                choiceTwo = input.next().charAt(0);
            if(choiceTwo == 'c' || choiceTwo == 'C'){
                i = 0;
                while(i == pick || cards[i] == 'Q')
                    i++;
                pick = i;
                System.out.println("Your card pick has been changed to card ");
                printCards(cards, pick);
            }
            if (choiceTwo == 'k' || choiceTwo == 'K') {
                invalidChoice = false;
                i = 0;
                while (i < 3) {
                    if (i == ace) {
                        cards[i] = 'A';
                    } else {
                        cards[i] = 'Q';
                    }
                    i++;
                }
                printCards(cards, pick);
                if (pick == ace) {
                    System.out.println("Congratulations! You are a Winner!");
                    System.out.println("You win: " + getWager());
                    wager += wager;
                    this.updateCredits(wager, "win");
                } else {
                    System.out.println("Sorry, You lose.");
                    this.updateCredits(wager, "lose");
                }
            }
            if (choiceTwo == 'i' || choiceTwo == 'I') {
                System.out.println("How much would you like to wager of your current: " + getCredits() + " Credits?");
                int wagerTwo = input.nextInt();
                while (wagerTwo > getCredits()) {
                    System.out.println("Sorry you don'd have enough for that wager. Try again.");
                    wagerTwo = input.nextInt();
                }
                wager += wagerTwo;
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        boolean menu = true;
        String newName;
        Scanner menuScan = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Player p1 = new Player();

        if (p1.getName().equals("new")) {
            System.out.println("Welcome. Please enter a New player name: ");
            Scanner newPlayerName = new Scanner(System.in);
            newName = newPlayerName.nextLine();
            p1.setName(newName);
        }
        while (menu) {
            System.out.println("---===[ Game of Chance Menu ]===---");
            System.out.println("1 - Play Pick a Number Game");
            System.out.println("2 - Play the No Match Dealer Game");
            System.out.println("3 - Play Find the Ace Game");
            System.out.println("4 - View current credits");
            System.out.println("5 - Change player name");
            System.out.println("6 - Reset Game");
            System.out.println("7 - Quit");
            int i = menuScan.nextInt();


            if (i < 1 || i > 7) {
                System.out.println("You have made an invalid selection. Try again.");
            } else if (i < 4) {
                if (i == 1) {
                    //pickNumberGame;
                    PickANumber newGame = new PickANumber();
                    newGame.runGame();
                } else if (i == 2) {
                    //noMatchGame;
                    NoMatchDealer newGame = new NoMatchDealer();
                    newGame.runGame();
                } else if (i == 3) {
                    //findAce;
                    AceGame newGame = new AceGame();
                    newGame.runGame();
                }
            } else if (i == 4) {
                //viewcredits;
                System.out.println(p1.getCredits());
            } else if (i == 5) {
                //changePlayer
                System.out.println("Changing player name will reset credits as well.");
                System.out.println("Would you like to continue? [y/n]");
                String yn = input.nextLine();
                if (yn.equals("y") || yn.equals("Y")) {
                    System.out.println("Change player name");
                    System.out.println("Enter new player name");
                    Scanner playerName = new Scanner(System.in);
                    newName = playerName.nextLine();
                    p1.setName(newName);
                    p1.resetCredits();
                    System.out.println("Welcome: " + p1.getName());
                    System.out.println("You have: " + p1.getCredits() + " Credits.");
                }
            } else if (i == 6) {
                //resetGame
                System.out.println("Your account has been reset to 100 credits.");
                p1.resetCredits();
            } else if (i == 7) {
                //quitGame
                System.out.println("Thank you for playing. Goodbye.");
                exit(1);
            }

        }
    }
}
