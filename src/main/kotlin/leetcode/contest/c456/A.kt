package leetcode.contest.c456

class SolutionA {
    fun partitionString(s: String): List<String> {
        val ans = ArrayList<String>()
        val sb = StringBuilder()
        val set = HashSet<String>()
        s.forEach {
            sb.append(it)
            if (sb.toString() !in set) {
                ans.add(sb.toString())
                set.add(sb.toString())
                sb.clear()
            }
        }
        return ans
    }
}