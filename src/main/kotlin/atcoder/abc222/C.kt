package atcoder.abc222

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.trim().split(' ').map { it.toInt() }
    val l = ArrayList<CharArray>()
    val rank = ArrayList<IntArray>()
    repeat(n * 2) {
        val arr = readLine()!!.trim()
        l.add(arr.toCharArray())
        rank.add(intArrayOf(it, 0))
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            val indexA = rank[j * 2][0]
            val indexB = rank[j * 2 + 1][0]
//            println("index $indexA $indexB")
            val a = l[indexA][i]
            val b = l[indexB][i]
            if (a == b) continue
            when (a) {
                'P' -> {
                    when (b) {
                        'G' -> rank[j * 2][1]++
                        'C' -> rank[j * 2 + 1][1]++
                    }
                }
                'G' -> {
                    when (b) {
                        'C' -> rank[j * 2][1]++
                        'P' -> rank[j * 2 + 1][1]++
                    }
                }
                'C' -> {
                    when (b) {
                        'P' -> rank[j * 2][1]++
                        'G' -> rank[j * 2 + 1][1]++
                    }
                }
            }
        }
        rank.sortWith(compareBy({ -it[1] }, { it[0] }))
    }
    rank.forEach {
        println(it[0] + 1)
    }
}