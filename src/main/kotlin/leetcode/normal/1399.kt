package leetcode.normal

class Solution1399 {
    fun countLargestGroup(n: Int): Int {
        val map = HashMap<Int, Int>()
        for (i in 1..n) {
            var sum = 0
            i.toString().forEach {
                sum += it - '0'
            }
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        val max = map.values.maxOrNull()!!
        var ans = 0
        map.forEach { k, v ->
            if (v == max) {
                ans++
            }
        }
        return ans
    }
}