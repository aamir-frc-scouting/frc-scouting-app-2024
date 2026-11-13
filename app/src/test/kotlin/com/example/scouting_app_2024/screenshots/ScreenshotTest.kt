package com.example.scouting_app_2024.screenshots

import android.view.LayoutInflater
import android.widget.ImageView
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.ScreenOrientation
import com.example.qrgenearator.QRGContents
import com.example.qrgenearator.QRGEncoder
import com.example.scouting_app_2024.R
import com.example.scouting_app_2024.RecordsActivity
import org.junit.Rule
import org.junit.Test

class ScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_10.copy(orientation = ScreenOrientation.PORTRAIT)
    )

    @Test
    fun main_activity() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_main, null)
        paparazzi.snapshot(view, "main")
    }

    @Test
    fun auto_activity() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_auto, null)
        paparazzi.snapshot(view, "auto")
    }

    @Test
    fun tele_activity() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_tele, null)
        paparazzi.snapshot(view, "tele")
    }

    @Test
    fun stage_activity() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_stage, null)
        paparazzi.snapshot(view, "stage")
    }

    @Test
    fun popup_activity() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_popup, null)
        paparazzi.snapshot(view, "popup")
    }

    @Test
    fun qr_activity() {
        RecordsActivity.Info.scoutName = "Demo Scout"
        RecordsActivity.Info.matchNumber = "42"
        RecordsActivity.Info.teamNumber = "99901"
        RecordsActivity.Info.preload = true
        RecordsActivity.Info.driverStation = 1
        RecordsActivity.Info.fieldPosition = 2
        RecordsActivity.Info.leave = true
        RecordsActivity.Info.autoNotes = 3
        RecordsActivity.Info.autoAmpNotes = 1
        RecordsActivity.Info.autoSpeakerNotes = 2
        RecordsActivity.Info.autoComments = "Clean auto"
        RecordsActivity.Info.teleNotes = 8
        RecordsActivity.Info.teleAmpNotes = 3
        RecordsActivity.Info.teleSpeakerNotes = 5
        RecordsActivity.Info.teleComments = "Fast cycles"
        RecordsActivity.Info.stageLevel = 2
        RecordsActivity.Info.harmony = true
        RecordsActivity.Info.trap = false
        RecordsActivity.Info.stageComments = "Climbed early"

        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_qr, null)

        val json = RecordsActivity.Info.CreateJSON()
        val qrgEncoder = QRGEncoder(json, null, QRGContents.Type.TEXT, 200)
        qrgEncoder.colorBlack = 0xFF292929.toInt()
        qrgEncoder.colorWhite = 0xFFffc815.toInt()
        val bitmap = qrgEncoder.bitmap

        val qrImage = view.findViewById<ImageView>(R.id.qrImageView)
        if (qrImage != null && bitmap != null) {
            qrImage.setImageBitmap(bitmap)
        }

        paparazzi.snapshot(view, "qr")
    }
}
