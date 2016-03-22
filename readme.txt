******************************************************************************************
************************************ BREAKOUT README *************************************
******************************************************************************************

//////////////////// Building and Running ////////////////////

Building without any Command line Parameter: Inside the directory just run the command:
make run

Building with Command line Parameters: Inside the directory run the command like following:
make run 40 60
where 40 is the frame rate in frame per second and 60 is the speed of ball/paddle in pixel per second.

Read along to know what can be the range of these parameters. :)

//////////////////// Range of Fps and Speed ////////////////////

Range of Fps: The recommended FPS for my implementation is $[35-150]$. However if you input something 
out of the range the game will run with default fps which is 60. If your input is not parse-able 
(like if you input \"abcd\" instead of a number), the game will also run with default parameters.

Range of Speed: My implementation is assuming the speed is in pixel per second. So the recommended range 
will be $[35-200]$. Again if your input is out of range, the game will run with default speed which is 60. 
The game actually has a different time responsible to take care of speed of the ball, so this speed is 
completely independent of the frame per second parameter. 

//////////////////// Playing the game ////////////////////

The game will ask you if you want to cheat a bit to test the additional feature which is included 
after this section. If you choose no, following is the way you play the game.

At the beginning of the game everything is at a halt. Move the paddle to start the game.

Moving the paddle: Move the left and right with the left and right key button.

Changing the ball speed dynamically: You can click on right button to increase speed of the ball and 
left click to decrease it.

Score: The game awards the player with every hit with the brick and the paddle.

Resizing: The game handles resizing the window and scales the components accordingly. However I am 
using a scaling factor, so the if the new size is more than the original screen size but less than 
twice of it, everything will be twice the size.Not very smooth, but at least it works for the game.

//////////////////// Required Features Covered ////////////////////

-- Used Java 8 and Swing and Painter's algorithm
-- 5 rows of colored block and movable paddle
-- Awarding the player for every hit
-- Added splash screen
-- Game accepts two command line parameters
-- Changing frame rate does not change ball speed
-- Used both keyboard and mouse input effectively

//////////////////// Additional Features Covered ////////////////////

-- Added music to the background
-- Added manual dynamic change of ball speed while playing
-- Added Multiple levels. Although the looks of the level change only
   on level 2. But it gets significantly harder on successive levels to
   break the bricks. If you want to test this feature, choose yes to the
   the question at the beginning of the game. You will only need to break
   3 bricks in order to win then. :)
-- Added the feature of replaying the level if you lost. To test this feature
   let the ball fall. :)
-- Not an additional feature, but the tried to be as efficient as possible. The
   runtime of the game is (on the outside) O(fps * n + speed) where n is the number of
   bricks.