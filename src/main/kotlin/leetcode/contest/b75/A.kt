package leetcode.contest.b75

class SolutionA {
    fun minBitFlips(start: Int, goal: Int): Int {
        val a = start.toString(2).padStart(100, '0')
        val b = goal.toString(2).padStart(100, '0')
        var ans = 0
        for (i in a.indices) {
            if (a[i] != b[i]) {
                ans++
            }
        }
        return ans
    }
}