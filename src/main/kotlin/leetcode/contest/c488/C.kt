package leetcode.contest.c488

class SolutionC {
    fun countSubarrays(nums: IntArray, k: Long): Long {

        val n = nums.size
        val maxDeque = ArrayDeque<Int>() // decreasing
        val minDeque = ArrayDeque<Int>() // increasing

        var l = 0
        var ans = 0L

        for (r in 0 until n) {
            val x = nums[r]

            while (maxDeque.isNotEmpty() && nums[maxDeque.last()] < x) {
                maxDeque.removeLast()
            }
            maxDeque.addLast(r)

            while (minDeque.isNotEmpty() && nums[minDeque.last()] > x) {
                minDeque.removeLast()
            }
            minDeque.addLast(r)

            while (true) {
                val maxVal = nums[maxDeque.first()]
                val minVal = nums[minDeque.first()]
                val len = r - l + 1
                val cost = (maxVal.toLong() - minVal.toLong()) * len.toLong()

                if (cost <= k) break

                if (maxDeque.first() == l) maxDeque.removeFirst()
                if (minDeque.first() == l) minDeque.removeFirst()
                l++
            }

            ans += (r - l + 1).toLong()
        }

        return ans
    }
}