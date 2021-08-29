package leetcode.normal

class Solution1588 {
    fun sumOddLengthSubarrays(arr: IntArray): Int {
        var sum = 0
        val n = arr.size
        for (i in 0 until n) {
            val rightCount = n - i - 1
            val leftOdd = (i + 1) / 2
            val rightOdd = (rightCount + 1) / 2
            val leftEven = i / 2 + 1
            val rightEven = rightCount / 2 + 1
            sum += arr[i] * (leftOdd * rightOdd + leftEven * rightEven)
        }
        return sum
    }
}