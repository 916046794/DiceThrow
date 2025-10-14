package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val ROLLEDNUMKEY = "rollednumber"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    //set it to an invalid number for our roll, can also set it to
    //Int? = null, or for stuff that's not a primitive to a lateinit
    var rolledNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //what's inside SavedInstanceState, if there's smth, use it,
        //otherwise look at what was there when the Fragment
        //was started

        //for us, start is DIESIDE, curr state is the rolled number
        //so arguments is the arguments, and savedInstanceState is what
        //was saved to keep when the state changes

        //if you had the start and state info as the same data, i.e. both
        //the number of die sides, you'd use if else
        savedInstanceState?.let {
            it.getInt(ROLLEDNUMKEY).run {
                rolledNum = this
            }
        }

        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(rolledNum == 0){
            //there was no roll! if rolledNum is 0
            //but also...check if the savedInstanceState w/ur required flag
            //exists, or if the savedInstanceState itself isn't null
            //if(savedInstanceState.getInt(ROLLEDNUMKEY) != null)
            rollDie()
        }
        else{
            //could set the TextView directly to rolledNum, but that's a bad
            //idea bc it's being accessed directly in two different places
            updateTextView(rolledNum)
        }
    }

    fun rollDie() {
        rolledNum = Random.nextInt(dieSides) + 1
        updateTextView(rolledNum)
    }

    private fun updateTextView(updateNum: Int){
        dieTextView.text = updateNum.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ROLLEDNUMKEY, rolledNum)
    }

    companion object{
        //creates an instance of our Fragment
        //Fragment Factory based on Factory design pattern
        fun newInstance(dieSides: Int) = DieFragment().apply{
            //add info for it
            arguments = Bundle().apply{
                //the Die Fragements arguments are set to a Bundle
                //that has an integer related to the DIESIDE label
                //and places our argument
                putInt(DIESIDE, dieSides)
            }
        }
    }
}