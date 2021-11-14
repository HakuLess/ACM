package kickstart.round2021.h

fun main() {
    val t = readLine()!!.trim().toInt()
    val map = HashMap<String, String>()
    map["01"] = "2"
    map["12"] = "3"
    map["23"] = "4"
    map["34"] = "5"
    map["45"] = "6"
    map["56"] = "7"
    map["67"] = "8"
    map["78"] = "9"
    map["89"] = "0"
    map["90"] = "1"
    repeat(t) {
        val n = readLine()!!.toInt()
        val s = readLine().toString()
        var rep = s
        while (true) {
            val oldLen = rep.length
            map.forEach { t, u ->
                rep = rep.replace(t, u)
            }
            if (rep.length == oldLen) break
        }

        println("Case #${it + 1}: $rep")
    }
}