/*
--- Part Two ---

Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.

Now, a nice string is one with all of the following properties:

It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
For example:

qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
How many strings are nice under these new rules?
 */

niceCount = 0
new File("five.txt").eachLine { line ->

  if (isNice(line)) {
    niceCount++
  }
}

print niceCount


boolean isNice(String string) {

  firstConstraint = false
  secondConstraint = false

  for (x = 0; x < string.length(); x++) {
    if ((x + 2) < string.length() && string[x].equals(string[x + 2])) {
      secondConstraint = true
    }

    if ((x + 2) < string.length() && string.substring(x + 2).contains("${string[x]}${string[x + 1]}")) {
      firstConstraint = true
    }
  }

  return firstConstraint && secondConstraint
}

