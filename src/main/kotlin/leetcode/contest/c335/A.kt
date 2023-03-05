package leetcode.contest.c335

class SolutionA {
    fun passThePillow(n: Int, time: Int): Int {
        var l = time
        var c = 1
        var add = true
        while (l != 0) {
            if (add) c++
            else c--

            if (c == 1) add = true
            if (c == n) add = false

            l--
        }
        return c
    }
}