package leetcode.contest.c289

class SolutionB {
    fun minimumRounds(tasks: IntArray): Int {
        val map = HashMap<Int, Int>()
        tasks.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        if (map.any { it.value == 1 }) return -1
        var ans = 0
        map.forEach { t, u ->
            if (u % 3 == 0) {
                ans += u / 3
            } else {
                ans += u / 3 + 1
            }
        }
        return ans
    }
}