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
    private void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void enemyTurn() {
        move = battle.enemyTurn();

        enemyDialouge.setContentText(String.format("%s has selected %s", enemy.toString(), move.toString()));

        battle.perform(enemy, player, move);
        battle.resetDefense(player);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());
        enemyDef.setWidth(enemy.getDefensePercent() * enemyDef.getWidth());
        playerDef.setWidth(playerDef.getWidth() * player.getDefensePercent());
    }

    private void playerTurn(int choice, Button button, Tooltip toolTip) {
        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(), battle.nextEnemyTurn().toString()));

        move = battle.select(player, choice);
        button.setText(player.getCardName(choice));
        toolTip.setText(move.getDescription());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);
        battle.resetDefense(enemy);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        playerDef.setWidth(playerDef.getWidth() * player.getDefensePercent());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());
        enemyDef.setWidth(enemy.getDefensePercent() * enemyDef.getWidth());
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
        pause();
        enemyTurn();
    }

    @FXML
    private Button cardTwo;
    @FXML
    private Tooltip CardTwoToolTip;
    @FXML
    protected void CardTwoOnClick() {

        playerTurn(1, cardTwo, CardTwoToolTip);
        pause();
        enemyTurn();
    }
    @FXML
    private Button cardThree;
    @FXML
    private Tooltip CardThreeToolTip;
    @FXML
    protected void CardThreeOnClick() {

        playerTurn(2, cardThree, CardThreeToolTip);
        pause();
        enemyTurn();
    }
    @FXML
    private Button cardFour;
    @FXML
    private Tooltip CardFourToolTip;
    @FXML
    protected void CardFourOnClick() {
        playerTurn(3, cardFour, CardFourToolTip);
        pause();
        enemyTurn();

    }
    @FXML
    private Button cardFive;
    @FXML
    private Tooltip CardFiveToolTip;
    @FXML
    protected void CardFiveOnClick() {
        playerTurn(4, cardFive, CardFiveToolTip);
        pause();
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