package leetcode.contest.c153

class SolutionD {
    fun maxActiveSectionsAfterTrade(s: String, queries: Array<IntArray>): List<Int> {

        fun maxActiveSectionsAfterTrade(s: String, total: Int): Int {
            val n = s.length
            var ans = 0
            var i = 0
            while (i < n) {
                if (s[i] == '1') {
                    val start = i
                    while (i < n && s[i] == '1') {
                        i++
                    }
                    val end = i - 1
                    if (start > 0 && end < n - 1) {
                        var leftZeros = 0
                        var j = start - 1
                        while (j >= 0 && s[j] == '0') {
                            leftZeros++
                            j--
                        }
                        var rightZeros = 0
                        j = end + 1
                        while (j < n && s[j] == '0') {
                            rightZeros++
                            j++
                        }
                        val more = leftZeros + rightZeros
                        ans = maxOf(ans, more)
                    }
                } else {
                    i++
                }
            }
            return total + ans
        }

        val ans = ArrayList<Int>()
        val total = s.count { it == '1' }
        queries.forEach {
            ans.add(maxActiveSectionsAfterTrade(s.substring(it[0], it[1] + 1), total))
        }
        return ans
    }
}