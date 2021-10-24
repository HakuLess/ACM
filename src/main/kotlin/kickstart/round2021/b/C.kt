package kickstart.round2021.b

import utils.countPrime
import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sqrt

fun main() {
    val t = readLine()!!.trim().toInt()
    val primes = ArrayList<Int>()
    val max = Int.MAX_VALUE / 10
    val isPrime = IntArray(max) { 1 }
    for (i in 2 until max) {
        if (isPrime[i] == 1) {
            primes.add(i)
        }
        var j = 0
        while (j < primes.size && i * primes[j] < max) {
            isPrime[i * primes[j]] = 0
            if (i % primes[j] == 0) {
                break
            }
            ++j
        }
    }

//        println(primes.joinToString())
    val ts = TreeSet<Long>()
    for (i in 1 until primes.size) {
        ts.add(primes[i].toLong() * primes[i - 1])
    }
    repeat(t) {
        val z = readLine()!!.trim().toLong()
//        ts.forEach {
//            it.print()
//        }
//        println(primes.size)

        println("Case #${it + 1}: ${ts.floor(z)}")
    }
}