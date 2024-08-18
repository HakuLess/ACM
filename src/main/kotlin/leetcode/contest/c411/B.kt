package leetcode.contest.c411

class SolutionB {
    fun maxEnergyBoost(energyDrinkA: IntArray, energyDrinkB: IntArray): Long {
        val n = energyDrinkA.size
        val dpA = LongArray(n)
        val dpB = LongArray(n)

        dpA[0] = energyDrinkA[0].toLong()
        dpB[0] = energyDrinkB[0].toLong()

        for (i in 1 until n) {
            dpA[i] = maxOf(
                dpA[i - 1] + energyDrinkA[i],
                if (i >= 2) dpB[i - 2] + energyDrinkA[i] else energyDrinkA[i].toLong()
            )
            dpB[i] = maxOf(
                dpB[i - 1] + energyDrinkB[i],
                if (i >= 2) dpA[i - 2] + energyDrinkB[i] else energyDrinkB[i].toLong()
            )
        }

        return maxOf(dpA[n - 1], dpB[n - 1])
    }
}