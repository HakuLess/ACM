package leetcode.contest.c373

class SolutionA {
    fun areSimilar(mat: Array<IntArray>, k: Int): Boolean {
        for (i in mat.indices) {
            if (i % 2 == 0) {
                // 右移K次
                val ori = mat[i].joinToString()
                val cur = ArrayList<Int>()
                cur.addAll(mat[i].toTypedArray())
                repeat(k) {
                    cur.add(0, cur.removeLast())
                }
                if (cur.joinToString() != ori) return false
            } else {
                // 左移K次
                val ori = mat[i].joinToString()
                val cur = ArrayList<Int>()
                cur.addAll(mat[i].toTypedArray())
                repeat(k) {
                    cur.add(cur.lastIndex, cur.removeFirst())
                }
                if (cur.joinToString() != ori) return false
            }
        }
        return true
    }
}