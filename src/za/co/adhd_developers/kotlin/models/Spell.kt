package za.co.adhd_developers.kotlin.models

import com.google.gson.annotations.SerializedName

data class Spell(
        var name: String,
        var source: String,
        var school: String,
        var description: String,
        @SerializedName("spellLevelsDisplay")
        var spelllevelsdisplay: String,
        @SerializedName("castingTime")
        var castingtime: String,
        var components: String,
        var range: String,
        var area: String,
        var duration: String,
        @SerializedName("savingThrow")
        var savingthrow: String,
        var sr: String,
        @SerializedName("Cleric")
        var cleric: Int = -1,
        @SerializedName("Oracle")
        var oracle: Int = -1,
        @SerializedName("Shaman")
        var shaman: Int = -1,
        @SerializedName("Psychic")
        var psychic: Int = -1,
        @SerializedName("Witch")
        var witch: Int = -1,
        @SerializedName("Spiritualist")
        var spiritualist: Int = -1,
        var descriptor: String,
        var targets: String,
        @SerializedName("Wizard")
        var wizard: Int = -1,
        @SerializedName("Sorcerer")
        var sorcerer: Int = -1,
        @SerializedName("Bard")
        var bard: Int = -1,
        @SerializedName("Occultist")
        var occultist: Int = -1,
        @SerializedName("Mesmerist")
        var mesmerist: Int = -1,
        var subschool: String,
        @SerializedName("Inquisitor")
        var inquisitor: Int = -1,
        @SerializedName("Alchemist")
        var alchemist: Int = -1,
        @SerializedName("Medium")
        var medium: Int = -1,
        @SerializedName("Antipaladin")
        var antipaladin: Int = -1,
        var effect: String,
        @SerializedName("Druid")
        var druid: Int = -1,
        @SerializedName("Hunter")
        var hunter: Int = -1,
        @SerializedName("Summoner")
        var summoner: Int = -1,
        @SerializedName("Unchained_Summoner")
        var unchainedSummoner: Int = -1,
        @SerializedName("Magus")
        var magus: Int = -1,
        @SerializedName("Bloodrager")
        var bloodrager: Int = -1,
        @SerializedName("Paladin")
        var paladin: Int = -1,
        @SerializedName("Ranger")
        var ranger: Int = -1,
        var patron: String,
        var domain: String,
        var mythic: String,
        var bloodline: String
)