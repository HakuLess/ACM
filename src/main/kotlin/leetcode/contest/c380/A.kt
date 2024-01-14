package leetcode.contest.c380

class SolutionA {
    fun maxFrequencyElements(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val st = map.values.sortedDescending()
        var ans = 0
        st.forEach {
            if (it == st[0]) {
                ans += it
            }
        }
        return ans
    }
}