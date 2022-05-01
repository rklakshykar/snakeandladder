# snakeandladder
Stats to be captured:
The following stats are to be captured across all the simulations:

● Minimum/Average/Maximum number of rolls needed to win.
● Minimum/Average/Maximum amount of climbs during the game 
A climb is the amount of distance covered by climbing up a ladder. For example,
if the token goes up a ladder from 21 to 51, the distance climbed is 30.

● Minimum/Average/Maximum amount of slides during the game
○ A slide is the amount of distance covered by sliding down a snake. For example,
if the token goes down a snake from 88 to 48, the slide distance is 40.

● The biggest climb in a single turn.
● The biggest slide in a single turn.
● Longest turn. The longest turn is the highest streak of consecutive rolls due to rolling 6s.
○ Examples:
■ Roll x is 5 and roll y is 3, then the longest turn so far is 5 as it is the
highest roll.
■ Rolls in turn x are [6,4] and rolls in turn y are [6,3]. The longest turn is
[6,4].
■ Rolls in turn x are [6,6,6,5] and rolls in turn y are [6,6,6,6,1]. The longest
turn is [6,6,6,6,1].

● Minimum/Average/Maximum unlucky rolls during the game
○ An unlucky roll is considered when any of the following is true
■ A player lands on a snake

● Minimum/Average/Maximum lucky rolls during the game
○ A lucky roll is considered when any of the following is true
■ A player lands on a ladder
■ Misses a snake by 1 or 2 steps
■ When they roll the exact number needed to win after 94 in a single roll.