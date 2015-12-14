/*--- Day 4: The Ideal Stocking Stuffer ---

    Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.

                                                                                                                                                       To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

For example:

If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
    If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
Your puzzle answer was 282749.

--- Part Two ---

    Now find one that starts with six zeroes.*/

import java.security.MessageDigest

String hash = '111111111'

int count = 0

String input = 'yzbqklnj'

while (!hash.substring(0,6).equals("000000")) {
  hash = generateMD5(input + count)
  count++
}

print "${count -1}, $hash"




def generateMD5(String s) {
  MessageDigest digest = MessageDigest.getInstance("MD5")
  digest.update(s.bytes);
  new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
}