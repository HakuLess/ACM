package leetcode.contest.c401

import kotlin.collections.HashMap

class SolutionD {
    fun maxTotalReward(rewardValues: IntArray): Int {
        rewardValues.sort()

        val seen = HashMap<String, Int>()
        fun dfs(index: Int, sum: Int): Int {
            if (index !in rewardValues.indices) return sum
            val key = "${index}_${sum}"
            if (key in seen.keys) {
                return seen[key]!!
            }

            var tmp = 0
            if (rewardValues[index] > sum) {
                tmp = maxOf(tmp, dfs(index + 1, sum + rewardValues[index]))
            }
            tmp = maxOf(tmp, dfs(index + 1, sum))

            return tmp.also {
                seen[key] = it
            }
        }

        return dfs(0, 0)
    }
}