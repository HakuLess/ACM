package leetcode.contest.b138

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.minDamage(4, intArrayOf(1, 2, 3, 4), intArrayOf(4, 5, 6, 8)).print()
    s.minDamage(1, intArrayOf(1, 1, 1, 1), intArrayOf(1, 2, 3, 4)).print()
    // 325
    s.minDamage(40, intArrayOf(31, 22, 54), intArrayOf(29, 92, 53)).print()
    // 408
    s.minDamage(95, intArrayOf(69, 51, 30), intArrayOf(99, 47, 97)).print()
}

class SolutionD {
    fun minDamage(power: Int, damage: IntArray, health: IntArray): Long {

        data class Item(val damage: Int, val live: Int)

        fun helper(): Long {
            var sumDamage = 0L
            var sumLive = 0L
            val list = ArrayList<Item>()

            val live = IntArray(damage.size) { 0 }
            for (i in damage.indices) {
                sumDamage += damage[i]
                live[i] = ((health[i] + power - 1) / power)
                sumLive += live[i]
                list.add(Item(damage[i], live[i]))
            }
            list.sortWith(compareBy({ -it.damage.toDouble() / it.live }, { it.live }))

            var ans = 0L
            while (list.isNotEmpty()) {
                val item = list.removeAt(0)
                ans += sumDamage * item.live
                sumLive -= item.live
                sumDamage -= item.damage
            }
            return ans
        }

        return helper()
    }
}