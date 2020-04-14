package com.avatarduel.model.gui;

import java.util.LinkedList;

import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.CharacterCardList;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.LandCardList;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.cards.SkillCardList;
import com.avatarduel.model.player.Hand;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Phase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;

public class GameController{
    
    @FXML private StackPane cardInfoSlot;
    @FXML private StackPane deckSlot;
    @FXML private StackPane statsSlot;
    @FXML private StackPane topFieldSlot;
    @FXML private StackPane bottomFieldSlot;
    @FXML private ScrollPane handSlot;
    
    private DeckController deckController;
    private FieldController bottomFieldController;
    private FieldController topFieldController;
    private StatsController statsController;
    private HandController handController;

    private Player playerA;
    private Player playerB;

    // For storing current active player's reference
    private Player activePlayer;

    
    public void initialize() {
        
        // Initialize game controller requirements
        this.playerA = new Player("A");
        this.playerB = new Player("B");
        this.activePlayer = this.playerA;
        this.setHandInterface();
        this.setDeckInterface();
        this.setStatsInterface();
        this.setFieldInterface(this.activePlayer, this.playerB);
        // this.phase = Phase.draw;
    }

    // Get active player
    public Player getActivePlayer()
    {
        return this.activePlayer;
    }

    // Get hand controller
    public HandController getHandController()
    {
        return this.handController;
    }

    // Get stats controller
    public StatsController getStatsController()
    {
        return this.statsController;
    }

    // Set top field interface
    @FXML
    public void setFieldInterface(Player bottomPlayer, Player topPlayer)
    {
        this.bottomFieldSlot.getChildren().clear();
        this.bottomFieldController = new FieldController(bottomPlayer, this, false);
        this.bottomFieldSlot.getChildren().add(this.bottomFieldController);

        this.topFieldSlot.getChildren().clear();
        this.topFieldController = new FieldController(topPlayer, this, true);
        this.topFieldSlot.getChildren().add(this.topFieldController);
    }

    // Set interface to current player's hand
    @FXML
    public void setHandInterface()
    {
        this.handController = new HandController(this.activePlayer, this);
        this.handSlot.setContent(this.handController);
    }
    
    // Set interface to current player's deck
    @FXML
    public void setDeckInterface()
    {
        this.deckSlot.getChildren().clear();
        
        this.deckController = new DeckController(this.activePlayer, this.handController);
        this.deckSlot.getChildren().add(this.deckController);
    }
    
    // Set interface to current player's stats
    @FXML
    public void setStatsInterface()
    {
        this.statsSlot.getChildren().clear();

        this.statsController = new StatsController(this);
        this.statsSlot.getChildren().add(this.statsController);
    }
    
    // Set interface to current player's card info when hovered
    @FXML
    public void setCardInfo(CharacterCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void setCardInfo(SkillCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void setCardInfo(LandCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    // Switch turns between player A and B
    @FXML
    public void changeTurn()
    {
        Player otherPlayer = this.activePlayer;
        this.activePlayer = this.activePlayer == this.playerA ? this.playerB : this.playerA;
        this.setHandInterface();
        this.setDeckInterface();
        this.setStatsInterface();
        this.setFieldInterface(this.activePlayer, otherPlayer);
        
    }


}