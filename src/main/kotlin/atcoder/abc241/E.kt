package atcoder.abc241

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toLong() }
    val arr = readLine()!!.split(" ").map { it.toLong() }

    var cur = 0L
    var left = k
    val seenA = HashMap<Int, Pair<Long, Long>>()
    val seenB = HashMap<Int, Pair<Long, Long>>()
    while (left != 0L) {
        val index = (cur % n).toInt()
        if (index !in seenA.keys) {
            cur += arr[index]
            left--
            seenA[index] = Pair(left, cur)
//            println("a $left $cur")
        } else if (index !in seenB.keys) {
            cur += arr[index]
            left--
            seenB[index] = Pair(left, cur)
//            println("b $left $cur")
        } else {
            val (aLeft, aValue) = seenA[index]!!
            val (bLeft, bValue) = seenB[index]!!
//            println("$lstLeft, $lstValue : $left $cur")
            val diff = aLeft - bLeft
            val diffValue = bValue - aValue
//            println("c: $diff $diffValue")
            cur += diffValue * (left / diff)
            left %= diff
            seenA.clear()
            seenB.clear()
        }
    }
    println(cur)
//    println(826617499998784056)
}