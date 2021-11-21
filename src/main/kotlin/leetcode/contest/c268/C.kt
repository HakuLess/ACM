package leetcode.contest.c268

import utils.SegmentTree
import utils.print
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val r = RangeFreqQuery(intArrayOf(12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56))
    r.query(1, 2, 4).print()
    r.query(0, 11, 33).print()
}

class RangeFreqQuery(arr: IntArray) {

    val map = HashMap<Int, TreeMap<Int, Int>>()

    init {
        for (i in arr.indices) {
            if (arr[i] !in map.keys) {
                map[arr[i]] = TreeMap<Int, Int>()
            }
            val index = map[arr[i]]!!.size
            map[arr[i]]!![i] = index + 1
        }
    }

    fun query(left: Int, right: Int, value: Int): Int {
        val tm = map[value]
        if (tm.isNullOrEmpty()) return 0
        val r = tm.floorKey(right)
        val l = tm.ceilingKey(left)
        if (r == null || l == null) return 0
        return tm[r]!! - tm[l]!! + 1
    }

}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */