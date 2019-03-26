package za.co.adhd_developers.kotlin.magicitemstuff

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import za.co.adhd_developers.kotlin.models.Armor
import za.co.adhd_developers.kotlin.models.Enchantment
import za.co.adhd_developers.kotlin.models.Spell
import za.co.adhd_developers.kotlin.models.Weapon
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.*
import kotlin.collections.ArrayList

class ClassBuilder {
    companion object {

        val TARD_DASH = "—"

        fun getEnchantmentData(): String {
            return "{\n" +
                    "   \"Type\": \"Armor\",\n" +
                    "   \"Name\": \"Benevolent\",\n" +
                    "   \"Enchantment\": 0,\n" +
                    "   \"Cost\": 2000\n" +
                    " }"
        }

        fun getArmorData(): String {
            return "{\n" +
                    "   \"Type\": \"Light\",\n" +
                    "   \"Name\": \"Armored kilt\",\n" +
                    "   \"Cost\": 20,\n" +
                    "   \"Bonus\": 1,\n" +
                    "   \"Max_Dex\": 6,\n" +
                    "   \"Check_Penalty\": 0,\n" +
                    "   \"Spell_Fail\": 0\n" +
                    " }"
        }

        fun getWeaponData(): String {
            return "{\n" +
                    "   \"Type\": \"Light\",\n" +
                    "   \"Name\": \"Battle aspergillum\",\n" +
                    "   \"Cost\": 5,\n" +
                    "   \"Damage_Type\": \"B\",\n" +
                    "   \"Is_Double\": \"N\"\n" +
                    " }"
        }

        fun getSpellData(): String {
            return "{\n" +
                    "   \"name\": \"Anti-Incorporeal Shell\",\n" +
                    "   \"source\": \"ACG\",\n" +
                    "   \"school\": \"Abjuration\",\n" +
                    "   \"description\": \"You bring into being a mobile, hemispherical energy field that incorporeal creatures cannot enter. This spell can be used only defensively, not aggressively. Forcing an abjuration barrier against creatures that the spell keeps at bay collapses the barrier.\",\n" +
                    "   \"spellLevelsDisplay\": \"cleric/oracle 4, shaman 4, witch 4, psychic 4, spiritualist 4\",\n" +
                    "   \"castingTime\": \"1 round\",\n" +
                    "   \"components\": \"V, S, DF\",\n" +
                    "   \"range\": \"10 ft.\",\n" +
                    "   \"area\": \"10-ft.-radius emanation centered on you\",\n" +
                    "   \"duration\": \"1 minute/level (D);\",\n" +
                    "   \"savingThrow\": \"none\",\n" +
                    "   \"sr\": \"yes\",\n" +
                    "   \"Cleric\": 4,\n" +
                    "   \"Oracle\": 4,\n" +
                    "   \"Shaman\": 4,\n" +
                    "   \"Psychic\": 4,\n" +
                    "   \"Witch\": 4,\n" +
                    "   \"Spiritualist\": 4,\n" +
                    "   \"descriptor\": \"\",\n" +
                    "   \"targets\": \"\",\n" +
                    "   \"Wizard\": null,\n" +
                    "   \"Bard\": null,\n" +
                    "   \"Occultist\": null,\n" +
                    "   \"Mesmerist\": null,\n" +
                    "   \"subschool\": \"\",\n" +
                    "   \"Inquisitor\": null,\n" +
                    "   \"Alchemist\": null,\n" +
                    "   \"Medium\": null,\n" +
                    "   \"Antipaladin\": null,\n" +
                    "   \"effect\": \"\",\n" +
                    "   \"Druid\": null,\n" +
                    "   \"Hunter\": null,\n" +
                    "   \"Summoner\": null,\n" +
                    "   \"Unchained_Summoner\": null,\n" +
                    "   \"Magus\": null,\n" +
                    "   \"Bloodrager\": null,\n" +
                    "   \"Paladin\": null,\n" +
                    "   \"Ranger\": null,\n" +
                    "   \"patron\": \"\",\n" +
                    "   \"domain\": \"\",\n" +
                    "   \"mythic\": \"\",\n" +
                    "   \"bloodline\": \"\"\n" +
                    " }"
        }

        fun buildClass(name: String, data: String) {
            val itemJsonObj = Gson().fromJson<JsonObject>(data, JsonObject::class.java)

            println("data class $name (")
            var count = 0
            itemJsonObj.entrySet().forEach { mutableEntry: MutableMap.MutableEntry<String, JsonElement> ->
                var variableDef = StringBuilder("")

                val convertedJsonKey = convertJsonKey(mutableEntry.key)
                if (mutableEntry.key != convertedJsonKey) {
                    variableDef.append("\t@SerializedName(\"${mutableEntry.key}\")")
                    variableDef.append("\n")
                }
                variableDef.append("\tvar $convertedJsonKey: ")
                variableDef.append(convertJsonElement(mutableEntry.value))
                if (count < itemJsonObj.entrySet().size - 1) {
                    variableDef.append(",")
                }
                println(variableDef.toString())
                count++
            }
            println("): SugarRecord() {\n" +
                    "\n" +
                    "}")
        }

        private fun convertJsonKey(key: String): String {
            return key.split("\\_".toRegex())
                    .mapIndexed { index, s ->
                        if (index == 0) s.toLowerCase() else s
                    }
                    .joinToString("")
        }

        private fun convertJsonElement(element: JsonElement): String {
            return when {
                element.isJsonNull -> return "Int"
                element.asJsonPrimitive.isString -> return "String"
                element.asJsonPrimitive.isNumber -> return "Int"
                element.asJsonPrimitive.isBoolean -> return "Boolean"
                else -> "unknowen"
            }
        }


        fun convertToArmorClass(fileData: String): List<Armor> {
            val listType = object : TypeToken<List<Armor>>() {}.type
            var itemArray = Gson().fromJson<List<Armor>>(fileData, listType)
            itemArray = itemArray.sortedWith(Comparator(
                    { o1, o2 ->

                        var result = if (o1.type != o2.type) {
                            when {
                                o1.type == "Light" -> 1
                                o1.type == "Medium" -> 2
                                o1.type == "Heavy" -> 3
                                else -> 0
                            }
                        } else {
                            0
                        }

                        if (result == 0) {
                            result = o1.type.compareTo(o2.type)
                        }
                        if (result == 0) {
                            result = o1.bonus.compareTo(o2.bonus)
                        }
                        if (result == 0) {
                            result = o1.maxDex.compareTo(o2.maxDex)
                        }
                        if (result == 0) {
                            result = o1.name.compareTo(o2.name)
                        }
                        return@Comparator result
                    }))

            return itemArray
        }

        fun convertToEnchantmentClass(fileData: String): List<Enchantment> {
            val listType = object : TypeToken<List<Enchantment>>() {}.type
            var itemArray = Gson().fromJson<List<Enchantment>>(fileData, listType)
            itemArray = itemArray.sortedWith(Comparator(
                    { o1, o2 ->
                        var result = o1.type.compareTo(o2.type)

                        if (result == 0) {
                            result = o1.enchantment.compareTo(o2.enchantment)
                        }
                        if (result == 0) {
                            result = o1.cost.compareTo(o2.cost)
                        }
                        if (result == 0) {
                            result = o1.name.compareTo(o2.name)
                        }
                        return@Comparator result
                    }))

            return itemArray
        }

        fun convertToWeaponClass(): List<Weapon> {
            val data = arrayListOf("Simple;Light;Battle aspergillum;5 gp;1d4;1d6;x2;—;4 lb.;B;see text;PZO1115",
                    "Simple;Light;Brass knife;2 gp;1d3;1d4;19-20/x2;10 ft.;1 lb.;P or S;fragile;PPC:Pirates",
                    "Simple;Light;Brass knuckles;1 gp;1d2;1d3;x2;—;1 lb.;B;monk;PPZO9410/PZO1115",
                    "Simple;Light;Cestus;5 gp;1d3;1d4;19-20/x2;—;1 lb.;B or P;monk;PZO1115",
                    "Simple;Light;Dagger;2 gp;1d3;1d4;19-20/x2;10 ft.;1 lb.;P or S;—;PZO1110",
                    "Simple;Light;Dagger, punching;2 gp;1d3;1d4;x3;—;1 lb.;P;—;PZO1110",
                    "Simple;Light;Gauntlet, spiked;5 gp;1d3;1d4;x2;—;1 lb.;P;—;PZO1110",
                    "Simple;Light;Handwraps;1 sp;—;—;—;—;—;—;See text;PPC:MAH",
                    "Simple;Light;Traveling kettle;5 gp;1d4;1d6;×2;—;2 lbs.;B;Monk, see text;PPC:MAH",
                    "Simple;Light;Hook hand;10 gp;1d3;1d4;x2;—;1 lb.;S;disarm;PPC:Pirates",
                    "Simple;Light;Kunai;2 gp;1d3;1d4;x2;10 ft.;2 lbs.;B or P;—;PPC:RTT",
                    "Simple;Light;Mace, light;5 gp;1d4;1d6;x2;—;4 lbs.;B;—;PZO1110",
                    "Simple;Light;Sickle;6 gp;1d4;1d6;x2;—;2 lbs.;S;trip;PZO1110",
                    "Simple;Light;Spring blade;70 gp;1d3;1d4;x2;10 ft.;1 lb.;P or S;—;PZO1134",
                    "Simple;Light;Wooden stake;—;1d3;1d4;x2;10 ft.;1 lb.;P;—;PZO1115",
                    "Simple;One-Handed;Club;—;1d4;1d6;x2;10 ft.;3 lbs.;B;—;PZO1110",
                    "Simple;One-Handed;Club, mere;2 gp;1d3;1d4;x2;—;2 lbs.;B or P;fragile;PPZO9410",
                    "Simple;One-Handed;Mace, heavy;12 gp;1d6;1d8;x2;—;8 lbs.;B;—;PZO1110",
                    "Simple;One-Handed;Morningstar;8 gp;1d6;1d8;x2;—;6 lbs.;B and P;—;PZO1110",
                    "Simple;One-Handed;Shortspear;1 gp;1d4;1d6;x2;20 ft.;3 lbs.;P;—;PZO1110",
                    "Simple;Two-Handed;Bayonet;5 gp;1d4;1d6;x2;—;1 lb.;P;—;PZO1115",
                    "Simple;Two-Handed;Boarding pike;8 gp;1d6;1d8;x3;—;9 lbs.;P;brace, reach;PPC:Pirates",
                    "Simple;Two-Handed;Kumade;5 gp;1d4;1d6;x3;—;4 lbs.;P;grapple;PZO9468",
                    "Simple;Two-Handed;Kumade, collapsible;10 gp;1d4;1d6;x3;—;4 lbs.;P;grapple;PZO9468",
                    "Simple;Two-Handed;Lantern staff;15 gp;1d4;1d6;×2;—;9 lbs.;B;See text;PPZO94102",
                    "Simple;Two-Handed;Longspear;5 gp;1d6;1d8;x3;—;9 lbs.;P;brace, reach;PZO1110",
                    "Simple;Two-Handed;Quarterstaff;—;1d4/1d4;1d6/1d6;x2;—;4 lbs.;B;double, monk;PZO1110",
                    "Simple;Two-Handed;Spear;2 gp;1d6;1d8;x3;20 ft.;6 lbs.;P;brace;PZO1110",
                    "Simple;Two-Handed;Spear, boar;5 gp;1d6;1d8;x2;—;8 lb.;P;brace, see text;PZO1115",
                    "Simple;Two-Handed;Spear, weighted;10 gp;1d6/1d4;1d8/1d6;x3/x2;—;8 lbs.;B or P;brace, double;PZO9468",
                    "Simple;Ranged;Blowgun;2 gp;1;1d2;x2;20 ft.;1 lb.;P;—;PZO1110",
                    "Simple;Ranged;Crossbow, heavy;50 gp;1d8;1d10;19-20/x2;120 ft.;8 lbs.;P;—;PZO1110",
                    "Simple;Ranged;Crossbow, heavy (underwater);100 gp;1d8;1d10;19-20/x2;120 ft.;8 lbs.;P;—;PZO1121",
                    "Simple;Ranged;Crossbow, light;35 gp;1d6;1d8;19-20/x2;80 ft.;4 lbs.;P;—;PZO1110",
                    "Simple;Ranged;Crossbow, light (underwater);70 gp;1d6;1d8;19-20/x2;80 ft.;4 lbs.;P;—;PZO1121",
                    "Simple;Ranged;Dart;5 sp;1d3;1d4;x2;20 ft.;1/2 lb.;P;—;PZO1110",
                    "Simple;Ranged;Javelin;1 gp;1d4;1d6;x2;30 ft.;2 lbs.;P;—;PZO1110",
                    "Simple;Ranged;Sling;—;1d3;1d4;x2;50 ft.;—;B;—;PZO1110",
                    "Simple;Ranged;Stingchuck;—;1d3;1d4;x2;10 ft.;9 lbs.;B;see text;PPZO9410",
                    "Simple;Ranged;Stonebow;35 gp;1d4;1d6;x2;50 ft.;4 lbs.;B;—;PPC:RTT",
                    "Martial;Light;Axe, boarding;6 gp;1d4;1d6;x3;—;3 lbs.;P or S;—;PPZO94102",
                    "Martial;Light;Axe, throwing;8 gp;1d4;1d6;x2;10 ft.;2 lbs.;S;—;PZO1110",
                    "Martial;Light;Blade boot;25 gp;1d3;1d4;x2;—;2 lbs.;P;see text;PPZO9410",
                    "Martial;Light;Cat-o’-nine-tails;1 gp;1d3;1d4;x2;—;1 lb.;S;disarm, nonlethal;PPZO94102",
                    "Martial;Light;Claw blades;305 gp;1d3;1d4;x2;—;2 lbs.;B or S;see text;PZO1121",
                    "Martial;Light;Dagger, dueling;12 gp;1d3;1d4;19–20/×2;10 ft.;1 lb.;P or S;See text;PPZO94102",
                    "Martial;Light;Dogslicer;8 gp;1d4;1d6;19-20/x2;—;1 lb.;S;fragile;PZO1121",
                    "Martial;Light;Hammer, light;1 gp;1d3;1d4;x2;20 ft.;2 lbs.;B;—;PZO1110",
                    "Martial;Light;Gladius;15 gp;1d4;1d6;19-20/x2;—;3 lbs.;P or S;performance;PZO1118",
                    "Martial;Light;Handaxe;6 gp;1d4;1d6;x3;—;3 lbs.;S;—;PZO1110",
                    "Martial;Light;Katar, tri-bladed;6 gp;1d3;1d4;×4;—;2 lbs.;P;—;PPZO94102",
                    "Martial;Light;Knife, switchblade;5 gp;1d3;1d4;19-20/x2;10 ft.;1 lb.;P;—;PPZO9410",
                    "Martial;Light;Kobold tail attachment, long lash;15 gp;1d4;1d6;x2;—;1 lb.;S;reach;PZO1121",
                    "Martial;Light;Kobold tail attachment, (pounder);1 gp;1d6;1d8;x2;—;4 lbs.;B;—;PZO1121",
                    "Martial;Light;Kobold tail attachment, (razored);3 gp;1d6;1d8;19-20/x2;—;2 lbs.;S;—;PZO1121",
                    "Martial;Light;Kobold tail attachment, (spiked);3 gp;1d6;1d8;x3;—;2 lbs.;P;—;PZO1121",
                    "Martial;Light;Kobold tail attachment, (sweeper);7 gp;1d4;1d6;x2;—;3 lbs.;B;trip;PZO1121",
                    "Martial;Light;Kukri;8 gp;1d3;1d4;18–20/x2;—;2 lbs.;S;—;PZO1110",
                    "Martial;Light;Machete;10 gp;1d4;1d6;19-20/x2;—;2 lbs.;S;—;PZO9468",
                    "Martial;Light;Pick, light;4 gp;1d3;1d4;x4;—;3 lbs.;P;—;PZO1110",
                    "Martial;Light;Ratfolk tailblade;11 gp;1d2;1d3;x2;—;1/2 lb.;S;—;PZO1121",
                    "Martial;Light;Sap;1 gp;1d4;1d6;x2;—;2 lbs.;B;nonlethal;PZO1110",
                    "Martial;Light;Sea-knife;8 gp;1d3;1d4;19-20/x2;—;1 lb.;S;—;PZO1121",
                    "Martial;Light;Shield, wooden, light;3 gp;1d2;1d3;x2;—;special;B;—;PZO1110",
                    "Martial;Light;Shield, steel, light;9 gp;1d2;1d3;x2;—;special;B;—;PZO1110",
                    "Martial;Light;Spiked armor;50 gp;1d4;1d6;x2;—;special;P;—;PZO1110",
                    "Martial;Light;Spiked shield, wooden, light;13 gp;1d3;1d4;x2;—;special;P;—;PZO1110",
                    "Martial;Light;Spiked shield, steel, light;19 gp;1d3;1d4;x2;—;special;P;—;PZO1110",
                    "Martial;Light;Starknife;24 gp;1d3;1d4;x3;20 ft.;3 lbs.;P;—;PZO1110",
                    "Martial;Light;Sword, short;10 gp;1d4;1d6;19-20/x2;—;2 lbs.;P;—;PZO1110",
                    "Martial;Light;War razor;8 gp;1d3;1d4;19-20/x2;—;1 lb.;S;—;PZO9226-Revised",
                    "Martial;One-Handed;Ankus;8 gp;1d6;1d8;x2;—;5 lbs.;P;disarm, trip;PZO9468",
                    "Martial;One-Handed;Battleaxe;10 gp;1d6;1d8;x3;—;6 lbs.;S;—;PZO1110",
                    "Martial;One-Handed;Combat scabbard;1 gp;1d4;1d6;x2;—;1 lb.;B;improvised, see text;PPZO9410",
                    "Martial;One-Handed;Cutlass;15 gp;1d4;1d6;18-20/x2;—;4 lbs.;S;—;PPZO94102",
                    "Martial;One-Handed;Flail, light;8 gp;1d6;1d8;x2;—;5 lbs.;B;disarm, trip;PZO1110",
                    "Martial;One-Handed;Gandasa;15 gp;1d6;2d4;x3;—;4 lbs.;S;—;PZO9468",
                    "Martial;One-Handed;Klar;12 gp;1d4;1d6;x2;—;6 lbs.;S;—;PZO9226-Revised",
                    "Martial;One-Handed;Longsword;15 gp;1d6;1d8;19-20/x2;—;4 lbs.;S;—;PZO1110",
                    "Martial;One-Handed;Manople;17 gp;1d6;1d8;x2;—;4 lbs.;P or S;blocking, disarm;PZO9468",
                    "Martial;One-Handed;Pick, heavy;8 gp;1d4;1d6;x4;—;6 lbs.;P;—;PZO1110",
                    "Martial;One-Handed;Rapier;20 gp;1d4;1d6;18–20/x2;—;2 lbs.;P;finesse;PZO1110",
                    "Martial;One-Handed;Scabbard, combat (sharpened);10 gp;1d4;1d6;x2;—;1 lb.;S;see text;PPZO9410",
                    "Martial;One-Handed;Scimitar;15 gp;1d4;1d6;18–20/x2;—;4 lbs.;S;—;PZO1110",
                    "Martial;One-Handed;Scizore;20 gp;1d8;1d10;x2;—;3 lbs.;P;—;PZO1118",
                    "Martial;One-Handed;Shield, wooden, heavy;7 gp;1d3;1d4;x2;—;special;B;—;PZO1110",
                    "Martial;One-Handed;Shield, steel, heavy;20 gp;1d3;1d4;x2;—;special;B;—;PZO1110",
                    "Martial;One-Handed;Spiked shield, wooden, heavy;17 gp;1d4;1d6;x2;—;special;P;—;PZO1110",
                    "Martial;One-Handed;Spiked shield, steel, heavy;30 gp;1d4;1d6;x2;—;special;P;—;PZO1110",
                    "Martial;One-Handed;Sword cane;45 gp;1d4;1d6;x2;—;4 lbs.;P;see text;PZO1115",
                    "Martial;One-Handed;Terbutje;5 gp;1d6;1d8;19-20/x2;—;2 lbs.;S;fragile;PPZO9410",
                    "Martial;One-Handed;Terbutje, steel;20 gp;1d6;1d8;19-20/x2;—;4 lbs.;S;—;PPZO9410",
                    "Martial;One-Handed;Trident;15 gp;1d6;1d8;x2;10 ft.;4 lbs.;P;brace;PZO1110",
                    "Martial;One-Handed;Warhammer;12 gp;1d6;1d8;x3;—;5 lbs.;B;—;PZO1110",
                    "Martial;Two-Handed;Bardiche;13 gp;1d8;1d10;19-20/x2;—;14 lbs.;S;brace, reach, see text;PZO1115",
                    "Martial;Two-Handed;Bec de corbin;15 gp;1d8;1d10;x3;—;12 lbs.;B or P;brace, reach, see text;PZO1115",
                    "Martial;Two-Handed;Bill;11 gp;1d6;1d8;x3;—;11 lbs.;S;brace, disarm, reach, see text;PZO1115",
                    "Martial;Two-Handed;Earth breaker;40 gp;1d10;2d6;x3;—;14 lbs.;B;—;PZO9226-Revised",
                    "Martial;Two-Handed;Falchion;75 gp;1d6;2d4;18–20/x2;—;8 lbs.;S;—;PZO1110",
                    "Martial;Two-Handed;Flail, heavy;15 gp;1d8;1d10;19-20/x2;—;10 lbs.;B;disarm, trip;PZO1110",
                    "Martial;Two-Handed;Glaive;8 gp;1d8;1d10;x3;—;10 lbs.;S;reach;PZO1110",
                    "Martial;Two-Handed;Glaive-guisarme;12 gp;1d8;1d10;x3;—;10 lbs.;S;brace, reach, see text;PZO1115",
                    "Martial;Two-Handed;Greataxe;20 gp;1d10;1d12;x3;—;12 lbs.;S;—;PZO1110",
                    "Martial;Two-Handed;Greatclub;5 gp;1d8;1d10;x2;—;8 lbs.;B;—;PZO1110",
                    "Martial;Two-Handed;Greatsword;50 gp;1d10;2d6;19-20/x2;—;8 lbs.;S;—;PZO1110",
                    "Martial;Two-Handed;Guisarme;9 gp;1d6;2d4;x3;—;12 lbs.;S;reach, trip;PZO1110",
                    "Martial;Two-Handed;Halberd;10 gp;1d8;1d10;x3;—;12 lbs.;P or S;brace, trip;PZO1110",
                    "Martial;Two-Handed;Hammer, lucerne;15 gp;1d10;1d12;x2;—;12 lbs.;B or P;brace, reach, see text;PZO1115",
                    "Martial;Two-Handed;Horsechopper;10 gp;1d8;1d10;x3;—;12 lbs.;P or S;reach, trip;PZO1121",
                    "Martial;Two-Handed;Lance;10 gp;1d6;1d8;x3;—;10 lbs.;P;reach;PZO1110",
                    "Martial;Two-Handed;Ogre hook;24 gp;1d8;1d10;x3;—;10 lbs.;P;trip;PZO9226-Revised",
                    "Martial;Two-Handed;Pickaxe;14 gp;1d6;1d8;x4;—;12 lbs.;P;—;PAP14",
                    "Martial;Two-Handed;Planson;10 gp;1d8;1d10;x2;—;10 lbs.;B or P;brace;PZO9468",
                    "Martial;Two-Handed;Ranseur;10 gp;1d6;2d4;x3;—;12 lbs.;P;disarm, reach;PZO1110",
                    "Martial;Two-Handed;Scythe;18 gp;1d6;2d4;x4;—;10 lbs.;P or S;trip;PZO1110",
                    "Martial;Two-Handed;Spear, syringe;100 gp;1d6;1d8;x3;20 ft.;6 lbs.;P;brace, see text;PPZO9410",
                    "Martial;Ranged;Ammentum;—;1d4;1d6;x2;50 ft.;1 lb.;P;performance;PZO1118",
                    "Martial;Ranged;Chakram;1 gp;1d6;1d8;x2;30 ft.;1 lb.;S;—;PZO1115",
                    "Martial;Ranged;Dart, jolting;100 gp;1d3;1d4;x2;20 ft.;1/2 lb.;P;see text;PZO1121",
                    "Martial;Ranged;Hunga munga;4 gp;1d4;1d6;x2;15 ft.;3 lbs.;P;—;PPZO9410",
                    "Martial;Ranged;Hurlbat;8 gp;1d4;1d6;x3;10 ft.;2 lbs.;P and S;—;PPC:RTT",
                    "Martial;Ranged;Longbow;75 gp;1d6;1d8;x3;100 ft.;3 lbs.;P;—;PZO1110",
                    "Martial;Ranged;Longbow, composite (+0);100 gp;1d6;1d8;x3;110 ft.;3 lbs.;P;Strength (-);PZO1110",
                    "Martial;Ranged;Longbow, composite (+1);200 gp;1d6;1d8;x3;110 ft.;3 lbs.;P;Strength (12);PZO1110",
                    "Martial;Ranged;Longbow, composite (+2);300 gp;1d6;1d8;x3;110 ft.;3 lbs.;P;Strength (14);PZO1110",
                    "Martial;Ranged;Longbow, composite (+3);400 gp;1d6;1d8;x3;110 ft.;3 lbs.;P;Strength (16);PZO1110",
                    "Martial;Ranged;Longbow, composite (+4);500 gp;1d6;1d8;x3;110 ft.;3 lbs.;P;Strength (18);PZO1110",
                    "Martial;Ranged;Longbow, composite (+5);600 gp;1d6;1d8;x3;110 ft.;3 lbs.;P;Strength (20);PZO1110",
                    "Martial;Ranged;Pilum;5 gp;1d6;1d8;x2;20 ft.;4 lbs.;P;see text;PZO1115",
                    "Martial;Ranged;Shortbow;30 gp;1d4;1d6;x3;60 ft.;2 lbs.;P;—;PZO1110",
                    "Martial;Ranged;Shortbow, composite;75 gp;1d4;1d6;x3;70 ft.;2 lbs.;P;—;PZO1110",
                    "Martial;Ranged;Throwing arrow cord;—;1d3;1d4;x2;60 ft.;—;P;—;PPC:RTT",
                    "Exotic;Light;Aklys;5 gp;1d4;1d6;x2;20 ft.;2 lbs.;B;performance, trip;PZO1118",
                    "Exotic;Light;Axe-Gauntlet, Dwarven Light;16 gp;1d4;1d6;x3;—;5 lbs.;S;blocking, disarm;PPC:HftF",
                    "Exotic;Light;Axe, knuckle;9 gp;1d4;1d6;x3;—;2 lbs.;S;monk, performance;PZO1118",
                    "Exotic;Light;Barbazu beard;25 gp;1d3;1d4;x2;—;5 lbs;S;see text;PPC:Devils",
                    "Exotic;Light;Battle poi;5 gp;1d3 (fire);1d4 (fire);x2;—;2 lbs.;fire;—;PPZO9410",
                    "Exotic;Light;Dagger, swordbreaker;10 gp;1d3;1d4;x2;—;3 lbs.;S;disarm, sunder;PZO1115",
                    "Exotic;Light;Flying Talon;15 gp;1d3;1d4;x2;10 ft.;5 lbs.;P;disarm, trip;PPZO94102",
                    "Exotic;Light;Gnome pincher;10 gp;1d4;1d6;x2;—;2 lbs.;B;disarm, see text;PZO9468",
                    "Exotic;Light;Halfling rope-shot;1 gp;1d4;1d6;x2;—;1 lb.;B;disarm;PZO9468",
                    "Exotic;Light;Helmet, dwarven boulder;20 gp;1d3;1d4;x2;—;10 lbs.;B;see text;PZO1121",
                    "Exotic;Light;Kama;2 gp;1d4;1d6;x2;—;2 lbs.;S;monk, trip;PZO1110",
                    "Exotic;Light;Knife, butterfly;5 gp;1d3;1d4;19-20/x2;—;1 lb.;P or S;—;PPZO9410",
                    "Exotic;Light;Knife, deer horn;10 gp;1d3;1d4;x3;20 ft.;3 lbs.;P;blocking, monk;PZO9468",
                    "Exotic;Light;Maulaxe, dwarven;25 gp;1d4;1d6;x3;10 ft.;5 lbs.;B or S;—;PPZO9410",
                    "Exotic;Light;Nunchaku;2 gp;1d4;1d6;x2;—;2 lbs.;B;disarm, monk;PZO1110",
                    "Exotic;Light;Quadrens;8 gp;1d4;1d6;19-20/x2;—;2 lbs.;P;performance;PZO1118",
                    "Exotic;Light;Razor, drow;25 gp;1d3;1d4;18–20/×2;—;2 lbs.;S;See text;PPZO94102",
                    "Exotic;Light;Rope gauntlet;2 sp;1d3;1d4;x2;—;2 lbs.;B (or S);—;PPZO9410",
                    "Exotic;Light;Sabre, sawtoothAA1;35 gp;1d6;1d8;19-20/x2;—;2 lbs.;S;—;PPZO9410",
                    "Exotic;Light;Sai;1 gp;1d3;1d4;x2;—;1 lb.;B;disarm, monk;PZO1110",
                    "Exotic;Light;Sanpkhang;60 gp;1d3;1d4;19–20/×2;—;1 lb.;P or S;monk, see text;PPZO94102",
                    "Exotic;Light;Siangham;3 gp;1d4;1d6;x2;—;1 lb.;P;monk;PZO1110",
                    "Exotic;Light;Sica;10 gp;1d4;1d6;x2;—;2 lbs.;S;performance;PZO1118",
                    "Exotic;Light;Thorn bracer;30 gp;1d4;1d6;x2;—;3 lbs.;P;—;PCS",
                    "Exotic;Light;War-shield, dwarven;50 gp;1d4;1d6;×2;—;8 lbs.;P or S;See text;PPZO94102",
                    "Exotic;Light;Waveblade;5 gp;1d4;1d6;18–20/×2;—;2 lbs.;P or S;monk, see text;PPZO94102",
                    "Exotic;Light;Whip, scorpion;5 gp;1d3;1d4;x2;—;3 lbs.;S;disarm, performance, reach, trip;PZO1118",
                    "Exotic;One-Handed;Axe-Gauntlet, Dwarven Heavy;21 gp;1d6;1d8;x3;—;5 lbs.;S;blocking, disarm;PPC:HftF",
                    "Exotic;One-Handed;Axe, hooked;20 gp;1d6;1d8;x3;—;7 lbs.;S;disarm, performance, trip;PZO1118",
                    "Exotic;One-Handed;Broken-back seax;40 gp;1d8;1d10;19–20/×2;—;4 lbs.;P & S;See text;PPC:MAH",
                    "Exotic;One-Handed;Estoc;50 gp;2d3;2d4;18–20/x2;—;4 lbs.;P;—;PZO9468",
                    "Exotic;One-Handed;Falcata;18 gp;1d6;1d8;19-20/x3;—;4 lbs.;S;—;PZO1115",
                    "Exotic;One-Handed;Flickmace;20 gp;1d6;1d8;x2;—;10 lb;B;reach, trip;PC:GoG",
                    "Exotic;One-Handed;Flindbar;9 gp;1d6;1d8;x2;—;6 lbs.;B and P;disarm, trip;MC",
                    "Exotic;One-Handed;Khopesh;20 gp;1d6;1d8;19-20/x2;—;8 lbs.;S;trip;PZO1115",
                    "Exotic;One-Handed;Knobkerrie;5 gp;1d4;1d6;x2;20 ft.;4 lbs.;B;see text;PPC:RTT",
                    "Exotic;One-Handed;Ram Hammer, Dwarven;25 gp;1d6;1d8;x3;10 ft.;5 lbs.;B;—;PPC:HftF",
                    "Exotic;One-Handed;Rapier, spiral;80 gp;1d4;1d6;18–20/×2;—;3 lbs.;P;blocking, disarm, see text;PPZO94102",
                    "Exotic;One-Handed;Rhoka;5 gp;1d6;1d8;18-20/x2;—;6 lbs.;S;—;PPZO9410",
                    "Exotic;One-Handed;Sabre, sawtoothAG;35 gp;1d6;1d8;19-20/x2;—;2 lbs.;S;—;PZO9226-Revised",
                    "Exotic;One-Handed;Shotel;30 gp;1d6;1d8;x3;—;3 lbs.;P;performance;PZO1118",
                    "Exotic;One-Handed;Sickle-sword;20 gp;1d6;1d8;19–20/×2;—;4 lbs.;S;distracting, see text;PPZO94102",
                    "Exotic;One-Handed;Sphinx Hammer, Dwarven;45 gp;1d8;1d10;x3;20 ft.;8 lbs.;B;—;PPC:HftF",
                    "Exotic;One-Handed;Split-blade sword;200 gp;1d10;2d6;×2;—;8 lbs.;S;Disarm, trip, see text;PPC:MAH",
                    "Exotic;One-Handed;Sword, dueling;20 gp;1d6;1d8;19-20/x2;—;3 lbs;S;—;PZO9226-Revised",
                    "Exotic;One-Handed;Sword, bastard;35 gp;1d8;1d10;19-20/x2;—;6 lbs.;S;—;PZO1110",
                    "Exotic;One-Handed;Tongi;18 gp;1d4;1d6;19-20/x3;—;4 lbs.;P;—;PZO9468",
                    "Exotic;One-Handed;Waraxe, dwarven;30 gp;1d8;1d10;x3;—;8 lbs.;S;—;PZO1110",
                    "Exotic;One-Handed;Waraxe, dwarven double;60 gp;1d8;1d10;x3;—;12 lbs.;S;see text;PZO1121",
                    "Exotic;One-Handed;Whip;1 gp;1d2;1d3;x2;—;2 lbs.;S;disarm, nonlethal, reach, trip;PZO1110",
                    "Exotic;Two-Handed;Axe, Orc Double;60 gp;1d6/1d6;1d8/1d8;x3;—;15 lbs.;S;double;PZO1110",
                    "Exotic;Two-Handed;Axe, Butchering;65 gp;1d12;3d6;×3;—;25 lbs.;S;see text;PPZO94102",
                    "Exotic;Two-Handed;Battle Ladder, gnome;20 gp;1d4/1d4;1d6/1d6;x2;—;8 lbs.;B;trip;PPZO94102",
                    "Exotic;Two-Handed;Boarding gaff;8 gp;1d4/1d4;1d6/1d6;x2;—;8 lbs.;S;double, reach, trip;PPZO94102",
                    "Exotic;Two-Handed;Chain-hammer;35 gp;1d4/1d4;1d6/1d6;×2;20 ft.;8 lbs.;B;double, see text;PPZO94102",
                    "Exotic;Two-Handed;Chain, spiked;25 gp;1d6;2d4;x2;—;10 lbs.;P;disarm, trip;PZO1110",
                    "Exotic;Two-Handed;Crook;1 gp;1d4;1d6;x2;—;5 lbs.;B;reach, trip;PZO9468",
                    "Exotic;Two-Handed;Curve blade, elven;80 gp;1d8;1d10;18–20/x2;—;7 lbs.;S;—;PZO1110",
                    "Exotic;Two-Handed;Dorn-Dergar, dwarven;50 gp;1d8;1d10;x2;—;15 lbs.;B;reach, see text;PPZO94102",
                    "Exotic;Two-Handed;Double spear;25 gp;1d6/1d6;1d8/1d8;×3;—;12 lbs.;P;Double;PPC:MAH",
                    "Exotic;Two-Handed;Elven branched spear;20 gp;1d6;1d8;x3;—;10 lbs.;P;brace, reach;PZO9468",
                    "Exotic;Two-Handed;Fauchard;14 gp;1d8;1d10;18-20/x2;—;10 lbs.;S;reach, trip;PPZO94102",
                    "Exotic;Two-Handed;Flail, dire;90 gp;1d6/1d6;1d8/1d8;x2;—;10 lbs.;B;disarm, double, trip;PZO1110",
                    "Exotic;Two-Handed;Flailpole;15 gp;1d6;1d8;x2;—;10 lbs.;B;disarm, reach, trip;PC:GoG",
                    "Exotic;Two-Handed;Flambard;50 gp;1d8;1d10;19-20/x2;—;6 lbs.;S;sunder;PPZO9410",
                    "Exotic;Two-Handed;Flying blade;40 gp;1d10;1d12;x3;—;12 lbs.;S;performance, reach;PZO1118",
                    "Exotic;Two-Handed;Garrote;3 gp;1d4;1d6;x2;—;1 lb.;S;grapple, see text;PPZO9410",
                    "Exotic;Two-Handed;Giant-Sticker, Dwarven;25 gp;1d8;2d6;x3;—;12 lbs.;P or S;brace, reach;PPC:HftF",
                    "Exotic;Two-Handed;Hammer, Gnome hooked;20 gp;1d6/1d4;1d8/1d6;x3/x4;—;6 lbs.;B or P;double, trip;PZO1110",
                    "Exotic;Two-Handed;Harpoon;5 gp;1d6;1d8;x3;10 ft.;16 lbs.;P;grapple;PPC:Pirates",
                    "Exotic;Two-Handed;Longaxe, dwarven;50 gp;1d10;1d12;x3;—;14 lbs.;S;reach;PZO1121",
                    "Exotic;Two-Handed;Longhammer, dwarven;70 gp;1d10;2d6;x3;—;20 lbs.;B;reach;PZO1121",
                    "Exotic;Two-Handed;Mancatcher;15 gp;1;1d2;x2;—;10 lbs.;P;grapple, reach, see text;PZO1115",
                    "Exotic;Two-Handed;Orc skull ram;15 gp;1d8;1d10;x3;—;20 lbs.;B;reach;PZO9468",
                    "Exotic;Two-Handed;Piston maul, gnome;70 gp;1d8;1d10;x2;—;15 lbs.;B;see text;PPZO94102",
                    "Exotic;Two-Handed;Ripsaw glaive, gnome;30 gp;1d8;1d10;x3;—;12 lbs.;S;reach, see text;PPZO94102",
                    "Exotic;Two-Handed;Scarf, bladed;12 gp;1d4;1d6;x2;—;2 lbs.;S;disarm, trip;PZO9226-Revised",
                    "Exotic;Two-Handed;Spear, totem;25 gp;1d8;1d10;x3;10 ft.;6 lbs.;P or S;see text;PZO1110",
                    "Exotic;Two-Handed;Switchscythe;18 gp;1d6;2d4;×4;—;10 lbs.;P or S;trip;PPZO94102",
                    "Exotic;Two-Handed;Sword, two-bladed;100 gp;1d6/1d6;1d8/1d8;19-20/x2;—;10 lbs.;S;double;PZO1110",
                    "Exotic;Two-Handed;Urgrosh, dwarven;50 gp;1d6/1d4;1d8/1d6;x3;—;12 lbs.;P or S;brace, double;PZO1110",
                    "Exotic;Ranged;Darts, featherweight (10);1 gp;—;—;—;—;—;—;—;PZO1134",
                    "Exotic;Ranged;Bola;5 gp;1d3;1d4;x2;10 ft.;2 lbs.;B;nonlethal, trip;PZO1110",
                    "Exotic;Ranged;Bola, Brutal;15 gp;1d3;1d4;x2;10 ft.;2 lbs.;B and P;trip;PZO9226-Revised",
                    "Exotic;Ranged;Boomerang;3 gp;1d4;1d6;x2;30 ft.;3 lbs.;B;—;PZO1115",
                    "Exotic;Ranged;Bow, thorn;50 gp;1d4;1d6;x3;40 ft.;2 lbs.;P;—;PPZO9410",
                    "Exotic;Ranged;Crossbow, crank (heavy);400 gp;1d8;1d10;19-20/x2;120 ft.;12 lbs.;P;—;PPC:RTT",
                    "Exotic;Ranged;Crossbow, crank (light);250 gp;1d6;1d8;19-20/x2;80 ft.;6 lbs.;P;—;PPC:RTT",
                    "Exotic;Ranged;Crossbow, double;300 gp;1d6;1d8;19-20/x2;80 ft.;18 lbs.;P;—;PZO1115",
                    "Exotic;Ranged;Crossbow, hand;100 gp;1d3;1d4;19-20/x2;30 ft.;2 lbs.;P;—;PZO1110",
                    "Exotic;Ranged;Crossbow, launching;75 gp;—;—;—;30 ft.;8 lbs.;—;see text;PPZO9410",
                    "Exotic;Ranged;Crossbow, repeating heavy;400 gp;1d8;1d10;19-20/x2;120 ft.;12 lbs.;P;—;PZO1110",
                    "Exotic;Ranged;Crossbow, repeating light;250 gp;1d6;1d8;19-20/x2;80 ft.;6 lbs.;P;—;PZO1110",
                    "Exotic;Ranged;Flask Thrower;25 gp;—;—;—;20 ft.;4 lbs.;—;see text;PPZO94102",
                    "Exotic;Ranged;Grappling hook;6 gp;1d4;1d6;x2;10 ft.;14 lbs.;P;grapple;PPC:Pirates",
                    "Exotic;Ranged;Hornbow, orc;130 gp;1d8;2d6;×3;80 ft.;7 lbs.;P;—;PPZO94102",
                    "Exotic;Ranged;Javelin, stormshaft;35 gp;1d4;1d6;×2;30 ft.;3 lbs.;P;See text;PPZO94102",
                    "Exotic;Ranged;Lasso;1 sp;—;—;—;—;5 lbs.;—;see text;PZO1115",
                    "Exotic;Ranged;Net;20 gp;—;—;—;10 ft.;6 lbs.;—;—;PZO1110",
                    "Exotic;Ranged;Net, snag;30 gp;see text;see text;—;10 ft.;10 lbs.;P;trip, see text;PZO1121",
                    "Exotic;Ranged;Pelletbow, Dwarven Heavy;75 gp;1d4;1d6;19-20/x3;60 ft.;8 lbs.;B;—;PPC:HftF",
                    "Exotic;Ranged;Pelletbow, Dwarven Light;50 gp;1d3;1d4;19-20/x3;40 ft.;4 lbs.;B;—;PPC:HftF",
                    "Exotic;Ranged;Shield, throwing;+50 gp;1d4;1d6;x2;20 ft.;—;B;performance, trip;PZO1118",
                    "Exotic;Ranged;Shrillshaft javelin;35 gp;1d4;1d6;x2;30 ft.;3 lbs.;P;see text;PPC:Goblins",
                    "Exotic;Ranged;Shuriken (5);1 gp;1;1d2;x2;10 ft.;1/2 lbs.;P;monk;CRB",
                    "Exotic;Ranged;Sling, double;10 gp;1d3;1d4;x2;50 ft.;1 lb.;B;double, see text;PPC:Halflings",
                    "Exotic;Ranged;Sling glove;5 gp;1d3;1d4;x2;50 ft.;2 lbs.;B;—;PPZO9410",
                    "Exotic;Ranged;Sling staff, halfling;20 gp;1d6;1d8;x3;80 ft.;3 lbs.;B;—;PZO1110",
                    "Exotic;Ranged;Sling, stitched;—;1d4;1d6;x2;—;1 lb.;B;disarm, trip;PPC:Halflings",
                    "Exotic;Ranged;Wrist launcher;200 gp;—;—;—;20 ft.;1 lb.;P;—;PZO1134",
                    "Exotic;Ranged;Wrist launcher, heavy;250 gp;1d3;1d4;19–20/x2;30 ft.;2 lbs.;P;—;PZO1134")

            var weaponList = data.map { weapon ->
                val weaponData = weapon.split(";")

                val classification: String = weaponData[0]
                val type: String = weaponData[1]
                val name: String = weaponData[2]
                val cost: Double = when {
                    weaponData[3].contains("gp") -> weaponData[3].replace("\\sgp".toRegex(), "").toDouble()
                    weaponData[3].contains("sp") -> weaponData[3].replace("\\ssp".toRegex(), "").toDouble().div(10)
                    weaponData[3].contains("cp") -> weaponData[3].replace("\\scp".toRegex(), "").toDouble().div(100)
                    else -> 0.0
                }

                var damage: String = weaponData[5]
                if (damage == TARD_DASH) {
                    damage = "—"
                }
                val critical: String = weaponData[6]
                var range: Int = 0
                try {
                    range = if (weaponData[7] == TARD_DASH) 0 else weaponData[7].replace("ft.", "").trim().toInt()
                } catch (e: NumberFormatException) {
                    println("$name: ${weaponData[7]}")
                }
                val damageType: String = if (weaponData[9] == TARD_DASH) "None" else weaponData[9]
                val special: String = weaponData[10].split(",").map {
                    (if (it == TARD_DASH) {
                        "-"
                    } else if (it.trim() == "see text" || it.trim() == "See text") {
                        ""
                    } else {
                        it.trim().capitalize()
                    }).trim()
                }.filter {
                    !it.isEmpty()
                }.joinToString(", ").trim()

                return@map Weapon(classification, type, name, cost, damage, critical, range, damageType, special)
            }

            weaponList = weaponList.sortedWith(Comparator(
                    { o1, o2 ->

                        var result = when {
                            o1.classification != o2.classification -> when {
                                o1.classification == "Simple" -> 1
                                o1.classification == "Martial" -> 2
                                o1.classification == "Exotic" -> 3
                                else -> 0
                            }
                            o1.type != o2.type -> when {
                                o1.type == "Light" -> 1
                                o1.type == "One-Handed" -> 2
                                o1.type == "Two-Handed" -> 3
                                o1.type == "Ranged" -> 4
                                else -> 0
                            }
                            else -> 0
                        }
                        if (result == 0) {
                            result = o1.name.compareTo(o2.name)
                        }
                        if (result == 0) {
                            result = o1.cost.compareTo(o2.cost)
                        }
                        return@Comparator result
                    }))

            return weaponList
        }

        fun convertToSpellClass(fileData: String): List<Spell> {
            val listType = object : TypeToken<List<Spell>>() {}.type

            val spells = GsonBuilder().serializeNulls().create().fromJson<List<Spell>>(fileData, listType)

            spells.forEach { spell ->
                var spelllevels = spell.spelllevelsdisplay.split(",").map { it.trim() }

                val tmp = ArrayList<String>()

                spelllevels.forEach {
                    if (it.contains("/".toRegex())) {
                        val level = it.substring(it.length - 1).toInt()
                        val names = it.substring(0, it.length - 1).trim().split("/".toRegex())
                        if (level > -1 && !names.isEmpty()) {
                            names.forEach {
                                tmp.add("$it $level")
                            }
                        }
                    } else {
                        tmp.add(it)
                    }
                }

                tmp.sort()
                spelllevels = tmp.toList()

                spelllevels = spelllevels.map {
                    var tmp = it
                    if (tmp.contains("unchained summoner\\s\\d".toRegex())) {
                        tmp = tmp.replace("unchained summoner".toRegex(), "summoner (unchained)")
                    }
                    return@map tmp
                }

                spell.sorcerer = spelllevels.filter { it.startsWith("sorcerer") }.firstOrNull()?.replace("sorcerer\\s".toRegex(), "")?.toInt() ?: -1
                spell.spelllevelsdisplay = spelllevels.joinToString(", ")

                spelllevels.map {
                    val sl = it.split(" ")
                    if (sl.size == 2) {
                        return@map Pair(sl[0], sl[1].toInt())
                    } else {
                        Pair(sl.subList(0, sl.size - 2).joinToString(" "), sl[sl.size - 1].toInt())
                    }
                }
                        .forEach {
                            when (it.first) {
                                "alchemist" -> spell.alchemist = it.second
                                "antipaladin" -> spell.antipaladin = it.second
                                "bard" -> spell.bard = it.second
                                "bloodrager" -> spell.bloodrager = it.second
                                "cleric" -> spell.cleric = it.second
                                "druid" -> spell.druid = it.second
                                "hunter" -> spell.hunter = it.second
                                "inquisitor" -> spell.inquisitor = it.second
                                "magus" -> spell.magus = it.second
                                "medium" -> spell.medium = it.second
                                "mesmerist" -> spell.mesmerist = it.second
                                "occultist" -> spell.occultist = it.second
                                "oracle" -> spell.oracle = it.second
                                "paladin" -> spell.paladin = it.second
                                "psychic" -> spell.psychic = it.second
                                "ranger" -> spell.ranger = it.second
                                "shaman" -> spell.shaman = it.second
                                "sorcerer" -> spell.sorcerer = it.second
                                "spiritualist" -> spell.spiritualist = it.second
                                "summoner" -> spell.summoner = it.second
                                "summoner (unchained)" -> spell.unchainedSummoner = it.second
                                "witch" -> spell.witch = it.second
                                "wizard" -> spell.wizard = it.second
                            }
                        }
            }

            return spells
        }

        fun createJsonFiles() {
            val armorFileData = Files.readAllLines(File("/Users/grantpienaar/Documents/RP Stuffs/raw_armor.json").toPath())

            val enchantmentsFileData = Files.readAllLines(File("/Users/grantpienaar/Documents/RP Stuffs/raw_enchantments.json").toPath())

            val gson = Gson()

            val armors = ClassBuilder.convertToArmorClass(armorFileData.joinToString(""))
            var file = File("/Users/grantpienaar/Documents/RP Stuffs/armor.json")
            file.delete()
            file.createNewFile()
            Files.write(File("/Users/grantpienaar/Documents/RP Stuffs/armor.json").toPath(), gson.toJson(armors).toByteArray(), StandardOpenOption.WRITE)

            println("================")
            val weapons = ClassBuilder.convertToWeaponClass()
            file = File("/Users/grantpienaar/Documents/RP Stuffs/weapons.json")
            file.delete()
            file.createNewFile()
            Files.write(File("/Users/grantpienaar/Documents/RP Stuffs/weapons.json").toPath(), gson.toJson(weapons).toByteArray(), StandardOpenOption.WRITE)

            println("================")
            val enchantments = ClassBuilder.convertToEnchantmentClass(enchantmentsFileData.joinToString(""))
            file = File("/Users/grantpienaar/Documents/RP Stuffs/enchantments.json")
            file.delete()
            file.createNewFile()
            Files.write(File("/Users/grantpienaar/Documents/RP Stuffs/enchantments.json").toPath(), gson.toJson(enchantments).toByteArray(), StandardOpenOption.WRITE)
        }

    }
}