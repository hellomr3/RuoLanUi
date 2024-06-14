import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.looptry.Dp64
import com.looptry.loading.AppLoading
import com.looptry.toast.ToastIcon
import com.looptry.toast.rememberToastState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val toastState = rememberToastState()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {toastState.show("测试toast", icon = ToastIcon.LOADING, mask = true) }) {
                Text("Click me!")
            }
            AppLoading(size = Dp64)
        }
    }
}