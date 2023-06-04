package leetcode.contest.c348

class SolutionC {
    fun matrixSumQueries(n: Int, queries: Array<IntArray>): Long {
        val a = LongArray(n)
        var ca = 0L
        val b = LongArray(n)
        var cb = 0L

        var ans = 0L

        for ((type, index, value) in queries.reversed()) {
            if (type == 0) {
                if (a[index] != 0L) continue
                a[index] = value.toLong()

                ans += n.toLong() * value
                ans -= cb * value
                ca++
            } else {
                if (b[index] != 0L) continue
                b[index] = value.toLong()

                ans += n.toLong() * value
                ans -= ca * value
                cb++
            }
        }
        return ans
    }
}