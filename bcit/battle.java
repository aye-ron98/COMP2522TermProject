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

    public void perform(Character current, Card c) {
        if (c.getAction() == Card.ACTION.ATTACK) {
            Character enemy = stack.peek();

            enemy.setHealth(enemy.getHealth() - c.getValue());
        } else if (c.getAction() == Card.ACTION.DEFEND) {
            current.setDefense(current.getDefense() + c.getValue());
        } else if (c.getAction() == Card.ACTION.HEAL) {
            current.setHealth(current.getHealth() + c.getValue());
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
        if (stack.peek() == player) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "battle{" +
                "stack=" + stack +
                '}';
    }
}