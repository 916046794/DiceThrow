package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel : ViewModel() {

    //the below is technically all state
    //but rolledNum is the only one the View cares abt directly
    //bc it doesnt change how the user views things
    private var dieSides = 6
    private var rolledNum : MutableLiveData<Int> = MutableLiveData()

    fun setDieSides(dieSideToSet: Int){
        if(dieSideToSet >0) {
            dieSides = dieSideToSet
        }
    }

    //alr a mutator to change here >:(, don't do directly!!
    fun rollDie(){
        rolledNum.value = Random.nextInt(dieSides) + 1
    }

    //NOT mutable bc we don't want them to change it
    //this is bc of the Liskov Subsitution Principle
    fun getRolledNum(): LiveData<Int> = rolledNum
}