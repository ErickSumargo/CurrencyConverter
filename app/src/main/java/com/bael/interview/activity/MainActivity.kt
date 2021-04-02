package com.bael.interview.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bael.interview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ErickSumargo on 01/04/21.
 */

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also { viewBinding ->
            setContentView(viewBinding.root)
        }
    }
}
