import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED = "Game not yet implemented.";

    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game

        String userInput = in.nextLine();
        System.out.println(PROMPT_MOVE);

        while (!userInput.equals(QUIT)) {
            String cpuMove = game.genCPUMove();
            if (game.isValidMove(userInput) == false) {
                System.out.println(INVALID_INPUT);
                System.out.println(PROMPT_MOVE);

            } else {
                game.playRPS(userInput, cpuMove);
                System.out.println(PROMPT_MOVE);
            }
            if (userInput.equals(QUIT)) {
                break;
            }
            userInput = in.next();

        }
        game.displayStats();

        // TODO: Insert the code to play the game.
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written
        // to do most of the work! And don't forget Javadoc.

        in.close();
    }

    /*
     * Takes in two moves and determines the winner of the two
     * 
     * @param playerMove and cpuMove are the moves inputted from the user
     * and the cpu, respectively
     * 
     * @return gives us back a string detailing who won
     */
    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
        // check input is valid
        boolean playerMoveValid = isValidMove(playerMove);
        boolean cpuMoveValid = isValidMove(cpuMove);
        int playerMoveNum = 0;
        int cpuMoveNum = 0;

        if ((playerMoveValid == false) || (cpuMoveValid == false)) {
            return INVALID_INPUT_OUTCOME;
        }

        for (int i = 0; i < possibleMoves.length; i++) {
            if (playerMove.equals(possibleMoves[i])) {
                playerMoveNum = i;
            }
        }
        for (int k = 0; k < possibleMoves.length; k++) {
            if (cpuMove.equals(possibleMoves[k])) {
                cpuMoveNum = k;
            }
        }
        // win-lose if statements
        if ((playerMoveNum - 1) == cpuMoveNum) {
            return PLAYER_WIN_OUTCOME;
        } else if ((cpuMoveNum - 1) == playerMoveNum) {
            return CPU_WIN_OUTCOME;
        } else if (((playerMoveNum + 1) == possibleMoves.length) &&
                (cpuMoveNum == 0)) {
            return CPU_WIN_OUTCOME;
        } else if (((cpuMoveNum + 1) == possibleMoves.length) &&
                (playerMoveNum == 0)) {
            return PLAYER_WIN_OUTCOME;
        } else {
            return TIE_OUTCOME;
        }

    }
}
