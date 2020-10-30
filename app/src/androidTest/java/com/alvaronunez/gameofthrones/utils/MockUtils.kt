package com.alvaronunez.gameofthrones.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object MockUtils {
    fun readJsonFile(context: Context, jsonFilePath: String): String {
        val res = context.packageManager.getResourcesForApplication("com.alvaronunez.gameofthrones.test")

        var br: BufferedReader? = null

        try {
            br = BufferedReader(
                InputStreamReader(
                    res.assets.open(
                        jsonFilePath
                    ), StandardCharsets.UTF_8
                )
            )
            var line: String?
            val text = StringBuilder()

            do {
                line = br.readLine()
                line?.let { text.append(line) }
            } while (line != null)
            br.close()
            return text.toString()
        } finally {
            br?.close()
        }
    }
}