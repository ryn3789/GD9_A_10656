package com.abcxyz.gd9_proximity_a_10656

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var sensorStatusTV: TextView
    lateinit var proximitySensor: Sensor
    lateinit var sensorManager: SensorManager

    var proximitySensorEventListener: SensorEventListener? = object: SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorStatusTV = findViewById(R.id.idTVSensorStatus)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (proximitySensor == null) {
            Toast.makeText(this,"no proximity sensor found",Toast.LENGTH_SHORT).show()
            finish()
        } else {
            sensorManager.registerListener(proximitySensorEventListener,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

        if(event.sensor.type==Sensor.TYPE_PROXIMITY) {
            if(event.values[0]==0f){
                sensorStatusTV.text="NEAR"
            } else{
                sensorStatusTV.text="AWAY"
            }
        }
    }

}

