package org.digitmind.powernapwaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.Toast
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            val powerNapWaker1Time = LocalDateTime.now().plusMinutes(20)
            val powerNapWaker2Time = LocalDateTime.now().plusMinutes(30)

            val alarmClockIntent1 = Intent(AlarmClock.ACTION_SET_ALARM)
            alarmClockIntent1.putExtra(AlarmClock.EXTRA_MESSAGE, "PowerNapWaker1")
            alarmClockIntent1.putExtra(AlarmClock.EXTRA_HOUR, powerNapWaker1Time.hour)
            alarmClockIntent1.putExtra(AlarmClock.EXTRA_MINUTES, powerNapWaker1Time.minute)
            startActivity(alarmClockIntent1)

            val alarmClockIntent2 = Intent(AlarmClock.ACTION_SET_ALARM)
            alarmClockIntent2.putExtra(AlarmClock.EXTRA_MESSAGE, "PowerNapWaker2")
            alarmClockIntent2.putExtra(AlarmClock.EXTRA_HOUR, powerNapWaker2Time.hour)
            alarmClockIntent2.putExtra(AlarmClock.EXTRA_MINUTES, powerNapWaker2Time.minute)
            startActivity(alarmClockIntent2)

            Toast.makeText(
                this,
                R.string.result_success,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
