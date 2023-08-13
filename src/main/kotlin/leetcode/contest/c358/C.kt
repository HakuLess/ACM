package leetcode.contest.c358

import utils.print
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    // 0
    s.minAbsoluteDifference(listOf(4, 3, 2, 4), 2).print()
}

class SolutionC {
    fun minAbsoluteDifference(nums: List<Int>, x: Int): Int {
        val tm = TreeMap<Int, HashSet<Int>>()
        for (i in x until nums.size) {
            val v = nums[i]
            tm[v] = tm.getOrDefault(v, hashSetOf())
            tm[v]!!.add(i)
        }
        var ans = Int.MAX_VALUE
        for (i in nums.indices) {
//            tm.keys.joinToString().print()
            val c = nums[i]

//            println("$c with ceil ${tm.ceilingKey(c)} floor ${tm.floorKey(c)}")
            var min = Int.MAX_VALUE
            tm.ceilingKey(c)?.let {
                min = minOf(min, it - c)
            }
            tm.floorKey(c)?.let {
                min = minOf(min, c - it)
            }
//            println("$i: $min")
            ans = minOf(ans, min)

            if (i + 1 - x in nums.indices) {
                val left = nums[i + 1 - x]
                tm[left] = tm.getOrDefault(left, hashSetOf())
                tm[left]!!.add(i + 1 - x)
            }
            if (i + x in nums.indices) {
                val right = nums[i + x]
                tm[right] = tm.getOrDefault(right, hashSetOf())
                tm[right]!!.remove(i + x)
                if (tm[right]!!.isEmpty()) {
                    tm.remove(right)
                }
            }
        }
        return ans
    }
}