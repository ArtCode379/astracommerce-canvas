package astracommercetrade.art.astracanvas.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import astracommercetrade.art.astracanvas.R
import astracommercetrade.art.astracanvas.ui.state.DataUiState
import astracommercetrade.art.astracanvas.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    if (orderState is DataUiState.Populated) {
        CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    }

    CheckoutContent(
        name = viewModel.customerFirstName,
        address = viewModel.customerLastName,
        phone = viewModel.customerEmail.removeSuffix("@pickup.local"),
        modifier = modifier,
        focusManager = LocalFocusManager.current,
        isButtonEnabled = viewModel.customerFirstName.isNotBlank() &&
            viewModel.customerLastName.isNotBlank() &&
            viewModel.customerEmail.isNotBlank(),
        onNameChanged = viewModel::updateCustomerFirstName,
        onAddressChanged = viewModel::updateCustomerLastName,
        onPhoneChanged = { phone ->
            val digits = phone.filter { it.isDigit() || it == '+' }
            viewModel.updateCustomerEmail("$digits@pickup.local")
        },
        onPlaceOrder = viewModel::placeOrder,
    )
}

@Composable
private fun CheckoutContent(
    name: String,
    address: String,
    phone: String,
    modifier: Modifier,
    focusManager: FocusManager,
    isButtonEnabled: Boolean,
    onNameChanged: (String) -> Unit,
    onAddressChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPlaceOrder: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
    ) {
        Text(stringResource(R.string.jvong_checkout_heading), style = MaterialTheme.typography.headlineSmall)
        Text(
            text = stringResource(R.string.jvong_checkout_intro),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp, bottom = 18.dp),
        )
        CheckoutTextField(name, onNameChanged, stringResource(R.string.jvong_checkout_name))
        CheckoutTextField(
            input = address,
            onInputChange = onAddressChanged,
            labelText = stringResource(R.string.jvong_checkout_address),
            modifier = Modifier.padding(top = 12.dp),
        )
        CheckoutTextField(
            input = phone,
            onInputChange = onPhoneChanged,
            labelText = stringResource(R.string.jvong_checkout_phone),
            modifier = Modifier.padding(top = 12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(stringResource(R.string.jvong_pickup_summary), style = MaterialTheme.typography.titleMedium)
                Text(
                    text = stringResource(R.string.jvong_pickup_summary_detail),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }
        }
        Button(
            onClick = {
                focusManager.clearFocus()
                onPlaceOrder()
            },
            enabled = isButtonEnabled && phone.length >= 7,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(stringResource(R.string.jvong_button_confirm_order_label), modifier = Modifier.padding(vertical = 6.dp))
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        label = { Text(labelText) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
    )
}
