import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.looptry.btn.BlockButton
import com.ui.dialog.DialogScreen
import com.ui.form.FormScreen
import com.ui.picker.PickerScreen
import com.ui.toast.ToastScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val nav = rememberNavController()
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme
    ) {
        NavHost(navController = nav, startDestination = "/home") {
            composable("/home") {
                Home(nav2Home = { nav.navigate("/toast") },
                    nav2Dialog = {
                        nav.navigate("/dialog")
                    }, nav2Picker = {
                        nav.navigate("/picker")
                    }, nav2Form = {
                        nav.navigate("/form")
                    })
            }
            composable("/toast") {
                ToastScreen()
            }
            composable("/dialog") {
                DialogScreen()
            }
            composable("/picker") {
                PickerScreen()
            }
            composable("/form") {
                FormScreen()
            }
        }
    }

}

@Composable
fun Home(
    nav2Home: () -> Unit,
    nav2Dialog: () -> Unit,
    nav2Picker: () -> Unit,
    nav2Form: () -> Unit,
) {
    Column {
        BlockButton(leadTitle = "Toast", onItemClick = nav2Home)
        BlockButton(leadTitle = "Dialog", onItemClick = nav2Dialog)
        BlockButton(leadTitle = "Picker", onItemClick = nav2Picker)
        BlockButton(leadTitle = "Form", onItemClick = nav2Form)
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