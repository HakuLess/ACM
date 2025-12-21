package leetcode.contest.b172

class SolutionA {
    fun minOperations(nums: IntArray): Int {
        val freq = HashMap<Int, Int>()
        for (x in nums) freq[x] = freq.getOrDefault(x, 0) + 1

        var dupCount = 0
        for (v in freq.values) {
            if (v >= 2) dupCount++
        }

        var ops = 0
        var i = 0
        val n = nums.size

        while (dupCount > 0 && i < n) {
            ops++

            repeat(3) {
                if (i >= n) return@repeat
                val x = nums[i++]
                val old = freq[x]!!
                if (old == 2) dupCount--
                if (old == 1) freq.remove(x)
                else freq[x] = old - 1
            }
        }

        return ops
    }
}
