package leetcode.normal

import java.util.*

class MedianFinder() {
    /** initialize your data structure here. */
    private val left = PriorityQueue<Int>(compareBy { -it })
    private val right = PriorityQueue<Int>(compareBy { it })

    fun addNum(num: Int) {
        if (left.isEmpty() || num <= left.peek()) {
            left.add(num)
            if (left.size > right.size + 1)
                right.add(left.poll())
        } else {
            right.add(num)
            if (right.size > left.size)
                left.add(right.poll())
        }
    }

    fun findMedian(): Double {
        return if (left.size == right.size) (left.peek() + right.peek()) / 2.0
        else left.peek().toDouble()
    }
}

//class MedianFinder() {
//
//    /** initialize your data structure here. */
//    val list = ArrayList<Int>()
//
//    var c = 0
//
//    fun addNum(num: Int) {
//        val index = list.binarySearch(num)
//        if (index < 0) {
//            list.add(-index - 1, num)
//        } else {
//            list.add(index, num)
//        }
//        c++
//    }
//
//    fun findMedian(): Double {
//        return if (c % 2 == 0) {
//            (list[(c - 1) / 2].toDouble() + list[c / 2].toDouble()) / 2
//        } else {
//            list[c / 2].toDouble()
//        }
//    }
//}