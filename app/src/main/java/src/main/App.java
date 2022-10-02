package src.main;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The game GUI by use lanterna
 *
 * @author Jingqi DOU
 * @author Bo ZHANG
 */
public class App {
    static BasicWindow window = new BasicWindow();
    static MultiWindowTextGUI gui;
    static Game game;
    private static Screen screen;

    public static void main(String[] args) throws Exception {
        // Setup terminal and screen layers
        try (Terminal terminal = new DefaultTerminalFactory().createTerminal()) {
            screen = new TerminalScreen(terminal);
            screen.startScreen();
            gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));

            // Panal Initialization
            Panel mainMenuPanel = new Panel();
            Panel startNewGame = new Panel();
            Panel resumeGame = new Panel();
            Panel curGame = new Panel();


//------------------Game Main Menu------------------//
            // Main Menu
            mainMenuPanel.setLayoutManager(new GridLayout(1));
            mainMenuPanel.setPreferredSize(new TerminalSize(30, 8));
            mainMenuPanel.setPosition(new TerminalPosition(0, 0));
            mainMenuPanel.addComponent(new Label("Main Menu"), GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER, true, false, 1, 1));
            mainMenuPanel.addComponent(new Label("   "));
            mainMenuPanel.addComponent(new Button("New Game", () -> {
                window.setComponent(startNewGame);
            }));
            mainMenuPanel.addComponent(new Button("Resume Game", () -> {
                window.setComponent(resumeGame);
            }));
            mainMenuPanel.addComponent(new Button("Exit Game", () -> {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
            String aboutText = """
                    TRPGeneration
                    a simple RPG game engine.
                    Copyright (C) 2022  Bo ZHANG; Jingqi DOU; Juhao TAO; Xiangda LI; Ge ZHAN
                    This program is free software: you can redistribute it and/or modify
                    it under the terms of the GNU General Public License as published by
                    the Free Software Foundation, either version 3 of the License, or
                    (at your option) any later version.

                    This program is distributed in the hope that it will be useful,
                    but WITHOUT ANY WARRANTY; without even the implied warranty of
                    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
                    GNU General Public License for more details.

                    You should have received a copy of the GNU General Public License
                    along with this program.  If not, see <https://www.gnu.org/licenses/>.""";
            mainMenuPanel.addComponent(new Button("About", () ->
                    new MessageDialogBuilder()
                            .setTitle("About")
                            .setText(aboutText)
                            .addButton(MessageDialogButton.Close)
                            .build()
                            .showDialog(gui)
            ));

//------------------New Game Menu------------------//
            startNewGame.setLayoutManager(new GridLayout(2));
            startNewGame.setPreferredSize(new TerminalSize(60, 5));
            startNewGame.addComponent(new Label("New Game"), GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER, true, false, 1, 1));
            startNewGame.addComponent(new Label("   "));
            startNewGame.addComponent(new Label("Please Enter the PATH of a Map:"));
            final TextBox mapPath = new TextBox(new TerminalSize(25, 1)).addTo(startNewGame);

            startNewGame.addComponent(new Label("Enter Your Player Name:"));
            final TextBox nameText = new TextBox(new TerminalSize(25, 1)).addTo(startNewGame);
            final String[] playerName = {nameText.getText()};
            startNewGame.addComponent(new Label("   "));
            startNewGame.addComponent(new Label("   "));

            new Button("Start Game!", () -> {
                // load game with method in SaveParser
                if (playerName[0].equals("")) {
                    playerName[0] = "Player";
                }
                final String gamePath = mapPath.getText();
                try {
                    game = SaveParser.loadGame(gamePath);
                    game.player.name = playerName[0];
                    startNewGame.removeAllComponents();
                    window.setComponent(curGame);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).addTo(startNewGame);

            startNewGame.addComponent(new Label("   "));

            // Back to Main Menu
            new Button("Go Back", () -> {
                window.setComponent(mainMenuPanel);
            }).addTo(startNewGame);

//------------------Resume Game Menu------------------//
            resumeGame.setLayoutManager(new GridLayout(2));
            resumeGame.setPreferredSize(new TerminalSize(60, 5));
            resumeGame.addComponent(new Label("New Game"), GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER, true, false, 1, 1));
            resumeGame.addComponent(new Label("   "));
            resumeGame.addComponent(new Label("Please Enter the PATH of a Save:"));
            final TextBox saveText = new TextBox(new TerminalSize(25, 1)).addTo(resumeGame);
            resumeGame.addComponent(new Label("   "));
            resumeGame.addComponent(new Label("   "));

            new Button("Start Game!", () -> {
                final String savePath = saveText.getText();
                try {
                    SaveParser.loadSave(savePath, game.player, game.level);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).addTo(resumeGame);

            // Exit Game
            new Button("Go Back", () -> {
                window.setComponent(mainMenuPanel);
            }).addTo(resumeGame);

            resumeGame.addComponent(new Label("   "));

//------------------New Game Start------------------//
            // set terminal size
            curGame.setPreferredSize(new TerminalSize(70, 20));
            curGame.setLayoutManager(new GridLayout(3));
            levelToPanel(curGame, game);
            // when the game starts, display the main menu
            window.setComponent(mainMenuPanel); // change to mainMenuPanel to display main menu, Remember!!!
            gui.addWindowAndWait(window);
        }
    }

    /**
     *  convert game to panel
     *
     * @param mainPanel
     * @param game
     * @author Bo ZHANG
     */
    static void gameToPanel(Panel mainPanel, Game game) {
        // remove all components at the beginning
        mainPanel.removeAllComponents();
        switch (game.player.p) {
            case NORMAL -> levelToPanel(mainPanel, game);
            case FIGHTING -> fightToPanel(mainPanel, game);
            case TRADING -> tradeToPanel(mainPanel, game);
            case TALKING -> talkToPanel(mainPanel, game);
            case SAVING -> saveToPanel(mainPanel, game);
            case INTERACTING -> interactToPanel(mainPanel, game);
        }
        window.setComponent(mainPanel);
    }

    /**
     * convert interact to panel
     *
     * @param mainPanel
     * @param game
     * @author Bo ZHANG
     */
    private static void interactToPanel(Panel mainPanel, Game game) {
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        String interactText = """
                You are not supposed to see this.
                If you see this, please contact the developer.
                Please also tell the developer what you did to get here.
                We are sorry for the inconvenience.
                """;
        new MessageDialogBuilder()
                .setTitle("Interacting Error")
                .setText("You can't interact with this object.")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(gui);
    }

    /**
     * convert level to panel
     *
     * @param mainPanel
     * @param game
     * @author Bo ZHANG
     */
    static void levelToPanel(Panel mainPanel, Game game) {
        mainPanel.setLayoutManager(new GridLayout(3));
        Panel leftPanel = new Panel();
        mainPanel.addComponent(leftPanel, GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        Panel mapPanel = new Panel();
        leftPanel.addComponent(mapPanel.withBorder(Borders.singleLine("Map")), GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        Table<String> table = new Table<>("L", "-1", "M", "+1", "R");
        //String[][] mapString = game.level.getSurronding(game.player.curLoc);
        String[][] mapString = {{"1", "2", "3", "4", "5"}, {"1", "2", "3", "4", "5"}, {"1", "2", "3", "4", "5"}, {"1", "2", "3", "4", "5"}, {"1", "2", "3", "4", "5"}};
        for (String[] strings : mapString) {
            table.getTableModel().addRow(strings);
        }
        mapPanel.addComponent(table);
        Panel skillPanel = new Panel();
        Table<String> skillTable = new Table<>("Skill", "Atk", "MP");
        //String[][] skillString = game.player.skillsToUIArray();
        String[][] skillString = {{"Fire", "10", "5"}, {"Ice", "10", "5"}, {"Thunder", "10", "5"}, {"Heal", "10", "5"}};
        for (String[] strings : skillString) {
            skillTable.getTableModel().addRow(strings);
        }
        skillPanel.addComponent(skillTable);
        leftPanel.addComponent(skillPanel.withBorder(Borders.singleLine("Skills")), GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));

        Panel midPanel = new Panel();
        mainPanel.addComponent(midPanel, GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        midPanel.addComponent(new Label("Terminal"));
        midPanel.addComponent(new Button("Left", () -> {
            try {
                game.player.curLoc = game.player.curLoc.getLeft();
            } catch (IllegalArgumentException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText("You can't go that way!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
            gameToPanel(mainPanel, game);
        }));
        midPanel.addComponent(new Button("Right", () -> {
            try {
                game.player.curLoc = game.player.curLoc.getRight();
            } catch (IllegalArgumentException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText("You can't go that way!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
            gameToPanel(mainPanel, game);
        }));
        midPanel.addComponent(new Button("Up", () -> {
            try {
                game.player.curLoc = game.player.curLoc.getUp();
            } catch (IllegalArgumentException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText("You can't go that way!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
            gameToPanel(mainPanel, game);
        }));
        midPanel.addComponent(new Button("Down", () -> {
            try {
                game.player.curLoc = game.player.curLoc.getDown();
            } catch (IllegalArgumentException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText("You can't go that way!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
            gameToPanel(mainPanel, game);
        }));
        midPanel.addComponent(new Label("   "));
        midPanel.addComponent(new Button("Interact", () -> {
            if (game.level.isInteractable(game.player.curLoc)) {
                NPC npc = game.level.getNPC(game.player.curLoc);
                npc.interact(game.player);
                if (npc instanceof Trader curNPC) {
                    game.setInteraction(new Trade(game.player, curNPC));
                } else if (npc instanceof TalkNPC curNPC) {
                    game.setInteraction(new Talk(game.player, curNPC));
                } else if (npc instanceof Enemy curNPC) {
                    game.setInteraction(new Fight(game.player, curNPC));
                } else if (npc instanceof Saver) {
                    game.setInteraction(new SaveParser(game.player));
                }
                // Update the panel
                gameToPanel(mainPanel, game);
            } else {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText("There's nothing to interact with here!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
        }));


        Panel rightPanel = new Panel();
        Panel playerPanel = new Panel();
        rightPanel.addComponent(playerPanel.withBorder(Borders.singleLine("Player")), GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        mainPanel.addComponent(rightPanel, GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        //String playerName = game.player.name;
        String playerName = "111";
        playerPanel.addComponent(new Label("Name: " + playerName));
        //String playerLevel = game.player.fightStat.level;
        String playerLevel = "111";
        playerPanel.addComponent(new Label("Level: " + playerLevel));
        //String playerHp = game.player.fightStat.hp + "/" + game.player.fightStat.maxHp;
        String playerHp = "111";
        playerPanel.addComponent(new Label("HP: " + playerHp));
        //String playerMp = game.player.fightStat.mp + "/" + game.player.fightStat.maxMp;
        String playerMp = "111";
        playerPanel.addComponent(new Label("MP: " + playerMp));
        //String playerAtk = game.player.fightStat.atk + "";
        String playerAtk = "111";
        playerPanel.addComponent(new Label("ATK: " + playerAtk));
        //String playerDef = game.player.fightStat.def + "";
        String playerDef = "111";
        playerPanel.addComponent(new Label("DEF: " + playerDef));
        //String playerMoney = game.player.money + "";
        String playerMoney = "111";
        playerPanel.addComponent(new Label("Money: " + playerMoney));
        Panel itemPanel = new Panel();
        rightPanel.addComponent(itemPanel.withBorder(Borders.singleLine("Items")), GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        Table<String> itemTable = new Table<>("Item", "Amount", "Recover");
        //List<Backpack.Thing> itemList = game.player.backpack.getThings();
        Backpack backpack = new Backpack();
        backpack.add("Potion", 5, 10);
        backpack.add("Meat", 5, 10);
        List<Backpack.Thing> itemList = backpack.getThings();
        for (Backpack.Thing thing : itemList) {
            itemTable.getTableModel().addRow(thing.name, thing.amount + "", thing.recover + "");
        }
        itemTable.setSelectAction(() -> {
            int row = itemTable.getSelectedRow();
            Backpack.Thing thing = itemList.get(row);
            try {
                System.out.println(thing.name);
                game.player.use(thing, game.player.fightStat);
                table.getTableModel().setCell(1, row, thing.amount + "");
            } catch (GameException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText(e.getMessage())
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
        });
        itemPanel.addComponent(itemTable);
    }

    @SuppressWarnings("unchecked")
    /**
     * convert talk to panel
     *
     * @param mainPanel
     * @param game
     * @author Bo ZHANG
     */
    static void talkToPanel(Panel mainPanel, Game game) {
        mainPanel.setLayoutManager(new GridLayout(2));
        Talk talk = (Talk) game.interaction;
        // check if the panel is not talk panel
        Table<String> table;
        if (mainPanel.getChildrenList().isEmpty()) {
            // create table
            table = new Table<>("Conversation");
            mainPanel.addComponent(table);
        } else {
            // grab table from mainPanel
            if (mainPanel.getChildrenList().get(0) instanceof Table) {
                table = (Table<String>) mainPanel.getChildrenList().get(0);
            } else {
                mainPanel.removeAllComponents();
                table = new Table<>("Conversation");
                mainPanel.addComponent(table, GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
            }
            // remove ActionListBox in the panel
            ActionListBox actionListBox = (ActionListBox) mainPanel.getChildrenList().get(1);
            mainPanel.removeComponent(actionListBox);
        }
        mainPanel.addComponent(new Label("Talk to " + talk.npc.name));
        // no scroll support in lanterna, limit table size to 7
        // check table row size
        while (table.getTableModel().getRowCount() > 7) {
            table.getTableModel().removeRow(0);
        }
        table.getTableModel().addRow(talk.curSentence.sentence);

        ActionListBox actionListBox = new ActionListBox();
        mainPanel.addComponent(actionListBox, GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        for (Sentence sentence : talk.curSentence.nextSentences) {
            actionListBox.addItem(sentence.sentence, () -> {
                table.getTableModel().addRow(sentence.sentence);
                try {
                    talk.nextSentence(sentence.sentence);
                } catch (GameException e) {
                    talk.interrupt();
                    new MessageDialogBuilder()
                            .setTitle("Error")
                            .setText(e.getMessage())
                            .addButton(MessageDialogButton.OK)
                            .build()
                            .showDialog(gui);
                }
                talk.nextSentence();
                if (talk.curSentence.nextSentences.isEmpty()) {
                    gameToPanel(mainPanel, game);
                } else { // if there are more sentences keep the talk panel
                    talkToPanel(mainPanel, game);
                }
            });
        }

    }

    /**
     * convert trade to panel
     *
     * @param mainPanel
     * @param game
     */
    static void tradeToPanel(Panel mainPanel, Game game) {
        Trade trade = (Trade) game.interaction;
        Panel leftPanel = new Panel();
        Panel rightPanel = new Panel();
        mainPanel.addComponent(leftPanel.withBorder(Borders.singleLine("Player's Backpack")), GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        mainPanel.addComponent(rightPanel.withBorder(Borders.singleLine("NPC's Backpack")), GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING, true, false, 1, 1));
        rightPanel.addComponent(new Label("Trading with " + trade.npc.name));
        Table<String> playerTable = new Table<>("Item", "Amount", "Recover");
        List<Backpack.Thing> playerString = trade.player.b.getThings();
        for (Backpack.Thing thing : playerString) {
            playerTable.getTableModel().addRow(thing.name, thing.amount + "", thing.recover + "");
        }
        leftPanel.addComponent(playerTable);
        Table<String> npcTable = new Table<>("Item", "Amount", "Recover", "Price");
        List<Backpack.Thing> npcString = trade.npc.backpack.getThings();
        for (Backpack.Thing thing : npcString) {
            npcTable.getTableModel().addRow(thing.name, thing.amount + "", thing.recover + "", thing.price + "");
        }
        npcTable.setSelectAction(() -> {
            int row = npcTable.getSelectedRow();
            Backpack.Thing thing = npcString.get(row);
            try {
                trade.trade(thing);
            } catch (GameException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText(e.getMessage())
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
            gameToPanel(mainPanel, game);
        });
        rightPanel.addComponent(npcTable);
        leftPanel.addComponent(new Button("Back", () -> {
            try {
                trade.interrupt();
            } catch (GameException e) {
                new MessageDialogBuilder()
                        .setTitle("Error")
                        .setText(e.getMessage())
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(gui);
            }
            gameToPanel(mainPanel, game);
        }));

    }

    /**
     * convert fight to panel
     *
     * @param mainPanel
     * @param game
     * @author Bo ZHANG
     */
    static void fightToPanel(Panel mainPanel, Game game) {
        Fight fight = (Fight) game.interaction;
    }

    /**
     * convert save to panel
     *
     * @param mainPanel
     * @param game
     */
    static void saveToPanel(Panel mainPanel, Game game) {
        mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        SaveParser saveParser = (SaveParser) game.interaction;
        mainPanel.addComponent(new Label("Saving..."));
        String saveName = "path placeholder";
        try {
            saveName = SaveParser.createSaveFile(game);
        } catch (IOException e) {
            new MessageDialogBuilder()
                    .setTitle("Error")
                    .setText(e.getMessage())
                    .addButton(MessageDialogButton.OK)
                    .build()
                    .showDialog(gui);
        }
        new MessageDialogBuilder()
                .setTitle("Save")
                .setText("Save successfully! at " + new File(saveName).getAbsolutePath())
                .addButton(MessageDialogButton.OK)
                .build()
                .showDialog(gui);
        saveParser.interrupt();
        gameToPanel(mainPanel, game);
    }
}
