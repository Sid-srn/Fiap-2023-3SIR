package com.example.profilecustonpkmcard

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.profilecustonpkmcard.Constants.PROFILE_SKILL
import com.example.profilecustonpkmcard.databinding.ActivityMainBinding
import com.example.profilecustonpkmcard.databinding.ActivitySkillBinding

class SkillActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySkillBinding
    private lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Constants.currentTheme)
        bind = ActivitySkillBinding.inflate(layoutInflater)
        setContentView(bind.root)

        (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.PROFILE_MAIN, Profile::class.java)
        } else {
            intent.getParcelableExtra<Profile>(Constants.PROFILE_MAIN)
        })?.let { profile = it }

        bind.switchBite.isChecked = profile.skills.contains(getString(R.string.bite))
        bind.switchEmber.isChecked = profile.skills.contains(getString(R.string.ember))
        bind.switchFireFang.isChecked = profile.skills.contains(getString(R.string.fire_fang))
        bind.switchFireBlast.isChecked = profile.skills.contains(getString(R.string.fire_blast))

        bind.switchBite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                profile.skills.add(getString(R.string.bite))
            else
                profile.skills.remove(getString(R.string.bite))
        }
        bind.switchEmber.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                profile.skills.add(getString(R.string.ember))
            else
                profile.skills.remove(getString(R.string.ember))
        }
        bind.switchFireFang.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                profile.skills.add(getString(R.string.fire_fang))
            else
                profile.skills.remove(getString(R.string.fire_fang))
        }
        bind.switchFireBlast.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                profile.skills.add(getString(R.string.fire_blast))
            else
                profile.skills.remove(getString(R.string.fire_blast))
        }

        bind.btnUpdateSkill.setOnClickListener {
            Intent().apply {
                putExtra(PROFILE_SKILL, profile)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }
}