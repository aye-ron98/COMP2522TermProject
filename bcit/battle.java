import java.util.Random;
import java.util.Scanner;

public class battle {
    private class Stack {
        Character[] characters;
        Character lastReturned;
        int size;
        Stack() {
            this.characters = new Character[10];

            if (player.getAgility() > enemy.getAgility()) {

                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0) {
                        characters[i] = player;
                    } else {
                        characters[i] = enemy;
                    }
                }
            } else {

                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0) {
                        characters[i] = enemy;
                    } else {
                        characters[i] = player;
                    }
                }
            }

            this.size = 10;
        }

        Character pop() {
            this.size--;
            Character toReturn = characters[this.size];
            lastReturned = toReturn;
            characters[this.size] = null;

            return toReturn;
        }

        Character peek() {
            if (this.size - 1 < 0) {
                return lastReturned;
            }
            return characters[this.size - 1];
        }

        @Override
        public String toString() {
            String toPrint = "Stack {";
            for (int i = 0; i < characters.length; i++) {
                String temp = String.valueOf(characters[i]);
                toPrint = toPrint.concat(temp);
            }
            return "Stack{" +
                    toPrint +
                    '}';
        }
    }

    Character player;
    Character enemy;

    Stack stack;
    battle(Character p, Character e) {
        this.player = p;
        this.enemy = e;

        p.getCardsForBattle();
        e.getCardsForBattle();
        this.stack = new Stack();
    }

    void checkStack() {
        if (stack.size == 0) {
            this.stack = new Stack();
        }
    }

    Character battleTurn() {
        return this.stack.pop();
    }

    public String showCards(Character c) {
        String Cards = c.displayCards();
        return "The following moves are available to you:" + Cards;
    }
    public Card select(Character c, int choice) {
        System.out.printf("You have selected %s!\n", c.returnCard(choice));
        return c.returnCard(choice);
    }

    public void resetDefense(Character c) {
        c.setDefense(c.getDEFULT_DEFENSE());
    }

    public void perform(Character current, Card c) {

        Card.ACTION action = c.getAction();
        Card.TYPE type = c.getType();
        Card.TYPE tempPlayer;

        Character nextPlayer = stack.peek();
        Card.TYPE currentPlayerType = current.getType();
        Card.TYPE nextPlayerType = nextPlayer.getType();

        if (action == Card.ACTION.ATTACK) {
            tempPlayer = nextPlayerType;
        } else {
            tempPlayer = currentPlayerType;
        }

        double typeMultiplier;


        if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = 1.0;
        } else if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = 0.2;
        } else if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = 0.8;
        } else if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = 1.5;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = 0.8;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = 0.2;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = 1.0;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = 1.5;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = 0.8;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = 0.2;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = 1.0;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = 1.5;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = 0.8;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = 0.2;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = 1.0;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = 1.5;
        } else {
            typeMultiplier = 0;
        }


        if (action == Card.ACTION.ATTACK) {
            int damageDealt = (int) ((c.getValue() - nextPlayer.getDefense()) * typeMultiplier);
            if (damageDealt > 0) {
                nextPlayer.setHealth(nextPlayer.getHealth() - c.getValue());
            }
        } else if (action == Card.ACTION.DEFEND) {
            current.setDefense((int) ((current.getDefense() + c.getValue()) * typeMultiplier));
        } else if (action == Card.ACTION.HEAL) {
            current.setHealth((int) ((current.getHealth() + c.getValue()) * typeMultiplier));
        }

        }

    public boolean checkForVictory() {
        if (this.player.getHealth() < 0) {
            System.out.println("You lost");
            return true;
        } else if (this.enemy.getHealth() < 0) {
            System.out.println("you win");
            return true;
        }
        return false;
    }

    public boolean playerGoesFirst() {
        return stack.peek() == player;
    }

    @Override
    public String toString() {
        return "battle{" +
                "stack=" + stack +
                '}';
    }

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

                b.resetDefense(enemy);
                b.checkStack();

                current = b.battleTurn();
                int enemyChoice = rand.nextInt(0, 5);

                move = b.select(current, enemyChoice);
                b.perform(current, move);

                if (b.checkForVictory()) {
                    System.out.println("Enemy Wins");
                    break;
                }

                b.resetDefense(player);
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

                b.resetDefense(player);
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

                b.resetDefense(enemy);
                b.checkStack();
            }

        }


    }
}
