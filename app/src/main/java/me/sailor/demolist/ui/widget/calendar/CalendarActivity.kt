package me.sailor.demolist.ui.widget.calendar

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
import me.sailor.demolist.R
import me.sailor.libcommon.utils.DateUtils
import java.util.*

class CalendarActivity : AppCompatActivity() {
    var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        timeStart.text = "${DateUtils.format(calendar.time)}"
        timeEnd.text = "${DateUtils.format(calendar.time)}"

        timeStart.setOnClickListener {
            showDataDialog(DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                timeStart.text = "${DateUtils.format(calendar.time)}"
            })
        }
        timeEnd.setOnClickListener {
            showDataDialog(DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                timeEnd.text = "${DateUtils.format(calendar.time)}"
            })

        }

        click.setOnClickListener {
            Toast.makeText(this, "请选择开始时间！", Toast.LENGTH_SHORT).show()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                timeStart.text = "${DateUtils.format(calendar.time)}"
                var year = calendar.get(Calendar.YEAR)
                var month = calendar.get(Calendar.MONTH)
                var day = calendar.get(Calendar.DAY_OF_MONTH)
                var dialog1 = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    timeEnd.text = "${DateUtils.format(calendar.time)}"
                }, year, month, day)
                dialog1.datePicker.minDate = calendar.timeInMillis
                dialog1.datePicker.maxDate = currentTime
                dialog1.show()
            }, year, month, day)

            dialog.datePicker.maxDate = currentTime
            dialog.show()
        }
    }

    companion object {
        fun  start(context: Context) {
            val starter = Intent(context, CalendarActivity::class.java)
            context.startActivity(starter)
        }
    }




    private var currentTime: Long = calendar.timeInMillis
    fun showDataDialog(listener: DatePickerDialog.OnDateSetListener) {
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var dialog = DatePickerDialog(this, listener, year, month, day)

        dialog.datePicker.maxDate = currentTime

        Log.d("DATE", "currentTime--->" + currentTime)
        dialog.show()
    }
}
