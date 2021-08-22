package leetcode.b59

class Solution5835 {
    fun maxMatrixSum(matrix: Array<IntArray>): Long {
        val a = arrayListOf<Int>()
        var ans = 0L
        var cnt = 0
        matrix.forEach {
            it.forEach {
                if (it >= 0) {
                    a.add(it)
                } else {
                    cnt++
                    a.add(-it)
                }
            }
        }
        a.forEach {
            ans += it
        }
        if (cnt % 2 == 1) ans -= a.minOrNull()!! * 2
        return ans
    }
}