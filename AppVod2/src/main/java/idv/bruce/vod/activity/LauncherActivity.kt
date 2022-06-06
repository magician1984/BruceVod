package idv.bruce.vod.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import idv.bruce.vod.enum.SystemState
import idv.bruce.vod.pages.IdlePage
import idv.bruce.vod.pages.LauncherPage
import idv.bruce.vod.pages.MainPage
import idv.bruce.vod.ui.theme.AientecKTVTheme
import idv.bruce.vod.viewmodel.SystemViewModel

class LauncherActivity : ComponentActivity() {
    private val systemViewModel: SystemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AientecKTVTheme {
                val navController = rememberNavController()

                systemViewModel.systemState.observe(this) {
                    when (it) {
                        is SystemState.Error -> {}
                        is SystemState.Preparing -> { navController.navigate("launcher")    }
                        is SystemState.Idle -> {
                            navController.navigate("idle")
                        }
                        SystemState.Cleaning -> TODO()
                        SystemState.Using -> {navController.navigate("main")}
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController, startDestination = "launcher") {
                        composable("launcher") { LauncherPage(systemViewModel) }
                        composable("idle") { IdlePage() }
                        composable("main"){ MainPage()}
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        systemViewModel.onSystemInit()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AientecKTVTheme {
        LauncherPage(systemViewModel = SystemViewModel())
    }
}