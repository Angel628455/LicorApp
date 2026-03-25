package edu.ucne.licorapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val OrangePrimary = Color(0xFFFF6F00)
val OrangeLight = Color(0xFFFFA040)

val PurplePrimary = Color(0xFF7B1FA2)
val PurpleDark = Color(0xFF4A0072)

val PinkAccent = Color(0xFFE91E63)
val BackgroundLight = Color(0xFFFFF3E0)
val BackgroundDark = Color(0xFF121212)

private val DarkColorScheme = darkColorScheme(
    primary = OrangeLight,
    secondary = PurplePrimary,
    tertiary = PinkAccent,
    background = BackgroundDark,
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary,
    secondary = PurplePrimary,
    tertiary = PinkAccent,
    background = BackgroundLight,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White
)

@Composable
fun LicorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}