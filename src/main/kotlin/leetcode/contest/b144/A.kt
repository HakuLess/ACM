package leetcode.contest.b144

class SolutionA {
    fun canAliceWin(n: Int): Boolean {
        var remove = 10
        var ans = false
        var left = n
        while (left >= remove) {
            left -= remove
            remove -= 1
            ans = !ans
        }
        return ans
    }
}