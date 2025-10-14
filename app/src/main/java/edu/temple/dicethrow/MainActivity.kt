package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.rollDiceButton)

        //makes a new fragment every single time the Activity is restarted/created
        //change to making sure that there's no Fragment previously

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