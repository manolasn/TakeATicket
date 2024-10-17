package gr.manolasn.takeaticket.ui.composables.shared.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.common.utils.SortByMethod
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal
import gr.manolasn.takeaticket.ui.theme.Typography


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheet(
    viewModel: BottomSheetViewModel,
    goToHomePage: () -> Unit = {},
) {
    val isConnectSheetOpen by viewModel.isConnectSheetOpen.collectAsState()
    val filterOptions = SortByMethod.entries.toTypedArray()
    val selectedFilterOption by viewModel.selectedFilterOption.collectAsState()

    val connectSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    if (isConnectSheetOpen) {
        ModalBottomSheet(
            sheetState = connectSheetState,
            onDismissRequest = { viewModel.updateConnectSheetOpen() },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Filter Selection Drawer
                Text(
                    text = stringResource(id = R.string.select_filter),
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)
                        )
                        .imePadding(),
                    verticalArrangement = Arrangement.Top
                ) {

                    items(filterOptions.size) { index ->
                        FilterOptionItem(option = filterOptions[index],
                            isSelected = filterOptions[index] == selectedFilterOption,
                            onSelect = { viewModel.updateSelectedFilterOption(filterOptions[index]) })
                    }

                }

                Spacer(Modifier.height(10.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppGreyBlack),
                    onClick = {
                        goToHomePage.invoke()
                        viewModel.updateConnectSheetOpen()
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.confirm),
                        style = Typography.bodyLarge,
                        fontSize = 18.sp,
                        color = AppTeal
                    )

                }
            }


        }
    }
}

@Composable
fun FilterOptionItem(
    option: SortByMethod, isSelected: Boolean, onSelect: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onSelect() }
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelect() },
            colors = RadioButtonDefaults.colors(selectedColor = AppTeal)
        )
        Text(
            text = stringResource(option.toDisplayValue()),
            style = Typography.bodyLarge
        )
    }
}
