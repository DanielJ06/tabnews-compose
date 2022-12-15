package com.djr.tabnews.core.uikit.components.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djr.tabnews.core.uikit.components.button.FilledButton
import com.djr.tabnews.core.uikit.theme.TabNewsTheme
import com.djr.tabnews.core.uikit.theme.extensions.nanoCorners

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseInput(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSrc: MutableInteractionSource = remember { MutableInteractionSource() },
    error: Boolean = false,
    errorMsg: String = ""
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    val selectionColors = TextSelectionColors(
        handleColor = TabNewsTheme.colors.accentPrimary,
        backgroundColor = TabNewsTheme.colors.accentPrimary.copy(alpha = 0.4f)
    )

    val borderColor = if (isFocused && !error) {
        TabNewsTheme.colors.accentPrimary
    } else if (error && !isFocused) {
        TabNewsTheme.colors.error
    } else {
        TabNewsTheme.colors.borderMedium
    }

    Column(horizontalAlignment = Alignment.End) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .nanoCorners()
                .background(TabNewsTheme.colors.secondaryBg)
                .height(48.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(TabNewsTheme.spacing.Nano)
                )
                .padding(horizontal = TabNewsTheme.spacing.Mini)
        ) {
            CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
                BasicTextField(
                    value = value,
                    onValueChange = { t -> onValueChange.invoke(t) },
                    keyboardActions = keyboardActions,
                    keyboardOptions = keyboardOptions,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    cursorBrush = SolidColor(TabNewsTheme.colors.accentPrimary),
                    visualTransformation = visualTransformation,
                    textStyle = TabNewsTheme.typography.TextNormal.copy(
                        color = TabNewsTheme.colors.textNeutralLight
                    ),
                    decorationBox = @Composable { tf ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = value,
                            innerTextField = tf,
                            enabled = true,
                            singleLine = false,
                            visualTransformation = visualTransformation,
                            interactionSource = interactionSrc,
                            placeholder = { BasePlaceholder(placeholder) },
                            contentPadding = PaddingValues(0.dp)
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onFocusChanged { isFocused = it.isFocused }
                )
            }
        }
        if (errorMsg.isNotBlank() && error) {
            Text(
                text = errorMsg,
                style = TabNewsTheme.typography.TextSmall,
                color = TabNewsTheme.colors.error
            )
        }
    }
}

@Composable
fun BasePlaceholder(
    placeholder: String
) {
    Text(
        text = placeholder,
        style = TabNewsTheme.typography.TextNormal,
        color = TabNewsTheme.colors.textNeutral
    )
}

@Preview
@Composable
fun BaseInputPreview() {
    var example by remember { mutableStateOf("Lorem ipsum") }

    Column(
        modifier = Modifier
            .background(TabNewsTheme.colors.primaryBg)
            .padding(TabNewsTheme.spacing.Xxs)
    ) {
        BaseInput(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            placeholder = "Email"
        )
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
        BaseInput(
            modifier = Modifier.fillMaxWidth(),
            value = example,
            onValueChange = { example = it }
        )
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
        BaseInput(
            modifier = Modifier.fillMaxWidth(),
            value = example,
            onValueChange = { example = it },
            error = true,
            errorMsg = "Error example"
        )
        Spacer(modifier = Modifier.height(TabNewsTheme.spacing.Nano))
        FilledButton(modifier = Modifier.fillMaxWidth())
    }
}