package leetcode.contest.b147

class SolutionD {
    fun maxSubarraySum(nums: IntArray): Long {
        val longArray = nums.map { it.toLong() }.toLongArray()
        var maxResult = Long.MIN_VALUE

        // 辅助函数，计算最大子数组和
        fun maxSubArraySum(arr: LongArray): Long {
            if (arr.isEmpty()) return 0
            var maxSoFar = arr[0]
            var currentMax = arr[0]
            for (i in 1 until arr.size) {
                currentMax = maxOf(arr[i], currentMax + arr[i])
                maxSoFar = maxOf(maxSoFar, currentMax)
            }
            return maxSoFar
        }

        // 不删除任何元素的情况
        maxResult = maxOf(maxResult, maxSubArraySum(longArray))
        val uniqueValues = longArray.toSet().filter { it < 0 }.shuffled().take(100)

        for (value in uniqueValues) {
            val filteredList = longArray.filter { it != value }.toLongArray()
            if (filteredList.isNotEmpty()) {
                maxResult = maxOf(maxResult, maxSubArraySum(filteredList))
            }
        }

        if (longArray.size == 1) {
            return longArray[0]
        }
        return maxResult
    }
}