package leetcode.contest.c415

class SolutionA {
    fun getSneakyNumbers(nums: IntArray): IntArray {
        val n = nums.size - 2
        val frequency = IntArray(n) { 0 }
        val result = mutableListOf<Int>()

        for (num in nums) {
            frequency[num]++
            if (frequency[num] == 2) {
                result.add(num)
            }
        }

        return result.toIntArray()
    }
}