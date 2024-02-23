/*
  Author: Liam Fernandez
  Email: lgf001@ucsd.edu
  Sources Used: Java Interface Documentation, Qingyang Hu

  This file is for CSE 12 PA1 in Winter 2024 and contains a tester for 
  debugging and testing purposes.
*/

import org.junit.*;

import static org.junit.Assert.*;

import java.io.*;

public class RPSTester {
    public static final int CPU_WIN_OUTCOME = 2;
    public static final int PLAYER_WIN_OUTCOME = 1;
    public static final int TIE_OUTCOME = 0;
    public static final int INVALID_INPUT_OUTCOME = -1;
    String[] defaultMoves = {"rock", "paper", "scissors"};
    String[] pokemon = {"electric", "ground", "ice", "fire", "water"};
    private RPS rpsGame;
    private RPS pokemonGame;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setUp() {
        rpsGame = new RPS(defaultMoves);
        pokemonGame = new RPS(pokemon);
    }

    /* Tests for isValidMove */
    @Test
    public void testIsValidMoveValid() {
        assertTrue("rock should be a valid move", rpsGame.isValidMove("rock"));
        assertTrue("paper should be a valid move", rpsGame.isValidMove("paper"));
        assertTrue("scissors should be a valid move", rpsGame.isValidMove("scissors"));
    }

    @Test
    public void testIsValidMoveInvalid() {
        assertFalse("r should be an invalid move", rpsGame.isValidMove("r"));
        assertFalse("p should be an invalid move", rpsGame.isValidMove("p"));
        assertFalse("s should be an invalid move", rpsGame.isValidMove("s"));
    }

    @Test
    public void testIsValidMoveNull() {
        assertFalse("null should be an invalid move", rpsGame.isValidMove(null));
    }

    /* Tests for play */
    @Test
    public void testPlayPlayerWin() {
        rpsGame.playRPS("paper", "rock");
        assertEquals(1, rpsGame.numPlayerWins);
        assertEquals("paper", rpsGame.playerMoves[0]);
        assertEquals("rock", rpsGame.cpuMoves[0]);
        assertEquals(1, rpsGame.numGames);
        rpsGame.playRPS("rock", "scissors");
        assertEquals(2, rpsGame.numPlayerWins);
        assertEquals("rock", rpsGame.playerMoves[1]);
        assertEquals("scissors", rpsGame.cpuMoves[1]);
        assertEquals(2, rpsGame.numGames);
        rpsGame.playRPS("scissors", "paper");
        assertEquals(3, rpsGame.numPlayerWins);
        assertEquals("scissors", rpsGame.playerMoves[2]);
        assertEquals("paper", rpsGame.cpuMoves[2]);
        assertEquals(3, rpsGame.numGames);
        assertEquals(0, rpsGame.numCPUWins);
        assertEquals(0, rpsGame.numTies);
    }

    @Test
    public void testPlayCPUWin() {
        rpsGame.playRPS("rock", "paper");
        assertEquals(1, rpsGame.numCPUWins);
        assertEquals("rock", rpsGame.playerMoves[0]);
        assertEquals("paper", rpsGame.cpuMoves[0]);
        assertEquals(1, rpsGame.numGames);
        rpsGame.playRPS("scissors", "rock");
        assertEquals(2, rpsGame.numCPUWins);
        assertEquals("scissors", rpsGame.playerMoves[1]);
        assertEquals("rock", rpsGame.cpuMoves[1]);
        assertEquals(2, rpsGame.numGames);
        rpsGame.playRPS("paper", "scissors");
        assertEquals(3, rpsGame.numCPUWins);
        assertEquals("paper", rpsGame.playerMoves[2]);
        assertEquals("scissors", rpsGame.cpuMoves[2]);
        assertEquals(3, rpsGame.numGames);
        assertEquals(0, rpsGame.numPlayerWins);
        assertEquals(0, rpsGame.numTies);
    }

    @Test
    public void testPlayTies() {
        rpsGame.playRPS("paper", "paper");
        assertEquals(1, rpsGame.numTies);
        assertEquals("paper", rpsGame.playerMoves[0]);
        assertEquals("paper", rpsGame.cpuMoves[0]);
        assertEquals(1, rpsGame.numGames);
        rpsGame.playRPS("rock", "rock");
        assertEquals(2, rpsGame.numTies);
        assertEquals("rock", rpsGame.playerMoves[1]);
        assertEquals("rock", rpsGame.cpuMoves[1]);
        assertEquals(2, rpsGame.numGames);
        rpsGame.playRPS("scissors", "scissors");
        assertEquals(3, rpsGame.numTies);
        assertEquals("scissors", rpsGame.playerMoves[2]);
        assertEquals("scissors", rpsGame.cpuMoves[2]);
        assertEquals(3, rpsGame.numGames);
        assertEquals(0, rpsGame.numCPUWins);
        assertEquals(0, rpsGame.numPlayerWins);
    }

    @Test
    public void testPlayPokemon() {
        pokemonGame.playRPS("water", "fire");
        assertEquals(1, pokemonGame.numPlayerWins);
        assertEquals("water", pokemonGame.playerMoves[0]);
        assertEquals("fire", pokemonGame.cpuMoves[0]);
        assertEquals(1, pokemonGame.numGames);
        pokemonGame.playRPS("fire", "ice");
        assertEquals(2, pokemonGame.numPlayerWins);
        assertEquals("fire", pokemonGame.playerMoves[1]);
        assertEquals("ice", pokemonGame.cpuMoves[1]);
        assertEquals(2, pokemonGame.numGames);

        pokemonGame.playRPS("water", "electric");
        assertEquals(1, pokemonGame.numCPUWins);
        assertEquals("water", pokemonGame.playerMoves[2]);
        assertEquals("electric", pokemonGame.cpuMoves[2]);
        assertEquals(3, pokemonGame.numGames);
        pokemonGame.playRPS("fire", "water");
        assertEquals(2, pokemonGame.numCPUWins);
        assertEquals("fire", pokemonGame.playerMoves[3]);
        assertEquals("water", pokemonGame.cpuMoves[3]);
        assertEquals(4, pokemonGame.numGames);

        pokemonGame.playRPS("water", "ice");
        assertEquals(1, pokemonGame.numTies);
        assertEquals("water", pokemonGame.playerMoves[4]);
        assertEquals("ice", pokemonGame.cpuMoves[4]);
        assertEquals(5, pokemonGame.numGames);
        pokemonGame.playRPS("fire", "ground");
        assertEquals(2, pokemonGame.numTies);
        assertEquals("fire", pokemonGame.playerMoves[5]);
        assertEquals("ground", pokemonGame.cpuMoves[5]);
        assertEquals(6, pokemonGame.numGames);
    }

    /* Tests for determineWinner */
    @Test
    public void testDetermineWinnerRPS() {
        int outcome = rpsGame.determineWinner("scissors", "paper");
        assertEquals(PLAYER_WIN_OUTCOME, outcome);

        outcome = rpsGame.determineWinner("rock", "paper");
        assertEquals(CPU_WIN_OUTCOME, outcome);

        outcome = rpsGame.determineWinner("paper", "paper");
        assertEquals(TIE_OUTCOME, outcome);
    }

    @Test
    public void testDetermineWinnerInvalid() {
        int outcome = rpsGame.determineWinner("s", "paper");
        assertEquals(INVALID_INPUT_OUTCOME, outcome);
        outcome = rpsGame.determineWinner("rock", "p");
        assertEquals(INVALID_INPUT_OUTCOME, outcome);
        outcome = rpsGame.determineWinner("r", "p");
        assertEquals(INVALID_INPUT_OUTCOME, outcome);
    }

    @Test
    public void testDetermineWinnerPokemon() {
        int outcome = pokemonGame.determineWinner("electric", "water");
        assertEquals(PLAYER_WIN_OUTCOME, outcome);

        outcome = pokemonGame.determineWinner("ice", "fire");
        assertEquals(CPU_WIN_OUTCOME, outcome);

        outcome = pokemonGame.determineWinner("water", "ice");
        assertEquals(TIE_OUTCOME, outcome);
        outcome = pokemonGame.determineWinner("ground", "ground");
        assertEquals(TIE_OUTCOME, outcome);
    }

    // Helper function to test standard output from main
    public void checkStandardOutput(String inputString, String[] args, String expectedOutput) {
        // Initalize output and input streams
        PrintStream origOut = System.out;
        InputStream origIn = System.in;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newps = new PrintStream(baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(inputString.getBytes());

        // Replace output and input streams for the test so we can feed input
        // and compare the output
        try {
            System.setIn(bais);
        } catch (Exception e) {
            // shouldn't happen
        }
        System.setOut(newps);
        RPS.main(args);

        System.out.flush();

        // Set output and input stream to original streams
        System.setOut(origOut);
        try {
            System.setIn(origIn);
        } catch (Exception e) {
            // shouldn't happen
        }
        String stuOutput = baos.toString().replaceAll("\\R", "\n");

        assertEquals(expectedOutput, stuOutput);
    }

    /* Tests for main */
    @Test
    public void testRPSMainQuit() {
        String inputString = "q";
        String[] args = new String[]{};
        String expectedOutput = "Let's play! What's your move? (Type the move or q to quit)\nThanks for playing!\nOur most recent games were:\nOur overall stats are:\nI won: NaN%\nYou won: NaN%\nWe tied: NaN%\n";
        checkStandardOutput(inputString, args, expectedOutput);
    }

    @Test
    public void testRPSMainShortGame() {
        String inputString = "rock\npaper\nscissors\nq\n";
        String[] args = defaultMoves;
        String expectedOutput = "Let's play! What's your move? (Type the move or q to quit)\nI chose rock. It's a tie.\nLet's play! What's your move? (Type the move or q to quit)\nI chose scissors. I win.\nLet's play! What's your move? (Type the move or q to quit)\nI chose paper. You win.\nLet's play! What's your move? (Type the move or q to quit)\nThanks for playing!\nOur most recent games were:\nMe: paper, You: scissors\nMe: scissors, You: paper\nMe: rock, You: rock\nOur overall stats are:\nI won: 33.33%\nYou won: 33.33%\nWe tied: 33.33%\n";
        checkStandardOutput(inputString, args, expectedOutput);
    }

    @Test
    public void testRPSMainLongGame() {
        String inputString = "rock\npaper\nscissors\nrock\npaper\nscissors\nq\n";
        String[] args = defaultMoves;
        String expectedOutput = "Let's play! What's your move? (Type the move or q to quit)\nI chose rock. It's a tie.\nLet's play! What's your move? (Type the move or q to quit)\nI chose scissors. I win.\nLet's play! What's your move? (Type the move or q to quit)\nI chose paper. You win.\nLet's play! What's your move? (Type the move or q to quit)\nI chose paper. I win.\nLet's play! What's your move? (Type the move or q to quit)\nI chose paper. It's a tie.\nLet's play! What's your move? (Type the move or q to quit)\nI chose paper. You win.\nLet's play! What's your move? (Type the move or q to quit)\nThanks for playing!\nOur most recent games were:\nMe: paper, You: scissors\nMe: paper, You: paper\nMe: paper, You: rock\nMe: paper, You: scissors\nMe: scissors, You: paper\nMe: rock, You: rock\nOur overall stats are:\nI won: 33.33%\nYou won: 33.33%\nWe tied: 33.33%\n";
        checkStandardOutput(inputString, args, expectedOutput);
    }

    @Test
    public void testRPSMainInvalidMoves() {
        String inputString = "rock\nwater\nice\nq\n";
        String[] args = defaultMoves;
        String expectedOutput = "Let's play! What's your move? (Type the move or q to quit)\nI chose rock. It's a tie.\nLet's play! What's your move? (Type the move or q to quit)\nThat is not a valid move. Please try again.\nLet's play! What's your move? (Type the move or q to quit)\nThat is not a valid move. Please try again.\nLet's play! What's your move? (Type the move or q to quit)\nThanks for playing!\nOur most recent games were:\nMe: rock, You: rock\nOur overall stats are:\nI won: 0.00%\nYou won: 0.00%\nWe tied: 100.00%\n";
        checkStandardOutput(inputString, args, expectedOutput);
    }

    @Test
    public void testRPSMainPokemonMoves() {
        String inputString = "water\nfire\nice\nground\nelectric\nq\n";
        String[] args = pokemon;
        String expectedOutput = "Let's play! What's your move? (Type the move or q to quit)\nI chose ground. It's a tie.\nLet's play! What's your move? (Type the move or q to quit)\nI chose ice. You win.\nLet's play! What's your move? (Type the move or q to quit)\nI chose ground. You win.\nLet's play! What's your move? (Type the move or q to quit)\nI chose fire. It's a tie.\nLet's play! What's your move? (Type the move or q to quit)\nI chose water. You win.\nLet's play! What's your move? (Type the move or q to quit)\nThanks for playing!\nOur most recent games were:\nMe: water, You: electric\nMe: fire, You: ground\nMe: ground, You: ice\nMe: ice, You: fire\nMe: ground, You: water\nOur overall stats are:\nI won: 0.00%\nYou won: 60.00%\nWe tied: 40.00%\n";
        checkStandardOutput(inputString, args, expectedOutput);
    }
}