package leetcode.contest.b112

class SolutionC {
    fun maxSum(nums: List<Int>, m: Int, k: Int): Long {
        var c = 0
        val map = HashMap<Int, Int>()
        var sum = 0L
        var ans = 0L
        for (i in nums.indices) {
            val item = nums[i]
            map[item] = map.getOrDefault(item, 0) + 1
            c++
            sum += item
            if (c > k) {
                val left = nums[i - k]
                map[left] = map.getOrDefault(left, 0) - 1
                sum -= left
                if (map[left] == 0) {
                    map.remove(left)
                }
                c--
            }
            if (map.keys.size >= m) {
                ans = maxOf(ans, sum)
            }
        }
        return ans
    }
}