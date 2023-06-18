package leetcode.contest.c350

import utils.print

fun main() {
    val s = SolutionC()
    s.specialPerm(intArrayOf(2, 3, 6)).print()
    s.specialPerm(intArrayOf(1, 4, 3)).print()
    s.specialPerm(intArrayOf(83, 105)).print()
}

// seen用二进制就能过
class SolutionC {
    fun specialPerm(nums: IntArray): Int {
        val mod = 1000000007L
        val map = HashMap<Int, HashSet<Int>>()
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (i != j && ((nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0))) {
                    val set = map.getOrDefault(i, hashSetOf())
                    set.add(j)
                    map[i] = set
                }
            }
        }

        val target = (1 shl nums.size) - 1
        var ans = 0L
        val seen = HashMap<String, Long>()
        fun dfs(mask: Int, cur: Int): Long {
            if (mask == target) return 1L
            val key = "${mask}_$cur"
            if (key in seen) {
                return seen[key]!!
            }
            var tmp = 0L
            map[cur]?.forEach {
                if ((mask and (1 shl it)) == 0)
                    tmp += dfs(mask or (1 shl it), it)
            }
            tmp %= mod
            return tmp.also {
                seen[key] = it
            }
        }

        for (i in nums.indices) {
            ans += dfs(1 shl i, i)
            ans %= mod
        }

        return ans.toInt()
    }
}