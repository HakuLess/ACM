package leetcode.contest.b140

class SolutionA {
    fun minElement(nums: IntArray): Int {

        fun digitSum(n: Int): Int {
            var sum = 0
            var num = n
            while (num > 0) {
                sum += num % 10
                num /= 10
            }
            return sum
        }

        return nums.map { digitSum(it) }.minOrNull() ?: 0
    }
}