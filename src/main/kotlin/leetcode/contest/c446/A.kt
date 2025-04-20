package leetcode.contest.c446

class SolutionA {
    fun calculateScore(instructions: Array<String>, values: IntArray): Long {
        var ans = 0L
        var i = 0
        val seen = HashSet<Int>()
        while (true) {
            val method = instructions[i]
            seen.add(i)
            if (method == "add") {
                ans += values[i].toLong()
                i++
            } else {
                i += values[i]
            }
            if (i in seen) {
                return ans
            }

            if (i !in instructions.indices) {
                return ans
            }
        }
        return ans
    }
}