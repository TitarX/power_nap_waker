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
        // Потребовалось для корректного создания двух будильников
        // на "Xiaomi Redmi Note 8 (2021)"
        val alarmClockIntent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
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
            startActivity(thisActivity.intent)
        }, 2000)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            // Задержка 2 секунды
            Timer().schedule(timerTask {
                // Задаём будильник на 20 минут от текущего времени
                MscHelper.setAlarmClock(
                    thisActivity,
                    LocalDateTime.now().plusMinutes(20),
                    "PowerNapWaker1"
                )
            }, 2000)

            // Задержка 2 секунды
            Timer().schedule(timerTask {
                // Задаём будильник на 30 минут от текущего времени
                MscHelper.setAlarmClock(
                    thisActivity,
                    LocalDateTime.now().plusMinutes(30),
                    "PowerNapWaker2"
                )
            }, 2000)
        }
    }
}
