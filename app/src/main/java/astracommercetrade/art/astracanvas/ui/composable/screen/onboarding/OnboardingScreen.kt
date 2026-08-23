package astracommercetrade.art.astracanvas.ui.composable.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import astracommercetrade.art.astracanvas.R
import astracommercetrade.art.astracanvas.ui.viewmodel.JVONGOnboardingVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private data class OnboardingContent(
    val title: Int,
    val description: Int,
    val icon: ImageVector,
)

private val onboardingPages = listOf(
    OnboardingContent(R.string.jvong_page_1_title, R.string.jvong_page_1_description, Icons.Default.Brush),
    OnboardingContent(R.string.jvong_page_2_title, R.string.jvong_page_2_description, Icons.Default.Inventory2),
    OnboardingContent(R.string.jvong_page_3_title, R.string.jvong_page_3_description, Icons.Default.Storefront),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: JVONGOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { page ->
            val content = onboardingPages[page]
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = content.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(32.dp))
                        .padding(28.dp),
                )
                Spacer(Modifier.height(34.dp))
                Text(
                    text = stringResource(content.title),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = stringResource(content.description),
                    modifier = Modifier.padding(top = 12.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            onboardingPages.indices.forEach { index ->
                Spacer(
                    modifier = Modifier
                        .size(if (index == pagerState.currentPage) 10.dp else 8.dp)
                        .background(
                            color = if (index == pagerState.currentPage) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outline
                            },
                            shape = CircleShape,
                        ),
                )
            }
        }
        Button(
            onClick = {
                if (pagerState.currentPage == onboardingPages.lastIndex) {
                    viewModel.setOnboarded()
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(
                    if (pagerState.currentPage == onboardingPages.lastIndex) {
                        R.string.jvong_start_button_title
                    } else {
                        R.string.jvong_next_button_title
                    },
                ),
                modifier = Modifier.padding(vertical = 6.dp),
            )
        }
    }
}
