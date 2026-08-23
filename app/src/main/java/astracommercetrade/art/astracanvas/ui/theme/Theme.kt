package astracommercetrade.art.astracanvas.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CanvasColorScheme = lightColorScheme(
    primary = CanvasViolet,
    onPrimary = CanvasSurface,
    primaryContainer = CanvasChip,
    onPrimaryContainer = CanvasVioletDark,
    secondary = CanvasOrange,
    onSecondary = CanvasSurface,
    tertiary = CanvasRose,
    background = CanvasCream,
    onBackground = CanvasInk,
    surface = CanvasSurface,
    onSurface = CanvasInk,
    surfaceVariant = CanvasChip,
    onSurfaceVariant = CanvasMuted,
    outline = CanvasBorder,
    error = Color(0xFFBA1A1A),
)

@Composable
fun ProductAppJVONGTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = CanvasColorScheme,
        typography = AppTypography,
        content = content,
    )
}
