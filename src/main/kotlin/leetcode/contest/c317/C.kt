package leetcode.contest.c317

class SolutionC {
    fun makeIntegerBeautiful(n: Long, target: Int): Long {
        var mod = 10L
        var cur = n
        while (cur.toString().map { it - '0' }.sum() > target) {
            val add = mod - cur % mod
            cur += add
            mod *= 10
        }
        return cur - n
    }
}