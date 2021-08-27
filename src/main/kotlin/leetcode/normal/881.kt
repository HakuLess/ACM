package leetcode.normal

import java.util.*

class Solution881 {
    fun numRescueBoats(people: IntArray, limit: Int): Int {
        val tm = TreeMap<Int, Int>()
        people.forEach {
            tm[it] = tm.getOrDefault(it, 0) + 1
        }
        var ans = 0
        while (tm.isNotEmpty()) {
            val keyA = tm.firstKey()
            tm[keyA] = tm[keyA]!! - 1
            if (tm[keyA]!! == 0) tm.remove(keyA)
            val keyB = tm.floorKey(limit - keyA)
            if (keyB != null) {
                tm[keyB] = tm[keyB]!! - 1
                if (tm[keyB]!! == 0) tm.remove(keyB)
            }
            ans++
        }
        return ans
    }
}