package kr.co.lion.androidproject1test

import android.content.Context
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.android01.androidprojecttest.AnimalClass
import kotlin.concurrent.thread

class Util {

    companion object {
        var animalList = mutableListOf<AnimalClass>()
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
        fun showDiaLog(context: Context, title:String, message:String, listener:(DialogInterface, Int) -> Unit){
            var viewDiaLog = MaterialAlertDialogBuilder(context)
            viewDiaLog.setTitle(title)
            viewDiaLog.setMessage(message)
            viewDiaLog.setPositiveButton("확인", listener)
            viewDiaLog.show()
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

//자주 써야하는 필터 다이아 로그를 enum Class로 만들어준다
    enum class showFilterDiaLog(var num:Int, var str:String){
        //각 항목들을 작성해준다
        FILTER_TYPE_ALL(0, "전체"),
        FILTER_TYPE_LION(0, "사자"),
        FILTER_TYPE_TIGER(0, "호랑이"),
        FILTER_TYPE_GIRAFFE(0, "기린")

    }
