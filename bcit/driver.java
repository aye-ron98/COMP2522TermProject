import java.util.Random;
import java.util.Scanner;

public class driver {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        Character enemy = Character.generateEnemyCharacter();
        Character player = Character.generateEnemyCharacter();
        battle b = new battle(enemy, player);

        while (true) {
            if (b.playerGoesFirst()) {
                Character current = b.battleTurn();
                System.out.println(b.showCards(current));

                int choice = scanner.nextInt();

                Card move = b.select(current, choice);
                b.perform(current, move);

            if (b.checkForVictory()) {
                System.out.println("You Win");
                break;
            }

            b.checkStack();

            current = b.battleTurn();
            int enemyChoice = rand.nextInt(0, 5);

            move = b.select(current, enemyChoice);
            b.perform(current, move);

            if (b.checkForVictory()) {
                System.out.println("Enemy Wins");
                break;
            }

            b.checkStack();
        } else {
                int enemyChoice = rand.nextInt(0, 5);
                Character current = b.battleTurn();

                Card move = b.select(current, enemyChoice);
                b.perform(current, move);

                if (b.checkForVictory()) {
                    System.out.println("enemy won");
                    break;
                }
                b.checkStack();

                current = b.battleTurn();
                System.out.println(b.showCards(current));

                int choice = scanner.nextInt();
                move = b.select(current, choice);

                b.perform(current, move);

                if (b.checkForVictory()) {
                    System.out.println("You win");
                    break;
                }
                b.checkStack();
            }

       }


    }
}
