package leetcode.contest.c358

class SolutionA {
    fun maxSum(nums: IntArray): Int {
        val map = HashMap<Int, ArrayList<Int>>()
        nums.forEach {
            val c = it.toString().toCharArray().sortedDescending()[0] - '0'
            map[c] = map.getOrDefault(c, arrayListOf())
            map[c]!!.add(it)
        }
        var ans = -1
        map.forEach {
            it.value.sortDescending()
            if (it.value.size >= 2) {
                ans = maxOf(ans, it.value[0] + it.value[1])
            }
        }
        return ans
    }
}