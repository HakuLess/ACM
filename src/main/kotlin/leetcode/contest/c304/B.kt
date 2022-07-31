package leetcode.contest.c304

class SolutionB {
    fun maximumGroups(grades: IntArray): Int {
        val n = grades.size
        var c = 1
        var cur = 0
        var ans = 0
        while (true) {
            cur += c
            ans++
            c++
            if (cur > n) {
                return ans - 1
            }
        }
    }
}