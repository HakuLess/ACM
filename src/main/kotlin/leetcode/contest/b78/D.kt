package leetcode.contest.b78

import utils.print


fun main() {
    val s = SolutionD()
    s.largestVariance("aababbb").print()
    s.largestVariance("lripaa").print()
    s.largestVariance("ababab").print()
}

class SolutionD {
    fun largestVariance(s: String): Int {
        var ans = 0
        for (a in 'a'..'z') {
            for (b in 'a'..'z') {
                if (a == b) continue
                // a 最多
                // b 最少
                var aCount = 0
                var bCount = 0
                var can = false
                s.forEach {
                    if (it == a) aCount++
                    if (it == b) {
                        bCount++
                        can = true
                    }
                    if (bCount > aCount) {
                        aCount = 0
                        bCount = 0
                    }
                    if (aCount != 0 && bCount != 0) {
                        ans = maxOf(ans, aCount - bCount)
                    }
                    if (can) {
                        ans = maxOf(ans, aCount - bCount - 1)
                    }
                }
            }
        }
        return ans
    }
}