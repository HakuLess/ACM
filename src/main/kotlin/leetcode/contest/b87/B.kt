package leetcode.contest.b87

import java.util.*

class SolutionB {
    fun matchPlayersAndTrainers(players: IntArray, trainers: IntArray): Int {
        val tm = TreeMap<Int, Int>()
        trainers.forEach {
            tm[it] = tm.getOrDefault(it, 0) + 1
        }
        var ans = 0
        players.forEach {
            val t = tm.ceilingEntry(it)
            if (t != null) {
                ans++
                if (t.value == 1) {
                    tm.remove(t.key)
                } else {
                    tm[t.key] = t.value - 1
                }
            }
        }
        return ans
    }
}