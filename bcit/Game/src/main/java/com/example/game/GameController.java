package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GameController extends Thread{

    Character player = Character.generateEnemyCharacter();
    Character enemy = Character.generateEnemyCharacter();

    Battle battle = new Battle(player, enemy);
    Card move;

    // scense assets
    @FXML
    private DialogPane DialougeText;
    @FXML
    private DialogPane enemyDialouge;
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

    private void enemyTurn() {
        move = battle.enemyTurn();

        enemyDialouge.setContentText(String.format("%s has selected %s", enemy.toString(), move.toString()));

        battle.perform(enemy, player, move);
        battle.resetDefense(player);

        playerHealth.setWidth(player.getHealth());
        playerDef.setWidth(player.getDefense());
        EnemyHealth.setWidth(enemy.getHealth());
        enemyDef.setWidth(enemy.getDefense());

        gameOver();
    }

    private void playerTurn(int choice, Button button, Tooltip toolTip) {
        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(), battle.nextEnemyTurn().toString()));

        move = battle.select(player, choice);
        button.setText(player.getCardName(choice));
        toolTip.setText(move.getDescription());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);
        battle.resetDefense(enemy);

        playerHealth.setWidth(playerHealth.getWidth() - (playerHealth.getWidth() * (1.0 - player.getHealthPercent())));
        playerDef.setWidth(playerDef.getWidth() - (playerDef.getWidth() * (1.0 - player.getDefensePercent())));
        EnemyHealth.setWidth(EnemyHealth.getWidth() - (EnemyHealth.getWidth() * (1.0 - enemy.getHealthPercent())));
        enemyDef.setWidth(enemyDef.getWidth() - (enemyDef.getWidth() * (1.0 - enemy.getDefensePercent())));

        System.out.println(player.getHealth());
        System.out.println(enemy.getHealth());

        gameOver();
    }

    @FXML
    private Rectangle EnemyHealth;
    @FXML
    private Rectangle playerHealth;

    @FXML
    private Button cardOne;
    @FXML
    private Tooltip CardOneToolTip;
    @FXML
    protected void CardOneOnClick() {
        playerTurn(0, cardOne, CardOneToolTip);
//        pause();
        enemyTurn();
    }

    @FXML
    private Button cardTwo;
    @FXML
    private Tooltip CardTwoToolTip;
    @FXML
    protected void CardTwoOnClick() {

        playerTurn(1, cardTwo, CardTwoToolTip);
//        pause();
        enemyTurn();
    }
    @FXML
    private Button cardThree;
    @FXML
    private Tooltip CardThreeToolTip;
    @FXML
    protected void CardThreeOnClick() {

        playerTurn(2, cardThree, CardThreeToolTip);
//        pause();
        enemyTurn();
    }
    @FXML
    private Button cardFour;
    @FXML
    private Tooltip CardFourToolTip;
    @FXML
    protected void CardFourOnClick() {
        playerTurn(3, cardFour, CardFourToolTip);
//        pause();
        enemyTurn();

    }
    @FXML
    private Button cardFive;
    @FXML
    private Tooltip CardFiveToolTip;
    @FXML
    protected void CardFiveOnClick() {
        playerTurn(4, cardFive, CardFiveToolTip);
//        pause();
        enemyTurn();

    }

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

        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(), battle.nextEnemyTurn().toString()));
    }

}