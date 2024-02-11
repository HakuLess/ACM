package leetcode.contest.c384

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.maxPalindromesAfterOperations(arrayOf("cd", "ef", "a")).print()
    s.maxPalindromesAfterOperations(arrayOf("abbb", "ba", "aa")).print()
    s.maxPalindromesAfterOperations(arrayOf("abc","ab")).print()
}

class SolutionC {
    fun maxPalindromesAfterOperations(words: Array<String>): Int {
        val map = HashMap<Char, Int>()
        // 字符串总长度
        var total = 0
        words.forEach {
            total += it.length
            it.forEach {
                map[it] = map.getOrDefault(it, 0) + 1
            }
        }
        val sizeList = words.map { it.length }.sorted()

        // 字符串对长度
        var totalP = 0
        map.values.forEach {
            totalP += it - it % 2
        }

        // 未配对总长度
        var ones = total - totalP

//        println("$totalP with $ones")
        var ans = 0
        sizeList.forEach {
            if (it % 2 == 0) {
                if (totalP >= it) {
                    totalP -= it
                    ans++
                }
            } else {
                if (totalP >= it - 1) {
                    if (ones > 0) {
                        ones--
                        totalP -= it - 1
                        ans++
                    } else if (totalP >= it) {
                        totalP -= it
                        ans++
                    }
                }
            }
        }
        return ans
    }
}