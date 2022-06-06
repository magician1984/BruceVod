package idv.bruce.vod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import idv.bruce.vod.enum.SystemState
import kotlinx.coroutines.launch

class SystemViewModel : ViewModel() {
    private val _actionMessage: MutableLiveData<String> = MutableLiveData()

    val actionMessage: LiveData<String>
        get() = _actionMessage

    private val _systemState: MutableLiveData<SystemState> = MutableLiveData(SystemState.Preparing)

    val systemState: LiveData<SystemState>
        get() = _systemState

    fun onSystemInit() {
        viewModelScope.launch {
            Thread {
                _actionMessage.postValue("System preparing...")
                _systemState.postValue(SystemState.Preparing)
                Thread.sleep(3000)
                _actionMessage.postValue("System ready!")
                _systemState.postValue(SystemState.Using)
            }.start()
        }
    }
}