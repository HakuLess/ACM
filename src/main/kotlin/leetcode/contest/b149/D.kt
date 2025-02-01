package leetcode.contest.b149

import utils.print

fun main() {
    val s = SolutionD()
    s.minCostGoodCaption("cdcd").print()
}

// Not Finished
class SolutionD {
    fun minCostGoodCaption(caption: String): String {
        val n = caption.length

        if (n < 3) return ""

        val ids = IntRange(0, n - 1).toList().toTypedArray()
        ids.sortBy { caption[it] }

        val arr = IntArray(26)
        caption.forEach {
            arr[it - 'a']++
        }
        arr.print()

        return ""
    }
}