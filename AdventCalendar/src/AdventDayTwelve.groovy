import groovy.json.JsonSlurper

/*
--- Day 12: JSAbacusFramework.io ---

Santa's Accounting-Elves need help balancing the books after a recent order. Unfortunately, their accounting software uses a peculiar storage format. That's where you come in.

They have a JSON document which contains a variety of things: arrays ([1,2,3]), objects ({"a":1, "b":2}), numbers, and strings. Your first job is to simply find all of the numbers throughout the document and add them together.

For example:

[1,2,3] and {"a":2,"b":4} both have a sum of 6.
[[[3]]] and {"a":{"b":4},"c":-1} both have a sum of 3.
{"a":[-1,1]} and [-1,{"a":1}] both have a sum of 0.
[] and {} both have a sum of 0.
You will not encounter any strings containing numbers.

What is the sum of all numbers in the document?

Your puzzle answer was 119433.
 */

JsonSlurper jsonSlurper = new JsonSlurper()

Map object = jsonSlurper.parseText(new File("twelve.txt").getText())


print handleObject(object.values())

Integer handleObject(Collection object) {
  Integer count = 0
  object.each { thing ->
    if (thing instanceof Collection) {
      count += handleObject(thing)
    }
    else if (thing instanceof Map) {
      count += handleObject(thing.values())
    }
    else {
      if (thing instanceof Integer) {
        count += thing
      }
    }
  }
  return count
}