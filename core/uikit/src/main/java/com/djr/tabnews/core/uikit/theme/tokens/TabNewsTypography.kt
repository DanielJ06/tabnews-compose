package com.djr.tabnews.core.uikit.theme.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class TabNewsTypography(
    val HeadingH1: TextStyle = TextStyle(
        fontSize = 35.sp,
        fontWeight = FontWeight.W600
    ),
    val HeadingH2: TextStyle = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.W600
    ),
    val HeadingH3: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W600
    ),
    val HeadingH4: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W600
    ),

    val TextLarge: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W400
    ),
    val TextLargeSB: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W600
    ),
    val TextMedium: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W400
    ),
    val TextMediumSB: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W600
    ),
    val TextCaption: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
    ),
    val TextNormal: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400
    ),
    val TextNormalSB: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W600
    ),
    val TextSmall: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400
    ),
    val TextSmallSB: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600
    ),
    val TextSmaller: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400
    ),
    val TextSmallerSB: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W600
    )
)
