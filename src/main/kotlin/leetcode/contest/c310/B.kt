package leetcode.contest.c310

class SolutionB {
    fun partitionString(s: String): Int {
        val set = HashSet<Char>()
        var ans = 1
        s.forEach {
            if (it in set) {
                set.clear()
                set.add(it)
                ans++
            } else {
                set.add(it)
            }
        }
        return ans
    }
}