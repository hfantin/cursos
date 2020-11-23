package com.github.hfantin

import java.util.*


/**
 * Essa classe tem o objetivos ocultos e assim deve permanecer.
 */
class KeystorePasswordGenerator {

    private var pass: String? = null

    private val map = byteArrayOf(
            64, 48, 77,
            38, 90, 95, 84,
            122, 40, 119,
            95, 100, 120, 2, 40, 118,
            1, 22, 120, 2, 76,
            110, 64, 106,
            40, 65,
            40, 48, 65,
            64, 48, 77,
            38, 90, 95, 84,
            40, 65,
            122, 40, 119,
            120, 2, 76,
            110, 64, 106,
            40, 48, 65,
            95, 100, 120, 2, 40, 118,
            125, 93, 122, 83, 85,
            38, 90, 95, 69,
            1, 12, 25, 38, 90, 78,
            38, 90, 95, 100, 120, 2, 113,
            48, 125, 79,
            122, 38, 83, 49,
            90, 120, 116,
            122, 64, 105,
            122, 83, 81,
            90, 95, 100, 105,
            38, 38, 107,
            12, 1, 61,
            38, 90, 95, 84,
            64, 48, 77,
            40, 65,
            95, 100, 120, 2, 40, 118,
            122, 40, 119,
            120, 2, 76,
            110, 64, 106,
            40, 48, 65,
            12, 25, 38, 90, 95, 100, 106
    )

    private val values = byteArrayOf(1, 12, 25, 38, 90, 95, 100, 120, 2, 40, 48, 125, 93, 122, 83, 110, 64, 22)



    fun generate() {
        var result = byteArrayOf()
        for (str in map) {
            if (!values.contains(str)) {
                result = result.plus(str)
            }
        }
    }

    fun generate2() {
//        var result = byteArrayOf()
        val result = map
                .filter { s -> !values.contains(s) }
                .fold(byteArrayOf()) { acc, s -> acc.plus(s)}
//        for (str in map) {
//            if (!values.contains(str)) {
//                result = result.plus(str)
//            }
//        }

        println("result ${result} - " + Base64.getEncoder().encodeToString(result))
    }


}

fun main() {
    KeystorePasswordGenerator().generate()
    KeystorePasswordGenerator().generate2()
}