package kr.co.lion.androidproject1test

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.android01.androidprojecttest.AnimalClass
import kotlin.concurrent.thread

class Util {
    var animalList = mutableListOf<AnimalClass>()

    companion object {
        // 포커스를 주고 키보드를 올려주는 메서드
        //키보드를 올린다
        fun showSoftInput(view: View, context: Context) {
            // 포커스를 준다.
            view.requestFocus()

            thread {
                SystemClock.sleep(1000)
                val inputMethodManager =
                    context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, 0)
            }
        }

        //키보드를 내려준다
        fun hideSoftInput(activity: AppCompatActivity) {
            //현재 포커스를 가지고있는 뷰가 있다면 키보드를 내린다
            if (activity.window.currentFocus != null) {
                val inputMethodManager =
                    activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    activity.window.currentFocus?.windowToken,
                    0
                );
            }
        }
    }
}
    enum class AnimalType(var num:Int, var str:String){
        ANIMAL_TYPE_LION(0, "사자"),
        ANIMAL_TYPE_TIGER(0, "호랑이"),
        ANIMAL_TYPE_GIRAFFE(0, "기린")
    }
    enum class LION_GENDER(var num:Int, var str:String){
        LION_GENDER1(0, "남자"),
        LION_GENDER2(0, "여자")
    }
