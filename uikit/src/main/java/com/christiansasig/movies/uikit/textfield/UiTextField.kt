package com.christiansasig.movies.uikit.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.christiansasig.movies.uikit.text.UiMessageText
import com.christiansasig.movies.uikit.textfield.util.TextFieldState

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@Composable
fun UiTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    textFieldState: TextFieldState = remember { TextFieldState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(start = 0.dp, end = 0.dp, top = 0.dp),
    isValidation: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    isError: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    isRequiredFocus: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    placeHolderTextStyle: TextStyle = MaterialTheme.typography.titleMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    specificationMessage: String = "",
    maxLines: Int = 1,
    enabled: Boolean = true,
    widgetLeading: @Composable (() -> Unit)? = null,
    focusRequester: FocusRequester = FocusRequester(),
    validateText: (String) -> String = { it },
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (isError.value) {
        textFieldState.enableShowErrors()
        textFieldState.showErrors()
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            maxLines = maxLines,
            value = textFieldState.text,
            onValueChange = {
                onValueChange(it)
                textFieldState.text = validateText(it)
                if (isValidation.value) {
                    textFieldState.enableShowErrors()
                }
            },
            label = {
                CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 0.74f)) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.secondary,
                        style = placeHolderTextStyle,
                        textAlign = TextAlign.Center
                    )
                }
            },
            leadingIcon = widgetLeading,
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    textFieldState.onFocusChange(focusState.isFocused)
                }
                .focusRequester(focusRequester)
                .padding(paddingValues),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = textStyle.fontFamily,
                fontWeight = textStyle.fontWeight,
                fontSize = textStyle.fontSize
            ),
            isError = !textFieldState.isValid,
            keyboardOptions = keyboardOptions.copy(
                imeAction = imeAction,
                capitalization = KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeAction()
                    keyboardController?.hide()
                }
            ),
            shape = shape,
            enabled = enabled,
        )

        if (isValidation.value) {
            textFieldState.enableShowErrors()
            isError.value = true
            textFieldState.getError()?.let { error ->
                UiMessageText(
                    message = error, isError.value
                )
            }
        }

        if(specificationMessage.isNotEmpty()){
            AnimatedVisibility(
                visible = !textFieldState.isValid,
                enter = fadeIn(
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    targetAlpha = 0.4f,
                    animationSpec = tween(durationMillis = 1)
                )
            ) {
                Text(
                    modifier = modifier.padding(top = 5.dp, start = 24.7.dp, end = 16.dp),
                    text = specificationMessage,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        if (isRequiredFocus) {
            focusRequester.requestFocus()
        }
    }
}