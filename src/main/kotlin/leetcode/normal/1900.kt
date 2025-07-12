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

    data class State(val size: Int, val aIndex: Int, val bIndex: Int, val isMax: Boolean)

    fun earliestAndLatest(n: Int, firstPlayer: Int, secondPlayer: Int): IntArray {

        // 总人数 i位次 j位次
        val seen = HashMap<State, Int>()

        fun dfsMax(list: List<Int>, isMax: Boolean): Int {
            val size = list.size
            val a = list.indexOf(firstPlayer)
            val b = list.indexOf(secondPlayer)

//            println("enter ${list.joinToString()}")

            val key = State(size, a, b, isMax)
            if (key in seen) return seen[key]!!

            val nextList = ArrayList<Int>()
            for (i in list.indices) {
                if (i > size - i - 1) break
                val p1 = list[i]
                val p2 = list[size - i - 1]

                if (p1 == firstPlayer && p2 == secondPlayer) {
                    return 1
                } else if (p1 == p2) {
                    nextList.add(p1)
                } else if (p1 == firstPlayer || p1 == secondPlayer) {
                    nextList.add(p1)
                } else if (p2 == firstPlayer || p2 == secondPlayer) {
                    nextList.add(p2)
                } else if (isMax) {
                    // 尽量让 firstPlayer 和 secondPlayer 不在同一半边

                } else {
                    // 尽量让 firstPlayer 和 secondPlayer 在同一半边

                }
            }

            nextList.sort()
            val next = dfsMax(nextList, isMax)
            return (next + 1).also {
                seen[key] = it
            }
        }

        val max = dfsMax((1..n).toList(), true)
        val min = dfsMax((1..n).toList(), false)
        return intArrayOf(min, max)
    }
}