package astracommercetrade.art.astracanvas.data.model

import androidx.annotation.StringRes
import astracommercetrade.art.astracanvas.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    PAINTS(R.string.jvong_category_paints),
    DRAWING(R.string.jvong_category_drawing),
    BRUSHES(R.string.jvong_category_brushes),
    PAPER(R.string.jvong_category_paper),
    CRAFT(R.string.jvong_category_craft),
}
