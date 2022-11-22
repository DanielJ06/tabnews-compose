package com.djr.tabnews.core.uikit.components.markdownWrapper

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.annotation.IdRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.core.content.res.ResourcesCompat
import coil.ImageLoader
import io.noties.markwon.AbstractMarkwonPlugin
import io.noties.markwon.Markwon
import io.noties.markwon.MarkwonConfiguration
import io.noties.markwon.core.MarkwonTheme
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.AsyncDrawable
import io.noties.markwon.image.ImageSize
import io.noties.markwon.image.ImageSizeResolverDef
import io.noties.markwon.image.coil.CoilImagesPlugin
import io.noties.markwon.linkify.LinkifyPlugin

internal fun createTextView(
    context: Context,
    color: Color = Color.White,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    @FontRes fontResource: Int? = null,
    style: TextStyle,
    @IdRes viewId: Int? = null,
    onClick: (() -> Unit)? = null
): TextView {
    val mergedStyle = style.merge(
        TextStyle(
            color = color,
            fontSize = if (fontSize != TextUnit.Unspecified) fontSize else style.fontSize,
            textAlign = textAlign,
        )
    )

    return TextView(context).apply {
        onClick?.let { setOnClickListener { onClick() } }
        setTextColor(color.toArgb())
        setMaxLines(maxLines)
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, mergedStyle.fontSize.value)
        viewId?.let { id = viewId }
        textAlign?.let { align ->
            textAlignment = when (align) {
                TextAlign.Left, TextAlign.Start -> View.TEXT_ALIGNMENT_TEXT_START
                TextAlign.Right, TextAlign.End -> View.TEXT_ALIGNMENT_TEXT_END
                TextAlign.Center -> View.TEXT_ALIGNMENT_CENTER
                else -> View.TEXT_ALIGNMENT_TEXT_START
            }
        }
        fontResource?.let { font ->
            typeface = ResourcesCompat.getFont(context, font)
        }
    }
}

internal fun createRender(
    context: Context,
    themePlugin: ThemedMd
): Markwon {
    val coilLoader = ImageLoader.Builder(context)
        .apply {
            crossfade(true)
        }.build()
    return Markwon.builder(context)
        .usePlugin(HtmlPlugin.create())
        .usePlugin(StrikethroughPlugin.create())
        .usePlugin(TablePlugin.create(context))
        .usePlugin(LinkifyPlugin.create())
        .usePlugin(object : AbstractMarkwonPlugin() {
            override fun configureConfiguration(builder: MarkwonConfiguration.Builder) {
                builder.imageSizeResolver(FitWidthIgmResolver())
            }
        })
        .usePlugin(CoilImagesPlugin.create(context, coilLoader))
        .usePlugin(themePlugin)
        .build()
}

internal class FitWidthIgmResolver : ImageSizeResolverDef() {
    override fun resolveImageSize(drawable: AsyncDrawable): Rect {
        return resolveImageSize(
            ImageSize(
                ImageSize.Dimension(100F, UNIT_PERCENT),
                null
            ),
            drawable.result.bounds,
            drawable.lastKnownCanvasWidth,
            drawable.lastKnowTextSize
        )
    }
}

internal class ThemedMd(
    private val accentColor: Color
) : AbstractMarkwonPlugin() {
    override fun configureTheme(builder: MarkwonTheme.Builder) {
        builder
            .linkColor(accentColor.toArgb())
            .isLinkUnderlined(false)
            .blockQuoteColor(accentColor.toArgb())
    }
}