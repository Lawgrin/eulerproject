package za.co.adhd_developers.kotlin.models

import com.google.gson.annotations.SerializedName

data class Enchantment(
        @SerializedName("Type")
        var type: String,
        @SerializedName("Name")
        var name: String,
        @SerializedName("Enchantment")
        var enchantment: Int,
        @SerializedName("Cost")
        var cost: Int
) {
    override fun toString(): String {
        return "Enchantment(type='$type', name='$name', enchantment=$enchantment, cost=$cost)"
    }
}