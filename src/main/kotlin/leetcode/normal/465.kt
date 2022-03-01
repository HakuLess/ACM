package leetcode.normal

import utils.print
import utils.printInt
import utils.toGrid

fun main() {
    val s = Solution465()
    s.minTransfers("[[0,1,10],[2,0,5]]".toGrid()).print()
    s.minTransfers("[[0,1,2],[1,2,1],[1,3,1]]".toGrid()).print()
    s.minTransfers("[[0,1,5],[2,3,1],[2,0,1],[4,0,2]]".toGrid()).print()
}

class Solution465 {
    fun minTransfers(transactions: Array<IntArray>): Int {
        val map = HashMap<Int, Int>()
        transactions.forEach {
            map[it[0]] = map.getOrDefault(it[0], 0) + it[2]
            map[it[1]] = map.getOrDefault(it[1], 0) - it[2]
        }

        var ans = Int.MAX_VALUE

        fun dfs(list: IntArray, left: Int, step: Int) {
            if (step >= ans) return
            if (list.all { it == 0 }) {
                ans = step
                return
            }

            val pre = list[left]
            if (pre == 0) {
                dfs(list, left + 1, step)
                return
            }
            for (i in left + 1 until list.size) {
                list[i] += list[left]
                list[left] = 0
                dfs(list, left + 1, step + 1)
                list[i] -= pre
                list[left] = pre
            }
        }

        val cur = map.values.toList().toIntArray()
        dfs(cur, 0, 0)
        return ans
    }
}