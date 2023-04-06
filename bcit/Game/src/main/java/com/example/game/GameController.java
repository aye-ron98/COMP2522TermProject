package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * com.example.game.GameController.
 *
 * @author Aron Zhang
 * @author Lex Wong
 * @version 202213
 */
public class GameController extends Thread {
    /**
     * Assign player to a generated Character.
     */
    Character player = Character.generateEnemyCharacter();
    /**
     * Assign enemy to a generated Character.
     */
    Character enemy = Character.generateEnemyCharacter();
    /**
     * Assign battle to a new Battle between the player character and the enemy character.
     */
    Battle battle = new Battle(player, enemy);
    /**
     * Create new variable for Cards.
     */
    Card move;

    /**
     * Assets for Scenes.
     */
    @FXML
    private DialogPane dialogText;
    @FXML
    private DialogPane enemyDialog;
    @FXML
    private DialogPane nextEnemyTurn;
    @FXML
    private Button str;
    @FXML
    private Text playerName;
    @FXML
    private Text  enemyName;
    @FXML
    private Rectangle playerDef;
    @FXML
    private Rectangle enemyDef;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private DialogPane gameOver;
    @FXML
    private Rectangle enemyHealth;
    @FXML
    private Rectangle playerHealth;
    @FXML
    private Button cardOne;
    @FXML
    private Tooltip cardOneToolTip;
    @FXML
    private Button cardTwo;
    @FXML
    private Tooltip cardTwoToolTip;
    @FXML
    private Button cardThree;
    @FXML
    private Tooltip cardThreeToolTip;
    @FXML
    private Button cardFour;
    @FXML
    private Tooltip cardFourToolTip;
    @FXML
    private Button cardFive;
    @FXML
    private Tooltip cardFiveToolTip;

    private void gameOver() {
        if (battle.checkForVictory(player, enemy)) {
            if (player.getHealth() < 0) {
                gameOver.setHeaderText("Game Over!");
                gameOver.setContentText(String.format("%s, wins!", enemy.toString()));
            } else {
                if (enemy.getHealth() < 0) {
                    gameOver.setHeaderText("Game Over!");
                    gameOver.setContentText(String.format("%s, wins!", player.toString()));
                }
            }
            gameOver.setOpacity(100);
            cardOne.setVisible(false);
            cardTwo.setVisible(false);
            cardThree.setVisible(false);
            cardFour.setVisible(false);
            cardFive.setVisible(false);
        }
    }
//    private void pause() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }

    /**
     * Run enemy turn.
     */
    private void enemyTurn() {
        move = battle.enemyTurn();

        enemyDialog.setContentText(String.format("%s has selected %s", enemy.toString(), move.toString()));

        battle.perform(enemy, player, move);
        battle.resetDefense(player);

        playerHealth.setWidth(player.getHealth());
        playerDef.setWidth(player.getDefense());
        enemyHealth.setWidth(enemy.getHealth());
        enemyDef.setWidth(enemy.getDefense());

        gameOver();
    }

    /**
     * Run player turn.
     * @param choice int choice for Card
     * @param button Button pressed for int choice of Card
     * @param toolTip Tooltip String for Card choice
     */
    private void playerTurn(final int choice, final Button button, final Tooltip toolTip) {
        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(),
                battle.nextEnemyTurn().toString()));

        move = battle.select(player, choice);
        button.setText(player.getCardName(choice));
        toolTip.setText(move.getDescription());

        dialogText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);
        battle.resetDefense(enemy);

        playerHealth.setWidth(playerHealth.getWidth() - (playerHealth.getWidth() * (1.0 - player.getHealthPercent())));
        playerDef.setWidth(playerDef.getWidth() - (playerDef.getWidth() * (1.0 - player.getDefensePercent())));
        enemyHealth.setWidth(enemyHealth.getWidth() - (enemyHealth.getWidth() * (1.0 - enemy.getHealthPercent())));
        enemyDef.setWidth(enemyDef.getWidth() - (enemyDef.getWidth() * (1.0 - enemy.getDefensePercent())));

        System.out.println(player.getHealth());
        System.out.println(enemy.getHealth());

        gameOver();
    }

    /**
     * The player chooses to play the first displayed card.
     */
    @FXML
    protected void cardOneOnClick() {
        playerTurn(0, cardOne, cardOneToolTip);
//        pause();
        enemyTurn();
    }

    /**
     * The player chooses to play the second displayed card.
     */
    @FXML
    protected void cardTwoOnClick() {
        playerTurn(1, cardTwo, cardTwoToolTip);
//        pause();
        enemyTurn();
    }
    /**
     * The player chooses to play the third displayed card.
     */
    @FXML
    protected void cardThreeOnClick() {
        playerTurn(2, cardThree, cardThreeToolTip);
//        pause();
        enemyTurn();
    }
    /**
     * The player chooses to play the fourth displayed card.
     */
    @FXML
    protected void cardFourOnClick() {
        playerTurn(3, cardFour, cardFourToolTip);
//        pause();
        enemyTurn();
    }
    /**
     * The player chooses to play the fifth displayed card.
     */
    @FXML
    protected void cardFiveOnClick() {
        playerTurn(4, cardFive, cardFiveToolTip);
//        pause();
        enemyTurn();
    }

    /**
     * Start the game.
     */
    @FXML
    protected void startGame() {
        cardOne.setText(player.getCardName(0));
        cardTwo.setText(player.getCardName(1));
        cardThree.setText(player.getCardName(2));
        cardFour.setText(player.getCardName(3));
        cardFive.setText(player.getCardName(4));

        playerName.setText(player.toString());
        enemyName.setText(enemy.toString());

        playerImage.setImage(player.getCharacterImage());
        enemyImage.setImage(enemy.getCharacterImage());

        str.setVisible(false);

        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(),
                battle.nextEnemyTurn().toString()));
    }

}
