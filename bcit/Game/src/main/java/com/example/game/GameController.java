package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class GameController {

    Random rand = new Random();
    Character player = Character.generateEnemyCharacter();
    Character enemy = Character.generateEnemyCharacter();

    battle battle = new battle(player, enemy);
    Card move;

    @FXML
    private DialogPane DialougeText;
    @FXML
    private DialogPane enemyDialouge;
    @FXML
    private DialogPane nextEnemyTurn;

    @FXML
    private Button str;

    private void enemyTurn() {
        move = battle.enemyTurn();

        enemyDialouge.setContentText(String.format("%s has selected %s", enemy.toString(), move.toString()));

        battle.perform(enemy, player, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());
    }

    private void playerTurn(int choice, Button button, Tooltip toolTip) {
        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(), battle.nextEnemyTurn().toString()));

        move = battle.select(player, choice);
        button.setText(player.getCardName(choice));
        toolTip.setText(move.getDescription());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());

        enemyTurn();
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
    }

    @FXML
    private Button cardTwo;
    @FXML
    private Tooltip CardTwoToolTip;
    @FXML
    protected void CardTwoOnClick() {
        playerTurn(1, cardTwo, CardTwoToolTip);
    }
    @FXML
    private Button cardThree;
    @FXML
    private Tooltip CardThreeToolTip;
    @FXML
    protected void CardThreeOnClick() {
        playerTurn(2, cardThree, CardThreeToolTip);
    }
    @FXML
    private Button cardFour;
    @FXML
    private Tooltip CardFourToolTip;
    @FXML
    protected void CardFourOnClick() {
        playerTurn(3, cardFour, CardFourToolTip);
    }
    @FXML
    private Button cardFive;
    @FXML
    private Tooltip CardFiveToolTip;
    @FXML
    protected void CardFiveOnClick() {
        playerTurn(4, cardFive, CardFiveToolTip);
    }

    @FXML
    protected void startGame() {
        cardOne.setText(player.getCardName(0));
        cardTwo.setText(player.getCardName(1));
        cardThree.setText(player.getCardName(2));
        cardFour.setText(player.getCardName(3));
        cardFive.setText(player.getCardName(4));

        str.setVisible(false);

        nextEnemyTurn.setContentText(String.format("%s will use %s next!", enemy.toString(), battle.nextEnemyTurn().toString()));
    }

}