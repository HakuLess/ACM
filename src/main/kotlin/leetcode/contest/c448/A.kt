package leetcode.contest.c448

class SolutionA {
    fun maxProduct(n: Int): Int {
        var ans = 0
        val arr = n.toString().map { it - '0' }
        for (i in arr.indices) {
            for (j in i + 1 until arr.size) {
                ans = maxOf(ans, arr[i] * arr[j])
            }
        }
        return ans
    }
}