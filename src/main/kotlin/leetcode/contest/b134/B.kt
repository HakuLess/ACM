package leetcode.contest.b134

class SolutionB {
    fun maximumPoints(enemyEnergies: IntArray, currentEnergy: Int): Long {
        enemyEnergies.sort()
        var cur = currentEnergy.toLong()
        // 最小的覆盖不了
        if (cur < enemyEnergies[0]) {
            return 0L
        }
        // 每一个标记一遍
        for (i in enemyEnergies.indices) {
            if (i == 0) continue
            cur += enemyEnergies[i]
        }
        return cur / enemyEnergies[0]
    }
}