package leetcode.contest.c264

import utils.permute
import utils.permuteUnique
import utils.print
import java.util.*

fun main() {
    val s = Solution5907()
    s.nextBeautifulNumber(3000).print()
}

class Solution5907 {
    fun nextBeautifulNumber(n: Int): Int {
        val ts = TreeSet<Int>()

        ts.add(1)
        ts.add(22)
        ts.add(333)
        ts.add(4444)
        ts.add(55555)
        ts.add(666666)
        intArrayOf(1, 2, 2).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(1, 3, 3, 3).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(1, 4, 4, 4, 4).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(2, 2, 3, 3, 3).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(2, 2, 4, 4, 4, 4).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(1, 5, 5, 5, 5, 5).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(1, 6, 6, 6, 6, 6, 6).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(1, 2, 2, 3, 3, 3).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(1, 2, 2, 4, 4, 4, 4).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }
        intArrayOf(3, 3, 3, 4, 4, 4, 4).permuteUnique().forEach {
            ts.add(it.joinToString("").toInt())
        }

        return ts.ceiling(n + 1)!!
    }
}