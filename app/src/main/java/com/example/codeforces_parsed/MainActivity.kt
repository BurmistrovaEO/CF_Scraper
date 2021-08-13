package com.example.codeforces_parsed

import ContestListAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforces_parsed.database.ContestViewModel
import com.example.codeforces_parsed.database.ContestViewModelFactory

class MainActivity : AppCompatActivity() {

    private val contestsViewModel: ContestViewModel by viewModels {
        ContestViewModelFactory((application as ContestsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsButton : Button = findViewById(R.id.button)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ContestListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        contestsViewModel.allContests.observe(this){
            contests -> contests.let { adapter.submitList(it) }
        }

        settingsButton.setOnClickListener{
            val settingsIntent = Intent(this, Settings::class.java)
            this.startActivity(settingsIntent)
        }
    }


}