package leetcode.contest.b67

import utils.MultiSet

class SORTracker() {

    // 二分
    class Item(var name: String, var value: Int) : Comparable<Item> {
        override fun compareTo(other: Item): Int {
            if (value > other.value) return -1
            if (value < other.value) return 1
            if (name > other.name) return 1
            if (name < other.name) return -1
            return 0
        }
    }

    var t = -1

    private val multiSet = MultiSet<Item>()

    fun add(name: String, score: Int) {
        multiSet.add(Item(name, score))
    }

    fun get(): String {
        t++
        return multiSet.get(t).name
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * var obj = SORTracker()
 * obj.add(name,score)
 * var param_2 = obj.get()
 */