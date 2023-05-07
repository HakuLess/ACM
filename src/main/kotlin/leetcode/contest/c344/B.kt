package leetcode.contest.c344

import utils.print

fun main() {
    val f = FrequencyTracker()
    f.add(1)
    f.deleteOne(1)
    f.hasFrequency(1).print()
}

class FrequencyTracker() {

    private val map = HashMap<Int, Int>()

    private val mapF = HashMap<Int, Int>()

    fun add(number: Int) {
        if (map[number] in mapF.keys) {
            mapF[map[number]!!] = mapF[map[number]!!]!! - 1
        }
        map[number] = map.getOrDefault(number, 0) + 1
        mapF[map[number]!!] = mapF.getOrDefault(map[number], 0) + 1
    }

    fun deleteOne(number: Int) {
        if (map.getOrDefault(number, 0) > 0) {
            mapF[map[number]!!] = mapF[map[number]!!]!! - 1
            map[number] = map.getOrDefault(number, 0) - 1
            mapF[map[number]!!] = mapF.getOrDefault(map[number], 0) + 1
        }
    }

    fun hasFrequency(frequency: Int): Boolean {
        return mapF.getOrDefault(frequency, 0) > 0
    }

}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * var obj = FrequencyTracker()
 * obj.add(number)
 * obj.deleteOne(number)
 * var param_3 = obj.hasFrequency(frequency)
 */