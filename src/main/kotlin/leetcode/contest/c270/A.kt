package leetcode.contest.c270

class SolutionA {
    fun findEvenNumbers(digits: IntArray): IntArray {
        val set = HashSet<Int>()
        for (i in 0 until digits.size - 2) {
            for (j in i + 1 until digits.size - 1) {
                for (k in j + 1 until digits.size) {
                    val a = digits[i]
                    val b = digits[j]
                    val c = digits[k]
                    set.add(a * 100 + b * 10 + c)
                    set.add(a * 100 + c * 10 + b)
                    set.add(b * 100 + a * 10 + c)
                    set.add(b * 100 + c * 10 + a)
                    set.add(c * 100 + a * 10 + b)
                    set.add(c * 100 + b * 10 + a)
                }
            }
        }
        return set.sorted().filter { it >= 100 && it % 2 == 0 }.toIntArray()
    }
}