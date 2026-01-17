package leetcode.contest.b174

class SolutionB {
    fun minOperations(nums: IntArray, target: IntArray): Int {
        val need = HashSet<Int>()
        for (i in nums.indices) {
            if (nums[i] != target[i]) {
                need.add(nums[i])
            }
        }
        return need.size
    }
}