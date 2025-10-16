package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    lateinit var dieTextView: TextView

    private val dieViewModel: DieViewModel by lazy{
        //you're getting the Activity's ViewModel by using the ref to it
        //only try to access this when the Fragment's attached
        ViewModelProvider(requireActivity())[DieViewModel::class.java]
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
        //after everything's set up can the ViewModel be accessed

        //saying as long as the view is active, observe this data
        dieViewModel.getRolledNum().observe(viewLifecycleOwner){
            updateTextView(it)
        }
    }

    private fun updateTextView(updateNum: Int){
        dieTextView.text = updateNum.toString()
    }
}