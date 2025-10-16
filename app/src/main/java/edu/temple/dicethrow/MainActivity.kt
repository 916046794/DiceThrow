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

    //the below lazy only gets executed when you try to use the object and it's not
    //executed more than once
    private val dieViewModel: DieViewModel by lazy{
        //the instantiation of DieViewModel is delegated by lazy
        //and doesn't provide functionality for setters, but only the
        //getters, which is why it's fine for a val and not a var.
        ViewModelProvider(this)[DieViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.rollDiceButton)

        //makes a new fragment every single time the Activity is restarted/created
        //change to making sure that there's no Fragment previously

        dieViewModel.setDieSides(20)

        //refactor, rename: renames every instance/reference to that function/class/file
        rollButton.setOnClickListener {
            //triggers the rollDie() from the ViewModel
            dieViewModel.rollDie()
        }
    }
}