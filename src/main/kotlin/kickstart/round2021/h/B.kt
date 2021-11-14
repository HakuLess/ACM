package kickstart.round2021.h

fun main() {
    val t = readLine()!!.trim().toInt()
    val map = HashMap<Char, Int>()
    map['U'] = 0
    map['R'] = 1
    map['Y'] = 2
    map['B'] = 4
    map['O'] = 1 + 2
    map['P'] = 1 + 4
    map['G'] = 2 + 4
    map['A'] = 1 + 2 + 4
    repeat(t) {
        val n = readLine()!!.toInt()
        val p = readLine().toString()
        var ans = 0
        val arr = p.map {
            map[it]!!
        }.toIntArray()

        var i = 0
        while (i in arr.indices) {
            if (arr[i] == 0) {
                i++
                continue
            }
            val paint = arrayOf(1, 2, 4).first {
                (arr[i] and it) == it
            }
            for (j in i until arr.size) {
                if ((arr[j] and paint) == paint) {
                    arr[j] -= paint
                } else {
                    break
                }
            }
            ans++
        }
        println("Case #${it + 1}: $ans")
    }
}