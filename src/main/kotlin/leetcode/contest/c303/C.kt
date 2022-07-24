package leetcode.contest.c303

import java.util.*
import kotlin.collections.HashMap

class FoodRatings(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) {

    // food cuisine
    val map = HashMap<String, String>()

    // cuisine  foods & scores
    val pqs = HashMap<String, PriorityQueue<Pair<String, Int>>>()

    // food rating
    val scores = HashMap<String, Int>()

    init {
        for (i in foods.indices) {
            map[foods[i]] = cuisines[i]
            scores[foods[i]] = ratings[i]

            if (pqs[cuisines[i]] == null) {
                pqs[cuisines[i]] = PriorityQueue<Pair<String, Int>>(compareBy({ -it.second }, { it.first }))
            }
            pqs[cuisines[i]]!!.offer(Pair(foods[i], ratings[i]))
        }
    }

    fun changeRating(food: String, newRating: Int) {
        val oldScore = scores[food]!!
        pqs[map[food]]!!.remove(Pair(food, oldScore))
        pqs[map[food]]!!.offer(Pair(food, newRating))
        scores[food] = newRating
    }

    fun highestRated(cuisine: String): String {
        return pqs[cuisine]!!.peek().first
    }

}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * var obj = FoodRatings(foods, cuisines, ratings)
 * obj.changeRating(food,newRating)
 * var param_2 = obj.highestRated(cuisine)
 */