package leetcode.contest.b83

import utils.print
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val s = NumberContainers()
    s.find(10).print()
    s.change(2, 10)
    s.change(1, 10)
    s.change(3, 10)
    s.change(5, 10)
    s.find(10).print()
    s.change(1, 20)
    s.find(10).print()
}

class NumberContainers() {

    // value indexList
    val map = HashMap<Int, PriorityQueue<Int>>()

    // index value
    val map0 = HashMap<Int, Int>()

    fun change(index: Int, number: Int) {
        val pre = map0.getOrDefault(index, -1)
        map0[index] = number
        if (pre != -1) {
//            println("$pre remove $index")
            map[pre]!!.remove(index)
        }
        map[number] = map.getOrDefault(number, PriorityQueue())
        map[number]!!.offer(index)
    }

    fun find(number: Int): Int {
        return map[number]?.peek() ?: -1
//        return map[number]?.min() ?: -1
    }

}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * var obj = NumberContainers()
 * obj.change(index,number)
 * var param_2 = obj.find(number)
 */