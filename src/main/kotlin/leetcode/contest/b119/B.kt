package leetcode.contest.b119

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.removeAlmostEqualCharacters("zyxyxyz").print()
}

class SolutionB {
    fun removeAlmostEqualCharacters(word: String): Int {
        var i = 0
        var ans = 0
        while (i + 1 in word.indices) {
//            println("$i: ${word[i + 1] - word[i]}")
            if (abs(word[i + 1] - word[i]) <= 1) {
//                println("add ${i + 1} with ${word[i + 1]}")
                ans++
                i++
            }
            i++
        }
        return ans
    }
}