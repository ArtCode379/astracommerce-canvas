package astracommercetrade.art.astracanvas.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import astracommercetrade.art.astracanvas.R
import astracommercetrade.art.astracanvas.ui.composable.shared.JVONGContentWrapper
import astracommercetrade.art.astracanvas.ui.state.CartItemUiState
import astracommercetrade.art.astracanvas.ui.state.DataUiState
import astracommercetrade.art.astracanvas.ui.viewmodel.CartViewModel
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    JVONGContentWrapper(
        dataState = cartItemsState,
        dataPopulated = {
            val items = (cartItemsState as DataUiState.Populated).data
            CartItems(
                items = items,
                total = totalPrice,
                modifier = modifier,
                onPlus = viewModel::incrementProductInCart,
                onMinus = { item ->
                    if (item.quantity == 1) {
                        viewModel.deleteFromCart(item.productId)
                    } else {
                        viewModel.decrementItemInCart(item.productId)
                    }
                },
                onDelete = viewModel::deleteFromCart,
                onCheckout = onNavigateToCheckoutScreen,
            )
        },
        dataEmpty = { EmptyCart(modifier) },
    )
}

@Composable
private fun EmptyCart(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.RemoveShoppingCart,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(72.dp),
        )
        Text(
            text = stringResource(R.string.jvong_cart_state_empty_primary_text),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 18.dp),
        )
        Text(
            text = stringResource(R.string.jvong_cart_empty_hint),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}

@Composable
private fun CartItems(
    items: List<CartItemUiState>,
    total: Double,
    modifier: Modifier,
    onPlus: (Int) -> Unit,
    onMinus: (CartItemUiState) -> Unit,
    onDelete: (Int) -> Unit,
    onCheckout: () -> Unit,
) {
    Column(modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items, key = { it.productId }) { item ->
                Card(shape = RoundedCornerShape(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = item.productImageUrl,
                            contentDescription = item.productTitle,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(64.dp),
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp),
                        ) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = stringResource(R.string.jvong_price, item.productPrice),
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                OutlinedButton(onClick = { onMinus(item) }) {
                                    Text("−")
                                }
                                Text(item.quantity.toString(), modifier = Modifier.padding(horizontal = 12.dp))
                                OutlinedButton(onClick = { onPlus(item.productId) }) {
                                    Text("+")
                                }
                            }
                        }
                        IconButton(onClick = { onDelete(item.productId) }) {
                            Icon(Icons.Default.DeleteOutline, contentDescription = stringResource(R.string.jvong_delete_item_icon_description))
                        }
                    }
                }
            }
        }
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(R.string.jvong_total), style = MaterialTheme.typography.titleLarge)
                Text(
                    text = stringResource(R.string.jvong_price, total),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Button(
                onClick = onCheckout,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(stringResource(R.string.jvong_proceed_checkout), modifier = Modifier.padding(vertical = 6.dp))
            }
        }
    }
}
