package leetcode.contest.c291

class SolutionA {
    fun removeDigit(number: String, digit: Char): String {
        val ans = StringBuilder()
        var remove = false
        for (i in number.indices) {
            if (number[i] == digit && !remove) {
                if (i == number.lastIndex) {
                    remove = true
                } else if (number[i] < number[i + 1]) {
                    remove = true
                } else if (i == number.indexOfLast { it == digit }) {
                    remove = true
                } else {
                    ans.append(number[i])
                }
            } else {
                ans.append(number[i])
            }
        }
        return ans.toString()
    }
}