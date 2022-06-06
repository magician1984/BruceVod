package idv.bruce.vod.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.constraintlayout.compose.ConstraintLayout
import idv.bruce.vod.R
import idv.bruce.vod.ui.theme.AientecKTVTheme
import idv.bruce.vod.viewmodel.SystemViewModel

@OptIn(ExperimentalUnitApi::class)
@Composable
fun LauncherPage(systemViewModel: SystemViewModel) {
    val message = systemViewModel.actionMessage.observeAsState("No Value")

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (logo, msg) = createRefs()

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth(0.5f), contentScale = ContentScale.FillWidth
        )

        Text(text = message.value, modifier = Modifier.constrainAs(msg) {
            start.linkTo(logo.start)
            end.linkTo(logo.end)
            top.linkTo(logo.bottom)
        }, color = Color.White, fontSize = TextUnit(64f, TextUnitType.Sp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AientecKTVTheme() {
        LauncherPage(systemViewModel = SystemViewModel())
    }
}
