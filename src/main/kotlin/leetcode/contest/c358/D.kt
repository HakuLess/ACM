package leetcode.contest.c358

import utils.*

fun main() {
    val s = SolutionD()
//    s.maximumScore(listOf(8, 3, 9, 3, 8), 2).print()
    // 4788
//    s.maximumScore(listOf(19, 12, 14, 6, 10, 18), 3).print()
    // 256720975
    s.maximumScore(listOf(3289, 2832, 14858, 22011), 6).print()
}

class SolutionD {
    fun maximumScore(nums: List<Int>, k: Int): Int {
        val mod = 1000000007L
        val l = ArrayList<Pair<Int, Int>>()
        val primes = getPrime(100001)
//        primes.print()
        nums.forEach {
            var c = it
            val set = HashSet<Int>()
            while (c != 1) {
                val p = primes[c]
                if (p == 1) {
                    set.add(c)
                    break
                } else {
                    set.add(p)
                }
                c /= p
//                println("next c is $c")
            }
//            println("$it with primes ${set.joinToString()}")
            l.add(Pair(it, set.size))
        }
//        l.joinToString().print()
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in l.indices) {
            val cnt = l[i].second
            map[cnt] = map.getOrDefault(cnt, arrayListOf())
            map[cnt]!!.add(i)
        }
        // 可覆盖次数
        val overList = ArrayList<Pair<Int, Int>>()
        for (i in l.indices) {
            val cnt = l[i].second
            var left = 0
            var right = l.lastIndex
            map.keys.forEach {
                val list = map[it]!!
                if (it == cnt) {
                    list.biLastIndexOf { it < i }.let {
                        if (it != -1) {
                            left = maxOf(left, list[it] + 1)
                        }
                    }
//                    list.joinToString().print()
//                    println("find last it < $i")
//                    println("get left $left")
                } else if (it > cnt) {
                    list.biLastIndexOf { it < i }.let {
                        if (it != -1) {
                            left = maxOf(left, list[it] + 1)
                        }
                    }
                    map[it]!!.biFirstIndexOf { it > i }.let {
                        if (it != -1) {
                            right = minOf(right, list[it] - 1)
                        }
                    }
                }
            }
//            println("$i with ${l[i].first} over $left..$right")
            overList.add(Pair(l[i].first, (i - left + 1) * (right - i + 1)))
        }
        var ans = 1L
        overList.sortByDescending { it.first }
        var left = k
        while (left != 0 && overList.isNotEmpty()) {
            val item = overList.removeAt(0)
            val times = minOf(left, item.second)
//            println("multi ${item.first} times $times")
            ans *= quickPower(item.first.toLong(), times.toLong(), mod)
            ans %= mod
            left -= times
        }
        return ans.toInt()
    }
}