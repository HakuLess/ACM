package leetcode.contest.c439

class SolutionA {
    fun largestInteger(nums: IntArray, k: Int): Int {
        val freq = HashMap<Int, Int>()

        for (i in 0..nums.size - k) {
            val sub = nums.slice(i until i + k).toSet()
            for (num in sub.toHashSet()) {
                freq[num] = freq.getOrDefault(num, 0) + 1
            }
        }

        var ans = -1
        for ((num, count) in freq) {
            if (count == 1) {
                ans = maxOf(ans, num)
            }
        }
        return ans
    }
}