package za.co.adhd_developers.kotlin.models

import com.google.gson.annotations.SerializedName

data class Armor(
        @SerializedName("Type")
        var type: String,
        @SerializedName("Name")
        var name: String,
        @SerializedName("Cost")
        var cost: Int,
        @SerializedName("Bonus")
        var bonus: Int,
        @SerializedName("Max_Dex")
        var maxDex: Int,
        @SerializedName("Check_Penalty")
        var checkPenalty: Int,
        @SerializedName("Spell_Fail")
        var spellFail: Int
) {
    override fun toString(): String {
        return "Armor(type='$type', name='$name', cost=$cost, bonus=$bonus, maxDex=$maxDex, checkPenalty=$checkPenalty, spellFail=$spellFail)"
    }
}