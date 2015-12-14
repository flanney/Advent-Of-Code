/*
--- Day 11: Corporate Policy ---

Santa's previous password expired, and he needs help choosing a new one.

To help him remember his new password after the old one expires, Santa has devised a method of coming up with a password based on the previous one. Corporate policy dictates that passwords must be exactly eight lowercase letters (for security reasons), so he finds his new password by incrementing his old password string repeatedly until it is valid.

Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so on. Increase the rightmost letter one step; if it was z, it wraps around to a, and repeat with the next letter to the left until one doesn't wrap around.

Unfortunately for Santa, a new Security-Elf recently started, and he has imposed some additional password requirements:

Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
For example:

hijklmmn meets the first requirement (because it contains the straight hij) but fails the second requirement requirement (because it contains i and l).
abbceffg meets the third requirement (because it repeats bb and ff) but fails the first requirement.
abbcegjk fails the third requirement, because it only has one double letter (bb).
The next password after abcdefgh is abcdffaa.
The next password after ghijklmn is ghjaabcc, because you eventually skip all the passwords that start with ghi..., since i is not allowed.
Given Santa's current password (your puzzle input), what should his next password be?

Your puzzle answer was hepxxyzz.

--- Part Two ---

Santa's password expired again. What's the next one?
 */


String string = "hepxxyzz"

boolean constraint1 = false
boolean constraint2 = false
boolean constraint3 = false

while (!(constraint1 && constraint2 && constraint3)) {
  string = increment(string, string.length()-1)

  constraint1 = checkConstraintOne(string)
  constraint2 = checkConstraintTwo(string)
  constraint3 = checkConstraintThree(string)
}

print string


String increment(String string, int index) {
  if(string[index].equals("z")) {
    string = (string.substring(0,index) + "a" + string.substring(index+1))
    return increment(string, index-1)
  } else
  {
    return (string.substring(0,index) + ((char) (((int) string.charAt(index)) + 1).intValue()) + string.substring(index+1))
  }
}

boolean checkConstraintOne(String string) {
  for (int x=0; x<string.length()-2; x++) {
    if(string.charAt(x+1) == ((char) (((int) string.charAt(x)) + 1).intValue()) && string.charAt(x+2) == ((char) (((int) string.charAt(x+1)) + 1).intValue())) {
      return true
    }
  }
  return false
}

boolean checkConstraintTwo(String string) {
  char[] badThings = ["i","o","l"]
  return !badThings.any( {c ->
    string.contains(c.toString())
  })
}

boolean checkConstraintThree(String string) {
  int success = 0
  for (int x=0; x<string.length()-1; x++) {
    if(string[x].equals(string[x+1])) {
      success++
      x++
    }
  }
  return success > 1
}