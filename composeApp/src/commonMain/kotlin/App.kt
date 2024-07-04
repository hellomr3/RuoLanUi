import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.ui.picker.PickerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var page by remember {
        mutableStateOf(0)
    }

//    ToastScreen()
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme
    ) {
//        DialogScreen()
        PickerScreen()
    }

}


val lightScheme
    @Composable
    get() = MaterialTheme.colorScheme.copy(
        primary = Color(0xFF0092FF),
        onPrimary = Color(0xFFFFFFFF),
        onBackground = Color(0xFF000000),
        background = Color(0xFFFFFFFF)
    )

val darkScheme
    @Composable
    get() = MaterialTheme.colorScheme.copy(
        primary = Color(0xFFFFFFFF),
        onPrimary = Color(0xFF000000),
        onBackground = Color(0xFFFFFFFF),
        background = Color(0xFF000000)
    )