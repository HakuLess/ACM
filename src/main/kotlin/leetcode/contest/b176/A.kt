package leetcode.contest.b176

class SolutionA {
    fun mapWordWeights(words: Array<String>, weights: IntArray): String {
        val sb = StringBuilder()

        for (word in words) {
            var sum = 0
            for (c in word) {
                sum += weights[c - 'a']
            }
            val r = sum % 26
            val mappedChar = ('z'.code - r).toChar()
            sb.append(mappedChar)
        }

        return sb.toString()
    }
}