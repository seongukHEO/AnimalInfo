package kr.co.lion.android01.androidprojecttest

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import kr.co.lion.android01.androidprojecttest.databinding.ActivityAnimalInfoBinding
import kr.co.lion.android01.androidprojecttest.databinding.ActivityInputAnimalBinding

class AnimalInfoActivity : AppCompatActivity() {
    lateinit var activityAnimalInfoBinding: ActivityAnimalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAnimalInfoBinding = ActivityAnimalInfoBinding.inflate(layoutInflater)
        setContentView(activityAnimalInfoBinding.root)

    }





















































}