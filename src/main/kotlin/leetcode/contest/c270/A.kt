package leetcode.contest.c270

class SolutionA {
    fun findEvenNumbers(digits: IntArray): IntArray {
        val set = HashSet<Int>()
        for (i in digits.indices) {
            for (j in digits.indices) {
                if (i == j) continue
                for (k in digits.indices) {
                    if (i == k || j == k) continue
                    val a = digits[i]
                    val b = digits[j]
                    val c = digits[k]
                    set.add(a * 100 + b * 10 + c)
                }
            }
        }
        return set.sorted().filter { it >= 100 && it % 2 == 0 }.toIntArray()
    }
}