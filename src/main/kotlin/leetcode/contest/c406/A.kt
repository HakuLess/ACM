package leetcode.contest.c406

class SolutionA {
    fun getSmallestString(s: String): String {
        val n = s.length
        var minStr = s

        for (i in 0 until n - 1) {
            if ((s[i] - '0') % 2 == (s[i + 1] - '0') % 2) {
                val swapped = s.toCharArray()
                val temp = swapped[i]
                swapped[i] = swapped[i + 1]
                swapped[i + 1] = temp
                val newStr = String(swapped)
                if (newStr < minStr) {
                    minStr = newStr
                }
            }
        }
        return minStr
    }
}