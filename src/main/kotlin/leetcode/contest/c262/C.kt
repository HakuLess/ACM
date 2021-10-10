package leetcode.contest.c262

import java.util.*

class StockPrice() {

    val tm = TreeMap<Int, Int>()

    val pqMax = PriorityQueue<Int>(compareBy { -it })
    val pqMin = PriorityQueue<Int>(compareBy { it })

    fun update(timestamp: Int, price: Int) {
        if (timestamp in tm.keys) {
            pqMax.remove(tm[timestamp])
            pqMin.remove(tm[timestamp])
        }
        tm[timestamp] = price
        pqMax.offer(price)
        pqMin.offer(price)
    }

    fun current(): Int {
        return tm.lastEntry().value
    }

    fun maximum(): Int {
        return pqMax.peek()
    }

    fun minimum(): Int {
        return pqMin.peek()
    }

}

/**
 * Your StockPrice object will be instantiated and called as such:
 * var obj = StockPrice()
 * obj.update(timestamp,price)
 * var param_2 = obj.current()
 * var param_3 = obj.maximum()
 * var param_4 = obj.minimum()
 */