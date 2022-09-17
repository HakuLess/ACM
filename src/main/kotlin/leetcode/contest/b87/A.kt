package leetcode.contest.b87

import utils.longComb
import utils.preSumArray
import utils.print
import java.text.SimpleDateFormat
import java.util.*


fun main() {
    val s = SolutionA()
    s.countDaysTogether(
        "08-15",
        "08-18",
        "08-16",
        "08-19"
    ).print()

    s.countDaysTogether(
        "10-01",
        "10-31",
        "11-01",
        "12-31"
    ).print()

    s.countDaysTogether(
        "01-01",
        "10-31",
        "11-01",
        "12-31"
    ).print()
}

class SolutionA {
    fun countDaysTogether(arriveAlice: String, leaveAlice: String, arriveBob: String, leaveBob: String): Int {
        val arr = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31).preSumArray(true)
        val a = arriveAlice.split("-").let {
            arr[it[0].toInt() - 1] + it[1].toInt()
        }
        val b = leaveAlice.split("-").let {
            arr[it[0].toInt() - 1] + it[1].toInt()
        }
        val c = arriveBob.split("-").let {
            arr[it[0].toInt() - 1] + it[1].toInt()
        }
        val d = leaveBob.split("-").let {
            arr[it[0].toInt() - 1] + it[1].toInt()
        }
        val ans = minOf(b, d) - maxOf(a, c) + 1
        return maxOf(ans.toInt(), 0)
    }
}