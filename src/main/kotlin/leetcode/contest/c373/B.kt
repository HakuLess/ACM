package leetcode.contest.c373

import utils.print

fun main() {
    val s = SolutionB()
//    s.beautifulSubstrings("baeyh", 2).print()
//    s.beautifulSubstrings("abba", 1).print()
//    s.beautifulSubstrings("bcdf", 1).print()

    // 21
    s.beautifulSubstrings("ilougekqlovegioemdvu", 4).print()
    s.beautifulSubstrings("ilougekqlovegioemdvub", 4).print()
}

class SolutionB {
    fun beautifulSubstrings(s: String, k: Int): Int {
        val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')
        var ans = 0
        for (i in s.indices) {
            var a = 0
            var b = 0
            for (j in i + 2..s.length step 2) {
                if (s[j - 2] in vowels) a++ else b++
                if (s[j - 1] in vowels) a++ else b++

                if (a == b && ((a * b) % k) == 0) {
//                    println("$i..$j with $sb with $a ${a * b}")
                    ans++
                }
            }
        }
        return ans
    }
}