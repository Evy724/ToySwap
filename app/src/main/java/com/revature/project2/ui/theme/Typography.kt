package com.revature.project2.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.revature.project2.R

val Chewy = FontFamily(
    Font(R.font.chewy)
)

val HachiMaruPop = FontFamily(
    Font(R.font.hachi_maru_pop)
)

val Project2Typography = Typography(
    h4 = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.W600,
        fontSize = 25.sp
    ),
    h6 = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),

    subtitle1 = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),

    body1 = TextStyle(
        fontFamily = HachiMaruPop,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Chewy,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    caption = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    overline = TextStyle(
        fontFamily = Chewy,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
)