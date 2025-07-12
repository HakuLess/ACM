package leetcode.normal

import utils.print

fun main() {
    val s = Solution1900()
    // [3, 4]
    s.earliestAndLatest(11, 2, 4).print()
    s.earliestAndLatest(5, 1, 5).print()
    // [2, 3]
    s.earliestAndLatest(7, 3, 4).print()
}

// TODO Not Finished
class Solution1900 {

    fun earliestAndLatest(n: Int, firstPlayer: Int, secondPlayer: Int): IntArray {

        data class State(val size: Int, val aIndex: Int, val bIndex: Int)

        val seen = HashMap<State, IntArray>()

        fun dfs(n: Int, first: Int, second: Int): IntArray {
            var a = first
            var b = second

            // 相遇
            if (a + b == n + 1) return intArrayOf(1, 1)

            // 统一成左边人数较少的情况
            if (a + b > n + 1) {
                val tmp = a
                a = n + 1 - b
                b = n + 1 - tmp
            }

            val key = State(n, a, b)
            if (key in seen) return seen[key]!!

            val m = (n + 1) / 2
            val minMid = if (b <= m) 0 else b - n / 2 - 1
            val maxMid = if (b <= m) b - a else m - a

            var earliest = Int.MAX_VALUE
            var latest = 0

            for (left in 0 until a) {
                for (mid in minMid until maxMid) {
                    val res = dfs(m, left + 1, left + mid + 2)
                    earliest = minOf(earliest, res[0])
                    latest = maxOf(latest, res[1])
                }
            }

            return intArrayOf(earliest + 1, latest + 1).also {
                seen[key] = it
            }
        }

        return dfs(n, firstPlayer, secondPlayer)
    }

//    data class State(val size: Int, val aIndex: Int, val bIndex: Int, val isMax: Boolean)
//
//    fun earliestAndLatest(n: Int, firstPlayer: Int, secondPlayer: Int): IntArray {
//
//        // 总人数 i位次 j位次
//        val seen = HashMap<State, Int>()
//
//        fun dfsMax(list: List<Int>, isMax: Boolean): Int {
//            val size = list.size
//            val a = list.indexOf(firstPlayer)
//            val b = list.indexOf(secondPlayer)
//
////            println("enter ${list.joinToString()}")
//
//            val key = State(size, a, b, isMax)
//            if (key in seen) return seen[key]!!
//
//            val nextList = ArrayList<Int>()
//            for (i in list.indices) {
//                if (i > size - i - 1) break
//                val p1 = list[i]
//                val p2 = list[size - i - 1]
//
//                if (p1 == firstPlayer && p2 == secondPlayer) {
//                    return 1
//                } else if (p1 == p2) {
//                    nextList.add(p1)
//                } else if (p1 == firstPlayer || p1 == secondPlayer) {
//                    nextList.add(p1)
//                } else if (p2 == firstPlayer || p2 == secondPlayer) {
//                    nextList.add(p2)
//                } else if (isMax) {
//                    // 尽量让 firstPlayer 和 secondPlayer 不在同一半边
//
//                } else {
//                    // 尽量让 firstPlayer 和 secondPlayer 在同一半边
//
//                }
//            }
//
//            nextList.sort()
//            val next = dfsMax(nextList, isMax)
//            return (next + 1).also {
//                seen[key] = it
//            }
//        }
//
//        val max = dfsMax((1..n).toList(), true)
//        val min = dfsMax((1..n).toList(), false)
//        return intArrayOf(min, max)
//    }
}