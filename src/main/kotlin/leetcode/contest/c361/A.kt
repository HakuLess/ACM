package leetcode.contest.c361

class SolutionA {
    fun countSymmetricIntegers(low: Int, high: Int): Int {
        var ans = 0
        for (it in low..high) {
            val item = it.toString()
            if (item.length % 2 != 0) continue
            val a = item.substring(0, item.length / 2).sumOf {
                it - '0'
            }
            val b = item.substring(item.length / 2, item.length).sumOf {
                it - '0'
            }
            if (a == b) {
                ans++
            }
        }
        return ans
    }
}