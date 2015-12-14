
/*
--- Day 5: Doesn't He Have Intern-Elves For This? ---

Santa needs help figuring out which strings in his text file are naughty or nice.

A nice string is one with all of the following properties:

It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
For example:

ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
jchzalrnumimnmhp is naughty because it has no double letter.
haegwjzuvuyypxyu is naughty because it contains the string xy.
dvszwmarrgswjxmb is naughty because it contains only one vowel.
How many strings are nice?

Your puzzle answer was 255.
 */

final List vowels = ["a", "e", "i", "o", "u"]
final List naughty = ["ab","cd","pq","xy"]


niceCount = 0
new File("five.txt").eachLine { line ->

  if(isNice(line, vowels, naughty)){
    niceCount++
  }
}

print niceCount


boolean isNice(String string, List vowels, List naughty) {

  int vowelCount = 0
  string.each {letter ->
    if (vowels.contains(letter)) {
      vowelCount++
    }
  }

  if(vowelCount < 3) {
    return false
  }

  currentLetter = ""
  hasDoubleLetter = false;
  string.each { letter ->
    if(letter.equals(currentLetter)) {
      hasDoubleLetter = true;
    }
    currentLetter = letter;
  }

  if(!hasDoubleLetter){
    return false
  }

  hasNaughtyLetter = false
  naughty.each { naughtyLetters ->
    if(string.contains(naughtyLetters)) {
      hasNaughtyLetter = true
    }
  }

  if(hasNaughtyLetter) {
    return false
  }

  return true

}

