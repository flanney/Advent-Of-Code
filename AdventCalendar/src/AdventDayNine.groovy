import javafx.util.Pair

/*
--- Day 9: All in a Single Night ---

Every year, Santa manages to deliver all of his presents in a single night.

This year, however, he has some new locations to visit; his elves have provided him the distances between every pair of locations. He can start and end at any two (different) locations he wants, but he must visit each location exactly once. What is the shortest distance he can travel to achieve this?

For example, given the following distances:

London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141
The possible routes are therefore:

Dublin -> London -> Belfast = 982
London -> Dublin -> Belfast = 605
London -> Belfast -> Dublin = 659
Dublin -> Belfast -> London = 659
Belfast -> Dublin -> London = 605
Belfast -> London -> Dublin = 982
The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this example.

What is the distance of the shortest route?

Your puzzle answer was 141.

--- Part Two ---

The next year, just to show off, Santa decides to take the route with the longest distance instead.

He can still start and end at any two (different) locations he wants, and he still must visit each location exactly once.

For example, given the distances above, the longest route would be 982 via (for example) Dublin -> London -> Belfast.

What is the distance of the longest route?

Your puzzle answer was 736.
 */

Map<Pair<String, String>, Integer> map = new HashMap<>()
List<String> cities = new ArrayList<>()

new File("nine.txt").eachLine { line -> addCitiesToList(map, cities, line)
}


List<Integer> distances = new ArrayList<>()

cities.eachPermutation { list ->
  Integer distance = 0
  for (x = 0; x < list.size() - 1; x++) {
    distance += map.get(new Pair<String, String>(list[x], list[x + 1]))
  }
  distances.add(distance)
}

print Collections.max(distances)


def addCitiesToList(Map<Pair<String, String>, Integer> map, List<String> cities, String line) {
  List<String> items = line.split(" ")

  if (!cities.contains(items[0])) {
    cities.add(items[0])
  }
  if (!cities.contains(items[2])) {
    cities.add(items[2])
  }

  map.put(new Pair<String, String>(items[0], items[2]), Integer.parseInt(items[4]))
  map.put(new Pair<String, String>(items[2], items[0]), Integer.parseInt(items[4]))
}

