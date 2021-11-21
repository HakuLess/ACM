package leetcode.contest.c268

import utils.biMax
import utils.biMin
import utils.print

fun main() {
    val s = SolutionB()
    s.wateringPlants(intArrayOf(2, 2, 3, 3), 5).print()
}

class SolutionB {
    fun wateringPlants(plants: IntArray, capacity: Int): Int {
        var cur = capacity
        var ans = 0
        for (i in plants.indices) {
            if (cur >= plants[i]) {
                ans++
                cur -= plants[i]
            } else {
                cur = capacity - plants[i]
                ans += i * 2 + 1
            }
        }
        return ans
    }
}