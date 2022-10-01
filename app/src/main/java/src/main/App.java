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

import java.io.IOException;


public class App {
    static BasicWindow window = new BasicWindow();
    private static Screen screen;

    static MultiWindowTextGUI gui;
    static Game game;

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
                mainMenuPanel.removeAllComponents();
                window.setComponent(startNewGame);
            }));
            mainMenuPanel.addComponent(new Button("Resume Game", () -> {
                mainMenuPanel.removeAllComponents();
                window.setComponent(resumeGame);
            }));
            mainMenuPanel.addComponent(new Button("Exit Game", () -> {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));

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

            // Exit Game
            new Button("Exit Game:(", () -> {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
            new Button("Exit Game:(", () -> {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    static void gameToPanel(Panel mainPanel, Game game) {
        // remove all components at the beginning
        mainPanel.removeAllComponents();
        switch (game.player.p) {
            case NORMAL -> levelToPanel(mainPanel, game);
            case FIGHTING -> fightToPanel(mainPanel, game);
            case TRADING -> tradeToPanel(mainPanel, game);
            case TALKING -> talkToPanel(mainPanel, game);
            case SAVING -> saveToPanel(mainPanel, game);
        }
        window.setComponent(mainPanel);
    }

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
        //Backpack.Thing[] itemString = game.player.backpack.getThings();
        Backpack backpack = new Backpack();
        backpack.add("Potion", 5, 10);
        Backpack.Thing[] itemString = backpack.getThings().toArray(new Backpack.Thing[0]);
        for (Backpack.Thing thing : itemString) {
            itemTable.getTableModel().addRow(thing.name, thing.amount + "", thing.recover + "");
        }
        table.setSelectAction(() -> {
            int row = table.getSelectedRow();
            Backpack.Thing thing = itemString[row];
            try {
                game.player.b.use(thing, game.player.fightStat);
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

    static void talkToPanel(Panel mainPanel, Game game) {

    }

    static void tradeToPanel(Panel mainPanel, Game game) {

    }

    static void fightToPanel(Panel mainPanel, Game game) {

    }

    static void saveToPanel(Panel mainPanel, Game game) {

    }
}
