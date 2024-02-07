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
    }

}























































