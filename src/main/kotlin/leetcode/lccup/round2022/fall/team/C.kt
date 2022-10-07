package leetcode.lccup.round2022.fall.team

import utils.print

fun main() {
    val s = SolutionC()
    s.beautifulBouquet(intArrayOf(1, 2, 3, 2), 1).print()
    s.beautifulBouquet(intArrayOf(5, 3, 3, 3), 2).print()
}

class SolutionC {
    fun beautifulBouquet(flowers: IntArray, cnt: Int): Int {
        var ans = 0L
        val mod = 1000000007L
        val cur = HashMap<Int, Int>()
        var r = 0
        for (i in flowers.indices) {
            while (r in flowers.indices) {
                val c = flowers[r]
                if (cur.getOrDefault(c, 0) < cnt) {
                    cur[c] = cur.getOrDefault(c, 0) + 1
                } else {
                    ans += (r - i)
//                    println("left is $i, right is $r, add ${r - i}")
                    break
                }
                r++
            }
            if (r !in flowers.indices) {
                ans += (r - i)
            }
            cur[flowers[i]] = cur.getOrDefault(flowers[i], 0) - 1
        }
        return (ans % mod).toInt()
    }
}