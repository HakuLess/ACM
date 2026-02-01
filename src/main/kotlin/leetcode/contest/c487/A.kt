package leetcode.contest.c487

class SolutionA {
    fun countMonobit(n: Int): Int {
        var x = n + 1
        var ans = 0
        while (x > 1) {
            x = x shr 1
            ans++
        }
        return ans + 1
    }
}