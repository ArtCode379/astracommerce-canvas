package astracommercetrade.art.astracanvas.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import astracommercetrade.art.astracanvas.R
import astracommercetrade.art.astracanvas.data.entity.OrderEntity
import astracommercetrade.art.astracanvas.ui.composable.shared.JVONGContentWrapper
import astracommercetrade.art.astracanvas.ui.composable.shared.JVONGEmptyView
import astracommercetrade.art.astracanvas.ui.state.DataUiState
import astracommercetrade.art.astracanvas.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        JVONGContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                JVONGEmptyView(
                    primaryText = stringResource(R.string.jvong_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}