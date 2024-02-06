package kr.co.lion.android01.androidprojecttest

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kr.co.lion.android01.androidprojecttest.databinding.ActivityAnimalInfoBinding
import kr.co.lion.android01.androidprojecttest.databinding.ActivityModifyInfoBinding

class ModifyInfoActivity : AppCompatActivity() {

    lateinit var activityModifyInfoBinding: ActivityModifyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyInfoBinding = ActivityModifyInfoBinding.inflate(layoutInflater)
        setContentView(activityModifyInfoBinding.root)

        setToolBar()
        setData()

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
    fun setData(){
        activityModifyInfoBinding.apply {
            var info6 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("please1", LionClass::class.java)
            }else{
                intent.getParcelableExtra<LionClass>("please1")
            }
            if(info6 != null){
                containerModifyType1.isVisible = true
                containerModifyType2.isVisible = false
                containerModifyType3.isVisible = false
            }
            var info7 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("please2", TigerClass::class.java)
            }else{
                intent.getParcelableExtra<TigerClass>("please2")
            }
            if (info7 != null){
                containerModifyType1.isVisible = false
                containerModifyType2.isVisible = true
                containerModifyType3.isVisible = false
            }
            var info8 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("please3", GiraffeClass::class.java)
            }else{
                intent.getParcelableExtra<GiraffeClass>("please3")
            }
            if (info8 != null){
                containerModifyType1.isVisible = false
                containerModifyType2.isVisible = false
                containerModifyType3.isVisible = true
            }
            textFieldModifyAge.apply {
                Log.e("test1234", "${info6?.name}")
                Log.e("test1234", "${info7?.name}")
                Log.e("test1234", "${info8?.name}")
                if (info6 != null){
                    setText("${info6.name}")
                }else if (info7 != null){
                    setText("${info7.name}")
                }else if (info8 != null){
                    setText("${info8.name}")
                }
            }
            textFieldModifyAge.apply {
                if (info6 != null){
                    setText("${info6.age}")
                }else if (info7 != null){
                    setText("${info7.age}")
                }else if (info8 != null){
                    setText("${info8.age}")
                }
            }




        }
    }


}























































