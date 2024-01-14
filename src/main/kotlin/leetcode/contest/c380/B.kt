package leetcode.contest.c380

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.beautifulIndices("isawsquirrelnearmysquirrelhouseohmy", "my", "squirrel", 15).joinToString().print()
    s.beautifulIndices("abcd", "a", "a", 4).joinToString().print()

}

class SolutionB {
    fun beautifulIndices(s: String, a: String, b: String, k: Int): List<Int> {
        val aList = ArrayList<Int>()
        val bList = ArrayList<Int>()

        var cur = -1
        do {
            cur = s.indexOf(a, cur + 1)
            if (cur != -1)
                aList.add(cur)
        } while (cur != -1)

        cur = -1
        do {
            cur = s.indexOf(b, cur + 1)
            if (cur != -1)
                bList.add(cur)
        } while (cur != -1)

        val ans = ArrayList<Int>()
//        aList.joinToString().print()
//        bList.joinToString().print()

        aList.forEach { aItem ->
            if (bList.any { abs(it - aItem) <= k }) {
                ans.add(aItem)
            }
        }
        return ans
    }
}