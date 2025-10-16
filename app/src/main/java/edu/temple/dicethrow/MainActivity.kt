package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var dieViewModel: DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.rollDiceButton)

        //makes a new fragment every single time the Activity is restarted/created
        //change to making sure that there's no Fragment previously

        //the below can't be executes before the Activity is active, and same/findViewById
        //.get() isn't suggested so use []
        dieViewModel = ViewModelProvider(this)[DieViewModel::class.java]

        //fragments can be labelled by tags!
        /*if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(20))
                .commit()
        }*/
        //or
        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null){
            //if there's no Fragment attached to the container, add one
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(20))
                .commit()
        }

        //refactor, rename: renames every instance/reference to that function/class/file
        rollButton.setOnClickListener {
            //rollDie()
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as DieFragment)
                .rollDie()
        }
    }
}