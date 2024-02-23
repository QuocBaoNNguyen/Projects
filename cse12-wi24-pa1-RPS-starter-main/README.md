# RPS (Rock-Paper-Scissors)

      Running the tester on UNIX based systems (including a mac):

      * Compile: `javac -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. RPSTester.java`
      * Execute: `java -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. org.junit.runner.JUnitCore RPSTester`

      Running the tester on Windows systems:

      * Compile: `javac -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" RPSTester.java`
      * Execute: `java -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" org.junit.runner.JUnitCore RPSTester`


### Game Play

When the user starts your game, it should play the game of Rock-Paper-Scissors with the user until the user types `q`. Here is an example run. User input appears after the `(Type the move or q to quit)` prompt.

```
$ javac RPS.java
$ java RPS
Let's play! What's your move? (Type the move or q to quit)
rock
I chose scissors. You win.
Let's play! What's your move? (Type the move or q to quit)
rock
I chose rock. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
rock
I chose paper. I win.
Let's play! What's your move? (Type the move or q to quit)
q
Thanks for playing!
Our most recent games were: 
Me: paper, You: rock
Me: rock, You: rock
Me: scissors, You: rock
Our overall stats are:
I won: 33.33%
You won: 33.33%
We tied: 33.33%
```


Additional output examples can be viewed at the end of the writeup under "**Example Output for Part 3”.**


### What to Implement

Your task is to implement the following methods in the following files:

#### `RPSAbstract.java`
* `public boolean isValidMove(String move)`: Returns true if the move is valid, and returns false otherwise.  An invalid move is a move that is **not** in the `possibleMoves` array. 
* `public void playRPS(String playerMove, String cpuMove)`: Play one game of RPS using the moves provided.  This method should:
    * Determine the game outcome. **Hint:** use the `determineWinner` method (see below).  
    * Print an appropriate message as shown above (e.g. `I chose scissors. You win.`).  Please use the provided static  member string variables for this purpose.  
    * Record the moves made by the CPU and the Player in the appropriate member variables (`cpuMoves` and `playerMoves`).
    * Increment the number of games played and either the number of ties, CPU wins or player wins, depending on the outcome in the appropriate member variables (`numPlayerWins`, `numCPUWins`, `numTies`, and `numGames`) .



#### `RPS.java`
* `public int determineWinner(String playerMove, String cpuMove)`: Given the two moves, determine the outcome of the game (-1 for invalid input, 0 for tie, 1 for player win, and 2 for cpu win).  But this is not as simple as “rock beats scissors”, “scissors beats paper” and “paper beats rock”!
    * Here is how you should determine the outcome:  All of the possible moves are stored in the array `possibleMoves`.  Each element in the array beats the **previous** element in the array, and the end wraps around to the beginning. All other pairings lead to a tie. This means each move beats one move and loses to one move. For example, in the move set  `{ "elephant", "alligator", "hedgehog", "mouse" }` mouse beats hedgehog, hedgehog beats alligator, alligator beats elephant, and elephant beats mouse (all other pairings tie).  <span style="text-decoration:underline;">Your code should be able to handle **any** array of possible moves with at least 3 elements.</span>
    * Make sure you use the provided static member variables from RPSAbstract (`CPU_WIN_OUTCOME, PLAYER_WIN_OUTCOME, TIE_OUTCOME, INVALID_INPUT_OUTCOME`)  for your return statements. 

* `public static void main(String[] args)`: main method that reads user input, generates CPU moves, and plays the game. This method is partially completed, you are to fill in the rest.
    * The game should repeat until the player enters `q`.
    * If the player enters `q`, then the game should end and the system should print out up to the last 10 games, in reverse order. If 10 games have not been played, it should print out as many games as has been played. Use the provided `displayStats()` method to print the win and tie statistics as shown in the example. As long as you update the member variables as described in `playRPS()`, then a call to `displayStats()` should display the statistics correctly.
    * If there is an invalid move, do not update any instance variables in the game. Do not store the player move or the CPU move in the `playerMoves` and `cpuMoves` arrays. Do not update any of the game stats (`numGames`, `numTies`, `numPlayerWins`, `numCPUWins`).  (Hint: Check if the move is valid before you call `playRPS()`and if it is not, do not call `playRPS()`).
    * Call `genCPUMove()` once each time you prompt the player for a new move. Even if the previous player move is invalid, generate a new move for the CPU.
    * Use the provided static member variables inherited from `RPSAbstract` (`PROMPT_MOVE`, `INVALID_INPUT`) for printing to standard output.


### Additional Implementation Requirements
* You **may not use additional Java packages** from what is given. Please do not include your own import statements. You should be able to do the assignment with what is given.
* You will write your code for the methods described above. Please **do not** alter the provided variable names in the starter code, we will test your code using these variables. Additionally, do not delete variables or change the number of arguments in the methods. Doing so will result in incorrect auto-graded results. Use all provided Strings for consistency.


## Testing
We have provided some JUnit tests in `RPSTester.java`. You are encouraged (but not required this week) to add your own tests to this file. For this week we have given you all of the tests we will grade you on, but in the future that will not be the case!  

## Style
On this assignment, we will give you feedback on style but not deduct points for problems with style. For future assignments, we will be grading the following for style on all files you submit:
* File header
* Class header
* Method header(s)
* Inline comments
* Proper indentation
* Descriptive variable names
* No magic numbers (Exception: Magic numbers can be used for testing.)
* Reasonably short methods (if you have implemented each method according to the specification in this write-up, you’re fine). This is not enforced as strictly.
* Lines shorter than 80 characters
* Javadoc conventions (`@param`, `@return` tags, `/** comments */`, etc.)

A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/guides/blob/main/resources/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.


## Example Output for Part 3
User enters `q` immediately:


```
$ java RPS
Let's play! What's your move? (Type the move or q to quit)
q
Thanks for playing!
Our most recent games were: 
Our overall stats are:
I won: NaN%
You won: NaN%
We tied: NaN%
```


More than 10 games are played:


```
$ java RPS
Let's play! What's your move? (Type the move or q to quit)
rock
I chose scissors. You win.
Let's play! What's your move? (Type the move or q to quit)
rock
I chose rock. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
rock
I chose paper. I win.
Let's play! What's your move? (Type the move or q to quit)
paper
I chose paper. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
paper
I chose paper. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
paper
I chose paper. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
scissors
I chose paper. You win.
Let's play! What's your move? (Type the move or q to quit)
scissors
I chose rock. I win.
Let's play! What's your move? (Type the move or q to quit)
scissors
I chose rock. I win.
Let's play! What's your move? (Type the move or q to quit)
rock
I chose paper. I win.
Let's play! What's your move? (Type the move or q to quit)
paper
I chose rock. You win.
Let's play! What's your move? (Type the move or q to quit)
q
Thanks for playing!
Our most recent games were: 
Me: rock, You: paper
Me: paper, You: rock
Me: rock, You: scissors
Me: rock, You: scissors
Me: paper, You: scissors
Me: paper, You: paper
Me: paper, You: paper
Me: paper, You: paper
Me: paper, You: rock
Me: rock, You: rock
Our overall stats are:
I won: 36.36%
You won: 27.27%
We tied: 36.36%
```


Invalid input:


```
$ java RPS
Let's play! What's your move? (Type the move or q to quit)
abc
That is not a valid move. Please try again.
Let's play! What's your move? (Type the move or q to quit)
rock
I chose rock. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
q
Thanks for playing!
Our most recent games were: 
Me: rock, You: rock
Our overall stats are:
I won: 0.00%
You won: 0.00%
We tied: 100.00%
```


Using a unique move set via command line arguments:


```
$ java RPS electric ground ice fire water
Let's play! What's your move? (Type the move or q to quit)
water
I chose ground. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
fire
I chose ice. You win.
Let's play! What's your move? (Type the move or q to quit)
ground
I chose ground. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
electric
I chose fire. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
ice
I chose water. It's a tie.
Let's play! What's your move? (Type the move or q to quit)
q
Thanks for playing!
Our most recent games were:
Me: water, You: ice
Me: fire, You: electric
Me: ground, You: ground
Me: ice, You: fire
Me: ground, You: water
Our overall stats are:
I won: 0.00%
You won: 20.00%
We tied: 80.00%
```

# Submission Instructions
There are two components you must submit for this assigment. Both are required by the due date.
1. Academic Integrity Pledge (Google Form). Once you submit the google form you are done with this component's submission.
2. Gradescope submission. Submit the following files to the "PA1" assignment on Gradescope:
    * `RPSAbstract.java`
    * `RPS.java`

**Important**: Even if your code does not pass all the tests, you will still be able to submit your files to receive partial points for the tests that you passed. **Make sure your code compiles on Gradescope in order to receive partial credit. The name of the files need to match those on the writeup, otherwise no points will be given for the submission.**
