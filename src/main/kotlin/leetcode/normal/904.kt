package leetcode.normal

class Solution904 {
    fun totalFruit(fruits: IntArray): Int {
        val map = HashMap<Int, Int>()
        var left = 0
        var ans = 0
        for (right in fruits.indices) {
            val rightFruit = fruits[right]
            map[rightFruit] = map.getOrDefault(rightFruit, 0) + 1
            while (map.keys.size > 2) {
                val leftFruit = fruits[left]
                map[leftFruit] = map.getOrDefault(leftFruit, 0) - 1
                if (map[leftFruit] == 0) {
                    map.remove(leftFruit)
                }
                left++
            }

            ans = maxOf(ans, map.values.sum())
        }
        return ans
    }
}