package com.luyny.brawlbase

import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("club")
    var club: Club? = null

    @SerializedName("3vs3Victories")
    var threeVsThree: Int? = null

    //    @SerializedName("isQualifiedFromChampionshipChallenge")
//    var isQualifiedFromChampionshipChallenge: Boolean? = null
    @SerializedName("icon")
    var icon: Icon? = null

    @SerializedName("tag")
    var tag: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("trophies")
    var trophies: Int? = null

    @SerializedName("expLevel")
    var expLevel: Int? = null

    @SerializedName("expPoints")
    var expPoints: Int? = null

    @SerializedName("highestTrophies")
    var highestTrophies: Int? = null

    //    @SerializedName("powerPlayPoints")
//    var powerPlayPoints: Int? = null
//    @SerializedName("highestPowerPlayPoints")
//    var highestPowerPlayPoints: Int? = null
//    @SerializedName("soloVictories")
//    var soloVictories: Int? = null
//    @SerializedName("duoVictories")
//    var duoVictories: Int? = null
//    @SerializedName("bestTimeAsBigBrawler")
//    var bestTimeAsBigBrawler: Int? = null
//    @SerializedName("nameColor")
//    var nameColor: String? = null
    @SerializedName("brawlers")
    var brawlers: List<Brawler>? = null
}

class Brawler {
    @SerializedName("gadgets")
    var gadgets: List<Gadget>? = null
//    @SerializedName("starPowers")
//    var starPowers: List<StarPower>? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("rank")
    var rank: Int? = null
    @SerializedName("trophies")
    var trophies: Int? = null

    @SerializedName("highestTrophies")
    var highestTrophies: Int? = null
    @SerializedName("power")
    var power: Int? = null
    @SerializedName("name")
    var name: String? = null
}

class Gadget {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

}

class StarPower {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

}

class Icon {
    @SerializedName("id")
    var id: Int? = null
}

class Club {
    @SerializedName("tag")
    var tag: String? = null

    @SerializedName("name")
    var name: String? = null
}