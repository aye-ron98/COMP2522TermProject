package com.example.game;


/**
 * com.example.game.Battle.
 *
 * @author Aron Zhang
 * @author Lex Wong
 * @version 202213
 */
public class Battle {
    /**
     * Multiplier when Card type and Character type are the same.
     */
    static final double SAME_TYPE_MULTIPLIER = 1.0;
    /**
     * Multiplier when Character type is Earth.
     */
    static final double EARTH_MULTIPLIER = 0.2;
    /**
     * Multiplier when Character type is Air.
     */
    static final double AIR_MULTIPLIER = 0.8;
    /**
     * Multiplier when Card type is strong against Character type.
     */
    static final double STRONG_MULTIPLIER = 1.5;
    /**
     * Multiplier when Card type is weak against Character type.
     */
    static final double WEAK_MULTIPLIER = 0.5;
    private static class Stack {
        static final int DEFAULT_SIZE = 10;
        Card[] cards;
        int size = DEFAULT_SIZE;
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
                this.size = DEFAULT_SIZE;

                for (int i = 0; i < this.size; i++) {
                    cards[i] = Card.constructNewCard();
                }

            }
        }
    }

    /**
     * Character that the player is currently playing as.
     */
    Character player;
    /**
     * Character of the enemy.
     */
    Character enemy;

    /**
     * Stack of turns of both the player and the enemy.
     */
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

    /**
     * Perform the action done from one Character to the other.
     * @param current Character dealing the action
     * @param next Character receiving the action
     * @param c Card chosen as the action
     */
    public void perform(final Character current, final Character next, final Card c) {

        Card.ACTION action = c.getAction();
        Card.TYPE type = c.getType();
        Card.TYPE tempPlayer;

        Card.TYPE currentPlayerType = current.getType();
        Card.TYPE nextPlayerType = next.getType();

        if (action == Card.ACTION.ATTACK) {
            tempPlayer = nextPlayerType;
        } else {
            tempPlayer = currentPlayerType;
        }

        double typeMultiplier;


        if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = SAME_TYPE_MULTIPLIER;
        } else if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = EARTH_MULTIPLIER;
        } else if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = WEAK_MULTIPLIER;
        } else if (type == Card.TYPE.AIR && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = STRONG_MULTIPLIER;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = AIR_MULTIPLIER;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = EARTH_MULTIPLIER;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = SAME_TYPE_MULTIPLIER;
        } else if (type == Card.TYPE.FIRE && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = WEAK_MULTIPLIER;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = AIR_MULTIPLIER;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = EARTH_MULTIPLIER;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = WEAK_MULTIPLIER;
        } else if (type == Card.TYPE.EARTH && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = STRONG_MULTIPLIER;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.AIR) {
            typeMultiplier = AIR_MULTIPLIER;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.EARTH) {
            typeMultiplier = EARTH_MULTIPLIER;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.FIRE) {
            typeMultiplier = STRONG_MULTIPLIER;
        } else if (type == Card.TYPE.WATER && tempPlayer == Card.TYPE.WATER) {
            typeMultiplier = SAME_TYPE_MULTIPLIER;
        } else {
            typeMultiplier = 0;
        }


        if (action == Card.ACTION.ATTACK) {
            int damageDealt = (int) ((c.getValue() - next.getDefense()) * typeMultiplier);
            if (damageDealt > 0) {
                next.setHealth(next.getHealth() - c.getValue());
            }
        } else if (action == Card.ACTION.DEFEND) {
            current.setDefense((int) ((current.getDefense() + c.getValue()) * typeMultiplier));
        } else if (action == Card.ACTION.HEAL) {
            current.setHealth((int) ((current.getHealth() + c.getValue()) * typeMultiplier));
        }

    }

    /**
     * Check if the player has won the fight.
     * @param playerCharacter Character that the player is currently playing as
     * @param enemyCharacter Character of the enemy
     * @return boolean stating if the player has beaten the enemy
     */
    public boolean checkForVictory(final Character playerCharacter, final Character enemyCharacter) {
        if (playerCharacter.getHealth() < 0) {
            System.out.println("You lost");
            return true;
        } else if (enemyCharacter.getHealth() < 0) {
            System.out.println("you win");
            return true;
        }
        return false;
    }

    }
