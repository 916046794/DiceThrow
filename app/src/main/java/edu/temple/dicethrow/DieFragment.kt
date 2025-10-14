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

    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        throwDie()
        view.setOnClickListener{
            throwDie()
        }
    }

    fun throwDie() {
        dieTextView.text = (Random.nextInt(dieSides) + 1).toString()
    }

    companion object{
        //creates an intance of our Fragment
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