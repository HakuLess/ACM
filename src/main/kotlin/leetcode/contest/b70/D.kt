package leetcode.contest.b70

import utils.print

fun main() {
    val s = SolutionD()
//    s.numberOfWays("SSPPSPS").print()
//    s.numberOfWays("SSPPSPSSSSS").print()
//    s.numberOfWays("PPSPSP").print()
//    s.numberOfWays("P").print()
    s.numberOfWays("SSSPPPSPPSPSSSSSSPPPSPP").print()
}

class SolutionD {
    fun numberOfWays(corridor: String): Int {
        val a = corridor.trimEnd('P')
        if (a.count { it == 'S' } % 2 != 0) return 0
        if (a.count { it == 'S' } == 0) return 0
        if (a.count { it == 'S' } == 2) return 1
        val mod = 1000000007L
        var ans = 1L
        var curS = 0
        var curP = 0
        for (i in a.indices) {
            if (a[i] == 'S') {
                curS++
            } else {
                if (curS == 2) {
                    curP++
                }
            }
            if (curS == 2 && (i + 1 !in a.indices || a[i + 1] == 'S')) {
                ans *= (curP + 1)
                ans %= mod
                curS = 0
                curP = 0
            }
        }
        return ans.toInt()
    }
}