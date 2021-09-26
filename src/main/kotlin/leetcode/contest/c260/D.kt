package leetcode.contest.c260

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = Solution5884()
//    s.scoreOfStudents("7+3*1*2", intArrayOf(20, 13, 42)).print()
    s.scoreOfStudents("1+4+4+1*0+2", intArrayOf(7)).print()
//    s.scoreOfStudents("3+5*2", intArrayOf(13, 0, 10, 13, 13, 16, 16)).print()
}

// todo 处理eval
class Solution5884 {
    fun scoreOfStudents(s: String, answers: IntArray): Int {
        val c = s.count { it == '+' }

        fun getReal(): Int {
            val stn = Stack<Int>()
            val op = Stack<Char>()
            s.forEach {
                if (it in '0'..'9') {
                    stn.push(it - '0')
                    if (op.isNotEmpty() && op.peek() == '*') {
                        val a = stn.pop()
                        val b = stn.pop()
                        val o = op.pop()
                        stn.push(a * b)
                    }
                } else {
                    op.push(it)
                }
            }
            return stn.sum()
        }

        val l = ArrayList<Int>()
        fun dfs(index: Int, stn: Stack<Int>, op: Stack<Char>) {
            if (index >= s.length && op.isEmpty()) l.add(stn.pop())

            stn.push(s[index] - '0')
            if (index + 1 in s.indices) {
                op.push(s[index + 1])
            }
            dfs(index + 2, stn.clone() as Stack<Int>, op.clone() as Stack<Char>)

            if (op.isNotEmpty()) {
                val a = stn.pop()
                val b = stn.pop()
                val o = op.pop()
                if (o == '*') stn.push(a * b)
                else stn.push(a + b)
            }
            dfs(index + 2, stn.clone() as Stack<Int>, op.clone() as Stack<Char>)
        }

        dfs(0, Stack(), Stack())
        val real = getReal()
        l.joinToString().print()
        return answers.map {
            when (it) {
                real -> 5
                in l -> 2
                else -> 0
            }
        }.sum()
    }
}