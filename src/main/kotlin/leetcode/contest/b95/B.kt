package leetcode.contest.b95

import java.util.*
import kotlin.collections.HashMap

class DataStream(val value: Int, val k: Int) {

    val q: Queue<Int> = LinkedList<Int>()

    val map = HashMap<Int, Int>()

    fun consec(num: Int): Boolean {
        q.offer(num)
        map[num] = map.getOrDefault(num, 0) + 1
        if (q.size > k) {
            val item = q.poll()
            if (map[item] == 1) {
                map.remove(item)
            } else {
                map[item] = map[item]!! - 1
            }
        }
        if (map.keys.size == 1 && q.size == k && value in map.keys) {
            return true
        }
        return false
    }

}

/**
 * Your DataStream object will be instantiated and called as such:
 * var obj = DataStream(value, k)
 * var param_1 = obj.consec(num)
 */