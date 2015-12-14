import groovy.json.JsonSlurper

/*
--- Part Two ---

Uh oh - the Accounting-Elves have realized that they double-counted everything red.

Ignore any object (and all of its children) which has any property with the value "red". Do this only for objects ({...}), not arrays ([...]).

[1,2,3] still has a sum of 6.
[1,{"c":"red","b":2},3] now has a sum of 4, because the middle object is ignored.
{"d":"red","e":[1,2,3,4],"f":5} now has a sum of 0, because the entire structure is ignored.
[1,"red",5] has a sum of 6, because "red" in an array has no effect.
Your puzzle answer was 68466.
 */

JsonSlurper jsonSlurper = new JsonSlurper()

Map object = jsonSlurper.parseText(new File("twelve.txt").getText())


print handleObject(object.values())

Integer handleObject(Object object) {
  Integer count = 0
  boolean red = false

  def iterable = object
  if(object instanceof Map) {
    iterable = object.values()
  }

  iterable.each { thing ->

    if(!(object instanceof ArrayList) && thing.equals("red")){
      red = true
    }

    if (thing instanceof Collection) {
      count += handleObject(thing)
    }
    else if (thing instanceof Map) {
      count += handleObject(thing)
    }
    else {
      if (thing instanceof Integer) {
        count += thing
      }
    }
  }
  if(red) {
    return 0
  }
  return count
}