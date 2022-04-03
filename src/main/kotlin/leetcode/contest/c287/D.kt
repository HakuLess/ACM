package leetcode.contest.c287

import utils.print

fun main() {
    val s = Encrypter(
        charArrayOf('a', 'b', 'c', 'd'),
        arrayOf("ei", "zf", "ei", "am"),
        arrayOf("abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad")
    )
    s.encrypt("abcd").print()
    s.decrypt("eizfeiam").print()
}

class Encrypter(keys: CharArray, values: Array<String>, dictionary: Array<String>) {

    val map = HashMap<Char, String>()

    val c = HashMap<String, Int>()

    init {
        for (i in keys.indices) {
            map[keys[i]] = values[i]
        }
        dictionary.forEach {
            val str = encrypt(it)
            c[str] = c.getOrDefault(str, 0) + 1
        }
    }

    fun encrypt(word1: String): String {
        val ans = StringBuilder()
        word1.forEach {
            ans.append(map[it])
        }
        return ans.toString()
    }

    fun decrypt(word2: String): Int {
        return c.getOrDefault(word2, 0)
    }

}

/**
 * Your Encrypter object will be instantiated and called as such:
 * var obj = Encrypter(keys, values, dictionary)
 * var param_1 = obj.encrypt(word1)
 * var param_2 = obj.decrypt(word2)
 */