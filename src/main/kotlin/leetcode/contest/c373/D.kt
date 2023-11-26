package leetcode.contest.c373

import utils.print
import utils.printInt

fun main() {
    val s = SolutionD()
    s.beautifulSubstrings("baeyh", 2).print()
}

class SolutionD {
    fun beautifulSubstrings(s: String, k: Int): Long {
        val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')

        val mapA = HashMap<Int, ArrayList<Int>>()
        val mapB = HashMap<Int, ArrayList<Int>>()

        for (i in s.indices) {
            if (i + 1 in s.indices) {
                var a = 0
                if (s[i] in vowels) a++ else a--
                if (s[i + 1] in vowels) a++ else a--
                if (i % 2 == 0) {
                    mapA[a] = mapA.getOrDefault(a, arrayListOf())
                    mapA[a]!!.add(i + 1)
                } else {
                    mapB[a] = mapB.getOrDefault(a, arrayListOf())
                    mapB[a]!!.add(i + 1)
                }
            }
        }
        mapA.forEach { t, u ->
            println("$t with ${u.joinToString()}")
        }
        mapB.forEach { t, u ->
            println("$t with ${u.joinToString()}")
        }
        var ans = 0L
//        for (key in mapA.keys) {
//            val a = mapA[key]!!
//            val b = mapA.getOrDefault(-key, arrayListOf())
//            for ()
//        }
        return ans
    }
}