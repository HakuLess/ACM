package leetcode.contest.b66

class SolutionB {
    fun minimumBuckets(street: String): Int {
        val ans = IntArray(street.length)
        for (i in street.indices) {
            if (street[i] == 'H') {
                if (i - 1 in street.indices && ans[i - 1] == 1) continue
                if (i + 1 in street.indices && street[i + 1] == '.') {
                    ans[i + 1] = 1
                    continue
                }
                if (i - 1 in street.indices && street[i - 1] == '.') {
                    ans[i - 1] = 1
                    continue
                }
                return -1
            }
        }
        return ans.count { it == 1 }
    }
}