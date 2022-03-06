package leetcode.contest.c283

class SolutionA {
    fun cellsInRange(s: String): List<String> {
        val ans = ArrayList<String>()
        for (i in s[0]..s[3]) {
            for (j in s[1]..s[4]) {
                ans.add("$i$j")
            }
        }
        return ans
    }
}