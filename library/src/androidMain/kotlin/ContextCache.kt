import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow

val contextCache = MutableStateFlow<Context?>(null)