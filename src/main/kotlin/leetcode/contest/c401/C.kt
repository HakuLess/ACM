package leetcode.contest.c401

import java.util.*
import kotlin.collections.HashSet

class SolutionC {
    fun maxTotalReward(rewardValues: IntArray): Int {
        rewardValues.sort()
        val ts = TreeSet<Int>()
        ts.add(0)
        rewardValues.forEach { item ->
            val set = HashSet<Int>()
            ts.forEach {
                if (it < item) {
                    set.add(it + item)
                }
            }
            ts.addAll(set)
        }
        return ts.maxOrNull()!!
    }
}