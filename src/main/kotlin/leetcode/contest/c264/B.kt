package leetcode.contest.c264

import utils.permuteUnique
import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = Solution5907()
    s.nextBeautifulNumber(3000).print()
}

class Solution5907 {
    fun nextBeautifulNumber(n: Int): Int {
        val ts = TreeSet<Int>()

        val list = ArrayList<ArrayList<Int>>()
        for (i in 1..6) {
            val cur = arrayListOf<Int>()
            repeat(i) {
                cur.add(i)
            }
            list.add(cur)
        }
        for (mask in 1 until (1 shl 6)) {
            val state = ArrayList<Int>()
            for (i in 0 until 6) {
                if (mask and (1 shl i) != 0) {
                    state.addAll(list[i])
                }
            }
            if (state.size <= 7) {
                state.toIntArray().permuteUnique().forEach {
                    ts.add(it.joinToString("").toInt())
                }
            }
        }

        return ts.ceiling(n + 1)!!
    }
}