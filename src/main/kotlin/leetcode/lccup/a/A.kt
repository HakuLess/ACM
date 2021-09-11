package leetcode.lccup.a

// 2021 LCCUP 秋季个人赛
class SolutionA {
    fun minimumSwitchingTimes(source: Array<IntArray>, target: Array<IntArray>): Int {
        val map = HashMap<Int, Int>()
        source.forEach {
            it.forEach {
                map[it] = map.getOrDefault(it, 0) + 1
            }
        }
        target.forEach {
            it.forEach {
                map[it] = map.getOrDefault(it, 0) - 1
            }
        }
        var ans = 0
        map.forEach { t, u ->
            if (u > 0) ans += u
        }
        return ans
    }
}