import static java.lang.Integer.parseInt

/*
--- Day 6: Probably a Fire Hazard ---

Because your neighbors keep defeating you in the holiday house decorating contest year after year, you've decided to deploy one million lights in a 1000x1000 grid.

Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how to display the ideal lighting configuration.

Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore refers to 9 lights in a 3x3 square. The lights all start turned off.

To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent you in order.

For example:

turn on 0,0 through 999,999 would turn on (or leave on) every light.
toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the ones that were off.
turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
After following the instructions, how many lights are lit?

Your puzzle answer was 543903.

--- Part Two ---

You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from Ancient Nordic Elvish.

The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.

The phrase turn on actually means that you should increase the brightness of those lights by 1.

The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.

The phrase toggle actually means that you should increase the brightness of those lights by 2.

What is the total brightness of all lights combined after following Santa's instructions?

For example:

turn on 0,0 through 0,0 would increase the total brightness by 1.
toggle 0,0 through 999,999 would increase the total brightness by 2000000.
 */

def grid = new int[1000][1000]

new File("six.txt").eachLine { line ->
  List<String> commands = line.split(" ")

  if (commands[0].equals("toggle")) {
    List xy1 = commands[1].split(",")
    List xy2 = commands[3].split(",")
    toggle(grid, parseInt(xy1[0]), parseInt(xy1[1]), parseInt(xy2[0]), parseInt(xy2[1]))
  }
  else if (commands[0].equals("turn")) {
    if (commands[1].equals("off")) {
      List xy1 = commands[2].split(",")
      List xy2 = commands[4].split(",")
      turnOff(grid, parseInt(xy1[0]), parseInt(xy1[1]), parseInt(xy2[0]), parseInt(xy2[1]))
    }
    else {
      List xy1 = commands[2].split(",")
      List xy2 = commands[4].split(",")
      turnOn(grid, parseInt(xy1[0]), parseInt(xy1[1]), parseInt(xy2[0]), parseInt(xy2[1]))
    }
  }
}

min = 0
max = 0

int count = 0
for (x = 0; x < 1000; x++) {
  for (y = 0; y < 1000; y++) {
    count += grid[x][y]
    if (grid[x][y] > max) {
      max = grid[x][y]
    }
    if (grid[x][y] < min) {
      min = grid[x][y]
    }
  }
}

print "$count, $min, $max"

def toggle(int[][] grid, int x1, int y1, int x2, int y2) {
  for (x = x1; x <= x2; x++) {
    for (y = y1; y <= y2; y++) {
      grid[x][y] += 2
    }
  }
}

def turnOff(int[][] grid, int x1, int y1, int x2, int y2) {
  for (x = x1; x <= x2; x++) {
    for (y = y1; y <= y2; y++) {
      if (grid[x][y] > 0) {
        grid[x][y] -= 1
      }
    }
  }
}

def turnOn(int[][] grid, int x1, int y1, int x2, int y2) {
  for (x = x1; x <= x2; x++) {
    for (y = y1; y <= y2; y++) {
      grid[x][y] += 1
    }
  }
}

// 14330402