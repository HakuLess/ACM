package leetcode.contest.c435

class SolutionA {
    fun maxDifference(s: String): Int {
        val frequencyMap = mutableMapOf<Char, Int>()
        for (char in s) {
            frequencyMap[char] = frequencyMap.getOrDefault(char, 0) + 1
        }

        var maxOdd = Int.MIN_VALUE
        var minEven = Int.MAX_VALUE

        for (count in frequencyMap.values) {
            if (count % 2 == 0) {
                minEven = minOf(minEven, count)
            } else {
                maxOdd = maxOf(maxOdd, count)
            }
        }

        return maxOdd - minEven
    }
}