package leetcode.contest.b65

import utils.print
import kotlin.math.abs

fun main() {
    val r = Robot(20, 14)
    r.move(32)
    r.move(18)
    r.move(18)
    r.getPos().print()
    r.getDir().print()
}

class Robot(val width: Int, val height: Int) {

    var index = 0
    val arr = ArrayList<Triple<Int, Int, String>>()
    init {
        for (i in 0 until width) {
            arr.add(Triple(i, 0, "East"))
        }
        for (i in 1 until height) {
            arr.add(Triple(width - 1, i, "North"))
        }
        for (i in width - 2 downTo 0) {
            arr.add(Triple(i, height - 1, "West"))
        }
        for (i in height - 2 downTo 1) {
            arr.add(Triple(0, i, "South"))
        }
    }

    fun move(num: Int) {
        index += num
    }

    fun getPos(): IntArray {
        return arr[index % arr.size].let {
            intArrayOf(it.first, it.second)
        }
    }

    fun getDir(): String {
        if (index != 0 && getPos().sum() == 0) return "South"
        return arr[index % arr.size].third
    }

}

/**
 * Your Robot object will be instantiated and called as such:
 * var obj = Robot(width, height)
 * obj.move(num)
 * var param_2 = obj.getPos()
 * var param_3 = obj.getDir()
 */