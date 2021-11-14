package leetcode.normal

class MapSum() {

    val map = HashMap<String, Int>()

    fun insert(key: String, `val`: Int) {
        map[key] = `val`
    }

    fun sum(prefix: String): Int {
        return map.keys.filter {
            it.startsWith(prefix)
        }.sumBy {
            map[it]!!
        }
    }

}

/**
 * Your MapSum object will be instantiated and called as such:
 * var obj = MapSum()
 * obj.insert(key,`val`)
 * var param_2 = obj.sum(prefix)
 */