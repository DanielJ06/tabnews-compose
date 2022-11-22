package com.djr.tabnews.core.uikit.components.markdownWrapper

import android.content.Context
import androidx.annotation.FontRes
import androidx.annotation.IdRes
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import io.noties.markwon.Markwon

@Composable
fun Markdown(
    markdown: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    @FontRes fontRes: Int? = null,
    style: TextStyle = LocalTextStyle.current,
    @IdRes viewId: Int? = null,
    onClick: (() -> Unit)? = null,
) {
    val accent = TabNewsTheme.colors.accentPrimary
    val context: Context = LocalContext.current
    val markdownRender: Markwon = remember {
        createRender(context, ThemedMd(accent))
    }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            createTextView(
                context = ctx,
                color = color,
                fontSize = fontSize,
                fontResource = fontRes,
                maxLines = maxLines,
                style = style,
                textAlign = textAlign,
                viewId = viewId,
                onClick = onClick,
            )
        },
        update = { tv ->
            markdownRender.setMarkdown(tv, markdown)
        }
    )
}