package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import kotlin.math.absoluteValue

class Question0 : Question {


    override fun doWork() {
        for (i in 1 until 11) {
            if (i > 1) {
                println()
            }
            for (j in 1 until 11) {
                if (i == 1 || i == 10 || j == 1 || j == 10) {
                    print("#")
                } else if (i == j) {
                    print("#")
                } else if (i == j.minus(11).absoluteValue) {
                    print("#")
                } else {
                    print(" ")
                }
            }
        }
        println()
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