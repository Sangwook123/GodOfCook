package org.mtc.godofcook.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mtc.godofcook.domain.entity.Food
import org.mtc.godofcook.domain.repository.FoodRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {
    private val _getFoodResult = MutableStateFlow<Food?>(Food(0,"","","",""))
    val getFood : StateFlow<Food?> = _getFoodResult.asStateFlow()

    private val _deleteResult = MutableStateFlow<Boolean>(false)
    val deleteResult : StateFlow<Boolean> = _deleteResult.asStateFlow()

    fun getFood(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            foodRepository.getFoodById(id).collect{
                _getFoodResult.value = it
            }
        }
    }

    fun deleteFood(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(id != null){
                runCatching { foodRepository.deleteFoodById(id) }.onSuccess {
                    _deleteResult.value = true
                }
            }
        }
    }
}