package leetcode.normal

class Solution1394 {
    fun findLucky(arr: IntArray): Int {
        val map = HashMap<Int, Int>()
        arr.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        map.keys.sortedDescending().forEach {
            if (map[it] == it) {
                return it
            }
        }
        return -1
    }
}