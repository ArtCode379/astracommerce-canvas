package astracommercetrade.art.astracanvas.di

import androidx.room.Room
import astracommercetrade.art.astracanvas.data.database.JVONGDatabase
import org.koin.dsl.module

private const val DB_NAME = "jvong_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = JVONGDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<JVONGDatabase>().cartItemDao() }

    single { get<JVONGDatabase>().orderDao() }
}