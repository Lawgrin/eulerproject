package za.co.adhd_developers.kotlin.models

import com.google.gson.annotations.SerializedName

data class Weapon(
        @SerializedName("Classification")
        var classification: String,
        @SerializedName("Type")
        var type: String,
        @SerializedName("Name")
        var name: String,
        @SerializedName("Cost")
        var cost: Double,
        @SerializedName("Damage")
        var damage:String,
        @SerializedName("Critical")
        var critical:String,
        @SerializedName("Range")
        var range:Int,
        @SerializedName("Damage_Type")
        var damageType: String,
        @SerializedName("Special")
        var special: String
) {
    override fun toString(): String {
        return "Weapon(classification='$classification', type='$type', name='$name', cost=$cost, damage='$damage', critical='$critical', range=$range, damageType='$damageType', special='$special')"
    }
}