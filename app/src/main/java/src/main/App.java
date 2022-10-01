package src.main;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.gui2.DefaultTextGUIGraphics;


import java.io.File;
import java.io.IOException;
import java.lang.Object;
import java.util.List;


public class App {
    static BasicWindow window = new BasicWindow();
    private static Screen screen;
    static Game game;

    public static void main(String[] args) throws Exception {
        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();


        // Panal Initialization
        Panel mainMenuPanel = new Panel();
        Panel startNewGame = new Panel();
        Panel resumeGame = new Panel();
        Panel gameStart = new Panel();

//------------------Game Main Menu------------------//
        // Main Menu
        mainMenuPanel.setLayoutManager(new GridLayout(1));
        mainMenuPanel.setPreferredSize(new TerminalSize(30, 8));
        mainMenuPanel.setPosition(new TerminalPosition(0, 0));
        mainMenuPanel.addComponent(new Label("Main Menu"), GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER, true, false, 1, 1));
        mainMenuPanel.addComponent(new Label("   "));
        mainMenuPanel.addComponent(new Button("New Game", new Runnable() {
            @Override
            public void run() {
                mainMenuPanel.removeAllComponents();
                window.setComponent(startNewGame);
            }
        }));
        mainMenuPanel.addComponent(new Button("Resume Game", new Runnable() {
            @Override
            public void run() {
                mainMenuPanel.removeAllComponents();
                window.setComponent(resumeGame);
            }
        }));
        mainMenuPanel.addComponent(new Button("Exit Game", new Runnable() {
            @Override
            public void run() {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

        new Button("Start Game!", new Runnable() {
            @Override
            public void run() {
                // load game with method in SaveParser
                if (playerName[0].equals("")) {
                    playerName[0] = "Player";
                }
                final String gamePath = mapPath.getText();
                System.out.println(new File(gamePath).getAbsolutePath());
                try {
                    game = SaveParser.loadGame(gamePath);
                    game.player.name = playerName[0];
                    gameStart.setPreferredSize(new TerminalSize(Location.maxX , Location.maxY));
                    startNewGame.removeAllComponents();
                    window.setComponent(gameStart);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).addTo(startNewGame);

        startNewGame.addComponent(new Label("   "));

        // Exit Game
        new Button("Exit Game:(", new Runnable() {
            @Override
            public void run() {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

        new Button("Start Game!", new Runnable() {
            @Override
            public void run() {
                final String savePath = saveText.getText();
                try {
                    Game curGame = SaveParser.loadGame(savePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).addTo(resumeGame);

        // Exit Game
        new Button("Exit Game:(", new Runnable() {
            @Override
            public void run() {
                try {
                    terminal.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).addTo(resumeGame);

        resumeGame.addComponent(new Label("   "));

//------------------New Game Start------------------//
        // set terminal size
        gameStart.setPreferredSize(new TerminalSize(Location.maxX, Location.maxY));
    


        // when the game starts, display the main menu
        window.setComponent(mainMenuPanel);
        // Create and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

}

