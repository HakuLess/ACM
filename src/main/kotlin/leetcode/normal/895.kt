package leetcode.normal

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FreqStack() {

    val tm = TreeMap<Int, ArrayList<Int>>()
    val map = HashMap<Int, Int>()

    fun push(`val`: Int) {
        val value = map.getOrDefault(`val`, 0) + 1
        map[`val`] = value
        tm[value] = tm.getOrDefault(value, arrayListOf())
        tm[value]!!.add(`val`)
    }

    fun pop(): Int {
        val lst = tm.lastEntry().value.last()
        tm.lastEntry().value.remove(lst)
        if (tm.lastEntry().value.isEmpty()) {
            tm.remove(tm.lastKey())
        }
        val value = map.getOrDefault(lst, 0) - 1
        if (value != 0) {
            map[lst] = value
        } else {
            map.remove(lst)
        }
        return lst
    }

}

/**
 * Your FreqStack object will be instantiated and called as such:
 * var obj = FreqStack()
 * obj.push(`val`)
 * var param_2 = obj.pop()
 */