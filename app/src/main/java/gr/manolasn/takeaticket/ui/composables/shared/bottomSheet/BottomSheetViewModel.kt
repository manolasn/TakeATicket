package gr.manolasn.takeaticket.ui.composables.shared.bottomSheet

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.manolasn.takeaticket.common.utils.SortByMethod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor() : ViewModel() {

    private val _isConnectSheetOpen = MutableStateFlow(false)
    val isConnectSheetOpen = _isConnectSheetOpen.asStateFlow()

    private val _selectedFilterOption = MutableStateFlow(SortByMethod.POPULARITY_DESC)
    val selectedFilterOption = _selectedFilterOption.asStateFlow()


    fun updateSelectedFilterOption(option: SortByMethod) {
        _selectedFilterOption.value = option
    }

    fun updateConnectSheetOpen() {
        _isConnectSheetOpen.value = !_isConnectSheetOpen.value
    }

}