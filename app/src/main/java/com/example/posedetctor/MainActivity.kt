package com.example.posedetctor

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var startBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById<Button>(R.id.startbtn)
        startBtn.setOnClickListener {
            /*val intent = Intent(applicationContext,LivePreviewActivity::class.java)
            Log.d("test","start activity")
            startActivity(intent)*/
            checkPermission()
        }
    }

    fun checkPermission() {

        // 1. 위험권한(Camera) 권한 승인상태 가져오기
        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // 카메라 권한이 승인된 상태일 경우
            val intent = Intent(applicationContext,LivePreviewActivity::class.java)
            Log.d("test","start activity")
            startActivity(intent)

        } else {
            // 카메라 권한이 승인되지 않았을 경우
            requestPermission()
        }
    }
    // 2. 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 99)
    }

    // 권한 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            99 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(applicationContext,LivePreviewActivity::class.java)
                    Log.d("test","start activity")
                    startActivity(intent)
                } else {
                    Log.d("test", "종료")
                }
            }
        }
    }

}