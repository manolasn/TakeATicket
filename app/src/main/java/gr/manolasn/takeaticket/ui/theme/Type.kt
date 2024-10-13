package gr.manolasn.takeaticket.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import gr.manolasn.takeaticket.R


val fontTitle = FontFamily(Font(R.font.asty,weight = FontWeight.Bold) )

val beauSansProSemiBold = FontFamily(Font(R.font.pf_beau_sans_pro_semibold,weight = FontWeight.SemiBold) )

val astyCfsTdBold = FontFamily(Font(R.font.asty_cfs_td_bold,weight = FontWeight.Bold) )

val fontBody = FontFamily(
    Font(R.font.manrope_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.manrope_light, weight = FontWeight.Light),
    Font(R.font.manrope_regular, weight = FontWeight.Normal),
    Font(R.font.manrope_medium, weight = FontWeight.Medium),
    Font(R.font.manrope_semibold, weight = FontWeight.SemiBold),
    Font(R.font.manrope_bold, weight = FontWeight.Bold),
    Font(R.font.manrope_extrabold, weight = FontWeight.ExtraBold),

    )

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = fontTitle,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = (-0.45).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.ExtraBold,
    ),
    displaySmall = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Light,
    )
)