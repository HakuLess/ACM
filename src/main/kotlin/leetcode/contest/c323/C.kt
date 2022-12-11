package leetcode.contest.c323

import utils.print

fun main() {
    val s = Allocator(2)
    s.allocate(5, 10).print()
}

class Allocator(val n: Int) {

    val map = HashMap<Int, ArrayList<Pair<Int, Int>>>()

    // start end
    val l = ArrayList<Pair<Int, Int>>()

    fun allocate(size: Int, mID: Int): Int {
        if (size > n) return -1
        l.sortBy { it.first }
        if (l.isEmpty() || l[0].first >= size) {
            val target = Pair(0, size - 1)
            l.add(target)
            map[mID] = map.getOrDefault(mID, arrayListOf())
            map[mID]!!.add(target)
            return target.first
        }
        for (i in 0 until l.lastIndex) {
            if (l[i + 1].first - l[i].second > size) {
                val target = Pair(l[i].second + 1, l[i].second + size)
                l.add(target)
                map[mID] = map.getOrDefault(mID, arrayListOf())
                map[mID]!!.add(target)
                return target.first
            }
        }
        if (n - l.last().second > size) {
            val target = Pair(l.last().second + 1, l.last().second + size)
            l.add(target)
            map[mID] = map.getOrDefault(mID, arrayListOf())
            map[mID]!!.add(target)
            return target.first
        }
        return -1
    }

    fun free(mID: Int): Int {
        if (mID !in map.keys) return 0
        val r = map.remove(mID)!!
        var ans = 0
        r.forEach {
            l.remove(it)
            ans += it.second - it.first + 1
        }
        return ans
    }

}

/**
 * Your Allocator object will be instantiated and called as such:
 * var obj = Allocator(n)
 * var param_1 = obj.allocate(size,mID)
 * var param_2 = obj.free(mID)
 */