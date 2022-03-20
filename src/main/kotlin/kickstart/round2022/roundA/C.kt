package kickstart.round2022.roundA

import utils.longestPalindrome
import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

// 通配过后，没有长度大于等于5的回文子字符串
fun main() {

    fun dfs(arr: CharArray, i: Int): Boolean {
        if (i !in arr.indices) {
//            println("${arr.joinToString("")} with ${arr.joinToString("").longestPalindrome()}")
            return (arr.joinToString("").longestPalindrome().length < 5)
        }
        var ans = false
        if (arr[i] == '?') {
//            println("${arr.joinToString("")}")
            arr[i] = '0'
            ans = ans or dfs(arr.clone(), i + 1)
            arr[i] = '1'
            ans = ans or dfs(arr.clone(), i + 1)
            return ans
        }
        return dfs(arr, i + 1)
    }

    val blocks = arrayListOf<String>()

    val arr = ArrayList<Char>()
    val queue: Queue<ArrayList<Char>> = LinkedList<ArrayList<Char>>()
    queue.offer(arr)
    val seen = HashSet<String>()
    var step = 0
    var c = 0
    while (queue.isNotEmpty()) {
        val size = queue.size
        step++
        if (step == 11) break
        for (i in 0 until size) {
            val item = queue.poll()
            if (item.isNotEmpty()) {
                if (dfs(item.joinToString("").toCharArray(), 0) == false) {
//                    println("${item.joinToString("")}")
                    blocks.add("${item.joinToString("")}")
                    c++
                }
            }
            val clone0 = item.clone() as ArrayList<Char>
            val clone1 = item.clone() as ArrayList<Char>
            val clone2 = item.clone() as ArrayList<Char>
            clone0.add('0')
            clone1.add('1')
            clone2.add('?')
            if (clone0.joinToString() !in seen) {
                queue.offer(clone0)
                seen.add(clone0.joinToString())
            }
            if (clone1.joinToString() !in seen) {
                queue.offer(clone1)
                seen.add(clone1.joinToString())
            }
            if (clone2.joinToString() !in seen) {
                queue.offer(clone2)
                seen.add(clone2.joinToString())
            }
        }
    }
//    println(c)
    blocks.forEach {
        it.print()
    }


    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val s = readLine()!!.toString()
        val ans = blocks.all { it !in s }
        if (ans) {
            println("Case #${it + 1}: POSSIBLE")
        } else {
            println("Case #${it + 1}: IMPOSSIBLE")
        }
    }
}