package leetcode.contest.c397

class SolutionB {
    fun maximumEnergy(energy: IntArray, k: Int): Int {
        val ans = IntArray(k)
        var res = energy.last()
        for (i in energy.indices.reversed() step k) {
            for (offset in 0 until k) {
                val index = i - offset
                if (index in energy.indices) {
                    ans[offset] += energy[index]
                    res = maxOf(res, ans[offset])
                }

            }
        }
        return res
    }
}