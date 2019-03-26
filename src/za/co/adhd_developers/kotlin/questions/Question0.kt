package za.co.adhd_developers.kotlin.questions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.magicitemstuff.ClassBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption

class Question0 : Question {


    override fun doWork() {
//        ClassBuilder.buildClass("Spell", ClassBuilder.getSpellData())
        val spellsFileData = Files.readAllLines(File("/Users/grantpienaar/Documents/RP Stuffs/raw_spells.json").toPath())
        var spells = ClassBuilder.convertToSpellClass(spellsFileData.joinToString(""))

//        spells = spells.filter { it.source == "CRB" }

        val components = spells
                .map { it.components }
                .filter { it.contains(".+\\(.+,.+\\)".toRegex()) }
                .union(emptyList())
                .sorted()
                .forEach {
                    println(it)
                }

//        components
//                .joinToString(", ")
//                .split(", ")
//                .groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }


//        val spellLevels = spells
//                .map { it.spelllevelsdisplay }
//                .joinToString(", ")
//                .split(",")
//                .map { it.trim() }

        //List all classes
//        spellLevels.map { it.substring(0, it.length - 2) }
//                .union(emptyList())
//                .sorted()
//                .forEach { println(it) }

        //Count per class
//        spellLevels.groupingBy { it.substring(0, it.length - 2) }
        //count per class per lever
//        spellLevels.groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t: $u")
//                }

//        spells.filter { it.wizard != it.sorcerer }
//                .sortedBy { it.wizard }
//                .forEach {
//                    println("${it.name} | wizard: ${it.wizard} - sorcerer: ${it.sorcerer}")
//                }

//        val castingTimes = spells.map { it.castingtime }
//        castingTimes
//                .union(emptyList())
//                .sorted()
//                .forEach { println(it) }

//        val schools = spells.map { it.school }
//        schools.union(emptyList())
//                .sorted()
//                .forEach { println(it) }

//        val ranges = spells.map { it.range }
//        ranges
////                .filter { it.contains("below".toRegex()) }
//                .union(emptyList())
//                .sorted()
//                .forEach { println(it) }

//        val durations= spells.map { it.duration }
//        durations
////                .filter { it.contains("/\\s".toRegex()) }
//                .groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        val saves= spells.map { it.savingthrow }
//        saves
////                .filter { it.contains("resistance") }
//                .groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        val areas= spells.map { it.area }
//        areas.groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        val srs= spells.map { it.sr }
//        srs.groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        val descriptors = spells.map { it.descriptor }
//        descriptors.groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        val targets = spells.map { it.targets }
//        targets.groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        spells.map { it.description }.joinToString(" ")
//                .split("\\s".toRegex())
//                .map { it.replace("(\\.|\\,|\\)|\\(|:|;|\\?|!)".toRegex(),"") }
//                .groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

//        val sources = spells.map { it.source }
//        sources.groupingBy { it }
//                .eachCount()
//                .toSortedMap()
//                .forEach { t, u ->
//                    println("$t| - $u")
//                }

        val shouldBePretty = true
        var gson = Gson()
        if (shouldBePretty) {
            gson = GsonBuilder().setPrettyPrinting().create()
        }
        val useSource = arrayListOf("CRB", "APG")
        val smallListSpells = spells.filter { useSource.contains(it.source) }

        println("Spells: ${smallListSpells.size}")

        var file = File("/Users/grantpienaar/Documents/RP Stuffs/Johann/crb_spells.json")
        file.delete()
        file.createNewFile()
        Files.write(File("/Users/grantpienaar/Documents/RP Stuffs/Johann/crb_spells.json").toPath(), gson.toJson(smallListSpells).toByteArray(), StandardOpenOption.WRITE)

        val jsonData = Gson().toJsonTree(smallListSpells[0])
        val objectDef = StringBuilder("Spell Object Definition")
        jsonData.asJsonObject.keySet()
                .forEach {
                    val je = jsonData.asJsonObject.get(it)
                    if (je.isJsonPrimitive) {
                        if (je.asJsonPrimitive.isString) {
                            objectDef.append("$it - String")
                        } else if (je.asJsonPrimitive.isNumber) {
                            objectDef.append("$it - Integer")
                        }
                    }
                    objectDef.append("\n")
                }

        file = File("/Users/grantpienaar/Documents/RP Stuffs/Johann/Spell Object.txt")
        file.delete()
        file.createNewFile()
        Files.write(File("/Users/grantpienaar/Documents/RP Stuffs/Johann/Spell Object.txt").toPath(), objectDef.toString().toByteArray(), StandardOpenOption.WRITE)
    }

    override fun printAnswer() {

    }

//    val functionNames = arrayListOf("AreaManagement", "Border", "DotOnMap", "DriverBehaviour", "DriverLicenceRenewal", "DriverTag",
//            "EmergencyContacts", "ETollCalculator", "FleetStack", "FollowVehicle", "FuelConsumptionStatistics",
//            "GroupManagement", "HighRisk", "HighUpdateMode", "Inbox", "LegalAssistance", "MainBatteryDisconnect",
//            "MaintenanceManagement", "MedicalEmergencySlider", "NotificationImpact", "NotificationsBorder", "NotificationsCarGuardSlider",
//            "NotificationsCarGuardSMS", "NotificationsDriverAlert", "NotificationsGoPlaces", "NotificationsHarshBrake",
//            "NotificationsHighRiskPhoneCall", "NotificationsHighRiskSMS", "NotificationsIgnition",
//            "NotificationsIncidentNotifications", "NotificationsIntruderAlert", "NotificationsMainBatteryDisconnect",
//            "NotificationsMotionAlert", "NotificationsMyPlaces", "NotificationsNoGoPlaces", "NotificationsOutsideWorkingHours",
//            "NotificationsOverSpeed", "NotificationsPotholeWarning", "NotificationsRestPhoneCall", "NotificationsRoadOverSpeed",
//            "NotificationsRouteViolate", "NotificationsTowNotify", "NotificationsVehicleLicenceDiskRenewal", "NotificationsVehiclePositionRequestSMS",
//            "NotificationsVehicleServiceIntervalReminder", "PersonalHealthAdvisor", "Playback", "PollUnit", "ReportVehicleDetailTripLog",
//            "ReportVehicleGroupDailyVehicleUsageOutsideWorkingHours", "ReportVehicleGroupExceptionStatsusGroup", "ReportVehicleGroupExceptionStatsusGroupbyStatus",
//            "ReportVehicleGroupFuelUsageGroup", "ReportVehicleGroupOverspeedExceptionDetail", "ReportVehicleGroupOverspeedExceptionSummary",
//            "ReportVehicleGroupServiceIntervals", "ReportVehicleGroupSummarytripLogGroup", "ReportVehicleGroupVehicleActivityOptionSummary",
//            "ReportVehicleGroupVehiclesNotReporting72Hours", "ReportVehicleGroupVehicleStatusGroup", "ReportVehicleGroupVehicleStatusGroupbyStatus",
//            "ReportVehicleGroupVehicleUsage", "ReportVehicleGroupVehicleUsageOutsideWorkingHours", "ReportVehicleNotificationSent",
//            "ReportVehicleSummaryTripLog", "ReportVehicleTrackerCarGuardHistory", "ReportVehicleTripLogbook",
//            "ReportVehicleWeeklyAnalysis", "ResponseSlider", "RoadsideSlider", "SafeZones", "SetOdo", "ShareTrip", "ShowOnEagle",
//            "ShowOnMobile", "SVRSlider", "TakeMeHome", "TrackerGuardAssist", "TrackerGuardContractManagementAdd",
//            "TrackerGuardContractManagementRemove", "TrackerGuardDependantManagement", "TrackerGuardSlider", "TrafficandIncidents",
//            "TripExpenses", "TripLogbook", "UnitConfigHarshBreak", "UnitConfigMaxSpeed", "VehicleIncident",
//            "VehicleLicenceDiskRenewal", "VehicleServiceIntervalReminder", "Weather")
//
//    override fun doWork() {
//        functionNames.forEach { name ->
//            var newName: String = ""
//            name.forEach { c ->
//                newName = when {
//                    newName.isEmpty() -> "$newName$c"
//                    c.toString().matches("[A-Z]".toRegex()) -> {
//                        if (!newName.last().toString().matches("[A-Z]".toRegex())) {
//                            "${newName}_$c"
//                        }else {
//                            "${newName}$c"
//                        }
//                    }
//                    else -> "$newName$c"
//                }
//            }
//
//            println("${newName.toUpperCase()}(\"$name\"),")
//        }
//
//    }


    //    val readPath = "/Users/grantpienaar/Desktop/UBI Documentation/user_data_crash_/"
//    val writePath = "/Users/grantpienaar/Desktop/UBI Documentation/UserIds.txt"
//
//    val limit = 5000
//
//    override fun doWork() {
//        val folder = File(readPath)
//
//        if (!folder.exists()) {
//            return
//        }
//
//        if (folder.listFiles() == null) {
//            return
//        }
//
//        val files : Array<File> = folder.listFiles()
//
//        val userIds = HashMap<String, Int>()
//
//        for (file in files) {
//            if (!file.name.endsWith(".csv")) {
//                continue
//            }
//            val data = Files.readAllLines(file.toPath())
//            for (i in 1..data.size - 1) {
//                if (data[i].isEmpty()) {
//                    continue
//                }
//                val entryData = data[i].split(",")
//                val userId = entryData[0]
//                val count = entryData[entryData.size-1].toInt()
//
//                if (userIds.containsKey(userId)) {
//                    var currentCount = userIds[userId]!!
//
//                    currentCount += count
//                    userIds[userId] = currentCount
//                } else {
//                    userIds[userId] = count
//                }
//            }
//        }
//
//        println(userIds.size)
//
//        var outFile = File(writePath)
//
//        if (!outFile.exists()) {
//            outFile.createNewFile()
//        } else {
//            Files.write(outFile.toPath(), byteArrayOf())
//        }
//
//        val output = StringBuilder()
//
//        userIds.forEach { t, u ->
//            output.append("$t")
//            output.append("\n")
//        }
//
//        Files.write(outFile.toPath(), output.toString().toByteArray())
//
//
////        val data = Files.readAllLines(File(readPath).toPath())
////
////        var folderNumber = 0
////        var destFile = getFile(folderNumber)
////        for (i in 0..data.size - 1) {
////            if (i > (limit-1) && i.rem(limit) == 0) {
////                folderNumber++
////                destFile = getFile(folderNumber)
////            }
////            if (destFile != null) {
////                Files.write(destFile!!.toPath(), arrayListOf(data[i]), StandardOpenOption.APPEND)
////            }
////        }
//    }
//
//    fun getFile(number: Int): File? {
//        val newFile = File("$writePath$number.csv")
//
//        if (!newFile.exists()) {
//            if (newFile.createNewFile()) {
//                return newFile
//            }
//        } else {
//
//        }
//
//        return null
//    }
//
//    override fun printAnswer() {
//
//    }
}