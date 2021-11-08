package leetcode.normal

import utils.print


fun main() {
    val s = Solution957()
//    s.prisonAfterNDays(intArrayOf(1, 0, 0, 1, 0, 0, 1, 0), 1000000000).print()
//    s.prisonAfterNDays(intArrayOf(1, 1, 0, 0, 0, 0, 1, 1), 7).print()
    s.prisonAfterNDays(intArrayOf(1, 1, 0, 1, 1, 0, 1, 1), 6).print()
}

class Solution957 {
    fun prisonAfterNDays(cells: IntArray, n: Int): IntArray {
        val arr = ArrayList<IntArray>()
        val set = ArrayList<String>()
        var cur = cells.clone()
        while (cur.joinToString() !in set) {
            set.add(cur.joinToString())
            arr.add(cur)
            val next = IntArray(cells.size)
            for (i in 1 until cells.lastIndex) {
                if (cur[i - 1] == 1 && cur[i + 1] == 1) {
                    next[i] = 1
                } else if (cur[i - 1] == 0 && cur[i + 1] == 0) {
                    next[i] = 1
                } else {
                    next[i] = 0
                }
            }
            cur = next
        }
        val index = set.indexOf(cur.joinToString())
        if (n in arr.indices) return arr[n]
        return arr[index + (n - index) % (arr.size - index)]
    }
}