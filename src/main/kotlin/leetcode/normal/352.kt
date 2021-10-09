package leetcode.normal

import utils.Interval
import utils.and
import java.util.*
import kotlin.collections.ArrayList

class SummaryRanges() {

    private val tmLeft = TreeMap<Int, Int>()
    private val tmRight = TreeMap<Int, Int>()

    fun addNum(`val`: Int) {
        if ((tmLeft.floorEntry(`val`)?.value ?: -1) >= `val`) return
        if ((tmRight.ceilingEntry(`val`)?.value ?: Int.MAX_VALUE) <= `val`) return

        var right = `val`
        var left = `val`
        if (`val` + 1 in tmLeft.keys) {
            right = tmLeft[`val` + 1]!!
            tmLeft.remove(`val` + 1)
        }
        if (`val` - 1 in tmRight.keys) {
            left = tmRight[`val` - 1]!!
            tmRight.remove(`val` - 1)
        }
        tmLeft[left] = right
        tmRight[right] = left
    }

    fun getIntervals(): Array<IntArray> {
        return tmLeft.map { intArrayOf(it.key, it.value) }.toTypedArray()
    }

}

//class SummaryRanges() {
//
//    private val arr = ArrayList<Interval>()
//
//    fun addNum(`val`: Int) {
//        var i = 0
//        while (i in arr.indices) {
//            val cur = arr[i]
//            (cur and Interval(`val`, `val`))?.let {
//                arr[i] = it
//                if (i + 1 in arr.indices) {
//                    (arr[i + 1] and it)?.let {
//                        arr[i] = it
//                        arr.removeAt(i + 1)
//                    }
//                }
//                return
//            }
//
//            if (cur.left > `val`) {
//                arr.add(i, Interval(`val`, `val`))
//                return
//            }
//            i++
//        }
//        arr.add(Interval(`val`, `val`))
//    }
//
//    fun getIntervals(): Array<IntArray> {
//        return arr.map { intArrayOf(it.left, it.right) }.toTypedArray()
//    }
//
//}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * var obj = SummaryRanges()
 * obj.addNum(`val`)
 * var param_2 = obj.getIntervals()
 */