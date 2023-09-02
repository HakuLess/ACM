package leetcode.contest.b112

import utils.C
import utils.originC
import utils.print

fun main() {
    val s = SolutionD()
//    s.countKSubsequencesWithMaxBeauty("bcca", 2).print()
//    s.countKSubsequencesWithMaxBeauty("abbcd", 4).print()
//    s.countKSubsequencesWithMaxBeauty("dd", 2).print()
//    s.countKSubsequencesWithMaxBeauty("fkp", 2).print()
//    s.countKSubsequencesWithMaxBeauty("fkp", 1).print()
//    s.countKSubsequencesWithMaxBeauty("fkps", 2).print()
//    s.countKSubsequencesWithMaxBeauty("lelxul", 1).print()
//    s.countKSubsequencesWithMaxBeauty("aaabbb", 1).print()
    // 4
    s.countKSubsequencesWithMaxBeauty("ffcsdqcnkr", 2).print()

//    println(C(3, 1))
}

class SolutionD {
    fun countKSubsequencesWithMaxBeauty(s: String, k: Int): Int {
        val mod = 1000000007L

        val map = HashMap<Int, Int>()
        s.forEach {
            val key = it - 'a'
            map[key] = map.getOrDefault(key, 0) + 1
        }
        if (map.keys.size < k) return 0
        val l = map.values.sortedDescending()
        val list = ArrayList<Int>()

        val lst = l[k - 1]
        l.forEach {
            if (it >= lst) {
                list.add(it)
            } else {
                return@forEach
            }
        }

        list.joinToString().print()

        var c = 0
        var ans = 1L
        list.forEach {
            if (it == lst) return@forEach
            c++
            ans *= it
            ans %= mod
        }

        var multi = originC(list.size - c, k - c, mod)
        repeat(k - c) {
            multi = multi * lst % mod
        }

        println("C ${list.size - c} ${k - c} with $multi")
        return (ans * multi % mod).toInt()
    }
}