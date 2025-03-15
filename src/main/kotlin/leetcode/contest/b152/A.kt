package leetcode.contest.b152

class SolutionA {
    fun totalNumbers(digits: IntArray): Int {
        val set = HashSet<Int>()
        for (i in digits.indices) {
            for (j in digits.indices) {
                for (k in digits.indices) {
                    if (i == j || j == k || i == k) continue
                    if (digits[i] == 0) continue

                    set.add(digits[i] * 100 + digits[j] * 10 + digits[k])
                }
            }
        }
        return set.filter { it % 2 == 0 }.size
    }
}