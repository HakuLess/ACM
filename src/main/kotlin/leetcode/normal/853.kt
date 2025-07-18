package leetcode.normal

import utils.print

fun main() {
    val s = Solution853()
    s.carFleet(10, intArrayOf(0, 4, 2), intArrayOf(2, 1, 3)).print()
}

class Solution853 {

    data class Car(val pos: Int, val speed: Int, var time: Double)

    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val l = ArrayList<Car>()
        for (i in position.indices) {
            val pos = position[i]
            val sp = speed[i]
            val t: Double = (target - pos).toDouble() / sp
            l.add(Car(pos, sp, t))
        }
        l.sortBy { it.pos }

//        l.joinToString().print()

        var ans = 1
        for (i in l.lastIndex downTo 1) {
            if (l[i].time < l[i - 1].time) {
                ans++
            } else {
                l[i - 1].time = l[i].time
            }
        }
        return ans
    }
}