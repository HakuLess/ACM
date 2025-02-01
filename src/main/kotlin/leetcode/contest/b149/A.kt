package leetcode.contest.b149

class SolutionA {
    fun findValidPair(s: String): String {
        val countMap = mutableMapOf<Char, Int>()
        for (ch in s) {
            countMap[ch] = countMap.getOrDefault(ch, 0) + 1
        }
        for (i in 0 until s.length - 1) {
            val first = s[i]
            val second = s[i + 1]

            if (first != second &&
                countMap[first] == first.toString().toInt() &&
                countMap[second] == second.toString().toInt()) {
                return "$first$second"
            }
        }

        return ""
    }
}