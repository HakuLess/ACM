package leetcode.contest.c330

class SolutionA {
    fun distinctIntegers(n: Int): Int {
        val set = HashSet<Int>()
        set.add(n)
        for (i in 0..100) {
            for (x in 1..100) {
                if (set.any { it % x == 1 && it > x }) {
                    set.add(x)
                }
            }
        }
        return set.size
    }
}