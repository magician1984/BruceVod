package idv.bruce.vod.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import idv.bruce.vod.R
import idv.bruce.vod.component.StatusBarButton
import idv.bruce.vod.ui.theme.AientecKTVTheme

@Composable
fun MainPage() {
    val statusBarHeight = with(LocalDensity.current) {
        113.toDp()
    }

    val controlBarHeight = with(LocalDensity.current) {
        113.toDp()
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Surface(modifier = Modifier.height(statusBarHeight)) {
            StatusBar()
        }
        Surface(
            modifier = Modifier
                .weight(1f)

        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.DarkGray, Color.Black, Color.DarkGray
                        )
                    )
                )
            }
            Content()
        }
        Surface(modifier = Modifier.height(controlBarHeight)) {

        }
    }
}

@Composable
fun StatusBar() {

    Row(modifier = Modifier.fillMaxSize()) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
            contentDescription = null
        )
        StatusBarButton(
            null,
            ImageVector.vectorResource(R.drawable.ic_status_search),
            null,
            false
        ) {}
        Spacer(modifier = Modifier.weight(1f))
        StatusBarButton(
            stringResource(R.string.status_home),
            ImageVector.vectorResource(R.drawable.ic_status_home),
            null,
            false
        ) {}
        StatusBarButton(
            stringResource(R.string.status_foods),
            ImageVector.vectorResource(R.drawable.ic_status_foods),
            null,
            false
        ) {}
        StatusBarButton(
            stringResource(R.string.status_service),
            ImageVector.vectorResource(R.drawable.ic_status_service),
            null,
            false
        ) {}
        StatusBarButton(
            stringResource(R.string.status_language),
            ImageVector.vectorResource(R.drawable.ic_status_language),
            null,
            false
        ) {}
    }
}

@Composable
fun Content() {

}

@Composable
fun ControlBar() {

}

@Preview(showBackground = true, backgroundColor = 0xff000000)
@Composable
fun MainPreview() {
    AientecKTVTheme() {
        MainPage()
    }
}