package leetcode.normal

import utils.biMax
import utils.hash
import utils.print
import kotlin.random.Random

fun main() {
    val s = Solution1044()
    s.longestDupSubstring("banana").print()
    s.longestDupSubstring("abcd").print()
    s.longestDupSubstring("aa").print()
}

// 字符串Hash
class Solution1044 {
    fun longestDupSubstring(s: String): String {

        while (true) {
            val toCheck = getRandom(s)
            if (toCheck.isEmpty()) return ""
            if (s.indexOf(toCheck) != s.lastIndexOf(toCheck)) {
                return toCheck
            }
        }

        return ""
    }

    fun getRandom(s: String): String {
        val randomBase = Random.nextInt(26, 100)
        val randomMod = Random.nextInt(1000000007, Int.MAX_VALUE)
        // 计算字符串Hash
        val strHash = s.hash(base = randomBase, mod = randomMod)

        val l = biMax(1L, s.length.toLong()) {
            val seen = HashSet<Long>()
            for (left in s.indices) {
                val right = left + it.toInt() - 1
                if (right !in s.indices) break

                val curHash = strHash.hash(left, right)
                if (curHash in seen) {
                    return@biMax true
                }
                seen.add(curHash)
            }
            false
        }.toInt()

//        l.print()

        if (l == -1) return ""

        val seen = HashSet<Long>()
        for (i in s.indices) {
            for (left in s.indices) {
                val right = left + l - 1
                if (right !in s.indices) break

                val curHash = strHash.hash(left, right)
                if (curHash in seen) {
                    return s.substring(left, right + 1)
                }
                seen.add(curHash)
            }
        }
        return ""
    }
}