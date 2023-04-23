package leetcode.contest.c342

class SolutionB {
    fun sumOfMultiples(n: Int): Int {
        var ans = 0
        (1..n).forEach {
            if (it % 3 == 0 || it % 5 == 0 || it % 7 == 0) {
                ans += it
            }
        }
        return ans
    }
}