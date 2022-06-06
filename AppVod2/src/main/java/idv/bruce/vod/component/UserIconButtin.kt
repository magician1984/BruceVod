package idv.bruce.vod.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aientec.structure.User
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.picasso.PicassoImage
import idv.bruce.vod.R
import idv.bruce.vod.ui.theme.AientecKTVTheme

@Composable
fun UserIconButton(user: LiveData<User>, emptyIconVector: ImageVector, onClick: () -> Unit) =
    UserIconButton(user, rememberVectorPainter(emptyIconVector), onClick)

@Composable
fun UserIconButton(user: LiveData<User>, emptyIcon: Painter, onClick: () -> Unit) {
    val mUser by user.observeAsState()

    Row(modifier = Modifier.fillMaxSize()) {
        PicassoImage(
            data = mUser?.icon ?: "",
            modifier = Modifier.fillMaxHeight(),
            content = { imageLoadState ->
                when (imageLoadState) {

                    is ImageLoadState.Success -> {
                        Image(painter = imageLoadState.painter, contentDescription = null)
                    }
                    is ImageLoadState.Error,
                    ImageLoadState.Loading,
                    ImageLoadState.Empty -> {
                        Image(painter = emptyIcon, contentDescription = null)
                    }
                }
            })

        Text(text = mUser?.name ?: "", modifier = Modifier.defaultMinSize())
    }
}

@Preview(backgroundColor = 0xff000000, showBackground = true)
@Composable
fun UserIconButtonPreview() {
    AientecKTVTheme() {
        UserIconButton(
            MutableLiveData<User>(null),
            ImageVector.vectorResource(R.drawable.ic_status_user)
        ) {}
    }
}
