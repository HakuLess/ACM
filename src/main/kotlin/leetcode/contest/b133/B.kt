package leetcode.contest.b133

class SolutionB {
    fun minOperations(nums: IntArray): Int {
        var n = nums.size
        var operations = 0

        for (i in 0 until n - 2) {
            // 如果当前元素是 0，则反转它和接下来的两个元素
            if (nums[i] == 0) {
                operations++
                nums[i] = nums[i] xor 1
                nums[i + 1] = nums[i + 1] xor 1
                nums[i + 2] = nums[i + 2] xor 1
            }
        }

        // 检查是否全部变为 1
        for (i in n - 2 until n) {
            if (nums[i] == 0) return -1
        }

        return operations
    }
}