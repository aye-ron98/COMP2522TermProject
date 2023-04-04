package com.example.game;

public class Battle {

    private class Stack {
        Card[] cards;
        int size = 10;
        Stack() {
            this.cards = new Card[size];

            for (int i = 0; i < this.size; i++) {
                cards[i] = Card.constructNewCard();
            }

        }

        Card pop() {
            checkStack();
            this.size--;
            Card toReturn = cards[this.size];
            cards[this.size] = null;

            return toReturn;
        }

        Card peek() {
            checkStack();
            return cards[this.size - 1];

        }

        void checkStack() {
            if (this.size - 1 < 0) {
                this.size = 10;

                for (int i = 0; i < this.size; i++) {
                    cards[i] = Card.constructNewCard();
                }

            }
        }
    }

    Character player;
    Character enemy;

    Stack stack;
    Battle(final Character p, final Character e) {
        this.player = p;
        this.enemy = e;

        p.getCardsForBattle();
        this.stack = new Stack();
    }

    /**
     * Proceed with the enemy's turn.
     * @return Card that enemy plays
     */
    Card enemyTurn() {
        return this.stack.pop();
    }

    /**
     * Peek at what enemy will do in the next turn.
     * @return Card that enemy will play in the next turn
     */
    Card nextEnemyTurn() {
        return this.stack.peek();
    }

    /**
     * Select card to play by the user.
     * @param c Character the player is playing as
     * @param choice int of the Card that the player has selected
     * @return Card that player has selected
     */
    public Card select(final Character c, final int choice) {
        Card returnCard = c.returnCard(choice);
        System.out.printf("You have selected %s!\n", returnCard.toString());
        return returnCard;
    }

    /**
     * Reset the Character's Defense stats to the default Defense stats.
     * @param c Character the player is playing as
     */
    public void resetDefense(final Character c) {
        c.setDefense(c.getDEFAULT_DEFENSE());
    }

    public void perform(final Character current, final Character next, final Card c) {

        Card.ACTION action = c.getAction();
        Card.TYPE type = c.getType();
        Card.TYPE tempPlayer;

        Character nextPlayer = next;
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

    public boolean checkForVictory(final Character player, final Character enemy) {
        if (player.getHealth() < 0) {
            System.out.println("You lost");
            return true;
        } else if (enemy.getHealth() < 0) {
            System.out.println("you win");
            return true;
        }
        return false;
    }

    }
