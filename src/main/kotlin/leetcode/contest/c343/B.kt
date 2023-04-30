package leetcode.contest.c343

class SolutionB {
    fun firstCompleteIndex(arr: IntArray, mat: Array<IntArray>): Int {
        val a = HashMap<Int, HashSet<Int>>()
        val b = HashMap<Int, HashSet<Int>>()
        val c = HashMap<Int, Pair<Int, Int>>()
        for (i in mat.indices) {
            for (j in mat[0].indices) {
                c[mat[i][j]] = Pair(i, j)
                a[i] = a.getOrDefault(i, hashSetOf())
                a[i]!!.add(mat[i][j])
                b[j] = b.getOrDefault(j, hashSetOf())
                b[j]!!.add(mat[i][j])
            }
        }
        for (i in arr.indices) {
            val v = arr[i]
            val (x, y) = c[v]!!
            a[x]!!.remove(v)
            if (a[x]!!.isEmpty()) return i
            b[y]!!.remove(v)
            if (b[y]!!.isEmpty()) return i
        }
        return -1
    }
}