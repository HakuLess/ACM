package leetcode.contest.c287

class SolutionA {
    fun convertTime(current: String, correct: String): Int {
        var t = 0
        correct.split(":").map { it.toInt() }.let {
            t += it[0] * 60
            t += it[1]
        }
        current.split(":").map { it.toInt() }.let {
            t -= it[0] * 60
            t -= it[1]
        }
        var ans = 0
        arrayOf(60, 15, 5).forEach {
            ans += t / it
            t %= it
        }
        ans += t
        return ans
    }
}