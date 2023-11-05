package leetcode.contest.c370

class SolutionB {
    fun findChampion(n: Int, edges: Array<IntArray>): Int {
        val set = HashSet<Int>()
        for (i in 0 until n) {
            set.add(i)
        }
        edges.forEach {
            val (a, b) = it
            set.remove(b)
        }
        if (set.size == 1) return set.first()
        return -1
    }
}