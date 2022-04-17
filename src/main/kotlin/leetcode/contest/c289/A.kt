package leetcode.contest.c289

class SolutionA {
    fun digitSum(s: String, k: Int): String {
        var sb = StringBuilder(s)
        while (sb.length > k) {
            var index = 0
            val newSb = StringBuilder()
            while (index in sb.indices) {
                var c = 0
                for (i in 0 until k) {
                    if (index !in sb.indices) {
                        break
                    }
                    c += sb[index] - '0'
                    index++
                }
                newSb.append(c)
            }
            sb = newSb
        }
        return sb.toString()
    }
}