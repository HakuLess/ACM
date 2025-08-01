package leetcode.normal

import utils.print

fun main() {
    val s = Solution118()
    s.generate(5).forEach {
        it.joinToString().print()
    }
}

class Solution118 {
    fun generate(numRows: Int): List<List<Int>> {
        val ans = ArrayList<ArrayList<Int>>()
        ans.add(arrayListOf(1))
        for (i in 2..numRows) {
            val last = ans.last()
            val cur = ArrayList<Int>()
            cur.add(1)
            for (j in 0 until last.lastIndex) {
                cur.add(last[j] + last[j + 1])
            }
            cur.add(1)
            ans.add(cur)
        }
        return ans
    }
}