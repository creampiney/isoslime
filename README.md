# IsoSlime

This project is a part of final project in "Programming Methodology"

IsoSlime is a puzzle and short story game created with Java (using JavaFx for GUI) inspired from Sokoban (Famous box sliding puzzle game), 
ice sliding puzzle, and more puzzles games. The main objective of this game is completing puzzles and challenging player to beat as many puzzles as you can.
This game will be shown with isometric perspective.

## Game Explanation
### Overview

The game is presented in isometric perspective. The goal of the game is to go to the finish gate by passing puzzles. The maximum size of puzzle map is 10 x 10 dimension.

![image](https://github.com/creampiney/isoslime/assets/58902996/7fe149cd-24dc-47b8-91f5-60be1f07731e)

### Modes

There are 2 modes in the game:

1. Story Mode - Player can play with short stories for 50 scenes.
2. Puzzle Mode - Beat as many levels as possible with time limitation. (In puzzle mode, the time limit is 2 minutes and player will receive 30 seconds per 1 level completed)

### Player

The main character of this game is pink slime, player can control character by pressing mouse.

### Tiles

Tiles are platform that player can walk, slide, interact or prevent from moving. There are 4 kinds of tiles:

1. Normal Tiles - These tiles don’t have any ability, but player can walk through.

![image](https://github.com/creampiney/isoslime/assets/58902996/bcc902b8-5db9-4fd0-a5c1-149160f3e157)

2. Ice - The ice allows player and crate to slide.

![image](https://github.com/creampiney/isoslime/assets/58902996/e01ff0fc-74d0-4584-882d-7626fe828504)

3. Water - These tiles don’t let player pass through but allow crate to dropped and player can move after it dropped.

![image](https://github.com/creampiney/isoslime/assets/58902996/570ea783-5ba0-477f-9c78-4f0615dd764d)

4. Color Platform - These tiles can be switched between active and inactive. Player can move through active platform but can’t on inactive platform.

![image](https://github.com/creampiney/isoslime/assets/58902996/df346d26-9fbb-4186-8293-3ddce0daab00)

5. Wooden Crate - Player can push the crate, also these crated can be dropped in water.

![image](https://github.com/creampiney/isoslime/assets/58902996/7ba95a73-e7db-4933-86cd-5fe415e01de6)

6. Level Gate - This gate act like finish line of the level, player must go to this gate to complete that level.

![image](https://github.com/creampiney/isoslime/assets/58902996/e59654ba-fa38-4d57-925b-ec822745c4fb)

7. Tree - These trees act like obstacles but player can chop them down.

![image](https://github.com/creampiney/isoslime/assets/58902996/4c6b6dfc-5d00-4cae-abe4-3bd929a06d34)

8. Button Sensor - Player or crate can active these sensors. The platform with same color as sensor will change their status.

![image](https://github.com/creampiney/isoslime/assets/58902996/2fb79e5e-ab88-45de-8612-5ec1fe207cee)

9. Items - There are 3 kinds of items in this game which can be selected in inventory pane.

- Magic Wand - Player can teleport yourself within range of 2 blocks from player.
- Axe - Player can chop the tree by using axe, after chop tree, 1 wood will be received.
- Wood - Player can place wood on empty space in the map to create wooden platform.
