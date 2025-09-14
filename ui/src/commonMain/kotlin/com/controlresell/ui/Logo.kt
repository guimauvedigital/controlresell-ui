package com.controlresell.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Logo: ImageVector
    get() {
        if (_logo != null) {
            return _logo!!
        }
        _logo = ImageVector.Builder(
            name = "Calque2",
            defaultWidth = 351.dp,
            defaultHeight = 193.dp,
            viewportWidth = 351f,
            viewportHeight = 193f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(195.26f, 86.36f)
                lineTo(177.52f, 66.57f)
                lineTo(159.76f, 86.36f)
                lineTo(142.01f, 106.09f)
                horizontalLineTo(165.22f)
                curveTo(163.14f, 121.88f, 155.79f, 136.49f, 144.36f, 147.53f)
                curveTo(132.93f, 158.57f, 118.11f, 165.4f, 102.31f, 166.89f)
                curveTo(86.51f, 168.38f, 70.68f, 164.45f, 57.4f, 155.74f)
                curveTo(44.12f, 147.02f, 34.18f, 134.05f, 29.21f, 118.93f)
                curveTo(24.24f, 103.81f, 24.52f, 87.44f, 30.02f, 72.51f)
                curveTo(35.52f, 57.58f, 45.91f, 44.96f, 59.49f, 36.72f)
                curveTo(73.07f, 28.48f, 89.03f, 25.11f, 104.76f, 27.16f)
                curveTo(120.5f, 29.2f, 135.07f, 36.55f, 146.1f, 47.99f)
                curveTo(146.36f, 48.25f, 146.6f, 48.52f, 146.85f, 48.79f)
                lineTo(163.84f, 29.28f)
                curveTo(163.73f, 29.19f, 163.65f, 29.08f, 163.54f, 28.99f)
                curveTo(148.16f, 13.53f, 128f, 3.8f, 106.36f, 1.38f)
                curveTo(84.73f, -1.03f, 62.92f, 4.02f, 44.53f, 15.7f)
                curveTo(26.14f, 27.39f, 12.27f, 45.01f, 5.2f, 65.67f)
                curveTo(-1.88f, 86.33f, -1.72f, 108.79f, 5.62f, 129.35f)
                curveTo(12.97f, 149.9f, 27.08f, 167.34f, 45.63f, 178.77f)
                curveTo(64.17f, 190.21f, 86.05f, 194.97f, 107.65f, 192.26f)
                curveTo(129.25f, 189.55f, 149.28f, 179.54f, 164.45f, 163.88f)
                curveTo(179.62f, 148.21f, 189.02f, 127.83f, 191.11f, 106.09f)
                horizontalLineTo(212.99f)
                lineTo(195.26f, 86.36f)
                close()
            }
            path(fill = SolidColor(Color(0xFFBFEE61))) {
                moveTo(350.41f, 63.55f)
                curveTo(350.4f, 46.91f, 343.82f, 30.96f, 332.1f, 19.19f)
                curveTo(320.38f, 7.43f, 304.48f, 0.81f, 287.9f, 0.8f)
                lineTo(199.02f, 0.8f)
                verticalLineTo(51.17f)
                horizontalLineTo(227.24f)
                verticalLineTo(27.68f)
                horizontalLineTo(285.17f)
                curveTo(294.66f, 27.68f, 303.76f, 31.47f, 310.47f, 38.2f)
                curveTo(317.19f, 44.93f, 320.96f, 54.06f, 320.96f, 63.59f)
                curveTo(320.95f, 73.1f, 317.17f, 82.23f, 310.46f, 88.95f)
                curveTo(303.75f, 95.68f, 294.65f, 99.45f, 285.17f, 99.45f)
                horizontalLineTo(227.24f)
                verticalLineTo(126.3f)
                horizontalLineTo(269.17f)
                curveTo(271.2f, 126.3f, 273.19f, 126.79f, 274.98f, 127.73f)
                curveTo(276.78f, 128.67f, 278.32f, 130.03f, 279.48f, 131.69f)
                lineTo(319.14f, 188.62f)
                curveTo(320.07f, 189.97f, 321.32f, 191.07f, 322.77f, 191.83f)
                curveTo(324.22f, 192.59f, 325.83f, 192.99f, 327.47f, 192.99f)
                horizontalLineTo(350.37f)
                lineTo(302.68f, 124.5f)
                curveTo(316.29f, 121.17f, 328.39f, 113.36f, 337.04f, 102.3f)
                curveTo(345.69f, 91.25f, 350.4f, 77.61f, 350.41f, 63.55f)
                close()
            }
        }.build()

        return _logo!!
    }

private var _logo: ImageVector? = null
