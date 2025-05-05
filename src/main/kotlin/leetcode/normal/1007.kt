package leetcode.normal

import utils.print

fun main() {
    val s = Solution1007()
    s.minDominoRotations(intArrayOf(2, 1, 2, 4, 2, 2), intArrayOf(5, 2, 6, 2, 3, 2)).print()
}

class Solution1007 {
    fun minDominoRotations(tops: IntArray, bottoms: IntArray): Int {
        val n = tops.size

        fun check(v: Int): Int {
//            println("enter check $v")
            var top = 0
            var bottom = 0
            for (i in 0 until n) {
                if (tops[i] != v && bottoms[i] != v) {
                    return Int.MAX_VALUE
                } else if (tops[i] == bottoms[i] && tops[i] == v) {

                } else if (tops[i] == v && bottoms[i] != v) {
                    top++
                } else if (tops[i] != v && bottoms[i] == v) {
                    bottom++
                }
            }
            return minOf(top, bottom).also {
//                println("check $v get $it")
            }
        }

        var ans = Int.MAX_VALUE
        ans = minOf(ans, check(tops[0]))
        ans = minOf(ans, check(bottoms[0]))

        if (ans == Int.MAX_VALUE) {
            return -1
        } else {
            return ans
        }
    }
}