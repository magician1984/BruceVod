package idv.bruce.vod.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import idv.bruce.vod.R

@Composable
fun StatusBarButton(
    label: String? = null,
    imageVector: ImageVector,
    count: LiveData<Int>? = null,
    showCount: Boolean = false,
    onClick: () -> Unit
) = StatusBarButton(
    label,
    rememberVectorPainter(imageVector),
    count,
    showCount,
    onClick
)

@OptIn(ExperimentalUnitApi::class)
@Composable
fun StatusBarButton(
    label: String? = null,
    painter: Painter,
    count: LiveData<Int>? = null,
    showCount: Boolean = false,
    onClick: () -> Unit
) {
    val countVal = if (count != null && showCount)
        count.observeAsState()
    else
        null

    ConstraintLayout(modifier = Modifier
        .clickable {
            onClick()
        }
        .padding(with(LocalDensity.current) { 24.toDp() })
        .fillMaxHeight()
    ) {
        val (mIcon, mLabel, mCount) = createRefs()

        Image(
            painter = painter!!,
            contentDescription = null,
            modifier = Modifier.constrainAs(mIcon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            colorFilter = ColorFilter.tint(Color.White, BlendMode.SrcAtop),
            contentScale = ContentScale.FillHeight
        )
        if (label != null) {
            Text(
                text = label,
                modifier = Modifier.constrainAs(mLabel) {
                    start.linkTo(mIcon.start)
                    end.linkTo(mIcon.end)
                    top.linkTo(mIcon.bottom)
                },
                color = Color.White,
                fontSize = TextUnit(24f, TextUnitType.Sp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun MyPreview() {
    StatusBarButton(label = "Test", painter = painterResource(R.drawable.ic_logo)) {

    }
}