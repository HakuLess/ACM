package leetcode.contest.b97

class SolutionB {
    fun maxCount(banned: IntArray, n: Int, maxSum: Int): Int {
        var cur = 0
        var ans = 0
        val set = HashSet<Int>()
        set.addAll(banned.toList())
        for (i in 1..n) {
            if (i !in set) {
                cur += i
                ans++
            }
            if (cur > maxSum) return ans - 1
        }
        return ans
    }
}