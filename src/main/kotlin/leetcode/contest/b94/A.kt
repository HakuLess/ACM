package leetcode.contest.b94

class SolutionA {
    fun captureForts(forts: IntArray): Int {
        var ans = 0
        var l = 0
        while (l in forts.indices) {
            if (forts[l] != 0) {
                for (r in l + 1 until forts.size) {
                    if (forts[r] != 0) {
                        ans = maxOf(ans, if (forts[l] == forts[r]) 0 else r - l - 1)
                        l = r
                    }
                }
            }
            l++
        }
        return ans
    }
}