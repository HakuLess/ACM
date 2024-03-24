package leetcode.contest.c390

import utils.SortedList

class SolutionC {
    fun mostFrequentIDs(nums: IntArray, freq: IntArray): LongArray {
        val map = HashMap<Int, Long>()
        val n = nums.size
        val ans = ArrayList<Long>()
        val l = SortedList<Long>()
        for (i in 0 until n) {
            val cnt = freq[i]
            val item = nums[i]
            val before = map.getOrDefault(item, 0)
            map[item] = map.getOrDefault(item, 0) + cnt
            val after = map.getOrDefault(item, 0)
            if (before != 0L)
                l.remove(before)
            l.insert(after)
            ans.add(l.valueList.last())
        }
        return ans.toLongArray()
    }
}