package org.digitmind.powernapwaker

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.util.Timer
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Запускаем часы с вкладкой будильников
        val alarmClockIntent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        alarmClockIntent.setFlags(Intent.FLAG_FROM_BACKGROUND)
        startActivity(alarmClockIntent)

        // Создание нескольких активити
        // Оставил для примера
        // TaskStackBuilder.create(this)
        //     .addNextIntent(alarmClockIntent)
        //     .startActivities()

        val thisActivity = this

        // Задержка 2 секунды
        Timer().schedule(timerTask {
            // Возвращаемся к приложению
            val thisActivityIndent = thisActivity.intent
            thisActivityIndent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(thisActivityIndent)
        }, 1500)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            startButton.isEnabled = false
            startButton.setBackgroundColor(resources.getColor(R.color.grey, null))

            // Задержка 2 секунды
            Timer().schedule(timerTask {
                // Задаём будильник на 20 минут от текущего времени
                MscHelper.setAlarmClock(
                    thisActivity,
                    LocalDateTime.now().plusMinutes(20),
                    "PowerNapWaker1"
                )

                // Задаём будильник на 120 минут от текущего времени
                MscHelper.setAlarmClock(
                    thisActivity,
                    LocalDateTime.now().plusMinutes(120),
                    "PowerNapWaker2"
                )

                startButton.isEnabled = true
                startButton.setBackgroundColor(resources.getColor(R.color.green, null))
            }, 1500)
        }
    }
}
