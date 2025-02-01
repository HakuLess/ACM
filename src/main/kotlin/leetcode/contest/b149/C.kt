package leetcode.contest.b149

import utils.SortedList
import utils.print
import utils.printInt
import utils.toSortedList
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
    // 7
    s.maxFreeTime(10, intArrayOf(0, 7, 9), intArrayOf(1, 8, 10)).print()
    // 2
    s.maxFreeTime(5, intArrayOf(1, 3), intArrayOf(2, 5)).print()
    // 18
    s.maxFreeTime(34, intArrayOf(0, 17), intArrayOf(14, 19)).print()
}

class SolutionC {
    fun maxFreeTime(eventTime: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size
        val gaps = ArrayList<Int>()
        gaps.add(startTime[0])
        for (i in 1 until n) {
            gaps.add(startTime[i] - endTime[i - 1])
        }
        gaps.add(eventTime - endTime[n - 1])

        val blocks = ArrayList<Int>()
        for (i in 0 until n) {
            blocks.add(endTime[i] - startTime[i])
        }


//        println()
//        gaps.joinToString().print()

        val tm = TreeMap<Int, Int>()
        gaps.forEach {
            tm[it] = tm.getOrDefault(it, 0) + 1
        }

        var ans = 0
        for (i in blocks.indices) {

            val a = gaps[i]
            val b = gaps[i + 1]
            val c = blocks[i]

            tm[a] = tm.getOrDefault(a, 0) - 1
            tm[b] = tm.getOrDefault(b, 0) - 1
            if (tm[a] == 0) tm.remove(a)
            if (tm[b] == 0) tm.remove(b)

//            tm.printInt()

            if (tm.ceilingKey(c) != null) {
                ans = maxOf(ans, startTime.getOrElse(i + 1) { eventTime } - endTime.getOrElse(i - 1) { 0 })
//                println("$i: 111 end ${startTime.getOrElse(i + 1) { eventTime }} - ${endTime[i]}")
            } else {
                ans = maxOf(ans, startTime.getOrElse(i + 1) { eventTime } - endTime.getOrElse(i - 1) { 0 } - c)
//                println("$i: 222 end ${startTime.getOrElse(i + 1) { eventTime }} - ${endTime.getOrElse(i - 1) { 0 }} - $c")
            }

            tm[a] = tm.getOrDefault(a, 0) + 1
            tm[b] = tm.getOrDefault(b, 0) + 1
        }

        return ans
    }
}