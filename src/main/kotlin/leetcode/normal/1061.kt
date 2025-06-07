package leetcode.normal

import utils.TypedUFS
import utils.print

fun main() {
    val s = Solution1061()
    s.smallestEquivalentString("parker", "morris", "parser").print()
}

class Solution1061 {
    fun smallestEquivalentString(s1: String, s2: String, baseStr: String): String {
        val ufs = TypedUFS<Char>(26)
        for (i in s1.indices) {
            val a = s1[i]
            val b = s2[i]
//            println("union $a $b")
            ufs.union(a, b)
        }

        for (key in 'a'..'z') {
            ufs.union(key, key)
        }

        val map = HashMap<Int, ArrayList<Char>>()
        for (key in 'a'..'z') {
            val c = ufs.typedFind(key)
            map[c] = map.getOrDefault(c, arrayListOf())
            map[c]!!.add(key)
        }
        map.forEach { k, v ->
            v.sort()
        }

        val ans = StringBuilder()
        baseStr.forEach { it ->
            val c = ufs.typedFind(it)
//            println("$it with ${map[c]}")
            ans.append(map[c]?.getOrElse(0) { it })
        }
        return ans.toString()
    }
}