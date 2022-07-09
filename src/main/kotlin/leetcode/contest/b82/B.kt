package leetcode.contest.b82

import utils.print

fun main() {
    val s = SolutionB()
    s.latestTimeCatchTheBus(intArrayOf(10, 20), intArrayOf(2, 17, 18, 19), 2).print()
    s.latestTimeCatchTheBus(intArrayOf(20, 30, 10), intArrayOf(19, 13, 26, 4, 25, 11, 21), 2).print()
    s.latestTimeCatchTheBus(intArrayOf(3), intArrayOf(2, 4), 2).print()
    s.latestTimeCatchTheBus(intArrayOf(3), intArrayOf(3, 4), 2).print()
    s.latestTimeCatchTheBus(intArrayOf(2), intArrayOf(2), 1).print()
    s.latestTimeCatchTheBus(intArrayOf(3), intArrayOf(4), 1).print()
}

class SolutionB {
    fun latestTimeCatchTheBus(buses: IntArray, passengers: IntArray, capacity: Int): Int {
        buses.sort()
        passengers.sort()
        val set = HashSet<Int>()
        set.addAll(passengers.toList())
        var j = 0
        for (i in buses.indices) {
            var cur = 0
            while (j in passengers.indices && cur < capacity) {
                if (passengers[j] <= buses[i]) {
                    j++
                    cur++
                } else {
                    break
                }
            }
            if (i == buses.lastIndex) {
                var ans = if (cur == capacity) passengers[j - 1] - 1
                else buses[i]
                while (ans in set) {
                    ans--
                }
                return ans
            }
        }
        return -1
    }
}