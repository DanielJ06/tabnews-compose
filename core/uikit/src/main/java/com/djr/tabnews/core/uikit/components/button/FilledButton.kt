package com.djr.tabnews.core.uikit.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.darken
import com.djr.tabnews.core.uikit.theme.extensions.nanoCorners

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Click me",
    isLoading: Boolean = false
) {
    Button(
        modifier = modifier
            .nanoCorners()
            .height(48.dp),
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = TabNewsTheme.colors.accentPrimary.darken(0.65f),
            contentColor = TabNewsTheme.colors.textLight
        )
    ) {
        if (isLoading) {
           CircularProgressIndicator(
               color = TabNewsTheme.colors.textLight,
               strokeWidth = 2.dp,
               modifier = Modifier.size(20.dp)
           )
        } else {
            Text(
                text = text,
                style = TabNewsTheme.typography.TextNormal
            )
        }
    }
}

@Preview
@Composable
fun FilledButtonPreview() {
    FilledButton()
}