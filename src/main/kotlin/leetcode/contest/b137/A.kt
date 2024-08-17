package leetcode.contest.b137

class SolutionA {
    fun resultsArray(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        val results = IntArray(n - k + 1)

        for (i in 0 until n - k + 1) {
            val subArray = nums.sliceArray(i until i + k)
            var isValid = true

            for (j in 1 until k) {
                if (subArray[j] != subArray[j - 1] + 1) {
                    isValid = false
                    break
                }
            }

            results[i] = if (isValid) subArray.maxOrNull() ?: -1 else -1
        }

        return results
    }
}