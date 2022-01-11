package leetcode.normal

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = Solution1036()
//    s.isEscapePossible("[[0,1],[1,0]]".toGrid(), intArrayOf(0, 0), intArrayOf(0, 2)).print()
//    s.isEscapePossible("[[10,9],[9,10],[10,11],[11,10]]".toGrid(), intArrayOf(0, 0), intArrayOf(10, 10)).print()
    s.isEscapePossible(
        "[[0,999991],[0,999993],[0,999996],[1,999996],[1,999997],[1,999998],[1,999999]]".toGrid(),
        intArrayOf(0, 999997),
        intArrayOf(0, 2)
    ).print()
}

class Solution1036 {
    fun isEscapePossible(blocked: Array<IntArray>, source: IntArray, target: IntArray): Boolean {
        val n = blocked.size
        val block = blocked.map { Pair(it[0], it[1]) }.toHashSet()

        fun check(source: IntArray, target: IntArray): Boolean {
            val hashSet = HashSet<Pair<Int, Int>>()
            val queue: Queue<Pair<Int, Int>> = LinkedList()
            queue.add(Pair(source[0], source[1]))
            while (queue.isNotEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val item = queue.poll()
                    if (item.first < 0 || item.second < 0 || item.first >= 1000000 || item.second >= 1000000) {
                        continue
                    }
                    if (item.first == target[0] && item.second == target[1]) {
                        return true
                    }
                    // n个Block，最多能困住 n * (n - 1) / 2 个点，可遍历点超过该范围，则证明该点未被困住
                    if (hashSet.size > n * (n - 1) / 2) return true
                    if (item in hashSet) {
                        continue
                    }
                    if (item in block) {
                        continue
                    }
                    hashSet.add(item)
                    queue.add(Pair(item.first + 1, item.second))
                    queue.add(Pair(item.first - 1, item.second))
                    queue.add(Pair(item.first, item.second + 1))
                    queue.add(Pair(item.first, item.second - 1))
                }
            }
            return false
        }

        return check(source, target) && check(target, source)
    }
}