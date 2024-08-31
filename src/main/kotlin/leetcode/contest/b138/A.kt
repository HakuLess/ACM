package leetcode.contest.b138

class SolutionA {
    fun generateKey(num1: Int, num2: Int, num3: Int): Int {
        val str1 = num1.toString().padStart(4, '0')
        val str2 = num2.toString().padStart(4, '0')
        val str3 = num3.toString().padStart(4, '0')

        var result = ""

        for (i in 0 until 4) {
            val minDigit = minOf(str1[i], str2[i], str3[i])
            result += minDigit
        }

        return result.toInt()
    }
}