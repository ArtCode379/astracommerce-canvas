package astracommercetrade.art.astracanvas.data.repository

import astracommercetrade.art.astracanvas.data.model.Product
import astracommercetrade.art.astracanvas.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = listOf(
        Product(
            id = 1,
            title = "Studio Acrylic Set",
            description = "A vibrant set of 24 richly pigmented acrylic colours for canvas, board, wood, and mixed media.",
            category = ProductCategory.PAINTS,
            price = 29.90,
            imageUrl = "https://images.unsplash.com/photo-1541961017774-22349e4a1262?w=1200",
        ),
        Product(
            id = 2,
            title = "Watercolour Pocket Box",
            description = "Twelve luminous half pans in a travel case.",
            category = ProductCategory.PAINTS,
            price = 18.50,
            imageUrl = "https://images.unsplash.com/photo-1513364776144-60967b0f800f?w=1200",
        ),
        Product(
            id = 3,
            title = "Graphite Sketch Collection",
            description = "Twelve pencils for shading and precise lines.",
            category = ProductCategory.DRAWING,
            price = 12.90,
            imageUrl = "https://images.unsplash.com/photo-1455390582262-044cdead277a?w=1200",
        ),
        Product(
            id = 4,
            title = "Soft Pastel Portrait Set",
            description = "Artist-grade pastels for natural tones.",
            category = ProductCategory.DRAWING,
            price = 26.40,
            imageUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=1200",
        ),
        Product(
            id = 5,
            title = "Golden Taklon Brush Set",
            description = "Eight brushes for acrylic and watercolour.",
            category = ProductCategory.BRUSHES,
            price = 21.00,
            imageUrl = "https://images.unsplash.com/photo-1460661419201-fd4cecdf8a8b?w=1200",
        ),
        Product(
            id = 6,
            title = "Detail Liner Trio",
            description = "Three brushes for lettering and details.",
            category = ProductCategory.BRUSHES,
            price = 9.80,
            imageUrl = "https://images.unsplash.com/photo-1599420186946-7b6fb4e297f0?w=1200",
        ),
        Product(
            id = 7,
            title = "Cold Press Watercolour Pad",
            description = "Acid-free 300 gsm paper for wet techniques.",
            category = ProductCategory.PAPER,
            price = 17.25,
            imageUrl = "https://images.unsplash.com/photo-1518005020951-eccb494ad742?w=1200",
        ),
        Product(
            id = 8,
            title = "Hardback Mixed Media Book",
            description = "A lay-flat book for ink, pencil, and collage.",
            category = ProductCategory.PAPER,
            price = 15.60,
            imageUrl = "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=1200",
        ),
        Product(
            id = 9,
            title = "Botanical Embroidery Kit",
            description = "Fabric, threads, pattern, and illustrated guide.",
            category = ProductCategory.CRAFT,
            price = 22.90,
            imageUrl = "https://images.unsplash.com/photo-1590736969955-71cc94901144?w=1200",
        ),
        Product(
            id = 10,
            title = "Air-Dry Clay Starter Kit",
            description = "Natural clay, tools, colours, and project guide.",
            category = ProductCategory.CRAFT,
            price = 24.50,
            imageUrl = "https://images.unsplash.com/photo-1565193566173-7a0ee3dbe261?w=1200",
        ),
        Product(
            id = 11,
            title = "Modern Calligraphy Set",
            description = "Dip pen, nibs, ink, guides, and practice paper.",
            category = ProductCategory.DRAWING,
            price = 19.75,
            imageUrl = "https://images.unsplash.com/photo-1456324504439-367cee3b3c32?w=1200",
        ),
        Product(
            id = 12,
            title = "Canvas Panel Bundle",
            description = "Six primed panels for acrylic or oil colour.",
            category = ProductCategory.PAPER,
            price = 20.00,
            imageUrl = "https://images.unsplash.com/photo-1577083552431-6e5fd01aa342?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
