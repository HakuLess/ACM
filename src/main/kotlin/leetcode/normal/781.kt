package leetcode.normal

class Solution781 {
    fun numRabbits(answers: IntArray): Int {
        val map = HashMap<Int, Int>()
        answers.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        var ans = 0
        map.keys.forEach { key ->
            val value = map[key]!!
            // 用于处理余数不为0场景 多计算一次
            val add = (value + key) / (key + 1)
            ans += add * (key + 1)
        }
        return ans
    }
}