package leetcode.lccup.b

import utils.dir8
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionE()
    s.gobang("[[1,2,1],[1,4,1],[1,5,1],[2,1,0],[2,3,0],[2,4,0],[3,2,1],[3,4,0],[4,2,1],[5,2,1]]".toGrid()).print()
}

class SolutionE {
    fun gobang(pieces: Array<IntArray>): String {
        val map = HashMap<Pair<Int, Int>, Int>()
        pieces.forEach {
            // 0 Black 1 White
            map[Pair(it[0], it[1])] = it[2]
        }
        var b4 = 0
        var w4 = 0
        var w4b0 = 0
        var b3w0 = 0
        val hitMap = HashMap<Pair<Int, Int>, Int>()
        val b3Hit = HashSet<Pair<Int, Int>>()
        val w4Hit = HashSet<Pair<Int, Int>>()

        fun check(arr: ArrayList<Pair<Int, Int>>) {
            var w = 0
            var b = 0
            val l = arrayListOf<Int>()
            val wl = arrayListOf<Int>()
            var rw = -1
            for (i in arr.indices) {
                val it = arr[i]
                if (map.getOrDefault(it, -1) == 0) {
                    b++
                    l.add(i)
                } else if (map.getOrDefault(it, -1) == 1) {
                    w++
                    wl.add(i)
                }
            }
            for (i in wl.indices) {
                if (wl[i] != i) {
                    rw = i
                    break
                }
            }
            if (b == 4) b4++
            if (w == 4 && b == 0 && (rw == 0 || rw == -1)) {
                // 连续4白 无黑
                w4++
            } else if (w == 4 && b == 0) {
                // 非连续4白 无黑
                w4Hit.add(arr[rw])
                w4b0++
            }

            val dir = Pair(arr[1].first - arr[0].first, arr[1].second - arr[0].second)

            if (b == 3 && w == 0 && l.last() - l.first() != 4) {
                // 4中 3黑
                if (map.getOrDefault(
                        Pair(arr[l.first()].first - dir.first, arr[l.first()].second - dir.second),
                        -1
                    ) != 1 &&
                    map.getOrDefault(Pair(arr[l.last()].first + dir.first, arr[l.last()].second + dir.second), -1) != 1
                ) {
                    // 且前后无白挡
                    b3w0++
                }
                if (l.last() - l.first() == 2) {
                    b3Hit.add(Pair(arr[l.last()].first + dir.first, arr[l.last()].second + dir.second))
                    b3Hit.add(Pair(arr[l.first()].first - dir.first, arr[l.first()].second - dir.second))
                } else if (l.last() - l.first() == 3) {
                    val miss = (0..4).first {
                        (it in l.first()..l.last() && map.getOrDefault(arr[it], -1) != 0)
                    }
                    b3Hit.add(arr[miss])
                }
            }

            if (b == 3 && w == 1) {
                if (map.getOrDefault(arr[0], -1) == 1 &&
                    map.getOrDefault(arr[4], -1) == -1
                ) {
                    hitMap[arr[4]] = hitMap.getOrDefault(arr[4], 0) + 1
                } else if (map.getOrDefault(arr[0], -1) == -1 &&
                    map.getOrDefault(arr[4], -1) == 1
                ) {
                    hitMap[arr[0]] = hitMap.getOrDefault(arr[0], 0) + 1
                }
            }
            println("from ${arr[0]} to ${arr[4]} is black $b white $w")
        }

        for (it in pieces) {
            for (dir in dir8) {
                val cur = ArrayList<Pair<Int, Int>>()
                for (s in -4..4) {
                    cur.add(Pair(it[0] + s * dir[0], it[1] + s * dir[1]))
                    if (cur.size == 5) {
                        check(cur)
                        cur.removeAt(0)
                    }
                }
            }
        }

        if (b4 > 0) return "Black"
        if (w4 > 0) return "White"
        if (w4b0 > 0 && w4Hit.any { it !in b3Hit }) return "White"
        if (b3w0 > 0) return "Black"
//        if (hitMap.isNotEmpty() && hitMap.values.max()!! >= 2) return "Black"
        if (hitMap.isNotEmpty() && hitMap.values.maxOrNull()!! >= 2) return "Black"
        return "None"
    }
}