### Game Play

When the user starts the game, it should play the game of Rock-Paper-Scissors with the user until the user types `q`. Here is an example run. User input appears after the `(Type the move or q to quit)` prompt.

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








## Testing
The following game was rigorously tested to ensure that each aspect including input/output, stats, functionality, and data stored executed as intended. Specific tests can be fonud in `RPSTester.java`.


## Example Output 
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


