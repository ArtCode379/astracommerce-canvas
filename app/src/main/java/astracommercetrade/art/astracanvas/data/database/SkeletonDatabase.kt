package astracommercetrade.art.astracanvas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import astracommercetrade.art.astracanvas.data.dao.CartItemDao
import astracommercetrade.art.astracanvas.data.dao.OrderDao
import astracommercetrade.art.astracanvas.data.database.converter.Converters
import astracommercetrade.art.astracanvas.data.entity.CartItemEntity
import astracommercetrade.art.astracanvas.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class JVONGDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}