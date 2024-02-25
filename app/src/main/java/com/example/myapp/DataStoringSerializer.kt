package com.example.myapp

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object DataStoringSerializer : Serializer<DataStoring> {

    override val defaultValue: DataStoring
        get() = DataStoring()

    override suspend fun readFrom(input: InputStream): DataStoring {
        return try {
            Json.decodeFromString(
                deserializer = DataStoring.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: DataStoring, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = DataStoring.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}