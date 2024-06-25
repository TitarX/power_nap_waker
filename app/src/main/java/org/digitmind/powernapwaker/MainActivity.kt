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
            val thisActivityIndent = thisActivity.intent

            // Если такая задача уже имеется, то новая не создаётся, а выводится на передний план имеющаяся
            thisActivityIndent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            // Ищет эту задачу в стеке задач и выводит на передний план
            thisActivityIndent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

            startActivity(thisActivityIndent)
        }, 2000)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            startButton.isEnabled = false
            startButton.setBackgroundColor(resources.getColor(R.color.grey, null))

            // Задержка 1 секунда
            Timer().schedule(timerTask {
                // Задаём будильник на 20 минут от текущего времени
                MscHelper.setAlarmClock(
                    thisActivity,
                    LocalDateTime.now().plusMinutes(20),
                    "PowerNapWaker1"
                )

                // Задаём будильник на 30 минут от текущего времени
                MscHelper.setAlarmClock(
                    thisActivity,
                    LocalDateTime.now().plusMinutes(30),
                    "PowerNapWaker2"
                )
            }, 1000)
        }
    }
}
