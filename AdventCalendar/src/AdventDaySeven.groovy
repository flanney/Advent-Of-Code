import static java.lang.Integer.*

Map<String, Integer> map = new HashMap<>()

while (!map.keySet().contains("a")) {
  new File("seven.txt").eachLine { line ->

    parsedLine = line.split(" ")

    if (map.get(parsedLine[parsedLine.size() - 1]) == null) {

      if (line.contains("AND")) {
        if ((parsedLine[0].isInteger() || map.get(parsedLine[0]) != null) &&(parsedLine[2].isInteger() || map.get(parsedLine[2]) != null)) {
          map.put(parsedLine[4], (parsedLine[0].isInteger() ? parseInt(parsedLine[0]) : map.get(parsedLine[0])) & (parsedLine[2].isInteger() ? parseInt(parsedLine[2]) : map.get(parsedLine[2])))
        }
      }
      else if (line.contains("OR")) {
        if ((parsedLine[0].isInteger() || map.get(parsedLine[0]) != null) &&(parsedLine[2].isInteger() || map.get(parsedLine[2]) != null)) {
          map.put(parsedLine[4], (parsedLine[0].isInteger() ? parseInt(parsedLine[0]) : map.get(parsedLine[0])) | (parsedLine[2].isInteger() ? parseInt(parsedLine[2]) : map.get(parsedLine[2])))
        }
      }
      else if (line.contains("SHIFT")) {
        if (map.get(parsedLine[0]) != null) {

          if (line.contains("LSHIFT")) {
            map.put(parsedLine[4], map.get(parsedLine[0]) << parseInt(parsedLine[2]))
          }
          else {
            map.put(parsedLine[4], map.get(parsedLine[0]) >> parseInt(parsedLine[2]))
          }
        }
      }
      else if (line.contains("NOT")) {
        if (map.get(parsedLine[1]) != null) {
          map.put(parsedLine[3], ~map.get(parsedLine[1]))
        }
      }
      else {
        if (parsedLine[0].isInteger()) {
          map.put(parsedLine[2], parseInt(parsedLine[0]))
        }
        else {
          if (map.get(parsedLine[0]) != null) map.put(parsedLine[2], map.get(parsedLine[0]))
        }
      }
    }
  }
}

print map.get("a")