package leetcode.contest.c489

class SolutionB {
    fun firstUniqueFreq(nums: IntArray): Int {
        val freq = HashMap<Int, Int>()
        for (x in nums) {
            freq[x] = (freq[x] ?: 0) + 1
        }

        val freqCount = HashMap<Int, Int>()
        for (count in freq.values) {
            freqCount[count] = (freqCount[count] ?: 0) + 1
        }

        for (x in nums) {
            val f = freq[x]!!
            if (freqCount[f] == 1) {
                return x
            }
        }

        return -1
    }
}