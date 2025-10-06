package leetcode.normal

class Solution498 {
    fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        if (mat.isEmpty()) {
            return intArrayOf()
        }
        val n = mat.size
        val m = mat[0].size
        val ans = arrayListOf<Int>()
        for (sum in 0 until n + m) {
            val tmp = arrayListOf<Int>()
            for (i in 0..sum) {
                if (i < mat.size && sum - i < mat[0].size) {
                    tmp.add(mat[i][sum - i])
                }
            }
            if (sum % 2 != 0) ans.addAll(tmp)
            else ans.addAll(tmp.reversed())
        }
        return ans.toIntArray()

    }
}