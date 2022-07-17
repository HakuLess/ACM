package leetcode.contest.c302

class SolutionB {
    fun maximumSum(nums: IntArray): Int {
        fun getSum(num: Int): Int {
            var ans = 0
            num.toString().forEach {
                ans += it - '0'
            }
            return ans
        }
        val map = HashMap<Int, ArrayList<Int>>()
        nums.forEach {
            val sum = getSum(it)
            map[sum] = map.getOrDefault(sum, arrayListOf())
            map[sum]!!.add(it)
        }
        var ans = -1
        map.forEach { t, u ->
            u.sortDescending()
            if (u.size > 1) {
                ans = maxOf(ans, u[0] + u[1])
            }
        }
        return ans
    }
}