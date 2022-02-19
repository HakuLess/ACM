package leetcode.normal

import utils.print

fun main() {
    val s = Solution969()
//    s.pancakeSort(intArrayOf(3, 2, 4, 1)).joinToString().print()
    s.pancakeSort(intArrayOf(3, 1, 2)).joinToString().print()
}

class Solution969 {
    fun pancakeSort(arr: IntArray): List<Int> {
        val ans = ArrayList<Int>()
        var cur = ArrayList<Int>()
        cur.addAll(arr.toList())
        while (cur.isNotEmpty()) {
            val tmp = cur.indexOfFirst { it == cur.maxOrNull()!! }
            ans.add(tmp + 1)
            ans.add(arr.size - ans.size / 2)
            val next = ArrayList<Int>()
            next.addAll(cur.subList(0, tmp + 1).reversed())
            next.addAll(cur.subList(tmp + 1, cur.size))
            next.removeAt(0)
            next.reverse()
            cur = next
        }
        return ans.filter { it != 1 }
    }
}