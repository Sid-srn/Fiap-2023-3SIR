package com.example.profilecustonpkmcard

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.profilecustonpkmcard.Constants.PROFILE_MAIN
import com.example.profilecustonpkmcard.Constants.PROFILE_SKILL
import com.example.profilecustonpkmcard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        profile = Profile("Growlithe", "", mutableListOf())

        bind.btnChangeTheme.text = Constants.getThemeName()

        bind.btnEditSkill.setOnClickListener {
            val intent = Intent(this, SkillActivity::class.java)
            intent.putExtra(PROFILE_MAIN, profile)
            register.launch(intent)
        }
        bind.btnChangeTheme.setOnClickListener {
            Constants.switchTheme()
            recreate()
        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra(PROFILE_SKILL, Profile::class.java)
                } else {
                    data.getParcelableExtra<Profile>(Constants.PROFILE_SKILL)
                })?.let { profile = it }
                bind.txtSkillList.text = ""
                for (skill in profile.skills) {
                    bind.txtSkillList.text = "${bind.txtSkillList.text}> ${skill}\n"
                }
            }
        }
    }
}