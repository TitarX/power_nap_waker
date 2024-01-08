package org.digitmind.powernapwaker

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import android.widget.Toast
import java.time.LocalDateTime

object MscHelper {
    fun setAlarmClock(activity: Activity, time: LocalDateTime, title: String) {
        val alarmClockIntent = Intent(AlarmClock.ACTION_SET_ALARM)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        alarmClockIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        alarmClockIntent.putExtra(AlarmClock.EXTRA_MESSAGE, title)
        alarmClockIntent.putExtra(AlarmClock.EXTRA_HOUR, time.hour)
        alarmClockIntent.putExtra(AlarmClock.EXTRA_MINUTES, time.minute)

        try {
            activity.startActivity(alarmClockIntent)

            // Используем этот способ, так как нельзя показывать уведомления
            // из потока не связанного с пользовательским интерфейсом (non-UI)
            Handler(Looper.getMainLooper()).post {
                // Выводим уведомлении об успешном создании будильника
                Toast.makeText(
                    activity,
                    String.format(activity.getString(R.string.result_success), title),
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: ActivityNotFoundException) {
            // Используем этот способ, так как нельзя показывать уведомления
            // из потока не связанного с пользовательским интерфейсом (non-UI)
            Handler(Looper.getMainLooper()).post {
                // Выводим уведомлении о неудачной попытке создания будильника
                Toast.makeText(
                    activity,
                    String.format(activity.getString(R.string.result_fail), title),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
