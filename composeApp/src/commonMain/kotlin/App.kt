import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.ui.dialog.DialogScreen
import com.ui.toast.ToastScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var page by remember {
        mutableStateOf(0)
    }

//    ToastScreen()
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(primary = Color(0xFF0092FF))
    ) {
        DialogScreen()
    }

}