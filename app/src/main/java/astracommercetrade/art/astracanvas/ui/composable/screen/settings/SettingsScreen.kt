package astracommercetrade.art.astracanvas.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import astracommercetrade.art.astracanvas.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Palette,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = stringResource(R.string.jvong_app_name),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 10.dp),
        )
        Text(
            text = stringResource(R.string.jvong_settings_intro),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp),
        )
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(Modifier.fillMaxWidth()) {
                SettingRow(stringResource(R.string.jvong_settings_screen_company_label), stringResource(R.string.jvong_company_name))
                HorizontalDivider()
                SettingRow(stringResource(R.string.jvong_settings_screen_version_label), stringResource(R.string.jvong_app_version))
            }
        }
        Text(
            text = stringResource(R.string.jvong_help_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 26.dp, bottom = 10.dp),
        )
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://astracommerce-trade.surf"))
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Icon(Icons.Default.OpenInNew, contentDescription = null)
            Text(
                text = stringResource(R.string.jvong_settings_screen_customer_support_label),
                modifier = Modifier.padding(start = 8.dp, top = 6.dp, bottom = 6.dp),
            )
        }
    }
}

@Composable
private fun SettingRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Default.Business, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Column(Modifier.padding(start = 12.dp)) {
            Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Text(value, style = MaterialTheme.typography.titleMedium)
        }
    }
}
