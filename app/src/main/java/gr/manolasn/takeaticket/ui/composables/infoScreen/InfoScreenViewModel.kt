package gr.manolasn.takeaticket.ui.composables.infoScreen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.manolasn.takeaticket.common.ErrorState
import gr.manolasn.takeaticket.common.utils.Constants.PRIVACY_POLICY
import gr.manolasn.takeaticket.common.utils.Constants.TERMS_OF_USE
import gr.manolasn.takeaticket.domain.model.info.BasicInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    var loading = _loading.asStateFlow()

    var errorState by mutableStateOf(ErrorState())
        private set

    var currentInfo by mutableStateOf(BasicInfo())
        private set

    fun getInfo(info: String) {
        viewModelScope.launch {
            val htmlContent = loadHtmlFromAssets(info)
            currentInfo = BasicInfo(
                title = info,
                description = htmlContent
            )
            _loading.value = false
        }
    }

    // Load HTML content based on the selected info type
    private suspend fun loadHtmlFromAssets(info: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val fileName = when (info) {
                    TERMS_OF_USE -> "terms_of_use.html"
                    PRIVACY_POLICY -> "privacy_policy.html"
                    else -> ""
                }

                if (fileName.isNotEmpty()) {
                    val inputStream = context.assets.open(fileName)
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val content = reader.readText()
                    reader.close()
                    content
                } else {
                    "Error: File not found."
                }
            } catch (e: Exception) {
                "Error loading content."
            }
        }
    }
}