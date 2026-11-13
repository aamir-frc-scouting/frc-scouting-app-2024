package com.example.scouting_app_2024.screenshots;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import app.cash.paparazzi.Paparazzi;

import com.example.scouting_app_2024.R;
import com.example.scouting_app_2024.RecordsActivity;
import com.example.qrgenearator.QRGEncoder;
import com.example.qrgenearator.QRGContents;

import org.junit.Rule;
import org.junit.Test;

public class ScreenshotTest {

    @Rule
    public Paparazzi paparazzi = new Paparazzi();

    @Test
    public void main_activity() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_main, null);
        paparazzi.snapshot(view, "main");
    }

    @Test
    public void auto_activity() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_auto, null);
        paparazzi.snapshot(view, "auto");
    }

    @Test
    public void tele_activity() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_tele, null);
        paparazzi.snapshot(view, "tele");
    }

    @Test
    public void stage_activity() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_stage, null);
        paparazzi.snapshot(view, "stage");
    }

    @Test
    public void popup_activity() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_popup, null);
        paparazzi.snapshot(view, "popup");
    }

    @Test
    public void qr_activity() {
        RecordsActivity.Info.scoutName = "Demo Scout";
        RecordsActivity.Info.matchNumber = "42";
        RecordsActivity.Info.teamNumber = "99901";
        RecordsActivity.Info.preload = true;
        RecordsActivity.Info.driverStation = 1;
        RecordsActivity.Info.fieldPosition = 2;
        RecordsActivity.Info.leave = true;
        RecordsActivity.Info.autoNotes = 3;
        RecordsActivity.Info.autoAmpNotes = 1;
        RecordsActivity.Info.autoSpeakerNotes = 2;
        RecordsActivity.Info.autoComments = "Clean auto";
        RecordsActivity.Info.teleNotes = 8;
        RecordsActivity.Info.teleAmpNotes = 3;
        RecordsActivity.Info.teleSpeakerNotes = 5;
        RecordsActivity.Info.teleComments = "Fast cycles";
        RecordsActivity.Info.stageLevel = 2;
        RecordsActivity.Info.harmony = true;
        RecordsActivity.Info.trap = false;
        RecordsActivity.Info.stageComments = "Climbed early";

        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_qr, null);
        
        String json = RecordsActivity.Info.CreateJSON();
        QRGEncoder qrgEncoder = new QRGEncoder(json, null, QRGContents.Type.TEXT, 200);
        qrgEncoder.setColorBlack(0xFF292929);
        qrgEncoder.setColorWhite(0xFFffc815);
        Bitmap bitmap = qrgEncoder.getBitmap();
        
        ImageView qrImage = view.findViewById(R.id.qrImageView);
        if (qrImage != null && bitmap != null) {
            qrImage.setImageBitmap(bitmap);
        }
        
        paparazzi.snapshot(view, "qr");
    }
}
