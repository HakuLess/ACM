package leetcode.contest.c400

class SolutionA {
    fun minimumChairs(s: String): Int {
        var ans = 0
        var cur = 0
        s.forEach {
            if (it == 'E') {
                cur++
            } else {
                cur--
            }
            ans = maxOf(ans, cur)
        }
        return ans
    }
}
