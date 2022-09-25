package leetcode.contest.c312

class SolutionA {
    fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
        val p = ArrayList<Pair<String, Int>>()
        for (i in names.indices) {
            p.add(Pair(names[i], heights[i]))
        }
        return p.sortedByDescending { it.second }.map { it.first }.toTypedArray()
    }
}