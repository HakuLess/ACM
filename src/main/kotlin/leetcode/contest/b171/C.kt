package leetcode.contest.b171

class SolutionC {
    fun maxPoints(technique1: IntArray, technique2: IntArray, k: Int): Long {

        val n = technique1.size
        var base = 0L
        val diff = LongArray(n)

        for (i in 0 until n) {
            base += technique2[i]
            diff[i] = (technique1[i] - technique2[i]).toLong()
        }

        diff.sortDescending()

        var ans = base
        for (i in 0 until k) {
            ans += diff[i]
        }
        for (i in k until n) {
            if (diff[i] > 0) ans += diff[i]
            else break
        }
        return ans
    }
}