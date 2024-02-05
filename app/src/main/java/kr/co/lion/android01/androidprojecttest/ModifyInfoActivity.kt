package kr.co.lion.android01.androidprojecttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.androidprojecttest.databinding.ActivityAnimalInfoBinding
import kr.co.lion.android01.androidprojecttest.databinding.ActivityModifyInfoBinding

class ModifyInfoActivity : AppCompatActivity() {

    lateinit var activityModifyInfoBinding: ActivityModifyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyInfoBinding = ActivityModifyInfoBinding.inflate(layoutInflater)
        setContentView(activityModifyInfoBinding.root)

        setToolBar()

    }

    //툴바 설정
    fun setToolBar(){
        activityModifyInfoBinding.apply {
            toolbarModify.apply {
                //타이틀
                title = "동물 정보 수정"
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌럮을 때
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.modifyinfo_menu)
                //메뉴를 클릭했을 때
            }
        }

    }
}
