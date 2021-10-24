package leetcode.normal

// 摩尔投票法
class Solution229 {
    fun majorityElement(nums: IntArray): List<Int> {
        var x: Int? = null
        var xc = 0
        var y: Int? = null
        var yc = 0

        nums.forEach {
            when {
                it == x -> {
                    xc++
                }
                it == y -> {
                    yc++
                }
                x == null -> {
                    x = it
                    xc++
                }
                y == null -> {
                    y = it
                    yc++
                }
                else -> {
                    xc--
                    if (xc == 0) x = null
                    yc --
                    if (yc == 0) y = null
                }
            }
        }

        val ans = arrayListOf<Int>()
        if (nums.count { it == x } > nums.size / 3) ans.add(x!!)
        if (nums.count { it == y } > nums.size / 3) ans.add(y!!)
        return ans
    }
}