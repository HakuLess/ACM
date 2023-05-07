package leetcode.contest.c344

class SolutionC {
    fun colorTheArray(n: Int, queries: Array<IntArray>): IntArray {
        val c = IntArray(n)
        val ans = ArrayList<Int>()
        var cur = 0
        for (i in queries.indices) {
            val (index, color) = queries[i]
            if (c[index] != 0) {
                if (index - 1 in c.indices && c[index - 1] == c[index]) {
                    cur--
                }
                if (index + 1 in c.indices && c[index + 1] == c[index]) {
                    cur--
                }
            }
            c[index] = color
            if (index - 1 in c.indices && c[index - 1] == c[index]) {
                cur++
            }
            if (index + 1 in c.indices && c[index + 1] == c[index]) {
                cur++
            }
            ans.add(cur)
        }
        return ans.toIntArray()
    }
}