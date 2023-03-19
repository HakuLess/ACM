package leetcode.contest.c337

class SolutionC {
    fun beautifulSubsets(nums: IntArray, k: Int): Int {
        fun dfs(i: Int, set: HashSet<Int>): Int {
            if (i !in nums.indices) return 1

            var ans = 0
            val v = nums[i]
            if (set.all { it - v != k && v - it != k }) {
                val clone = set.clone() as HashSet<Int>
                clone.add(v)
                ans += dfs(i + 1, clone)
            }
            ans += dfs(i + 1, set)
            return ans
        }
        return dfs(0, hashSetOf())
    }
}