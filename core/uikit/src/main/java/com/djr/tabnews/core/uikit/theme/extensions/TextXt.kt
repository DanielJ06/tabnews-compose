package com.djr.tabnews.core.uikit.theme.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.djr.tabnews.core.uikit.theme.TabNewsTheme

@Composable
fun String.toHighlight(
    segment: String,
    textColor: Color = TabNewsTheme.colors.textNeutralLight,
    color: Color = TabNewsTheme.colors.accentPrimary
): AnnotatedString {
    val source = this
    val start = source.indexOf(segment)
    val end = start + segment.length

    val highlightStyle = SpanStyle(
        color = textColor,
        background = color,
    )

    val annotated = buildAnnotatedString {
        append(source)
        addStyle(highlightStyle, start, end)

    }
    return annotated
}

@Composable
fun String.toColor(
    segment: String,
    color: Color = TabNewsTheme.colors.accentPrimary
): AnnotatedString {
    val source = this
    val start = source.indexOf(segment)
    val end = start + segment.length
    val highlightStyle = SpanStyle(
        color = color
    )
    val annotated = buildAnnotatedString {
        append(source)
        addStyle(highlightStyle, start, end)
    }
    return annotated
}

@Composable
fun AnnotatedString.toHighlight(
    segment: String,
    textColor: Color = TabNewsTheme.colors.textNeutralLight,
    color: Color = TabNewsTheme.colors.accentPrimary
): AnnotatedString {
    val source = this
    val start = source.indexOf(segment)
    val end = start + segment.length

    val highlightStyle = SpanStyle(
        color = textColor,
        background = color
    )

    val annotated = buildAnnotatedString {
        append(source)
        addStyle(highlightStyle, start, end)
    }
    return annotated
}