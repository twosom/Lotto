package itstudy.icloud.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_constellation.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class ConstellationActivity : AppCompatActivity() {

    //월 과 일을 넘겨받아서 별자리를 리턴해주는 메소드
    fun makeConstellationStirng(month: Int, day: Int): String {
        //월 과 일을 합쳐서 하나의 숫자 만들기

        //월은 0부터 시작하기 때문에 + 1
        //일은 두자리 숫자로 만들기 위해서 String.format  사용
        val target = "${month + 1}${String.format("%02d", day)}".toInt()
        when (target) {
            in 101..119 -> return "염소자리"
            in 120..218 -> return "물병자리"
            in 219..320 -> return "물고기자리"
            in 321..419 -> return "양자리"
            in 420..520 -> return "황소자리"
            in 521..621 -> return "쌍둥이자리"
            in 622..722 -> return "게자리"
            in 723..822 -> return "사자자리"
            in 823..923 -> return "처녀자리"
            in 924..1022 -> return "천칭자리"
            in 1023..1122 -> return "전갈자리"
            in 1123..1224 -> return "사수자리"
            else -> return "기타 별자리"

        }

    }


    //별자리 문자열을 받아서 6개의 정수 List를 만들어서 리턴하는 메서드
    fun getLottoNumbersFromHash(str: String): MutableList<Int> {
        //정수를 저장할 List
        var list = mutableListOf<Int>()

        //정수 1~45를 list에 저장
        for (number in 1..45) {
            list.add(number)
            Log.e("list", list.toString())
        }

        //오늘 날짜와 문자열을 합쳐서 새로운 문자열 만들기
        val targetString = SimpleDateFormat("yyyy-MM-dd").format(Date()) + str
        //list의 숫자 섞기
        //문자열의 해시코드를 Long으로 변환해서 Random의 seed를 생성해서 list의 숫자를
        //섞기
        list.shuffle(Random(targetString.hashCode()))
        Log.e("shuffle", list.subList(0, 6).toString())
        return list.subList(0, 6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        goResultButton.setOnClickListener {
            val intent: Intent = Intent(this, ResultActivity::class.java)

            //데이터 생성
            val lottoList = getLottoNumbersFromHash(
                makeConstellationStirng(
                    datePicker.month,
                    datePicker.dayOfMonth
                )
            )
            //인텐트에 저장
            intent.putIntegerArrayListExtra("result", ArrayList(lottoList))

            //별자리 이름도 전달
            intent.putExtra(
                "constellation",
                makeConstellationStirng(datePicker.month, datePicker.dayOfMonth)
            )

            startActivity(intent)
        }


        backButton.setOnClickListener {
            finish()
        }
        textView.text = makeConstellationStirng(datePicker.month, datePicker.dayOfMonth)
        val calendar = GregorianCalendar()
        datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            object : CalendarView.OnDateChangeListener, DatePicker.OnDateChangedListener {
                override fun onSelectedDayChange(
                    view: CalendarView,
                    year: Int,
                    month: Int,
                    dayOfMonth: Int
                ) {
                    TODO("Not yet implemented")
                }

                override fun onDateChanged(
                    view: DatePicker?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    textView.text=makeConstellationStirng(datePicker.month, datePicker.dayOfMonth)
                }

            }

        )


    }
}