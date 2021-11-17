package leetcode.contest.b65

import utils.biLastIndexOf
import utils.print
import utils.printInt
import utils.toArrayList
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    s.maxTaskAssign(intArrayOf(3, 2, 1), intArrayOf(0, 3, 3), 1, 1).print()
}

class SolutionD {
    // 二分，算isValid
    fun maxTaskAssign(tasks: IntArray, workers: IntArray, pills: Int, strength: Int): Int {
        tasks.sort()
        workers.sortDescending()

        fun check(mid: Int): Boolean {
            // 取出工作量最小的 及 最能干的工人 各mid个
            val tm = TreeMap<Int, Int>()
            for (i in 0 until mid) {
                tm[workers[i]] = tm.getOrDefault(workers[i], 0) + 1
            }
            var p = 0
            for (i in mid - 1 downTo 0) {
                val it = tasks[i]
                if (tm.ceilingKey(it) != null) {
                    val key = tm.ceilingKey(it)
                    tm[key] = tm.getOrDefault(key, 0) - 1
                    if (tm[key] == 0) tm.remove(key)
                } else if (tm.ceilingKey(it - strength) != null && p < pills) {
                    p++
                    val key = tm.ceilingKey(it - strength)
                    tm[key] = tm.getOrDefault(key, 0) - 1
                    if (tm[key] == 0) tm.remove(key)
                } else {
                    return false
                }
            }
            return true
        }

        var left = 0
        var right = minOf(tasks.size, workers.size)
        while (left + 1 < right) {
            val mid = (left + right).ushr(1)
            when {
                check(mid) -> left = mid
                else -> right = mid
            }
        }
        return when {
            check(right) -> right
            check(left) -> left
            else -> -1
        }
    }
}