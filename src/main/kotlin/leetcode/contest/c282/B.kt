package leetcode.contest.c282

class SolutionB {
    fun minSteps(s: String, t: String): Int {
        val a = IntArray(26)
        val b = IntArray(26)
        s.forEach {
            a[it - 'a']++
        }
        t.forEach {
            b[it - 'a']++
        }
        var ans = 0
        for (i in 0 until 26) {
            ans += maxOf(a[i], b[i]) * 2 - a[i] - b[i]
        }
        return ans
    }
}