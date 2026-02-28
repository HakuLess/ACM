package leetcode.contest.b177

class SolutionA {
    fun minDistinctFreqPair(nums: IntArray): IntArray {
        val freq = mutableMapOf<Int, Int>()
        for (num in nums) {
            freq[num] = freq.getOrDefault(num, 0) + 1
        }

        val values = freq.keys.sorted()

        for (i in values.indices) {
            val x = values[i]
            for (j in i + 1 until values.size) {
                val y = values[j]
                if (freq[x] != freq[y]) {
                    return intArrayOf(x, y)
                }
            }
        }

        return intArrayOf(-1, -1)
    }
}