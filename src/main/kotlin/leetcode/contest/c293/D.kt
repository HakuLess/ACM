package leetcode.contest.c293

import utils.BitTree
import utils.print

fun main() {
    val s = CountIntervals()
    s.add(8, 43)
    s.count().print()
    s.add(13, 16)
    s.count().print()
    s.add(26, 33)
    s.add(28, 36)
    s.add(29, 37)
    s.count().print()
    s.add(1, 100000000)
    s.count().print()
}

class CountIntervals() {

    val bitTree = BitTree(Int.MAX_VALUE / 2)

    var ans = 0

    fun add(left: Int, right: Int) {
        bitTree.update(left, right, 1)
    }

    fun count(): Int {
        return bitTree.query(1, Int.MAX_VALUE / 2)
    }

}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * var obj = CountIntervals()
 * obj.add(left,right)
 * var param_2 = obj.count()
 */