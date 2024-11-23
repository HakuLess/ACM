package leetcode.contest.b144

class SolutionB {
    fun shiftDistance(s: String, t: String, nextCost: IntArray, previousCost: IntArray): Long {
        val alphabetSize = 26
        var totalCost = 0L

        for (i in s.indices) {
            val charS = s[i] - 'a'
            val charT = t[i] - 'a'

            if (charS != charT) {
                val forwardSteps = if (charT >= charS) charT - charS else charT - charS + alphabetSize
                val forwardCost = (0 until forwardSteps).fold(0L) { acc, step ->
                    acc + nextCost[(charS + step) % alphabetSize]
                }

                val backwardSteps = if (charT <= charS) charS - charT else charS - charT + alphabetSize
                val backwardCost = (0 until backwardSteps).fold(0L) { acc, step ->
                    acc + previousCost[(charS - step + alphabetSize) % alphabetSize]
                }

                totalCost += minOf(forwardCost, backwardCost)
            }
        }

        return totalCost
    }
}