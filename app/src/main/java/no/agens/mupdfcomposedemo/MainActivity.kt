package no.agens.mupdfcomposedemo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.artifex.solib.ArDkLib
import com.artifex.solib.ConfigOptions
import com.artifex.sonui.editor.DocumentView
import no.agens.mupdfcomposedemo.ui.theme.MuPDFComposeDemoTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

                MuPDFComposeDemoTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ConstraintLayout {


                            AndroidView(factory = {
                                val documentView = DocumentView(it)

                                val configOptions = ConfigOptions().apply {
                                    isEditingEnabled = true
                                    isTrackChangesFeatureEnabled = false
                                    isAnimationFeatureEnabled = true
                                }
                                ArDkLib.setAppConfigOptions(configOptions)

                                documentView.setDocConfigOptions(ArDkLib.getAppConfigOptions())

                                documentView.start(
                                    Uri.parse("https://www.africau.edu/images/default/sample.pdf"),
                                    0,
                                    false
                                )

                                documentView
                            })
                        }
                    }
            }
        }
    }
}
