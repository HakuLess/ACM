package leetcode.contest.c290

class SolutionA {
    fun intersection(nums: Array<IntArray>): List<Int> {
        var set = HashSet<Int>()
        set.addAll(nums[0].toList())
        for (i in nums.indices) {
            set = set.intersect(nums[i].toSet()).toHashSet()
        }
        val ans = ArrayList<Int>()
        ans.addAll(set)
        return ans.sorted()
    }
}