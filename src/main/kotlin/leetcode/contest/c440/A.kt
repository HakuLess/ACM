package leetcode.contest.c440

class SolutionA {
    fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
        val n = fruits.size
        val used = BooleanArray(n) { false }
        var ans = 0

        for (fruit in fruits) {
            var placed = false
            for (i in baskets.indices) {
                if (!used[i] && baskets[i] >= fruit) {
                    used[i] = true
                    placed = true
                    break
                }
            }
            if (!placed) {
                ans++
            }
        }
        return ans
    }
}