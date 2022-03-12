package atcoder.abc243

import java.math.BigInteger
import java.util.*

fun main() {
    val (n, x) = readLine()!!.split(" ").map { it.toLong() }
    val max = BigInteger.valueOf(1000000000000000000)
    val s = readLine()!!
    var cur = x.toBigInteger()
    val two = BigInteger.valueOf(2)
    val one = BigInteger.valueOf(1)
    val st = Stack<Char>()
    s.forEach {
        when (it) {
            'U' -> {
                if (st.isNotEmpty() && st.peek() != 'U') {
                    st.pop()
                } else {
                    st.push(it)
                }
            }
            'R' -> {
                st.push(it)
            }
            else -> {
                st.push(it)
            }
        }
    }
    st.joinToString("").forEach {
        var next = cur
        when (it) {
            'U' -> {
                next = cur / two
            }
            'R' -> {
                next = cur * two + one
            }
            else -> {
                next = cur * two
            }
        }
        cur = maxOf(one, next)
    }
    println(minOf(cur, max))
}