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
    private Button str;

    private void enemyTurn() {
        move = battle.select(enemy, rand.nextInt(0, 5));

        enemyDialouge.setContentText(String.format("%s has selected %s", enemy.toString(), move.toString()));

        battle.perform(enemy, player, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());
    }

    @FXML
    private Rectangle EnemyHealth;
    @FXML
    private Rectangle playerHealth;

    @FXML
    private Button cardOne;
    @FXML
    protected void CardOneOnClick() {
        move = battle.select(player, 0);
        cardOne.setText(player.getCardOne());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());

        enemyTurn();
    }
    @FXML
    private Tooltip CardOneToolTip;

    @FXML
    protected void CardOneToolTip() {
        CardOneToolTip.setText(player.getCardOne());
    }

    @FXML
    private Button cardTwo;
    @FXML
    protected void CardTwoOnClick() {
        move = battle.select(player, 1);
        cardTwo.setText(player.getCardTwo());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());

        enemyTurn();
    }
    @FXML
    private Button cardThree;
    @FXML
    protected void CardThreeOnClick() {
        move = battle.select(player, 2);
        cardThree.setText(player.getCardThree());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());

        enemyTurn();
    }
    @FXML
    private Button cardFour;
    @FXML
    protected void CardFourOnClick() {
        move = battle.select(player, 3);
        cardFour.setText(player.getCardFour());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());

        enemyTurn();
    }
    @FXML
    private Button cardFive;
    @FXML
    protected void CardFiveOnClick() {
        move = battle.select(player, 4);
        cardFive.setText(player.getCardFive());

        DialougeText.setContentText(String.format("%s has selected %s", player.toString(), move.toString()));

        battle.perform(player, enemy, move);

        playerHealth.setWidth(player.getHealthPercent() * playerHealth.getWidth());
        EnemyHealth.setWidth(enemy.getHealthPercent() * EnemyHealth.getWidth());

        enemyTurn();
    }

    @FXML
    protected void startGame() {
        cardOne.setText(player.getCardOne());
        cardTwo.setText(player.getCardTwo());
        cardThree.setText(player.getCardThree());
        cardFour.setText(player.getCardFour());
        cardFive.setText(player.getCardFive());

        str.setVisible(false);
    }

}